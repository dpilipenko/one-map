package com.rosetta.onemap

import grails.converters.JSON
import groovy.json.JsonBuilder;

import com.google.gson.JsonObject;

class OneMapController {

    def show() {
		//TODO: implement the one map
	}
	
	def gethotspots () {
		Map<String, List<String>> hotspotMaps = new HashMap<String, String>()
		for (Hotspot h : Hotspot.findAllByFloor(params.floor)) {
			List<String> polygons = hotspotMaps.get(h.floor)
			if (polygons == null) {
				polygons = new ArrayList<String>()
			}
			polygons.add(h.polygon)
			hotspotMaps.put(h.floor, polygons)
		}
		render hotspotMaps as JSON
	}
}
