package com.rosetta.onemap

import java.util.Date;

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
