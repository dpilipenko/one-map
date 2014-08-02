package com.rosetta.onemap


class Pin {

	static belongsTo = [hotspot : Hotspot]
	Date dateCreated
	Date lastUpdated
	
	
    static constraints = {
    }
	
	static mapping = {
		tablePerHierarchy false
	}
}
