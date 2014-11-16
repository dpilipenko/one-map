package com.rosetta.onemap.pintypes

import com.rosetta.onemap.Hotspot
import com.rosetta.onemap.Office
import com.rosetta.onemap.User
import com.rosetta.onemap.Zone

class Desk extends Hotspot {
	
	@Override
	public String toString() {
		return "Desk [assignedSeatId=" + assignedSeatId + ", user=" + user
				+ ", office=" + office + ", type=" + type + ", zone=" + zone
				+ "]";
	}

	String assignedSeatId
	User user
	Office office
	/* Overriding Variables */
	String type = "desk"
	Zone zone
	
    static constraints = {
		user nullable:true
		zone nullable:true
		office nullable:true
    }
	
	boolean isVacant() {
		return user == null
	}
	
	boolean claim(User user) {
		if (isVacant() ) {
			this.user = user
			this.save(flush:true)
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((assignedSeatId == null) ? 0 : assignedSeatId.hashCode());
		result = prime * result + ((office == null) ? 0 : office.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Desk)) {
			return false;
		}
		Desk other = (Desk) obj;
		if (assignedSeatId == null) {
			if (other.assignedSeatId != null) {
				return false;
			}
		} else if (!assignedSeatId.equals(other.assignedSeatId)) {
			return false;
		}
		if (office == null) {
			if (other.office != null) {
				return false;
			}
		} else if (!office.equals(other.office)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
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
