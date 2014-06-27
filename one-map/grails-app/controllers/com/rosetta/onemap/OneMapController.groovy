package com.rosetta.onemap

import grails.converters.JSON
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class OneMapController {

	def springSecurityService
	
    def show() {
		def config = SpringSecurityUtils.securityConfig
		String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
		[postUrl: postUrl]
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
