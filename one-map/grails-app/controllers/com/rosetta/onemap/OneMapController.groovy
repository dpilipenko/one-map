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
	
	/* Public API begin */
	
	/**
	 * GET	/claimHotspot?hotspotID=h#
	 * @return {"success":boolean, "userinformation":{"name":String, "level":String, "craft":String, "phone":String, "email":String}}
	 */
	JSONObject claimHotspot() {
		boolean success
		User currentUser = springSecurityService.currentUser
		JSONObject res = new JSONObject()
		if (currentUser != null) {
			Hotspot hotspot = Hotspot.get(Long.parseLong(cleanseHotspotIdFromInput(params.hotspotID)))
			if (hotspot instanceof Room) {
				success = addUserToRoom(currentUser, (Room)hotspot)
			} else if (hotspot instanceof Desk){
				Desk desk = hotspot
				success = setUserToDesk(currentUser, desk)
			} else {
				success = false // Hotspot is not claimable
			}
			JSONObject userInformation = new JSONObject()
			printUser(userInformation, currentUser)
			res.put("userinformation", userInformation)
		}
		res.put("success", success)
		render res as JSON
	}
	
	/**
	 * GET	/closeWarRoom?hotspotID=h#
	 * @return {"success":boolean}
	 */
	JSONObject closeWarRoom() {
		boolean success
		Hotspot hotspot = Hotspot.get(Long.parseLong(cleanseHotspotIdFromInput(params.hotspotID)))
		if (hotspot instanceof Room) {
			success = removeProjectFromRoom((Room)hotspot)
		} else {
			success = false // Hotspot is not a Room
		}
		JSONObject res = new JSONObject()
		res.put("success", success)
		render res as JSON
	}

	/**
	 * GET	/createWarRoom?hotspotID=h#&projectName=STRING
	 * @return {"success":boolean}
	 */
	JSONObject createWarRoom() {
		boolean success
		String projectName = params.projectName
		Hotspot hotspot = Hotspot.get(Long.parseLong(cleanseHotspotIdFromInput(params.hotspotID)))
		if (hotspot instanceof Room) {
			success = setProjectToRoom((Room)hotspot, projectName)
		} else {
			success = false // Hotspot is not a Room
		}
		JSONObject res = new JSONObject()
		res.put("success", success)
		render res as JSON
	}
	
	/**
	 * GET /createZone?zoneName=STRING&zoneColor=STRING&hotspotIDs=h#
	 * Warning: `color` should be 808080 not #808080
	 */
	JSONObject createZone() {
		boolean success
		boolean validInput = (params.zoneName != null && params.zoneColor != null && params.hotspotID != null )
		if (validInput) {
			Zone zone = new Zone(name: params.zoneName, color: "#"+params.color).save(flush:true)
			String[] hotspotIDs = params.hotspotIDs.split(",")
			for (String hotspotID : hotspotIDs) {
				hotspotID = cleanseHotspotIdFromInput(params)
				if (hotspotID.isNumber()) {
					Hotspot hotspot = Hotspot.findById(hotspotID)
					success = updateZoneForHotspot(zone, hotspot)
				}
			}
			success = true
		} else {
			success = false
		}
		JSONObject res = new JSONObject()
		res.put("success", success)
		render res as JSON
	}
	
	/**
	 * GET /getAllZones
	 */
	JSONArray getAllZones() {
		JSONArray jsons = new JSONArray()
		for (Zone zone: Zone.all) {
			jsons.add(zone.toJSON())
		}
		render jsons as JSON
	}
	
	/**
	 * GET /getFreeZoneHotspots?floor=#
	 */
	JSONObject getFreeZoneHotspots() {
		JSONObject hotspots = new JSONObject()
		for (Hotspot h : Hotspot.findAllByZone(Zone.getFreeZone())) {
			if (h.floor.equals(params.floor))
				printHotspot(h, hotspots)
		}
		render hotspots as JSON
	}
	
	/**
	 * GET 	/getHotspot?hotspotID=h#
	 * @return
	 */
	JSONObject getHotspot () {
		User currentUser = springSecurityService.currentUser
		Hotspot hotspot = Hotspot.get(Long.parseLong(cleanseHotspotIdFromInput(params.hotspotID)));
		JSONObject res = new JSONObject()
		if (hotspot instanceof Desk) {
			printDeskHotspot((Desk)hotspot, res, currentUser)
		} else if (hotspot instanceof Room) {
			printRoomHotspot((Room)hotspot, res)
		} else {
			printUnclaimedHotspot(res, hotspot, currentUser)
		}
		render res as JSON
	}
	
	/**
	 * GET	/getHotspots?floor=#
	 * @return { ...,"hID":" SVG Path ",...  }
	 */
	JSONObject getHotspots () {
		JSONObject hotspots = new JSONObject()
		
		for (Hotspot h : Hotspot.findAllByFloor(params.floor)) {
			printHotspot(h, hotspots)
		}		
		render hotspots as JSON
	}

	/**
	 * GET	/runSearch?searchquery=STRING
	 * @return
	 */
	JSONArray runSearch() {
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
			userObject.put("type", "user")
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
			roomObject.put("type", "room")
			searchResults.add(roomObject);
		}
		
		def zoneResults = Zone.withCriteria {
			or {
				ilike('name', '%'+searchTerm+'%');
				ilike('color', '%'+searchTerm+'%');
			}
		}
		for (Zone zone : zoneResults) {
			
			Map<String, Integer> floorCounts = new HashMap<String, Integer>()
			// iterate through all associate hotspots and count how many per floor
			for (Hotspot h: Hotspot.findAllByZone(Zone.getFreeZone())) {
				Integer fCount = floorCounts.get(h.floor)
				if (fCount == null) {
					fCount = new Integer(1)
				} else {
					fCount = new Integer(fCount.intValue() + 1)
				}
				floorCounts.put(h.floor, fCount) 
			}
			for (String floorId : floorCounts.keySet()) {
				JSONObject zoneObject = new JSONObject()
				zoneObject.put("type", "zone");
				zoneObject.put("zoneId", zone.id)
				zoneObject.put("zoneName", zone.name)
				zoneObject.put("zoneColor", zone.color)
				zoneObject.put("floor", floorId)
				zoneObject.put("floorCount", floorCounts.get(floorId))
				searchResults.put(zoneObject)
			}
		}
		
		render searchResults as JSON
	}
	
	/**
	 * GET	/unclaimHotspot
	 * @return	{"success":boolean}
	 */
	JSONObject unclaimHotspot () {
		JSONObject res = new JSONObject()
		User currUser = springSecurityService.currentUser
		if (currUser == null) {
			res.put("success", false)
		} else {
			unclaimAllHotspotsForUser(currUser)
			res.put("success", true)
		}
		render res as JSON
	}
	
	/**
	 * GET /updateZone?hotspotID=#&zoneID=&
	 */
	JSONObject updateZone() {
		boolean success
		boolean validInput = (params.zoneID != null && params.zoneID.isNumber() && params.hotspotID != null && params.hotspotID.isNumber()) 
		if (validInput) {
			Hotspot hotspot = Hotspot.findById(cleanseHotspotIdFromInput(params.hotspotID))
			Zone zone = Zone.findById(params.zoneID)
			if (zone != null && hotspot != null) {
				success = updateZoneForHotspot(zone, hotspot)
			} else {
				success = false
			}
		} else {
			success = false
		}
		JSONObject res = new JSONObject()
		res.put("success", success)
		render res as JSON
	}
	/* Public API end */
	
	
	/* Helper Methods begin */
	
	/**
	 * Removes user from all current hotspots. Adds user to room.
	 * @param user	The user that is being moved
	 * @param room	The room to which the user is being moved
	 * @return	True if executed successfully
	 */
	private boolean addUserToRoom(User user, Room room) {
		unclaimAllHotspotsForUser(user)
		room.addUser(user)
		return true
	}
	
	/**
	 * Ensures that hotspotId does not begin with "h" prefix
	 * @param input	 The hotspotId value to be cleansed
	 * @return	The cleansed hotspotId value
	 */
	private String cleanseHotspotIdFromInput(String input) {
		String hotspotID = input
		if (hotspotID.startsWith("h")) { // we prefix IDs with 'h' in the UI
			hotspotID = hotspotID.substring(1, hotspotID.length())
		}
		return hotspotID
	}
	
	private void printDeskHotspot(Desk desk, JSONObject container, User currentUser) {
		container.put("zone", desk.zone)
		container.put("assignedSeatId", desk.assignedSeatId)
		container.put("zone", desk.zone)
		if (desk.user == null) {
			container.put("floor", hotspot.floor)
			container.put("type", hotspot.type)
			container.put("claimed", false)
			if (currentUser != null) {
				printUser(container, currentUser)
			}
		} else  if (desk.user != null) {
			printUser(container, desk.user)
			container.put("type", "desk")

			if (currentUser == null) {
				container.put("isOwn", false)
				container.put("claimed", true)
			} else {
				container.put("isOwn", desk.user.id == currentUser.id)
				if (desk.user.id != currentUser.id) {
					container.put("claimed", true)
				}  else {
					container.put("claimed", false)
				}
			}
		}
	}
	
	private void printHotspot(Hotspot hotspot, JSONObject container) {
		JSONObject properties = new JSONObject()
		properties.put("assignedSeatId", hotspot.assignedSeatId)
		properties.put("path", hotspot.polygon)
		properties.put("type", hotspot.type)
		properties.put("x", hotspot.x)
		properties.put("y", hotspot.y)
		properties.put("zone", hotspot.zone)
		properties.put("isVacant", hotspot.isVacant())
		container.put("h"+hotspot.id, properties)
	}
	
	private void printRoomHotspot(Room room, JSONObject container) {
		container.put("assignedSeatId", room.assignedSeatId)
		container.put("name", room.name)
		container.put("number", room.number)
		container.put("phone", room.phone)
		container.put("project", room.project)
		container.put("type", "room")
		container.put("zone", room.zone)
		JSONArray members = new JSONArray()
		for (User u : room.users) {
			JSONObject member = new JSONObject()
			printUser(member, u)
			members.add(member)
		}
		container.put("members", members)
	}
	
	private void printUnclaimedHotspot(JSONObject container, Hotspot hotspot, User currentUser) {
		container.put("floor", hotspot.floor)
		container.put("type", hotspot.type)
		container.put("claimed", false)
		container.put("zone", hotspot.zone)
		if (currentUser != null) {
			printUser(container, currentUser)
		}
	}

	private printUser(JSONObject container, User user) {
		container.put("name", user.firstName+" "+user.lastName)
		container.put("level", user.level)
		container.put("craft", user.craft)
		container.put("phone", user.phone)
		container.put("email", user.username)
	}
	
	/**
	 * Removes project information from room, making the room empty
	 * @param room The room to which the project information is cleared
	 * @return True if successfully executed
	 */
	private boolean removeProjectFromRoom(Room room) {
		boolean success
		if (room.hasProject()) {
			room.closeWarRoom()
			success = true
		} else {
			success = false // Room is not a WarRoom
		}
		return success
	}
	
	/**
	 * Sets project information to empty room
	 * @param room	The room to which the project is added to
	 * @param projectName Name of the project
	 * @return True if successfully executed
	 */
	private boolean setProjectToRoom(Room room, String projectName) {
		boolean success
		if ( ! room.hasProject() ) {
			room.initWarRoom(projectName)
			success = true
		} else {
			success = false // Room already has a Project
		}
		return success
	}
	
	/**
	 * Sets user to empty desk
	 * @param user	The user which is being set
	 * @param desk	The empty desk to which the user is added to 
	 * @return True if successful executed
	 */
	private boolean setUserToDesk(User user, Desk desk) {
		boolean success
		if (desk.isVacant()) {
			unclaimAllHotspotsForUser(user)
			desk.claim(user);
			success = true
		} else {
			success = false // Desk is not empty
		}
		return success
	}
	
	/**
	 * Updates the hotspot with given zone
	 * @param zone	The Zone to which the hotspot will be set
	 * @param hotspot	The hotspot which is being updated
	 * @return True if successfully executed
	 */
	private boolean updateZoneForHotspot(Zone zone, Hotspot hotspot) {
		boolean success
		if (hotspot != null) {
			hotspot.zone = zone
			hotspot.save(flush:true)
			success = true
		} else {
			success = false
		}
		return success
	}
	
	/**
	 * Removes User from all Hotspot types
	 * @param user	The user which is being removed
	 */
	private void unclaimAllHotspotsForUser(User user) {
		deskService.unclaimAllForUser(user)
		roomService.unclaimAllForUser(user)
	}
	/* Helper Methods end */
}
