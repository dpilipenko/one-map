package com.rosetta.onemap

class Role {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = true
		// both not equal if both are not Roles
		if (isEqual && !(obj instanceof Role)) {
			isEqual = false
		}
		// both not equal if both are not the same authority
		if (isEqual && !(this.authority.equals(obj.authority))) {
			isEqual = false
		}
		return super.equals(obj)
	}
}
