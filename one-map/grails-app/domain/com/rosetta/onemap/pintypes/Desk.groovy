package com.rosetta.onemap.pintypes

import com.rosetta.onemap.Pin
import com.rosetta.onemap.User

class Desk extends Pin {

	User user
	
    static constraints = {
		user nullable:true
    }
}
