package com.rosetta.onemap

class Role {

	@Override
	public String toString() {
		return "Role [authority=" + authority + "]";
	}
	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authority == null) ? 0 : authority.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Role)) {
			return false;
		}
		Role other = (Role) obj;
		if (authority == null) {
			if (other.authority != null) {
				return false;
			}
		} else if (!authority.equals(other.authority)) {
			return false;
		}
		return true;
	}
}
