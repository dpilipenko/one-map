package com.rosetta.onemap.services

import grails.transaction.Transactional

import com.rosetta.onemap.Hotspot
import com.rosetta.onemap.pintypes.Room
import com.rosetta.onemap.User

@Transactional
class RoomService {
    def serviceMethod() { }
	
	/**
	 * Removes all mentions of User in all Room pins
	 * @param user The user being removed
	 */
	void unclaimAllForUser(User user) {
		Room room = getRoomContainingUser(user)
		if (room != null) {
			room.removeFromUsers(currUser)
			romm.save(flush:true)
		}
	}
	
	/**
	 * Returns Room that contains the User or NULL if not found
	 * @param user The user being removed
	 * @return Room or NULL if not found
	 */
	Room getRoomContainingUser(User user) {
		def c = Room.createCriteria()
		def room = c.get {
		   users {
			  idEq(currUser.id)
		   }
		}
		return room
	}
	
	/**
	 * Adds user to the room and saves to database
	 * @param room	The room to which the user will be added
	 * @param user	The user that will be entered into the room
	 * @return	The room, now containing the user
	 */
	Room addUserToRoom(Room room, User user) {
		room.addToUsers(user)
		room.lastUpdated = new Date()
		room.save(flush:true)
		return room
	}
	
	/**
	 * Resets room as a new project room
	 * @param room	Room that will be reset
	 * @param projectName	Name for new project room
	 * @return	Updated room
	 */
	Room initRoomForProject(Room room, String projectName) {
		room.project = projectName
		room.users.clear()
		room.save(flush:true)
		return room
	}
	
	/**
	 * Removes project from room
	 * @param room
	 * @return
	 */
	Room closeRoomForProject(Room room) {
		room.project = new String()
		room.users.clear()
		room.save(flush:true)
		return room
	}
	
	Room createRoom(String name, String number, long hotspotId) {
		def r = new Room()
		r.name = name
		r.number = number
		r.hotspot = Hotspot.get(hotspotId)
		r.dateCreated = new Date()
		r.lastUpdated = new Date()
		r.save()
		if (r.hasErrors()) {
			log.info("Got some room errors on create!")
		}
		return r
	}
	
}
