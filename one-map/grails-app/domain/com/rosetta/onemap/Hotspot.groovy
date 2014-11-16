package com.rosetta.onemap

class Hotspot {

	@Override
	public String toString() {
		return "Hotspot [floor=" + floor + ", type=" + type + ", zone=" + zone
				+ ", polygon=" + polygon + ", x=" + x + ", y=" + y + "]";
	}

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((floor == null) ? 0 : floor.hashCode());
		result = prime * result + ((polygon == null) ? 0 : polygon.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
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
		if (!(obj instanceof Hotspot)) {
			return false;
		}
		Hotspot other = (Hotspot) obj;
		if (floor == null) {
			if (other.floor != null) {
				return false;
			}
		} else if (!floor.equals(other.floor)) {
			return false;
		}
		if (polygon == null) {
			if (other.polygon != null) {
				return false;
			}
		} else if (!polygon.equals(other.polygon)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (x == null) {
			if (other.x != null) {
				return false;
			}
		} else if (!x.equals(other.x)) {
			return false;
		}
		if (y == null) {
			if (other.y != null) {
				return false;
			}
		} else if (!y.equals(other.y)) {
			return false;
		}
		if (zone == null) {
			if (other.zone != null) {
				return false;
			}
		} else if (!zone.equals(other.zone)) {
			return false;
		}
		return true;
	}
}
