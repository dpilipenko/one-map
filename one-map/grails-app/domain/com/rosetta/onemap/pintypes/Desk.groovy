package com.rosetta.onemap.pintypes

import com.rosetta.onemap.Hotspot
import com.rosetta.onemap.User

class Desk extends Hotspot {

	User user
	
	/* Overriding Variables*/
	String type="desk"
	
    static constraints = {
		user nullable:true
    }
}
