package com.rosetta.onemap

class Hotspot {

	String floor
	String type
	String polygon
	Integer width
	Integer height
	Integer x
	Integer y 
	
    static constraints = {
		polygon blank: false
		type inList: ["desk", "room"]
    }
}
