package com.rosetta.onemap.services

import grails.transaction.Transactional

import com.rosetta.onemap.Hotspot
import com.rosetta.onemap.User
import com.rosetta.onemap.pintypes.Desk

@Transactional
class DeskService {
    def serviceMethod() { }
	
	/**
	 * Deletes all Desk pins that belong to user
	 * @param user Person that owns desk pins
	 */
	void unclaimAllForUser(User user) {
		for (Desk desk : Desk.findAllByUser(user)) {
			desk.user = null;
			desk.save(flush:true) 
		}
	}
	
	/**
	 * Creates new Desk pin, and assigns user to desk
	 * @param hotspot Hotspot where desk pin will be created
	 * @param user	User which will be assigned at the desk
	 * @return
	 */
	Desk createDeskAtHotspotWithUser(Hotspot hotspot, User user) {
		Desk desk = new Desk()
		desk.user = user
		desk.hotspot = hotspot
		desk.dateCreated = new Date()
		desk.lastUpdated = new Date()
		desk.save(flush:true)
	}
	
}
