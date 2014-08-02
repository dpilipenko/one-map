package com.rosetta.onemap.pintypes

import com.rosetta.onemap.Hotspot
import com.rosetta.onemap.Office
import com.rosetta.onemap.User

class Room extends Hotspot {
	
	Office office
	String name
	String number
	String phone
	String project
	String type = "room"
	static hasMany = [users: User]
	
    static constraints = {
    	name blank: false
		number blank: false
		phone nullable: true
		project nullable: true
		office nullable: true
	}
	
	boolean hasProject() {
		return (project != null && !project.isEmpty())
	}
	
}
