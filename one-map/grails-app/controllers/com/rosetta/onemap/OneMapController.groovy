package com.rosetta.onemap

import com.rosetta.onemap.pintypes.Desk
import com.rosetta.onemap.pintypes.Room
import grails.converters.JSON
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.codehaus.groovy.grails.web.json.JSONArray
import org.codehaus.groovy.grails.web.json.JSONObject

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
		Map<String, String> hotspots = new HashMap<String, String>()
		for (Hotspot h : Hotspot.findAllByFloor(params.floor)) {
			hotspots.put("h"+h.id, h.polygon)
		}		
		render hotspots as JSON
	}
	
	def gethotspotbyid () {

		deskService.createDesk("Bob", 1, 1)
		roomService.createRoom("The Beatles", 2)
		
		def hotspot = Hotspot.get(params.hotspotID)
		def p = Pin.findByHotspot(hotspot)
		if (p == null) {
			// no hotspot
			JSONObject o = new JSONObject()
			render o as JSON
		} else if (p instanceof Desk) {
			// found desk at hotspot
			JSONObject o = new JSONObject()
			o.put("type", "desk")
			o.put("name", "Dan Padget")
			o.put("craft", "Software Engineer")
			o.put("level", "Senior Associate")
			o.put("phone", "216.000.1234")
			o.put("email", "dan.padget@rosetta.com")
			o.put("claimed", "false")
			render o as JSON
			
		} else if (p instanceof Room) {
			// found room at hotspot
			JSONObject o = new JSONObject()
			o.put("type", "room")
			o.put("name", "The Beatles")
			o.put("number", "1728")
			o.put("phone", "216.000.0000")
			o.put("project", "AHA")
			
			JSONArray members = new JSONArray()
			JSONObject m1 = new JSONObject()
			m1.put("name", "Dave Fagan")
			m1.put("craft", "Creative Engineer")
			m1.put("level", "Senior Associate")
			m1.put("phone", "216.000.0000")
			m1.put("email", "dave.fagan@rosetta.com")
			members.put(m1);
			JSONObject m2 = new JSONObject()
			m2.put("name", "Liz Judd")
			m2.put("craft", "Creative Engineer")
			m2.put("level", "Senior Associate")
			m2.put("phone", "216.000.0000")
			m2.put("email", "liz.judd@rosetta.com")
			members.put(m2)
			JSONObject m3 = new JSONObject()
			m3.put("name", "Dan Padgett")
			m3.put("craft", "Software Engineer")
			m3.put("level", "Senior Associate")
			m3.put("phone", "216.000.0000")
			m3.put("email", "dan.padget@rosetta.com")
			members.put(m3)
			
			o.put("members", members)
			render o as JSON
		} else {
			// undefined action
			JSONObject o = new JSONObject()
			render o as JSON
		}
	}
	
	def runSearch() {
		//render (template:"/oneMap/results-template", model:[results: ])
	}
	
}
