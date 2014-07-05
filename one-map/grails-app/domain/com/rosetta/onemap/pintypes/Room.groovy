package com.rosetta.onemap.pintypes

import com.rosetta.onemap.Office
import com.rosetta.onemap.Pin
import com.rosetta.onemap.User

class Room extends Pin {

	Office office
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
		office nullable: true
	}
	
	boolean hasProject() {
		return !project.isEmpty()
	}
	
}
