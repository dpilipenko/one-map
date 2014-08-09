package com.rosetta.onemap;

import org.codehaus.groovy.grails.web.json.JSONObject

public class Zone {
	String name
	String color
	
	JSONObject toJSON() {
		JSONObject o = new JSONObject()
		o.put("id", this.id)
		o.put("name", this.name)
		o.put("color", this.color)
	}
}
