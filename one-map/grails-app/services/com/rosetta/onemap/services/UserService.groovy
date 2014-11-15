package com.rosetta.onemap.services

import com.rosetta.onemap.Office;
import com.rosetta.onemap.Role
import com.rosetta.onemap.User
import com.rosetta.onemap.UserRole;

import grails.transaction.Transactional

import org.springframework.ldap.core.DirContextOperations

@Transactional
class UserService {

	@Transactional
	public void sayHi() {
		System.out.println("Hi");
	}
	
	@Transactional
	public User readUserFromLDAP(org.springframework.ldap.core.DirContextOperations ctx) {
		
		String username = ctx.getAttributes().get("name").toString().split(": ")[1];
		
		User user = User.findByUsername(username);
		if (user == null) {
			user = new User(username);
		}
		
		user.craft = ctx.getAttributes().get("department").toString().split(": ")[1];
		user.emailAddress = ctx.getAttributes().get("mail").toString().split(": ")[1];
		user.firstName = ctx.getAttributes().get("givenName").toString().split(": ")[1];
		user.lastName = ctx.getAttributes().get("sn").toString().split(": ")[1];
		user.level = ctx.getAttributes().get("title").toString().split(": ")[1];
		user.office = Office.findByName(ctx.getAttributes().get("l").toString().split(": ")[1]);
		user.phone = ctx.getAttributes().get("telephoneNumber").toString().split(": ")[1];
		
		Role userRole = Role.findByAuthority('ROLE_USER');
		user.addToAuthorities(userRole);
		
		// Update local DB with LDAP data. TODO: Add conditional to save only if a data is new
		user.save(flush: true)

		return user;
	}
}
