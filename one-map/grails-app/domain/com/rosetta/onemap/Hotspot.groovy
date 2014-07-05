package com.rosetta.onemap

class Hotspot {

	String floor
	String type
	String polygon
	Pin pin
	
    static constraints = {
		polygon blank: false
		type inList: ["desk", "room"]
		pin nullable: true, unique: true
    }
}
