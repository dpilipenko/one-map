package com.rosetta.onemap


class User extends org.springframework.security.core.userdetails.User {

	String username
	String emailAddress
	String firstName
	String lastName
	Office office
	String phone
	String level // TODO change 'level' to 'title' because level is a SQL reserved keyword
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

	@Override
	public boolean equals(Object rhs) {
		boolean isEqual = true
		// not equal if both are not Users
		if (isEqual && !(rhs instanceof User)) {
			isEqual = false
		}
		// not equal if usernames are different
		if (isEqual && !(this.username.equals(rhs.username))) {
			isEqual = false
		}
		return isEqual
	}
}
