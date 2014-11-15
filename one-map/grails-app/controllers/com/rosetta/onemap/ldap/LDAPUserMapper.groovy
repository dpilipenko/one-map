package com.rosetta.onemap.ldap
import grails.transaction.Transactional;

import javax.naming.directory.Attribute

import org.springframework.ldap.core.DirContextAdapter
import org.springframework.ldap.core.DirContextOperations
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper

import com.rosetta.onemap.Office
import com.rosetta.onemap.User

@Transactional
class LDAPUserMapper implements UserDetailsContextMapper {

	@Override
	public User mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
		//printOutAttributes(ctx)
		
		String _username = ctx.getAttributes().get("name").toString().split(": ")[1];
		
		User user = User.findByUsername(username);
		if (user == null) {
			user = new User(username);
			user.save(flush: true)
		}
		
        user.craft = ctx.getAttributes().get("department").toString().split(": ")[1];
        user.emailAddress = ctx.getAttributes().get("mail").toString().split(": ")[1];
        user.firstName = ctx.getAttributes().get("givenName").toString().split(": ")[1];
        user.lastName = ctx.getAttributes().get("sn").toString().split(": ")[1];
        user.level = ctx.getAttributes().get("title").toString().split(": ")[1];
        user.office = Office.findByName(ctx.getAttributes().get("l").toString().split(": ")[1]);
        user.phone = ctx.getAttributes().get("telephoneNumber").toString().split(": ")[1];
		
		// Update local DB with LDAP data. TODO: Add conditional to save only if a data is new
		user.save(flush: true)
		
		return user;
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
