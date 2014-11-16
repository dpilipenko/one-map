package com.rosetta.onemap;

import org.codehaus.groovy.grails.web.json.JSONObject


public class Zone {
	/* Class Methods */
	static Zone getFreeZone() {
		if (FREE_ZONE == null) {
			createFreeZone()
		}
		return FREE_ZONE
	}
	static boolean doesColorAlreadyExist(String color) {
		return (Zone.findByColor(color) != null)
	}

	/* Public Members */
	String name
	String color
	
	/* Public Methods */
	JSONObject toJSON() {
		JSONObject o = new JSONObject()
		o.put("id", this.id)
		o.put("name", this.name)
		o.put("color", this.color)
	}
	
	/* Private Methods */
	private static void createFreeZone() {
		FREE_ZONE = new Zone(name: FREE_ZONE_NAME, color: FREE_ZONE_COLOR)
		FREE_ZONE.save(flush:true)
	}
	/* Private Members */
	private static Zone FREE_ZONE;
	private static String FREE_ZONE_COLOR = "#888888";
	private static String FREE_ZONE_NAME = "Free Zone";
}
