package com.rosetta.onemap

class Hotspot {

	String floor
	String type
	Zone zone
	String polygon
	Integer x
	Integer y 
	
    static constraints = {
		polygon blank: false
		type inList: ["desk", "room"]
		zone nullable:true
    }
	
	boolean isVacant() {
		return true;
	}
}
