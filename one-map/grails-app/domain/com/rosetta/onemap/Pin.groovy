package com.rosetta.onemap


class Pin {

	String name
	Date dateCreated
	Date lastUpdated
	Hotspot hotspot
	
    static constraints = {
		name(blank: false)
    }
	
	static mapping = {
		tablePerHierarchy false
	}
}
