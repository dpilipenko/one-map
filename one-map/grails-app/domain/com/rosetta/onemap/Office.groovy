package com.rosetta.onemap

class Office {

	String name
	
    static constraints = {
		name(blank: false)
    }

	@Override
	public boolean equals(Object obj) {
		boolean isEqual = true
		// Not equal if both are not Offices
		if (isEqual && !(obj instanceof Office)) {
			isEqual = false
		}
		// Not equal if both do not have the same name
		if (isEqual && !(this.name.equals(obj.name))) {
			isEqual = false
		}
		return super.equals(obj)
	}
}