package com.rosetta.onemap.pintypes

import com.rosetta.onemap.Pin
import com.rosetta.onemap.User

class Room extends Pin {

	String name
	String number
	String phone
	String project

	static hasMany = [users: User]
	
    static constraints = {
    	name blank: false
		number blank: false
		phone nullable: true
		project nullable: true
	}
	
}
