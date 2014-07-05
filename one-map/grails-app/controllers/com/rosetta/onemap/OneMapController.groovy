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
	
	def hotspotService
	
    def show() {
		def config = SpringSecurityUtils.securityConfig
		String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
		[postUrl: postUrl]
	}
	
	/**
	 * GET	/getHotspots?floor=#
	 * @return { ...,"hID":" SVG Path ",...  }
	 */
	def getHotspots () {
		def floor = params.floor
		Map<String, String> hotspots = new HashMap<String, String>()
		for (Hotspot h : hotspotService.getAllByFloor(floor)) {
			hotspots.put("h"+h.id, h.polygon)
		}		
		render hotspots as JSON
	}
	
	/**
	 * GET 	/getHotspot?hotspotID=h#
	 * @return
	 */
	def getHotspot () {
		def hotspotID = params.hotspotID
		if (hotspotID.startsWith("h")) { // we prefix IDs with 'h' in the UI
			hotspotID = hotspotID.substring(1, hotspotID.length())
		}
		
		Hotspot hotspot = hotspotService.getByID(hotspotID);
		def currUser = springSecurityService.currentUser
		def pin = Pin.findByHotspot(hotspot)
		JSONObject o = new JSONObject()
		if (pin == null) {
			o.put("floor", hotspot.floor)
			o.put("type", hotspot.type)
			o.put("claimed", false)
			if (currUser != null) {
				o.put("name", currUser.firstName+" "+currUser.lastName)
				o.put("craft", currUser.craft)
				o.put("level", currUser.level)
				o.put("phone", currUser.phone)
				o.put("email", currUser.username)
			}
		} else if (pin instanceof Desk) {		// Returning details about a Desk
			// found desk at hotspot
			if (pin.user == null) {
				o.put("floor", hotspot.floor)
				o.put("type", hotspot.type)
				o.put("claimed", false)
				if (currUser != null) {
					o.put("name", currUser.firstName+" "+currUser.lastName)
					o.put("craft", currUser.craft)
					o.put("level", currUser.level)
					o.put("phone", currUser.phone)
					o.put("email", currUser.username)
				}
			} else  if (pin.user != null) {
				o.put("name", pin.user.firstName+" "+pin.user.lastName)
				o.put("craft", pin.user.craft)
				o.put("level", pin.user.level)
				o.put("phone", pin.user.phone)
				o.put("email", pin.user.username)
				o.put("type", "desk")
				if (currUser.id == pin.user.id)
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
			o.put("name", pin.name)
			o.put("number", pin.number)
			o.put("phone", pin.phone)
			o.put("project", pin.project)
			o.put("type", "room")
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
	 * GET	/claimHotspot?hotspotID=h#
	 * @return
	 */
	def claimHotspot() {
		def hotspotID = params.hotspotID
		if (hotspotID.startsWith("h")) { // we prefix IDs with 'h' in the UI
			hotspotID = hotspotID.substring(1, hotspotID.length())
		}
		
		JSONObject o = new JSONObject()
		def currUser = springSecurityService.currentUser
			
		// find current seat and kick them out
		for (Desk desk : Desk.findAllByUser(currUser)) {
			deskService.updateDesk(desk.id, desk.name, null, desk.hotspot.id)
		}
		
		def c = Room.createCriteria()
		def room = c.get {
		   users {
			  idEq(currUser.id)
		   }
		}
		if (room != null) {
			room.removeFromUsers(currUser)
		}
		
		def hotspot = Hotspot.get(Long.parseLong(hotspotID))
		def pin = Pin.findByHotspot(hotspot)
		if (hotspot.type.equals("desk")) {
			if(pin == null) {
				pin = deskService.createDesk("desk", hotspot.id);
			}
			deskService.addUserToDesk(pin.id, currUser)
		} else if (hotspot.type.equals("room")) {
			if(pin == null) {
				pin = roomService.createRoom("room", "123", hotspot.id);
			}
			roomService.addUserToRoom(pin.id, currUser)
		}
		
		JSONObject userInfo = new JSONObject()
		userInfo.put("name", (currUser.firstName+" "+currUser.lastName))
		userInfo.put("level", currUser.level)
		userInfo.put("craft", currUser.craft)
		userInfo.put("phone", currUser.phone)
		userInfo.put("email", currUser.username)
		o.put("userinformation", userInfo);
		
		render o as JSON
	}
	
	/**
	 * GET	/unclaimHotspot
	 * @return
	 */
	def unclaimHotspot () {
		JSONObject o = new JSONObject()
		def currUser = springSecurityService.currentUser
		if (currUser == null) {
			o.put("success", false)	
		} else {
			// find current seat and kick them out
			for (Desk desk : Desk.findAllByUser(currUser)) {
				deskService.updateDesk(desk.id, desk.name, null, desk.hotspotId)
			}
			def c = Room.createCriteria()
			def room = c.get {
			   users {
				  idEq(currUser.id)
			   }
			}
			if (room != null) {
				room.removeFromUsers(currUser)
				romm.save(flush:true)
			}
			o.put("success", true)
		}
		render o as JSON
	}
	
	/**
	 * GET	/createWarRoom?hotspotID=h#&projectName=STRING
	 * @return 
	 */
	def createWarRoom() {
		def hotspotID = params.hotspotID
		if (hotspotID.startsWith("h")) { // we prefix IDs with 'h' in the UI
			hotspotID = hotspotID.substring(1, hotspotID.length())
		}
		
		JSONObject o = new JSONObject()
		Hotspot h = Hotspot.get(Long.parseLong(hotspotID))
		if (h.type.equals("room")) {
			def pin = Pin.findByHotspot(h)
			if (pin == null) {
				def room = roomService.createRoom("room", "123", h.id)
				room.project = params.projectName
				room.save(flush:true)
				o.put("success", true)
			} else {
				if(pin instanceof Room) {
					pin.project = params.projectName
					pin.save(flush: true)
					o.put("success", true)
				}
			}
		}
		render o as JSON
	}
	
	/**
	 * GET	/closeWarRoom?hotspotID=h#
	 * @return
	 */
	def closeWarRoom() {
		def hotspotID = params.hotspotID
		if (hotspotID.startsWith("h")) { // we prefix IDs with 'h' in the UI
			hotspotID = hotspotID.substring(1, hotspotID.length())
		}
		
		JSONObject o = new JSONObject()
		Room r = Room.get(Long.parseLong(hotspotID))
		if (r == null) {
			o.put("success", false)
		} else {
			if (r.project == null || r.project.isEmpty()) {
				o.put("success", false)
			} else {
				r.project = null
				o.put("success", true)
			}
		}
		render o as JSON
	}
	
	/**
	 * GET	/runSearch?searchquery=STRING
	 * @return
	 */
	def runSearch() {
		def searchTerm = params.searchquery
		
		JSONArray searchResults = new JSONArray()
		
		def userResults = User.withCriteria {
			or {
				ilike('username', '%'+searchTerm+'%')
				ilike('firstName', '%'+searchTerm+'%')
				ilike('lastName', '%'+searchTerm+'%')
			}
		}
		for(User user : userResults) {
			JSONObject userObject = new JSONObject()
			userObject.put("name", (user.firstName+" "+user.lastName));
			userObject.put("level", user?.level);
			userObject.put("craft", user?.craft);
			userObject.put("location", user?.office?.name);
			
			String hopstopId = "";
			String floor = "";
			
			def desk = Desk.findByUser(user);
			if (desk != null) {
				hopstopId = "h"+desk.getHotspot().id
				floor = desk.getHotspot().getFloor()
			}
			
			def c = Room.createCriteria()
			def room = c.get {
			   users {
				  idEq(user.id)
			   }
			}
			if(room != null) {
				hopstopId = "h"+room.getHotspot().id
				floor = room.getHotspot().getFloor()
			}
			
			userObject.put("floor", floor);
			userObject.put("hotspotId", hopstopId);
			
			searchResults.add(userObject);
		}
		
		def roomResults = Room.withCriteria {
			or {
				ilike('name', '%'+searchTerm+'%');
				like('number', '%'+searchTerm+'%');
			}
		}
		for(Room room : roomResults) {
			JSONObject roomObject = new JSONObject()
			roomObject.put("name", room.name);
			roomObject.put("level", "");
			roomObject.put("craft", "");
			if(room?.office?.name != null) {
				roomObject.put("location", room?.office?.name);
			} else {
				roomObject.put("location", "");
			}
			
			String hopstopId = "h"+room.getHotspot().id;
			String floor = room.getHotspot().getFloor();

			roomObject.put("floor", floor);
			roomObject.put("hotspotId", hopstopId);
			
			searchResults.add(roomObject);
		}
		
		render searchResults as JSON
	}
}
