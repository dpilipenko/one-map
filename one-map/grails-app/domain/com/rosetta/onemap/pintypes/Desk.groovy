package com.rosetta.onemap.pintypes

import com.rosetta.onemap.Hotspot
import com.rosetta.onemap.User

class Desk extends Hotspot {
	
	String assignedSeatId
	User user
	/* Overriding Variables */
	String type = "desk"
	
    static constraints = {
		user nullable:true
    }
	
	boolean isEmpty() {
		return user == null
	}
	
	boolean claim(User user) {
		if ( ! isEmpty() ) {
			this.user = user
			this.save(flush:true)
		} else {
			return false;
		}
	}
}
