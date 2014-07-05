package com.rosetta.onemap.services

import grails.transaction.Transactional

import com.rosetta.onemap.Hotspot

@Transactional
class HotspotService {

    def serviceMethod() { }
	
	List getAllByFloor(String floor) {
		return Hotspot.findAllByFloor(floor)
	}
	
	Hotspot getByID(String hotspotID) {
		return Hotspot.get(hotspotID)
	}
	
}
