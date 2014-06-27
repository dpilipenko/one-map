package com.rosetta.onemap.pintypes

import com.rosetta.onemap.Pin
import com.rosetta.onemap.User

class Room extends Pin {

    static constraints = {
    
	}
	
	static hasMany = [users: User]
}
