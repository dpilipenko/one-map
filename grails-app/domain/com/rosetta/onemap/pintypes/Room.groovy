package com.rosetta.onemap.pintypes

import com.rosetta.onemap.Hotspot
import com.rosetta.onemap.Office
import com.rosetta.onemap.User
import com.rosetta.onemap.Zone


class Room extends Hotspot {

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
	List users
	
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
}
