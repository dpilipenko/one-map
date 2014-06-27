package com.rosetta.onemap.services

import grails.transaction.Transactional

import com.rosetta.onemap.Hotspot
import com.rosetta.onemap.pintypes.Room
import com.rosetta.onemap.User

@Transactional
class RoomService {

    def serviceMethod() {

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
	
	Room addUserToRoom(long roomId, long userId) {
		def r = Room.get(roomId)
		r.addToUsers(User.get(userId))
		r.lastUpdated = new Date()
		r.save()
		if (r.hasErrors()) {
			log.info("Got some room errors on add user!")
		}
		return r
	}
	
	Room updateRoom(long roomId, String name, long hotspotId) {
		def r = Room.get(roomId)
		r.name = name
		r.hotspotId = hotspotId
		r.lastUpdated = new Date()
		r.save()
	}
	
}
