package com.rosetta.onemap.pintypes

import com.rosetta.onemap.Hotspot
import com.rosetta.onemap.Office
import com.rosetta.onemap.User
import com.rosetta.onemap.Zone

class Desk extends Hotspot {
	
	String assignedSeatId
	User user
	Office office
	/* Overriding Variables */
	String type = "desk"
	Zone zone
	
    static constraints = {
		user nullable:true
		zone nullable:true
		office nullable:true
    }
	
	boolean isVacant() {
		return user == null
	}
	
	boolean claim(User user) {
		if ( ! isVacant() ) {
			this.user = user
			this.save(flush:true)
		} else {
			return false;
		}
	}
}
