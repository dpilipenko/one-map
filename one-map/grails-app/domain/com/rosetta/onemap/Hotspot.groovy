package com.rosetta.onemap

class Hotspot {

	String floor
	String type
	String polygon
	Integer width
	Integer height
	Integer x
	Integer y 
	Pin pin
	
    static constraints = {
		polygon blank: false
		type inList: ["desk", "room"]
		pin nullable: true, unique: true
    }
}
