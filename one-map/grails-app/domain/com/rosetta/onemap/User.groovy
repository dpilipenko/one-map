package com.rosetta.onemap

import java.util.Date;
class User extends org.springframework.security.core.userdetails.User {

	def springSecurityService
	
	String username
	String emailAddress
	String firstName
	String lastName
	Office office
	String phone
	String level
	String craft
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	Date dateCreated
	Date lastUpdated

	static constraints = {
		username blank:false
		emailAddress blank:false, email:true
		firstName blank:false
		lastName blank:false
	}

	static mapping = {
	}
	
	public User() { 
		super( "guest", "", new HashSet());
		this.username = "guest";
	}
	
	public User(String username) {
		super(username, "", new HashSet());
		this.username = username;
	}
	
	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
//		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
//			encodePassword()
		}
	}

	protected void encodePassword() {
		password = this.springSecurityService.encodePassword(password)
	}
}
