package com.rosetta.onemap

class User {

	String firstName
	String lastName
	String level
	String craft
	Integer phone
	String email
	
    static constraints = {
		firstName size: 1..60, blank: false
		lastName size: 1..60, blank: false
		level nullable: true
		craft nullable: true
		email email: true, blank: false
    }
}
