package com.rosetta.onemap.ldap
import grails.transaction.Transactional;

import javax.naming.directory.Attribute

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter
import org.springframework.ldap.core.DirContextOperations
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper

import com.rosetta.onemap.Office
import com.rosetta.onemap.Role;
import com.rosetta.onemap.User
import com.rosetta.onemap.UserRole;
import com.rosetta.onemap.services.UserService;


class LDAPUserMapper implements UserDetailsContextMapper {

	@Autowired
	UserService userService;
	
	@Override
	public User mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
		//printOutAttributes(ctx)
		User myUser = userService.readUserFromLDAP(ctx);
		
		return myUser;
	}

	@Override
	public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
		throw new IllegalStateException("Only retrieving data from AD is currently supported");
	}

	private printOutAttributes(DirContextOperations ctx) {
		
		System.out.println("PRINT GROUPS: " + ctx.getObjectAttributes("memberOf"));
		
		for (Object attr : ctx.getAttributes().getAll()) {
			System.out.println(((Attribute)attr).getID() +":  "+((Attribute)attr).get());
		}
	}
}
