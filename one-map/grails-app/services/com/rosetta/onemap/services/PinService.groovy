package com.rosetta.onemap.services

import grails.transaction.Transactional

import com.rosetta.onemap.Hotspot
import com.rosetta.onemap.Pin

@Transactional
class PinService {

    def serviceMethod() {

    }
	
	Pin createPin(String name, long hotspotId) {
		def p = new Pin()
		p.name = name
		p.dateCreated = new Date()
		p.lastUpdated = new Date()
		p.save()
		if(p.hasErrors()) {
			log.info("YOYOY ERRORS!!!")
		}
		def h = Hotspot.get(hotspotId)
		p.hotspot = h
		return p
	}
	
	Pin updatePin(long pinId, String name, long hotspotId) {
		def p = Pin.get(id)
		p.name = name
		p.lastUpdated = new Date()
		
		def h = Hotspot.get(hotspotId)
		p.hotspot = h
		return p
	}
	
	void deletePin(long pinId) {
		def p = Pin.get(id)
		p.delete(flush: true)
	}
	
	
}
