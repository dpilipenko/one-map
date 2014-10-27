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
		User _user = User.findByUsername(_username);
		if (_user == null) {
			_user = new User(_username);
		}
		_user.setCraft(ctx.getAttributes().get("department").toString().split(": ")[1]);
		_user.setEmailAddress(ctx.getAttributes().get("mail").toString().split(": "[1]));
		_user.setFirstName(ctx.getAttributes().get("givenName").toString().split(": ")[1]);
		_user.setLastName(ctx.getAttributes().get("sn").toString().split(": ")[1]);
		_user.setLevel(ctx.getAttributes().get("title").toString().split(": ")[1]);
		_user.setOffice(Office.findByName(ctx.getAttributes().get("l").toString().split(": ")[1]));
		_user.setPhone(ctx.getAttributes().get("telephoneNumber").toString().split(": ")[1]);
		
		// Update local DB with LDAP data. TODO: Add conditional to save only if a data is new
		_user.save(flush: true)
		
		return _user;
	}

	@Override
	public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
		throw new IllegalStateException("Only retrieving data from AD is currently supported");
	}

	private printOutAttributes(DirContextOperations ctx) {
		for (Object attr : ctx.getAttributes().getAll()) {
			System.out.println(((Attribute)attr).getID() +":  "+((Attribute)attr).get());
		}
	}
}
