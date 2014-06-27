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
	
	def gethotspotbyid () {
		def currUser = springSecurityService.currentUser
		def hotspotID = params.hotspotID
		if (hotspotID.startsWith("h")) { // we prefix IDs with 'h' in the UI
			hotspotID = hotspotID.substring(1, hotspotID.length())
		}
		def hotspot = Hotspot.get(hotspotID)
		def p = Pin.findByHotspot(hotspot)
		JSONObject o = new JSONObject()
		
		if (p instanceof Desk) {
			// found desk at hotspot
			o.put("type", "desk")
			if (p.user != null) {
				o.put("name", p.user.firstName+" "+p.user.lastName)
				o.put("craft", p.user.craft)
				o.put("level", p.user.level)
				o.put("phone", p.user.phone)
				o.put("email", p.user.username)
				if (currUser != null && currUser.id != p.id)
					o.put("claimed", "true")
				else
					o.put("claimed", "false")
			}
		} else if (p instanceof Room) {
			// found room at hotspot
			o.put("type", "room")
			o.put("name", p.name)
			o.put("number", p.number)
			o.put("phone", p.phone)
			o.put("project", p.project)
			
			JSONArray members = new JSONArray()
			for (User u : p.users) {
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
	
	def runSearch() {
		def searchTerm = params.searchquery
		
		JSONArray searchResults = new JSONArray()
		
		def userResults = User.withCriteria {
			or {
				like('username', '%'+searchTerm+'%')
				like('firstName', '%'+searchTerm+'%')
				like('lastName', '%'+searchTerm+'%')
			}
		}
		for(User user : userResults) {
			JSONObject userObject = new JSONObject()
			userObject.put("name", (user.firstName+" "+user.lastName));
			userObject.put("level", user?.level);
			userObject.put("craft", user?.office?.name);
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
				like('name', '%'+searchTerm+'%');
			}
		}
		for(Room room : roomResults) {
			HashMap<String, String> roomMap = new HashMap<String,String>();
			JSONObject roomObject = new JSONObject()
			roomObject.put("name", room.name);
			roomObject.put("location", user?.office?.name);
			searchResults.add(roomObject);
		}
		
		render searchResults as JSON
	}
}
