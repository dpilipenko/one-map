package com.rosetta.onemap

import com.rosetta.onemap.pintypes.Desk
import com.rosetta.onemap.pintypes.Room
import grails.converters.JSON
import com.rosetta.onemap.pintypes.Room
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
	
	/**
	 * GET 	/gethotspotbyid?hotspotID=#
	 */
	def gethotspotbyid () {
		def currUser = springSecurityService.currentUser
		def hotspotID = params.hotspotID
		if (hotspotID.startsWith("h")) { // we prefix IDs with 'h' in the UI
			hotspotID = hotspotID.substring(1, hotspotID.length())
		}
		def hotspot = Hotspot.get(hotspotID)
		def pin = Pin.findByHotspot(hotspot)
		JSONObject o = new JSONObject()
		
		if (pin instanceof Desk) {		// Returning details about a Desk
			// found desk at hotspot
			o.put("type", "desk")
			if (pin.user != null) {
				o.put("name", pin.user.firstName+" "+pin.user.lastName)
				o.put("craft", pin.user.craft)
				o.put("level", pin.user.level)
				o.put("phone", pin.user.phone)
				o.put("email", pin.user.username)
				if (currUser.id == pin.id)
					o.put("isOwn", true)
				else
					o.put("isOwn", false)
				if (currUser != null && currUser.id != pin.id)
					o.put("claimed", true)
				else
					o.put("claimed", false)
			}
		} else if (pin instanceof Room) {		// Returning details about a Room
			// found room at hotspot
			o.put("type", "room")
			o.put("name", pin.name)
			o.put("number", pin.number)
			o.put("phone", pin.phone)
			o.put("project", pin.project)
			JSONArray members = new JSONArray()
			for (User u : pin.users) {
				JSONObject member = new JSONObject()
				member.put("name", u.firstName+" "+u.lastName)
				member.put("craft", u.craft)
				member.put("level", u.level)
				member.put("phone", u.phone)
				member.put("email", u.username)
				members.add(member)
			}
			o.put("members", members)
		} else {
			// undefined action
		}
		render o as JSON
	}
	
	/**
	 * GET	/claimHotspot?hotspotID=#
	 */
	def claimHotspot() {
		JSONObject res = new JSONObject()
		def currUser = springSecurityService.currentUser
		if (currUser != null) {
			
			// find current seat and kick them out
			for (Desk desk : Desk.findAllByUser(currUser)) {
				deskService.updateDesk(desk.id, desk.name, null, desk.hotspotId)
			}
			for (Room room : Room.findAllByUser(currUser)) {
				// do rooms
			}
			
			
			// add to hotspot
			def hotspot = Hotspot.get(params.hotspotID)
			def pin = Pin.findByHotspot(hotspot)
			if (pin == null) {
				res.put("success", false)
			} else {
				if (pin.type.equals("desk")) {
					deskService.updateDesk(pin.id, pin.name, currUser, pin.hotspotId)
				} else if (pin.type.equals("room")) {
					roomService.addUserToRoom(pin.id, currUser)
				}
				res.put("success", true)
			}
		} else {
			// cannot claim if not logged in
			res.put("success", false);
		}
		render res as JSON
	}
	
	def runSearch() {
		def searchTerm = params.searchquery
		
		List<HashMap<String,String>> searchResults = new ArrayList<HashMap<String,String>>();
		
		def userResults = User.withCriteria {
			or {
				like('username', '%'+searchTerm+'%')
				like('firstName', '%'+searchTerm+'%')
				like('lastName', '%'+searchTerm+'%')
			}
		}
		for(User user : userResults) {
			HashMap<String, String> userMap = new HashMap<String,String>();
			userMap.put("name", (user.firstName+" "+user.lastName));
			if(user?.level != null) {
				userMap.put("position", user.level);
			}
			if(user?.office != null) {
				userMap.put("location", user.office.name);
			}
			searchResults.add(userMap);
		}
		
		def roomResults = Room.withCriteria {
			or {
				like('name', '%'+searchTerm+'%');
			}
		}
		for(Room room : roomResults) {
			HashMap<String, String> roomMap = new HashMap<String,String>();
			roomMap.put("name", room.name);
			searchResults.add(roomMap);
		}
		
		render (template:"results-template", model:[searchResults: searchResults])
	}
}
