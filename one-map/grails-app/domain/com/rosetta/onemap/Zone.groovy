package com.rosetta.onemap;

import org.codehaus.groovy.grails.web.json.JSONObject

public class Zone {
	@Override
	public String toString() {
		return "Zone [name=" + name + ", color=" + color + "]";
	}
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Zone)) {
			return false;
		}
		Zone other = (Zone) obj;
		if (color == null) {
			if (other.color != null) {
				return false;
			}
		} else if (!color.equals(other.color)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
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
