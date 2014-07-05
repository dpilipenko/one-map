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
		String floor = params.floor
		Map<String, String> hotspots = new HashMap<String, String>()
		for (Hotspot h : Hotspot.findAllByFloor(floor)) {
			hotspots.put("h"+h.id, h.polygon)
		}		
		render hotspots as JSON
	}
	
	/**
	 * GET 	/getHotspot?hotspotID=h#
	 * @return
	 */
	def getHotspot () {
		String hotspotID = params.hotspotID
		if (hotspotID.startsWith("h")) { // we prefix IDs with 'h' in the UI
			hotspotID = hotspotID.substring(1, hotspotID.length())
		}
		
		JSONObject res = new JSONObject()
		User currUser = springSecurityService.currentUser
		Hotspot hotspot = Hotspot.get(Long.parseLong(hotspotID));
		if (hotspot.pin == null) {
			res.put("floor", hotspot.floor)
			res.put("type", hotspot.type)
			res.put("claimed", false)
			if (currUser != null) {
				res.put("name", currUser.firstName+" "+currUser.lastName)
				res.put("craft", currUser.craft)
				res.put("level", currUser.level)
				res.put("phone", currUser.phone)
				res.put("email", currUser.username)
			}
		} else {
			if (hotspot.pin instanceof Desk) {
				Desk pin = hotspot.pin
				if (pin.user == null) {
					res.put("floor", hotspot.floor)
					res.put("type", hotspot.type)
					res.put("claimed", false)
					if (currUser != null) {
						res.put("name", currUser.firstName+" "+currUser.lastName)
						res.put("craft", currUser.craft)
						res.put("level", currUser.level)
						res.put("phone", currUser.phone)
						res.put("email", currUser.username)
					}
				} else  if (pin.user != null) {
					res.put("name", pin.user.firstName+" "+pin.user.lastName)
					res.put("craft", pin.user.craft)
					res.put("level", pin.user.level)
					res.put("phone", pin.user.phone)
					res.put("email", pin.user.username)
					res.put("type", "desk")
					if (currUser.id == pin.user.id)
						res.put("isOwn", true)
					else
						res.put("isOwn", false)
					if (currUser != null && currUser.id != pin.id)
						res.put("claimed", true)
					else
						res.put("claimed", false)
				}
			} else if (hotspot.pin instanceof Room) {
				Room pin = hotspot.pin
				// found room at hotspot
				res.put("name", pin.name)
				res.put("number", pin.number)
				res.put("phone", pin.phone)
				res.put("project", pin.project)
				res.put("type", "room")
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
				res.put("members", members)
			}
		}

		render res as JSON
	}
	
	/**
	 * GET	/claimHotspot?hotspotID=h#
	 * @return {"success":boolean, "userinformation":{"name":String, "level":String, "craft":String, "phone":String, "email":String}}
	 */
	def claimHotspot() {
		String hotspotID = params.hotspotID
		if (hotspotID.startsWith("h")) { // we prefix IDs with 'h' in the UI
			hotspotID = hotspotID.substring(1, hotspotID.length())
		}
		JSONObject res = new JSONObject()
		User currUser = springSecurityService.currentUser
		if (currUser != null) {
			
			Hotspot hotspot = Hotspot.get(Long.parseLong(hotspotID))
			if (hotspot.type.equals("desk") && hotspot.pin != null) {
				// Attempting to claim an already claimed desk. Do not allow.
				res.put("success", false)
			} else if (hotspot.type.equals("room") && hotspot.pin == null) {
				// Attempting to join non-existing room. Do not allow.
				res.put("success", false)
			} else {
				deskService.unclaimAllForUser(currUser)
				roomService.unclaimAllForUser(currUser)
				if (hotspot.type.equals("desk") && hotspot.pin == null) {
					Desk pin = deskService.createDeskAtHotspotWithUser(hotspot, currUser)
							res.put("success", true)
				} else if (hotspot.type.equals("room") && hotspot.pin != null) {
					Room room = hotspot.pin
							roomService.addUserToRoom(room, currUser)
							res.put("success", true)
				} 
			}
			
			JSONObject userinformation = new JSONObject()
			userinformation.put("name", currUser.firstName+" "+currUser.lastName)
			userinformation.put("level", currUser.level)
			userinformation.put("craft", currUser.craft)
			userinformation.put("phone", phone)
			userinformation.put("email", currUser.username)
			res.put("userinformation", userinformation)
		}
		render res as JSON
	}
	
	/**
	 * GET	/unclaimHotspot
	 * @return	{"success":boolean}
	 */
	def unclaimHotspot () {
		JSONObject res = new JSONObject()
		User currUser = springSecurityService.currentUser
		if (currUser == null) {
			res.put("success", false)	
		} else {
			deskService.unclaimAllForUser(currUser)
			roomService.unclaimAllForUser(currUser)
			res.put("success", true)
		}
		render res as JSON
	}
	
	/**
	 * GET	/createWarRoom?hotspotID=h#&projectName=STRING
	 * @return {"success":boolean}
	 */
	def createWarRoom() {
		String hotspotID = params.hotspotID
		if (hotspotID.startsWith("h")) { // we prefix IDs with 'h' in the UI
			hotspotID = hotspotID.substring(1, hotspotID.length())
		}
		String projectName = params.projectName
		
		JSONObject res = new JSONObject()
		Hotspot hotspot = Hotspot.get(Long.parseLong(hotspotID))
		if (hotspot.pin == null) {
			// hotspot is empty. Do not allow.
			res.put("success", false)
		} else if (hotspot.pin != null && hotspot.type.equals("desk")) {
			// hotspot is desk, not a room. Do not allow.
			res.put("succes", false)
		} else if (hotspot.pin != null && hotspot.pin instanceof Room) {
			Room room = hotspot.pin
			if (room.hasProject()) {
				// room is already a war room. Do not allow.
				res.put("success", false)
			} else {
			
				room = roomService.initRoomForProject(room, projectName)
				res.put("success", true)
			}
		}
		render res as JSON
	}
	
	/**
	 * GET	/closeWarRoom?hotspotID=h#
	 * @return {"success":boolean}
	 */
	def closeWarRoom() {
		String hotspotID = params.hotspotID
		if (hotspotID.startsWith("h")) { // we prefix IDs with 'h' in the UI
			hotspotID = hotspotID.substring(1, hotspotID.length())
		}
		
		JSONObject res = new JSONObject()
		Hotspot hotspot = Hotspot.get(Long.parseLong(hotspotID))
		if (hotspot.pin == null) {
			// hotspot is empty. Do not allow.
			res.put("success", false)
		} else if (hotspot.pin != null && hotspot.type.equals("desk")) {
			// hotspot is desk, not a room. Do not allow.
			res.put("success", false)
		} else if (hotspot.pin != null && hotspot.pin instanceof Room) {
			Room room = hotspot.pin
			roomService.closeRoomForProject(room);
			res.put("success", true)
		}
		
		render res as JSON
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
