package com.rosetta.onemap.services

import grails.transaction.Transactional

import com.rosetta.onemap.Hotspot
import com.rosetta.onemap.User
import com.rosetta.onemap.pintypes.Desk

@Transactional
class DeskService {

    def serviceMethod() {

    }
	
	/**
	 * Deletes all Desk pins that belong to user
	 * @param user Person that owns desk pins
	 */
	void unclaimAllForUser(User user) {
		for (Desk desk : Desk.findAllByUser(user)) {
			desk.user = null;
			desk.delete(flush:true) 
		}
	}
	
	Desk createDeskAtHotspotWithUser(Hotspot hotspot, User user) {
		Desk desk = new Desk()
		desk.user = user
		desk.hotspot = hotspot
		desk.dateCreated = new Date()
		desk.lastUpdated = new Date()
		desk.save(flush:true)
	}
	
	Desk createDesk(long hotspotId) {
		def d = new Desk()
		d.hotspot = Hotspot.get(hotspotId);
		d.dateCreated = new Date()
		d.lastUpdated = new Date()
		d.save()
		if (d.hasErrors()) {
			log.info("Got some desk errors on create!")
		}
		return d
	}
	
	Desk createDesk(String name, User user, long hotspotId) {
		def d = new Desk()
		d.name = name
		d.user = user
		d.hotspot = Hotspot.get(hotspotId)
		d.dateCreated = new Date()
		d.lastUpdated = new Date()
		d.save()
		if (d.hasErrors()) {
			log.info("Got some desk errors on create!")
		}
		return d
	}
	
	Desk addUserToDesk(long deskId, User user) {
		def d = Desk.get(deskId)
		d.user = user
		d.lastUpdated = new Date()
		d.save()
		if (d.hasErrors()) {
			log.info("Got some desk errors on add user!")
		}
		return d
	}
	
	Desk updateDesk(long deskId, String name, User user, long hotspotId) {
		def d = Desk.get(deskId)
		d.name = name
		d.user = user
		d.hotspot = Hotspot.get(hotspotId)
		d.lastUpdated = new Date()
		d.save()
		if (d.hasErrors()) {
			log.info("Got some desk errors on update!")
		}
		return d
	}
	
	void deleteDesk(long deskId) {
		def d = Desk.get(id)
		d.delete(flush: true)
		if (d.hasErrors()) {
			log.info("Got some desk errors on delete!!")
		}
	}
	
}
