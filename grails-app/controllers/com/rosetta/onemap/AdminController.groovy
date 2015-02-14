package com.rosetta.onemap

import org.codehaus.groovy.grails.web.json.JSONArray;
import org.codehaus.groovy.grails.web.json.JSONObject
import org.grails.plugins.csv.CSVWriter

import grails.plugin.springsecurity.SpringSecurityUtils

class AdminController {
	
	def userService
	
	def showAdmin() {
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
		render '{status: "pending"}'
	}
}
