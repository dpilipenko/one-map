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
	 */
//	 * @return { ...,"hID":" SVG Path ",...  }
	def getHotspots () {
		Map<String, Map<String, String>> hotspots = new HashMap<String, HashMap<String, String>>()
		for (Hotspot h : Hotspot.findAllByFloor(params.floor)) {
			Map<String, String> properties = new HashMap<String, String>()
			properties.put("assignedSeatId", h.assignedSeatId)
			properties.put("path", h.polygon)
			properties.put("type", h.type)
			properties.put("width", h.width)
			properties.put("height", h.height)
			properties.put("x", h.x)
			properties.put("y", h.y)
			
			hotspots.put("h"+h.id, properties)
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
		
		if (hotspot instanceof Desk) {
			Desk desk = hotspot
			res.put("assignedSeatId", desk.assignedSeatId)
			if (desk.user == null) {
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
			} else  if (desk.user != null) {
				res.put("name", desk.user.firstName+" "+desk.user.lastName)
				res.put("craft", desk.user.craft)
				res.put("level", desk.user.level)
				res.put("phone", desk.user.phone)
				res.put("email", desk.user.username)
				res.put("type", "desk")
				
				if (currUser == null) {
					res.put("isOwn", false)
					res.put("claimed", true)
				} else {
					res.put("isOwn", desk.user.id == currUser.id)
					if (desk.user.id != currUser.id) {
						res.put("claimed", true)
					}  else {
						res.put("claimed", false)
					}
				}
			}
		} else if (hotspot instanceof Room) {
			Room room = hotspot
			// found room at hotspot
			res.put("assignedSeatId", room.assignedSeatId)
			res.put("name", room.name)
			res.put("number", room.number)
			res.put("phone", room.phone)
			res.put("project", room.project)
			res.put("type", "room")
			JSONArray members = new JSONArray()
			for (User u : room.users) {
				JSONObject member = new JSONObject()
				member.put("name", u.firstName+" "+u.lastName)
				member.put("craft", u.craft)
				member.put("level", u.level)
				member.put("phone", u.phone)
				member.put("email", u.username)
				members.add(member)
			}
			res.put("members", members)
		} else {
			// unclaimed hotspot
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

			if (hotspot instanceof Room) {
				Room room = hotspot
				unclaimHotspot()
				room.addUser(currUser)
				res.put("success", true)
			} else if (hotspot instanceof Desk){
				Desk desk = hotspot
				if (desk.isEmpty()) {
					unclaimHotspot()
					desk.claim(currUser);
					res.put("success", true);
				} else {
					res.put("success", false) // Desk is not empty
				}
			} else {
				res.put("success", false) // Hotspot is not claimable
			}
						
			JSONObject userinformation = new JSONObject()
			userinformation.put("name", currUser.firstName+" "+currUser.lastName)
			userinformation.put("level", currUser.level)
			userinformation.put("craft", currUser.craft)
			userinformation.put("phone", currUser.phone)
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
		if (hotspot instanceof Room) {
			Room room = hotspot
			if ( ! room.hasProject() ) {
				room.initWarRoom(projectName)
				res.put("success", true)
			} else {
				res.put("success", false) // Room already has a Project
			}
		} else {
			res.put("success", false) // Hotspot is not a Room
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
		if (hotspot instanceof Room) {
			Room room = hotspot
			if (room.hasProject()) {
				room.closeWarRoom()
				res.put("success", true)
			} else {
				res.put("success", false) // Room is not a WarRoom
			}
		} else {
			res.put("success", false) // Hotspot is not a Room
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
				hopstopId = "h"+desk.id
				floor = desk.getFloor()
			}
			
			def c = Room.createCriteria()
			Room room = c.get {
			   users {
				  idEq(user.id)
			   }
			}
			if(room != null) {
				hopstopId = "h"+room.id
				floor = room.getFloor()
			}
			
			userObject.put("floor", floor);
			userObject.put("hotspotId", hopstopId);
			
			searchResults.add(userObject);
		}
		
		def roomResults = Room.withCriteria {
			or {
				ilike('name', '%'+searchTerm+'%');
				like('number', '%'+searchTerm+'%');
				ilike('assignedSeatId', '%'+searchTerm+'%');
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
			
			String hopstopId = "h"+room.id;
			String floor = room.getFloor();

			roomObject.put("floor", floor);
			roomObject.put("hotspotId", hopstopId);
			
			searchResults.add(roomObject);
		}
		
		render searchResults as JSON
	}
}
