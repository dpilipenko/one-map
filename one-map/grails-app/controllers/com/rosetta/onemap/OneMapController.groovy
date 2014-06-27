package com.rosetta.onemap

import grails.converters.JSON

class OneMapController {

	def springSecurityService
	def pinService
	def deskService
	def roomService
	
    def show() {
		def config = SpringSecurityUtils.securityConfig
		String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
		[postUrl: postUrl]
	}
	
	def gethotspots () {
		Map<String, List<String>> hotspotMaps = new HashMap<String, List<String>>()
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
