package com.rosetta.onemap.pintypes

import com.rosetta.onemap.Office
import com.rosetta.onemap.Pin
import com.rosetta.onemap.User

class Room extends Pin {

	Office office
	
    static constraints = {
    
	}
	
	static hasMany = [users: User]
}
