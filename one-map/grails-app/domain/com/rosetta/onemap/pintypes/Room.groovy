package com.rosetta.onemap.pintypes

import com.rosetta.onemap.Hotspot
import com.rosetta.onemap.Office
import com.rosetta.onemap.User
import com.rosetta.onemap.Zone

class Room extends Hotspot {
	@Override
	public String toString() {
		return "Room [assignedSeatId=" + assignedSeatId + ", office=" + office
				+ ", name=" + name + ", number=" + number + ", phone=" + phone
				+ ", project=" + project + ", type=" + type + ", zone=" + zone
				+ "]";
	}

	String assignedSeatId
	Office office
	String name
	String number
	String phone
	String project
	/* Overriding Variables */
	String type = "room"
	Zone zone
	
	static hasMany = [users: User]
	
    static constraints = {
    	name blank: false
		number blank: false
		phone nullable: true
		project nullable: true
		office nullable: true
		zone nullable: true
	}
	
	static boolean isExistingProjectName(String projectName) {
		return (Room.findByProject(projectName) != null) // returns false if projectName was already taken by a Room
	}

	boolean isVacant() {
		return (project == null || project.isEmpty());
	}
	
	boolean hasProject() {
		return (project != null && !project.isEmpty())
	}
	
	
	void addUser(User user) {
		users.add(user)
		this.save(flush:true)
	}
	
	void initWarRoom(String projectName) {
		project = projectName
		users.clear()
		this.save(flush:true)
	}
	
	void closeWarRoom() {
		project = new String()
		users.clear()
		this.save(flush:true)
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Room)) {
			return false;
		}
		Room other = (Room) obj;
		if (assignedSeatId == null) {
			if (other.assignedSeatId != null) {
				return false;
			}
		} else if (!assignedSeatId.equals(other.assignedSeatId)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (number == null) {
			if (other.number != null) {
				return false;
			}
		} else if (!number.equals(other.number)) {
			return false;
		}
		if (office == null) {
			if (other.office != null) {
				return false;
			}
		} else if (!office.equals(other.office)) {
			return false;
		}
		if (phone == null) {
			if (other.phone != null) {
				return false;
			}
		} else if (!phone.equals(other.phone)) {
			return false;
		}
		if (project == null) {
			if (other.project != null) {
				return false;
			}
		} else if (!project.equals(other.project)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((assignedSeatId == null) ? 0 : assignedSeatId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((office == null) ? 0 : office.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
		return result;
	}
}
