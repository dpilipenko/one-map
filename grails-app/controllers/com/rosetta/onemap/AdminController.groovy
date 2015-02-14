package com.rosetta.onemap

import org.codehaus.groovy.grails.web.json.JSONArray;
import org.codehaus.groovy.grails.web.json.JSONObject
import org.grails.plugins.csv.CSVMapReader;
import org.grails.plugins.csv.CSVWriter

import au.com.bytecode.opencsv.CSVReader;
import com.rosetta.onemap.pintypes.Desk
import com.rosetta.onemap.pintypes.Room
import grails.plugin.springsecurity.SpringSecurityUtils

class AdminController {
	
	def userService
	def roomService
	def deskService
	
	def showAdmin() {
		def config = SpringSecurityUtils.securityConfig
		String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
		[postUrl: postUrl]
	}
	def showAdmin2() {
		def config = SpringSecurityUtils.securityConfig
		String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
		[postUrl: postUrl]
	}
	
	def seatChartExport() {
		def sw = new StringWriter()
		def b = new CSVWriter(sw, {
			"assignedSeatId" { it.val1 }
			"username" { it.val2 }
			"first name" { it.val3 }
			"last name" { it.val4 }
			"level" { it.val5 }
			"craft" { it.val6 }
			"phone" { it.val7 }
		})
		
		for (user in User.findAll()) {
			Hotspot hotspot = userService.getCurrentHotspot(user);
			
			String assignedSeatId = ""
			if (hotspot?.assignedSeatId != null) {
				assignedSeatId = hotspot?.assignedSeatId
			}
			
			
			b << [
				val1 : (String)assignedSeatId,
				val2 : (String)user.username,
				val3 : (String)user.firstName,
				val4 : (String)user.lastName,
				val5 : (String)user.level,
				val6 : (String)user.craft,
				val7 : (String)user.phone
				]
		}
		
		render text: b.writer.toString(), contentType: "text/csv", encoding: "UTF-8"
	}
	
	def seatChartImport() {
		InputStream istream = request.getFile("import").inputStream
		CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(istream)))
		
		new CSVMapReader(reader).each{ map ->
			User user = User.findByUsername(map["username"])
			if (user != null) {
				user.firstName = map["first name"]
				user.lastName = map["last name"]
				user.level = map["level"]
				user.craft = map["craft"]
				user.phone = map["phone"]
				user.save(true)
				
				String assignedSeatId = map["assignedSeatId"]
				if (assignedSeatId.isEmpty()) {
					userService.unclaimAllHotspots(user)	
				} else {
					userService.unclaimAllHotspots(user)
					Room room = Room.findByAssignedSeatId(assignedSeatId)
					if (room != null) {
						roomService.addUserToRoom(room, user)
					} else {
						Desk desk = Desk.findByAssignedSeatId(assignedSeatId)
						if (desk != null) {
							desk.claim(user)
						}
					}
				}
				
			}
		}
		render(view:"showAdmin2")
	}
}
