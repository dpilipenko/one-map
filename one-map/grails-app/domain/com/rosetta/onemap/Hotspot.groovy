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
	
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = true
		// Not equal if both are not Hotspots
		if (isEqual && !(obj instanceof Hotspot)) {
			isEqual = false
		} 
		// Not equal if both are not on the same floor
		if (isEqual && !(this.floor.equals(obj.floor))) {
			isEqual = false
		}
		// Not equal if both are not the same shape
		if (isEqual && !(this.polygon.equals(obj.polygon))) {
			isEqual = false
		}
		return isEqual
	}
}
