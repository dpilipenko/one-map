package com.rosetta.onemap

import java.util.Date;

class User {

	transient springSecurityService
	
	String username
	String password
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
		username blank: false, unique: true, email: true
		firstName blank:false
		lastName blank:false
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
