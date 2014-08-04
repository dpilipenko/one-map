package com.rosetta.onemap

class Hotspot {

	String floor
	String type
	String polygon
	Integer x
	Integer y 
	
    static constraints = {
		polygon blank: false
		type inList: ["desk", "room"]
    }
}
