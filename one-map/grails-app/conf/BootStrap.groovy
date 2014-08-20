import java.awt.Desktop;

import com.rosetta.onemap.Hotspot
import com.rosetta.onemap.User
import com.rosetta.onemap.Office
import com.rosetta.onemap.Zone
import com.rosetta.onemap.pintypes.Room
import com.rosetta.onemap.pintypes.Desk

class BootStrap {

    def init = { servletContext ->
		
		////
		//	Offices
		////
		
		def cloffice = Office.findByName("Cleveland")
		if (cloffice == null) { 
			cloffice = new Office(name: "Cleveland").save(flush:true);
		}
		def njoffice = Office.findByName("Princeton")
		if (njoffice == null) {
			njoffice = new Office(name: "Princeton").save(flush:true);
		}
		def nyhoffice = Office.findByName("New York - Hudson Street")
		if (nyhoffice == null) {
			nyhoffice = new Office(name: "New York - Hudson Street").save(flush:true);
		}
		def ny5office = Office.findByName("New York - 5th Avenue")
		if (ny5office == null) {
			ny5office = new Office(name: "New York - 5th Avenue").save(flush:true);
		}
		def chioffice = Office.findByName("Chicago")
		if (chioffice == null) {
			chioffice = new Office(name: "Chicago").save(flush:true);
		}
		def sfoffice = Office.findByName("San Francisco")
		if (sfoffice == null) {
			sfoffice = new Office(name: "San Francisco").save(flush:true);
		}
		def slooffice = Office.findByName("San Luis Obispo")
		if (slooffice == null) {
			slooffice = new Office(name: "San Luis Obispo").save(flush:true);
		}
		def laoffice = Office.findByName("Los Angeles")
		if (laoffice == null) {
			laoffice = new Office(name: "Los Angeles").save(flush:true);
		}
		def ldoffice = Office.findByName("London")
		if (ldoffice == null) {
			ldoffice = new Office(name: "London").save(flush:true);
		}
		
		////
		//	Users
		////
		def testUser = User.findByUsername("test@rosetta.com")
		if (testUser == null) {
			testUser = new User(firstName:"Tess", lastName:"Ting", username:"test@rosetta.com", password: "password", enabled: true,
			accountExpired: false, accountLocked: false, passwordExpired: false, office: cloffice, phone: "216-867-5309",
			level: "Associate", craft: "Quality Assurance").save(flush:true);
		}
		def dan = User.findByUsername("dan.padgett@rosetta.com")
		if (dan == null) {
			dan = new User(firstName: "Dan", lastName: "Padgett", username: "dan.padgett@rosetta.com", password: "passw0rd", enabled: true, 
			accountExpired: false, accountLocked: false, passwordExpired: false, office: cloffice, phone: "555-555-5555",
			level: "Senior Associate", craft: "Software Engineering").save(flush: true);
		}
		def dima = User.findByUsername("dmitriy.pilipenko@rosetta.com")
		if (dima == null) {
			dima = new User(firstName: "Dmitriy", lastName: "Pilipenko", username: "dmitriy.pilipenko@rosetta.com", password: "passw0rd", enabled: true, 
			accountExpired: false, accountLocked: false, passwordExpired: false, office: cloffice, phone: "555-555-5555",
			level: "Associate", craft: "Software Engineering").save(flush: true);
		}
		def liz = User.findByUsername("liz.judd@rosetta.com")
		if (liz == null) {
			liz = new User(firstName: "Liz", lastName: "Judd", username: "liz.judd@rosetta.com", password: "passw0rd", enabled: true, 
			accountExpired: false, accountLocked: false, passwordExpired: false, office: cloffice, phone: "555-555-5555",
			level: "Senior Associate", craft: "Creative Engineering").save(flush: true);
		}
		def dave = User.findByUsername("dave.fagan@rosetta.com")
		if (dave == null) {
			dave = new User(firstName: "Dave", lastName: "Fagan", username: "dave.fagan@rosetta.com", password: "passw0rd", enabled: true, 
			accountExpired: false, accountLocked: false, passwordExpired: false, office: cloffice, phone: "555-555-5555",
			level: "Senior Associate", craft: "Creative Engineering").save(flush: true);
		}
		def becky = User.findByUsername("becky.horvath@rosetta.com") 
		if (becky == null) {
			becky = new User(firstName: "Becky", lastName: "Horvath", username: "becky.horvath@rosetta.com", password: "passw0rd", enabled: true, 
			accountExpired: false, accountLocked: false, passwordExpired: false, office: cloffice, phone: "555-555-5555",
			level: "Senior Associate", craft: "CEO").save(flush: true);
		}

		////
		//  Zones
		////
		Zone freeZone = Zone.findByName("Free Zone")
		if (freeZone == null) {
			freeZone = Zone.getFreeZone().save(flush:true)
		}
		Zone ahaZone = Zone.findByName("AHA")
		if (ahaZone == null) {
			ahaZone = new Zone(name: "AHA", color: "#880088").save(flush:true)
		}
		Zone uidZone = Zone.findByName("UID Pod")
		if (uidZone == null) {
			uidZone = new Zone(name: "UID Pod", color: "#00FF00").save(flush:true)
		}
		
		////
		//	17th Floor
		////
		if (Room.findByAssignedSeatId("1728") == null) {
			new Room(name: "The Beatles", number: "1728", phone: "216.896.6666", office: cloffice, floor: "17", assignedSeatId: "1728", zone: freeZone, polygon: "M344.099,446.051 354.658,491.652 431.578,473.412 420.658,427.931z", x: "388", y: "460").save(flush: true);
		}
		if (Room.findByAssignedSeatId("1726") == null) {
			new Room(name: "Johnny Cash", number: "1726", phone: "216.896.7345", office: cloffice, floor: "17", assignedSeatId: "1726", zone: freeZone, polygon: "M452.818,387.131 504.658,375.492 525.14,451.435 470.099,464.403z", x: "489", y: "420").save(flush: true);
		}
		if (Room.findByAssignedSeatId("1723") == null) {
			new Room(name: "Chuck Berry", number: "1723", phone: "216.896.2324", office: cloffice, floor: "17", assignedSeatId: "1723", zone: ahaZone, polygon: "M467.818,303.372 498.118,350.886 446.578,363.011 445.019,367.931 405.898,372.612 385.139,324.011z", x: "442", y: "338").save(flush: true);
		}
		Room elvis = Room.findByAssignedSeatId("1715");
		if (elvis == null) {
			elvis = new Room(name: "Elvis Presley", number: "1715", phone: "216.896.1041", office: cloffice, floor: "17", assignedSeatId: "1715", zone:uidZone, polygon: "M280.979,172.452 279.178,227.532 231.778,231.011 199.229,224.863 191.938,221.652 178.802,213.057 181.858,198.372 186.658,175.211 187.138,173.292z", x: "230", y: "202").save(flush: true);
		}
		elvis.addToUsers(dan)
		elvis.save(flush:true);
		
		////
		//	11th Floor
		////
		
		new Room(name: "y", number: "1139", phone: "000.000.0000", office: cloffice, floor: "11", assignedSeatId: "1139", zone: freeZone, x:0, y:0, polygon: "M318.12,452.72 327.12,490.76 274.08,503.359 261.36,450.56 274.44,447.44 285.24,445.16 287.838,454.939 294.72,453.56z").save(flush: true);
		new Room(name: "y", number: "1121", phone: "000.000.0000", office: cloffice, floor: "11", assignedSeatId: "1121", zone: freeZone, x:0, y:0, polygon: "M222,115.28 265.2,114.68 265.2,169.16 222,169.16 226.13,143.72z").save(flush: true);
		new Room(name: "Nirvana", number: "1127", phone: "000.000.0000", office: cloffice, floor: "11", assignedSeatId: "1127", zone: freeZone, x:0, y:0, polygon: "M219.38,284.96 265.18,284.96 266.64,349.28 244.08,353.84 235.56,356.6z").save(flush: true);
		new Room(name: "Beastie Boys", number: "1141", phone: "000.000.0000", office: cloffice, floor: "11", assignedSeatId: "1141", zone: freeZone, x:0, y:0, polygon: "M209.16,461.36l13.08,54.066l51.3-12.067l-14.46-62.039l2.76-8.641l-9.66-3.239c0,0-20.6-5.111-21.9-4.2L215.4,459.8L209.16,461.36z").save(flush: true);

		new Desk(office: cloffice, floor: "11", assignedSeatId: "1117", zone: freeZone, x:0, y:0, polygon: "M221.88,5.96 265.20,5.96 265.20,30.32 221.88,30.32z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1118", zone: freeZone, x:0, y:0, polygon: "M221.88,31.52 265.2,31.52 265.2,58.28 222.96,58.28z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1119", zone: freeZone, x:0, y:0, polygon: "M223,59.49 265.2,59.72 265.2,86.24 226.32,86.12z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1120", zone: freeZone, x:0, y:0, polygon: "M226.32,87.86 265.2,87.32 265.2,113.84 222.3,113.55z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1122", zone: freeZone, x:0, y:0, polygon: "M222,171.2 265.2,170.66 265.2,225.8 222,225.8 226.299,198.44z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1123", zone: freeZone, x:0, y:0, polygon: "M222,227 265.2,227.72 265.2,253.04 226.68,254z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1124", zone: freeZone, x:0, y:0, polygon: "M226.68,254.96 265.073,254.84 265.2,280.881 222,280.28z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1138", zone: freeZone, x:0, y:0, polygon: "M319.2,452.48 328.2,490.52 352.8,484.598 342.36,442.04z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1136", zone: freeZone, x:0, y:0, polygon: "M370.44,440.48 379.44,478.4 404.52,472.4 394.561,428.96z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1137", zone: freeZone, x:0, y:0, polygon: "M369.526,440.55 378.615,478.76 353.641,484.598 343.306,441.409z").save(flush: true);
		if (Desk.findByAssignedSeatId("1125A") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125A", zone: freeZone, x:0, y:0, polygon: "M193.343,41.877l5.924-9.535c0,0-6.758-3.906-12.08-0.909c-5.323,2.998-4.978,10.238-4.978,10.238L193.343,41.877z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125B") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125B", zone: freeZone, x:0, y:0, polygon: "M193.343,41.877l5.58,9.665c0,0,7.307-4.029,7.191-10.139c-0.116-6.108-6.847-9.06-6.847-9.06L193.343,41.877z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125C") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125C", zone: freeZone, x:0, y:0, polygon: "M182.209,41.672l11.134,0.205l5.58,9.665c0,0-8.086,3.187-11.949,0.67C183.113,49.695,182.209,41.672,182.209,41.672z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125D") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125D", zone: freeZone, x:0, y:0, polygon: "M191.348,80.392l9.81-5.457c0,0-4.009-6.697-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L191.348,80.392z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125I") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125I", zone: freeZone, x:0, y:0, polygon: "M191.348,80.392l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.246-1.584-11.246L191.348,80.392z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125E") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125E", zone: freeZone, x:0, y:0, polygon: "M181.715,74.804l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C178.608,82.256,181.715,74.804,181.715,74.804z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125J") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125J", zone: freeZone, x:0, y:0, polygon: "M191.348,136.4l9.81-5.457c0,0-4.009-6.697-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L191.348,136.4z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125L") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125L", zone: freeZone, x:0, y:0, polygon: "M191.348,136.4l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.246-1.584-11.246L191.348,136.4z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125K") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125K", zone: freeZone, x:0, y:0, polygon: "M181.715,130.812l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C178.608,138.265,181.715,130.812,181.715,130.812z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125G") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125G", zone: freeZone, x:0, y:0, polygon: "M191.357,103.22l-9.81,5.457c0,0,4.009,6.697,10.118,6.663c6.109-0.035,9.325-6.531,9.325-6.531L191.357,103.22z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125F") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125F", zone: freeZone, x:0, y:0, polygon: "M191.357,103.22l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L191.357,103.22z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125H") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125H", zone: freeZone, x:0, y:0, polygon: "M200.989,108.808l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.218C204.096,101.355,200.989,108.808,200.989,108.808z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125O") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125O", zone: freeZone, x:0, y:0, polygon: "M191.348,172.766l9.81-5.457c0,0-4.009-6.697-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L191.348,172.766z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125P") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125P", zone: freeZone, x:0, y:0, polygon: "M191.348,172.766l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.245-1.584-11.245L191.348,172.766z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125N") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125N", zone: freeZone, x:0, y:0, polygon: "M181.715,167.178l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C178.608,174.63,181.715,167.178,181.715,167.178z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125R") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125R", zone: freeZone, x:0, y:0, polygon: "M191.357,195.594l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L191.357,195.594z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125M") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125M", zone: freeZone, x:0, y:0, polygon: "M191.357,195.594l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L191.357,195.594z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125Q") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Q", zone: freeZone, x:0, y:0, polygon: "M200.989,201.182l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C204.096,193.729,200.989,201.182,200.989,201.182z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125S") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125S", zone: freeZone, x:0, y:0, polygon: "M191.348,228.893l9.81-5.456c0,0-4.009-6.696-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L191.348,228.893z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125T") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125T", zone: freeZone, x:0, y:0, polygon: "M191.348,228.893l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.245-1.584-11.245L191.348,228.893z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125X") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125X", zone: freeZone, x:0, y:0, polygon: "M181.715,223.305l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C178.608,230.758,181.715,223.305,181.715,223.305z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125V") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125V", zone: freeZone, x:0, y:0, polygon: "M191.357,251.721l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L191.357,251.721z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125W") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125W", zone: freeZone, x:0, y:0, polygon: "M191.357,251.721l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L191.357,251.721z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125U") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125U", zone: freeZone, x:0, y:0, polygon: "M200.989,257.309l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C204.096,249.856,200.989,257.309,200.989,257.309z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132D") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132D", zone: freeZone, x:0, y:0, polygon: "M327.754,402.229l8.535,9.246c0,0,6.83-5.636,5.4-12.091c-1.426-6.456-9.666-8.507-9.666-8.507L327.754,402.229z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132C") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132C", zone: freeZone, x:0, y:0, polygon: "M327.754,402.229l-12.855,2.517c0,0,1.865,8.834,8.753,10.744c6.888,1.909,12.638-4.015,12.638-4.015L327.754,402.229z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132E") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132E", zone: freeZone, x:0, y:0, polygon: "M332.023,390.878l-4.27,11.352l-12.855,2.517c0,0-0.635-9.354,3.585-12.48C322.704,389.142,332.023,390.878,332.023,390.878z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132A") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132A", zone: freeZone, x:0, y:0, polygon: "M300.404,409.214l-8.669-9.318c0,0-6.934,5.681-5.483,12.188c1.451,6.508,9.817,8.574,9.817,8.574L300.404,409.214z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132F") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132F", zone: freeZone, x:0, y:0, polygon: "M300.404,409.214l13.055-2.534c0,0-1.894-8.905-8.89-10.831c-6.992-1.924-12.834,4.047-12.834,4.047L300.404,409.214z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132B") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132B", zone: freeZone, x:0, y:0, polygon: "M296.069,420.657l4.335-11.443l13.055-2.534c0,0,0.646,9.428-3.64,12.579C305.532,422.409,296.069,420.657,296.069,420.657z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1133D") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133D", zone: freeZone, x:0, y:0, polygon: "M410.608,381.38l8.535,9.246c0,0,6.83-5.636,5.4-12.091c-1.426-6.456-9.666-8.507-9.666-8.507L410.608,381.38z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133E") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133E", zone: freeZone, x:0, y:0, polygon: "M410.608,381.38l-12.855,2.517c0,0,1.865,8.834,8.753,10.744c6.888,1.909,12.638-4.015,12.638-4.015L410.608,381.38z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133C") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133C", zone: freeZone, x:0, y:0, polygon: "M414.878,370.028l-4.27,11.352l-12.855,2.517c0,0-0.635-9.354,3.585-12.48C405.559,368.292,414.878,370.028,414.878,370.028z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133A") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133A", zone: freeZone, x:0, y:0, polygon: "M383.259,388.364l-8.669-9.318c0,0-6.934,5.681-5.483,12.188c1.451,6.508,9.817,8.574,9.817,8.574L383.259,388.364z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133B") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133B", zone: freeZone, x:0, y:0, polygon: "M383.259,388.364l13.055-2.534c0,0-1.894-8.905-8.89-10.831c-6.992-1.924-12.834,4.047-12.834,4.047L383.259,388.364z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133F") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133F", zone: freeZone, x:0, y:0, polygon: "M378.924,399.808l4.335-11.443l13.055-2.534c0,0,0.646,9.428-3.64,12.579C388.387,401.56,378.924,399.808,378.924,399.808z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112C") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112C", zone: freeZone, x:30, y:140, polygon:"M35.345,143.126l0.466-11.215c0,0-7.805-0.076-10.964,5.152c-3.158,5.229,0.704,11.363,0.704,11.363L35.345,143.126z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112D") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112D", zone: freeZone, x:41, y:140, polygon:"M35.345,143.126l9.612,5.668c0,0,4.379-7.103,1.272-12.366c-3.106-5.26-10.418-4.518-10.418-4.518L35.345,143.126z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112B") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112B", zone: freeZone, x:35, y:149, polygon:"M25.551,148.425l9.794-5.299l9.612,5.668c0,0-5.471,6.752-10.072,6.463C30.286,154.965,25.551,148.425,25.551,148.425z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112F") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112F", zone: freeZone, x:60, y:158, polygon:"M54.821,155.034l-0.466,11.216c0,0,7.805,0.076,10.964-5.152c3.158-5.229-0.704-11.363-0.704-11.363L54.821,155.034z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112A") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112A", zone: freeZone, x:49, y:158, polygon:"M54.821,155.034l-9.613-5.668c0,0-4.379,7.103-1.272,12.365c3.107,5.261,10.419,4.519,10.419,4.519L54.821,155.034z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112E") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112E", zone: freeZone, x:55, y:149, polygon:"M64.615,149.734l-9.794,5.299l-9.613-5.668c0,0,5.471-6.753,10.073-6.462C59.881,143.194,64.615,149.734,64.615,149.734z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125Z1") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Z1", zone: freeZone, x:0, y:0, polygon:"M154.867,157.886l9.81-5.457c0,0-4.009-6.697-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L154.867,157.886z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125Z2") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Z2", zone: freeZone, x:0, y:0, polygon:"M154.867,157.886l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.245-1.584-11.245L154.867,157.886z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125Z") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Z", zone: freeZone, x:0, y:0, polygon:"M145.234,152.298l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C142.127,159.75,145.234,152.298,145.234,152.298z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112H") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112H", zone: freeZone, x:59, y:193, polygon:"M59.236,187l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L59.236,187z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112I") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112I", zone: freeZone, x:53, y:184, polygon:"M59.236,187l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L59.236,187z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112G") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112G", zone: freeZone, x:65, y:184, polygon:"M68.868,192.588L59.236,187l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C71.975,185.136,68.868,192.588,68.868,192.588z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112L") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112L", zone: freeZone, x:0, y:0, polygon:"M107.905,187.375l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L107.905,187.375z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112Q") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112Q", zone: freeZone, x:0, y:0, polygon:"M107.905,187.375l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L107.905,187.375z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112P") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112P", zone: freeZone, x:0, y:0, polygon:"M117.537,192.963l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C120.644,185.511,117.537,192.963,117.537,192.963z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125Z4") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Z4", zone: freeZone, x:0, y:0, polygon:"M155.996,184.375l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L155.996,184.375z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125Y") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Y", zone: freeZone, x:0, y:0, polygon:"M155.996,184.375l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L155.996,184.375z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125Z3") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Z3", zone: freeZone, x:0, y:0, polygon:"M165.628,189.963l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C168.735,182.511,165.628,189.963,165.628,189.963z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112R") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112R", zone: freeZone, x:0, y:0, polygon:"M88.252,199.241l9.627-5.773c0,0-4.225-6.562-10.329-6.33c-6.104,0.234-9.108,6.831-9.108,6.831L88.252,199.241z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112K") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112K", zone: freeZone, x:0, y:0, polygon:"M88.252,199.241l0.545,11.146c0,0,8.341-0.244,11.031-5.73c2.688-5.487-1.949-11.189-1.949-11.189L88.252,199.241z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112J") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112J", zone: freeZone, x:0, y:0, polygon:"M78.443,193.969l9.809,5.272l0.545,11.146c0,0-8.648-0.862-10.934-4.866C75.581,201.518,78.443,193.969,78.443,193.969z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112O") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112O", zone: freeZone, x:0, y:0, polygon:"M127.425,199.241l9.627-5.773c0,0-4.225-6.562-10.329-6.33c-6.104,0.234-9.108,6.831-9.108,6.831L127.425,199.241z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112N") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112N", zone: freeZone, x:0, y:0, polygon:"M127.425,199.241l0.545,11.146c0,0,8.341-0.244,11.031-5.73c2.688-5.487-1.949-11.189-1.949-11.189L127.425,199.241z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112M") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112M", zone: freeZone, x:0, y:0, polygon:"M117.616,193.969l9.809,5.272l0.545,11.146c0,0-8.648-0.862-10.934-4.866C114.753,201.518,117.616,193.969,117.616,193.969z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1132N") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132N", zone: freeZone, x:0, y:0, polygon:"M324.351,352.96l-8.865,8.628c0,0,5.905,6.278,12.286,4.602c6.382-1.68,8.083-9.68,8.083-9.68L324.351,352.96z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1132I") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132I", zone: freeZone, x:0, y:0, polygon:"M324.351,352.96l-3.045-12.207c0,0-8.734,2.211-10.354,8.908c-1.615,6.696,4.534,11.927,4.534,11.927L324.351,352.96z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1132J") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132J", zone: freeZone, x:0, y:0, polygon:"M335.854,356.51l-11.504-3.55l-3.045-12.207c0,0,9.304-1.06,12.6,2.84C337.199,347.49,335.854,356.51,335.854,356.51z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132H") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132H", zone: freeZone, x:0, y:0, polygon:"M302.921,372.381l8.832-9.167c0,0-6.266-6.237-12.766-4.292c-6.501,1.948-7.996,10.217-7.996,10.217L302.921,372.381z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132O") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132O", zone: freeZone, x:0, y:0, polygon:"M302.921,372.381l3.516,12.423c0,0,8.9-2.575,10.352-9.507c1.447-6.931-5.035-12.083-5.035-12.083L302.921,372.381z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132G") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132G", zone: freeZone, x:0, y:0, polygon:"M290.991,369.139l11.93,3.242l3.516,12.423c0,0-9.522,1.412-13.032-2.474C289.898,378.443,290.991,369.139,290.991,369.139z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132K") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132K", zone: freeZone, x:0, y:0, polygon:"M350.573,361.006l8.547-9.024c0,0-6.064-6.141-12.355-4.227c-6.291,1.919-7.738,10.059-7.738,10.059L350.573,361.006z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132L") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132L", zone: freeZone, x:0, y:0, polygon:"M350.573,361.006l3.402,12.231c0,0,8.615-2.536,10.019-9.359c1.4-6.823-4.874-11.896-4.874-11.896L350.573,361.006z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132M") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132M", zone: freeZone, x:0, y:0, polygon:"M339.026,357.813l11.547,3.192l3.402,12.231c0,0-9.217,1.391-12.612-2.436C337.969,366.975,339.026,357.813,339.026,357.813z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133N") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133N", zone: freeZone, x:0, y:0, polygon:"M419.556,328.663l-8.865,8.628c0,0,5.905,6.278,12.286,4.602c6.382-1.68,8.083-9.68,8.083-9.68L419.556,328.663z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133I") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133I", zone: freeZone, x:0, y:0, polygon:"M419.556,328.663l-3.045-12.207c0,0-8.734,2.211-10.354,8.908c-1.615,6.696,4.534,11.927,4.534,11.927L419.556,328.663z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133J") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133J", zone: freeZone, x:0, y:0, polygon:"M431.06,332.213l-11.504-3.55l-3.045-12.207c0,0,9.304-1.06,12.6,2.84C432.404,323.193,431.06,332.213,431.06,332.213z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133H") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133H", zone: freeZone, x:0, y:0, polygon:"M398.126,348.084l8.832-9.167c0,0-6.266-6.237-12.766-4.292c-6.501,1.948-7.996,10.217-7.996,10.217L398.126,348.084z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133O") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133O", zone: freeZone, x:0, y:0, polygon:"M398.126,348.084l3.516,12.423c0,0,8.9-2.575,10.352-9.507c1.447-6.931-5.035-12.083-5.035-12.083L398.126,348.084z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133G") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133G", zone: freeZone, x:0, y:0, polygon:"M386.196,344.842l11.93,3.242l3.516,12.423c0,0-9.522,1.412-13.032-2.474C385.104,354.146,386.196,344.842,386.196,344.842z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133K") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133K", zone: freeZone, x:0, y:0, polygon:"M445.778,336.709l8.547-9.024c0,0-6.064-6.141-12.355-4.227c-6.291,1.919-7.738,10.059-7.738,10.059L445.778,336.709z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1133L") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133L", zone: freeZone, x:0, y:0, polygon:"M445.778,336.709l3.402,12.231c0,0,8.615-2.536,10.019-9.359c1.4-6.823-4.874-11.896-4.874-11.896L445.778,336.709z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1133M") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133M", zone: freeZone, x:0, y:0, polygon:"M434.231,333.517l11.547,3.192l3.402,12.231c0,0-9.217,1.391-12.612-2.436C433.174,342.678,434.231,333.517,434.231,333.517z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1112Y") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112Y", zone: freeZone, x:0, y:0, polygon:"M107.915,153.747l9.81-5.457c0,0-4.009-6.696-10.118-6.662c-6.109,0.035-9.325,6.531-9.325,6.531L107.915,153.747z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1112U") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112U", zone: freeZone, x:0, y:0, polygon:"M107.915,153.747l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.246-1.584-11.246L107.915,153.747z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1112T") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112T", zone: freeZone, x:0, y:0, polygon:"M98.282,148.159l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.219C95.175,155.611,98.282,148.159,98.282,148.159z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1112V") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112V", zone: freeZone, x:0, y:0, polygon:"M127.567,141.881l-9.627,5.773c0,0,4.225,6.562,10.329,6.33c6.104-0.234,9.108-6.831,9.108-6.831L127.567,141.881z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1112X") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112X", zone: freeZone, x:0, y:0, polygon:"M127.567,141.881l-0.545-11.146c0,0-8.341,0.244-11.031,5.73c-2.688,5.487,1.949,11.189,1.949,11.189L127.567,141.881z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1112W") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112W", zone: freeZone, x:0, y:0, polygon:"M137.376,147.153l-9.809-5.272l-0.545-11.146c0,0,8.648,0.862,10.934,4.866C140.239,139.604,137.376,147.153,137.376,147.153z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1112S") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112S", zone: freeZone, x:0, y:0, polygon:"M88.394,141.881l-9.627,5.773c0,0,4.225,6.562,10.329,6.33c6.104-0.234,9.108-6.831,9.108-6.831L88.394,141.881z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1112Z1") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112Z1", zone: freeZone, x:0, y:0, polygon:"M88.394,141.881l-0.545-11.146c0,0-8.341,0.244-11.031,5.73c-2.688,5.487,1.949,11.189,1.949,11.189L88.394,141.881z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1112Z") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112Z", zone: freeZone, x:0, y:0, polygon:"M98.203,147.153l-9.809-5.272l-0.545-11.146c0,0,8.648,0.862,10.934,4.866C101.066,139.604,98.203,147.153,98.203,147.153z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135D") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135D", zone: freeZone, x:0, y:0, polygon:"M416.678,421.211l3.873,10.108l11.495,1.038l6.996-8.332c0,0-7.624-3.794-11.864-4.429C422.938,418.961,416.678,421.211,416.678,421.211z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135J") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135J", zone: freeZone, x:0, y:0, polygon:"M469.117,407.87l3.873,10.108l11.495,1.038l6.996-8.332c0,0-7.624-3.794-11.864-4.429C475.377,405.62,469.117,407.87,469.117,407.87z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135C") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135C", zone: freeZone, x:0, y:0, polygon:"M415.14,441.098l6.36-8.76l-5.4-10.2l-10.8-1.319c0,0,0.979,8.459,2.76,12.359S415.14,441.098,415.14,441.098z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135H") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135H", zone: freeZone, x:0, y:0, polygon:"M482.94,439.34l6.36-8.76l-5.4-10.2l-10.8-1.319c0,0,0.979,8.459,2.76,12.359S482.94,439.34,482.94,439.34z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135B") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135B", zone: freeZone, x:0, y:0, polygon:"M430.56,451.237l6.36-8.76l-5.4-10.2l-10.8-1.319c0,0,0.979,8.459,2.76,12.359S430.56,451.237,430.56,451.237z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135I") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135I", zone: freeZone, x:0, y:0, polygon:"M466.94,426.859l6.36-8.76l-5.4-10.2l-10.8-1.319c0,0,0.979,8.459,2.76,12.359S466.94,426.859,466.94,426.859z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135G") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135G", zone: freeZone, x:0, y:0, polygon:"M504.901,441.44l-4.325-9.923l-11.53-0.517l-6.612,8.641c0,0,7.787,3.444,12.052,3.887S504.901,441.44,504.901,441.44z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135A") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135A", zone: freeZone, x:0, y:0, polygon:"M452.834,453.299l-4.325-9.923l-11.53-0.517l-6.612,8.641c0,0,7.787,3.444,12.052,3.887S452.834,453.299,452.834,453.299z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135F") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135F", zone: freeZone, x:0, y:0, polygon:"M455.121,433.408l-6.828,8.4l4.836,10.48l10.711,1.905c0,0-0.516-8.5-2.081-12.491C460.194,437.711,455.121,433.408,455.121,433.408z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135K") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135K", zone: freeZone, x:0, y:0, polygon:"M490.935,410.946l-6.828,8.4l4.836,10.48l10.711,1.905c0,0-0.516-8.5-2.081-12.491C496.008,415.249,490.935,410.946,490.935,410.946z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1135E") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135E", zone: freeZone, x:0, y:0, polygon:"M438.795,423.545l-6.828,8.4l4.836,10.48l10.711,1.905c0,0-0.516-8.5-2.081-12.491C443.868,427.848,438.795,423.545,438.795,423.545z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1135L") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135L", zone: freeZone, x:0, y:0, polygon:"M507.255,421.928l-6.828,8.4l4.836,10.48l10.711,1.905c0,0-0.516-8.5-2.081-12.491C512.328,426.23,507.255,421.928,507.255,421.928z").save(flush: true);
		}

		////
		//	12th Floor
		////
		// desks
		/* new Desk(office: cloffice, floor: "12", assignedSeatId: "A", zone: freeZone, x:0, y:0, polygon: "M187.989,67.63l-10.955-6.057c0,0-4.288,7.195-0.765,12.744c3.524,5.555,11.736,5.047,11.736,5.047L187.989,67.63z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "B", zone: freeZone, x:0, y:0, polygon: "M187.989,67.63l10.848-6.066c0,0-4.734-7.621-11.667-7.381c-6.933,0.239-10.135,7.39-10.135,7.39L187.989,67.63z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "C", zone: freeZone, x:0, y:0, polygon: "M188.006,79.364l-0.017-11.734l10.848-6.066c0,0,3.799,8.464,1.029,12.579C197.095,78.261,188.006,79.364,188.006,79.364z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "D", zone: freeZone, x:0, y:0, polygon: "M187.676,92.21l-0.216-12.676c0,0-8.601-0.131-11.737,5.764c-3.142,5.898,1.515,12.852,1.515,12.852L187.676,92.21z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "E", zone: freeZone, x:0, y:0, polygon: "M187.676,92.21l10.955,6.461c0,0,4.361-8.004,0.598-13.97c-3.763-5.965-11.769-5.167-11.769-5.167L187.676,92.21z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "F", zone: freeZone, x:0, y:0, polygon: "M177.238,98.149l10.438-5.939l10.955,6.461c0,0-5.59,7.608-10.672,7.255C182.874,105.572,177.238,98.149,177.238,98.149z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "G", zone: freeZone, x:0, y:0, polygon: "M187.83,130.255l-11.02-6.469c0,0-4.491,7.573-1.014,13.458c3.477,5.891,11.831,5.408,11.831,5.408L187.83,130.255z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "H", zone: freeZone, x:0, y:0, polygon: "M187.83,130.255l11.136-6.337c0,0-4.668-8.081-11.718-7.873c-7.049,0.208-10.438,7.741-10.438,7.741L187.83,130.255z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "I", zone: freeZone, x:0, y:0, polygon: "M187.627,142.651l0.203-12.396l11.136-6.337c0,0,3.703,8.966,0.812,13.295C196.884,141.544,187.627,142.651,187.627,142.651z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "J", zone: freeZone, x:0, y:0, polygon: "M187.85,155l-11.076,6.372c0,0,4.356,7.651,11.191,7.544c6.84-0.104,10.556-7.602,10.556-7.602L187.85,155z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "K", zone: freeZone, x:0, y:0, polygon: "M187.85,155l0.007-12.813c0,0-9.332,0.055-12.642,6.284c-3.309,6.228,1.559,12.901,1.559,12.901L187.85,155z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "L", zone: freeZone, x:0, y:0, polygon: "M198.521,161.313L187.85,155l0.007-12.813c0,0,9.623,1.222,11.954,5.877C202.142,152.722,198.521,161.313,198.521,161.313z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "M", zone: freeZone, x:0, y:0, polygon: "M187.931,192.215l-0.022,12.367c0,0,8.804,0.054,12.13-5.728c3.33-5.782-1.304-12.525-1.304-12.525L187.931,192.215z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "N", zone: freeZone, x:0, y:0, polygon: "M187.931,192.215l-11.091-6.207c0,0-4.62,7.847-0.882,13.637c3.738,5.787,11.951,4.938,11.951,4.938L187.931,192.215z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "O", zone: freeZone, x:0, y:0, polygon: "M198.735,186.329l-10.804,5.886l-11.091-6.207c0,0,5.87-7.475,11.068-7.174C193.107,179.135,198.735,186.329,198.735,186.329z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "P", zone: freeZone, x:0, y:0, polygon: "M187.876,217.485l10.547,6.325c0,0,4.39-7.738,1.096-13.619c-3.293-5.887-11.336-5.237-11.336-5.237L187.876,217.485z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "Q", zone: freeZone, x:0, y:0, polygon: "M187.876,217.485l-10.772,6.62c0,0,4.42,8.076,11.207,7.731c6.785-0.348,10.112-8.026,10.112-8.026L187.876,217.485z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "R", zone: freeZone, x:0, y:0, polygon: "M188.183,204.954l-0.306,12.531l-10.772,6.62c0,0-3.484-8.989-0.663-13.422C179.264,206.251,188.183,204.954,188.183,204.954z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "S", zone: freeZone, x:0, y:0, polygon: "M188.136,254.3l-0.023,12.606c0,0,9.028,0.055,12.44-5.838c3.415-5.895-1.337-12.769-1.337-12.769L188.136,254.3z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "T", zone: freeZone, x:0, y:0, polygon: "M188.136,254.3l-11.375-6.327c0,0-4.738,7.998-0.905,13.9c3.834,5.899,12.256,5.033,12.256,5.033L188.136,254.3z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "U", zone: freeZone, x:0, y:0, polygon: "M199.216,248.3l-11.08,6l-11.375-6.327c0,0,6.02-7.62,11.351-7.313C193.444,240.966,199.216,248.3,199.216,248.3z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "V", zone: freeZone, x:0, y:0, polygon: "M151.681,147.489l-5.888,11.123c0,0,7.835,4.26,13.548,0.646c5.717-3.612,4.779-11.899,4.779-11.899L151.681,147.489z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "W", zone: freeZone, x:0, y:0, polygon: "M151.681,147.489l-6.958-10.893c0,0-7.848,4.853-7.258,11.854c0.592,6.998,8.328,10.162,8.328,10.162L151.681,147.489z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "X", zone: freeZone, x:0, y:0, polygon: "M164.12,147.359l-12.439,0.13l-6.958-10.893c0,0,8.788-3.921,13.286-1.165C162.508,138.191,164.12,147.359,164.12,147.359z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "Y", zone: freeZone, x:0, y:0, polygon: "M134.043,179.387l-0.025,13.147c0,0,9.762,0.058,13.45-6.089c3.692-6.147-1.446-13.316-1.446-13.316L134.043,179.387z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "Z", zone: freeZone, x:0, y:0, polygon: "M134.043,179.387l-12.298-6.599c0,0-5.123,8.342-0.978,14.497c4.145,6.152,13.251,5.249,13.251,5.249L134.043,179.387z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AA", zone: freeZone, x:0, y:0, polygon: "M146.022,173.129l-11.979,6.257l-12.298-6.599c0,0,6.509-7.947,12.272-7.627C139.782,165.481,146.022,173.129,146.022,173.129z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AB", zone: freeZone, x:0, y:0, polygon: "M81.647,195.415l-0.025,12.999c0,0,9.763,0.056,13.452-6.021c3.693-6.077-1.446-13.165-1.446-13.165L81.647,195.415z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AC", zone: freeZone, x:0, y:0, polygon: "M81.647,195.415l-12.3-6.523c0,0-5.124,8.247-0.979,14.332c4.146,6.083,13.253,5.19,13.253,5.19L81.647,195.415z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AD", zone: freeZone, x:0, y:0, polygon: "M93.628,189.229l-11.981,6.187l-12.3-6.523c0,0,6.51-7.857,12.273-7.542C87.387,181.667,93.628,189.229,93.628,189.229z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AE", zone: freeZone, x:0, y:0, polygon: "M80.719,148.842l-11.884,6.006c0,0,4.905,8.168,12.327,8.428c7.424,0.262,11.288-7.31,11.288-7.31L80.719,148.842z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AF", zone: freeZone, x:0, y:0, polygon: "M80.719,148.842l-0.288-13.282c0,0-10.132-0.449-13.584,5.829c-3.45,6.278,1.988,13.459,1.988,13.459L80.719,148.842z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AG", zone: freeZone, x:0, y:0, polygon: "M92.449,155.966l-11.73-7.124l-0.288-13.282c0,0,10.479,1.786,13.117,6.74C96.185,147.255,92.449,155.966,92.449,155.966z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AH", zone: freeZone, x:0, y:0, polygon: "M303.735,361.855l-9.443,8.627c0,0,6.317,6.609,13.127,5.052c6.812-1.556,8.605-9.728,8.605-9.728L303.735,361.855z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AI", zone: freeZone, x:0, y:0, polygon: "M303.735,361.855l-3.281-12.618c0,0-9.317,2.035-11.03,8.873c-1.711,6.835,4.868,12.372,4.868,12.372L303.735,361.855z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AJ", zone: freeZone, x:0, y:0, polygon: "M316.024,365.807l-12.289-3.951l-3.281-12.618c0,0,9.938-0.843,13.463,3.248C317.443,356.577,316.024,365.807,316.024,365.807z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AK", zone: freeZone, x:0, y:0, polygon: "M327.53,369.719l-12.023-3.722c0,0-2.437,8.747,2.286,13.788c4.722,5.045,12.535,2.449,12.535,2.449L327.53,369.719z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AL", zone: freeZone, x:0, y:0, polygon: "M327.53,369.719l9.041-9.187c0,0-6.38-6.946-13.025-4.956c-6.641,1.99-8.039,10.421-8.039,10.421L327.53,369.719z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AM", zone: freeZone, x:0, y:0, polygon: "M330.328,382.234l-2.798-12.516l9.041-9.187c0,0,5.682,8.081,3.98,13.166C338.851,378.784,330.328,382.234,330.328,382.234z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AN", zone: freeZone, x:0, y:0, polygon: "M292.996,409.831l-9.443,8.627c0,0,6.317,6.609,13.127,5.052c6.812-1.556,8.605-9.728,8.605-9.728L292.996,409.831z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AO", zone: freeZone, x:0, y:0, polygon: "M305.285,413.782l-12.289-3.951l-3.281-12.618c0,0,9.938-0.843,13.463,3.248C306.704,404.553,305.285,413.782,305.285,413.782z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AP", zone: freeZone, x:0, y:0, polygon: "M335.366,399.523l-9.288,8.476c0,0,6.293,6.535,13.02,5.021c6.729-1.512,8.453-9.562,8.453-9.562L335.366,399.523z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AQ", zone: freeZone, x:0, y:0, polygon: "M335.366,399.523l-3.326-12.451c0,0-9.206,1.977-10.858,8.714c-1.647,6.732,4.896,12.213,4.896,12.213L335.366,399.523z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AR", zone: freeZone, x:0, y:0, polygon: "M347.551,403.459l-12.185-3.936l-3.326-12.451c0,0,9.827-0.799,13.342,3.246C348.896,394.363,347.551,403.459,347.551,403.459z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AS", zone: freeZone, x:0, y:0, polygon: "M316.412,416.194l2.397,12.696c0,0,8.712-1.96,10.87-8.653c2.159-6.695-3.739-12.555-3.739-12.555L316.412,416.194z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AT", zone: freeZone, x:0, y:0, polygon: "M316.412,416.194l-12.179-3.83c0,0-3.032,9.108,1.797,14.195c4.827,5.082,12.779,2.331,12.779,2.331L316.412,416.194z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AU", zone: freeZone, x:0, y:0, polygon: "M325.94,407.683l-9.528,8.512l-12.179-3.83c0,0,4.341-9.015,9.539-9.896C318.97,401.587,325.94,407.683,325.94,407.683z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AV", zone: freeZone, x:0, y:0, polygon: "M357.898,350.166l-9.308,8.471c0,0,6.299,6.539,13.039,5.028c6.744-1.508,8.473-9.558,8.473-9.558L357.898,350.166z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AW", zone: freeZone, x:0, y:0, polygon: "M357.898,350.166l-3.326-12.452c0,0-9.223,1.971-10.881,8.707c-1.655,6.731,4.899,12.216,4.899,12.216L357.898,350.166z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AX", zone: freeZone, x:0, y:0, polygon: "M370.103,354.107l-12.204-3.941l-3.326-12.452c0,0,9.848-0.794,13.365,3.252C371.456,345.014,370.103,354.107,370.103,354.107z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AY", zone: freeZone, x:0, y:0, polygon: "M381.922,357.415l-11.975-3.8c0,0-2.547,8.558,2.113,13.564c4.657,5.013,12.502,2.559,12.502,2.559L381.922,357.415z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "AZ", zone: freeZone, x:0, y:0, polygon: "M381.922,357.415l9.153-8.908c0,0-6.29-6.898-12.961-5.025c-6.663,1.873-8.167,10.134-8.167,10.134L381.922,357.415z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BA", zone: freeZone, x:0, y:0, polygon: "M384.562,369.738l-2.641-12.323l9.153-8.908c0,0,5.581,8.005,3.817,12.978C393.127,366.457,384.562,369.738,384.562,369.738z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BB", zone: freeZone, x:0, y:0, polygon: "M400.346,339.902l-8.968,8.913c0,0,6.312,6.416,12.897,4.652c6.59-1.759,8.126-9.985,8.126-9.985L400.346,339.902z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BC", zone: freeZone, x:0, y:0, polygon: "M400.346,339.902l-3.514-12.516c0,0-9.012,2.313-10.506,9.204c-1.49,6.886,5.052,12.225,5.052,12.225L400.346,339.902z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BD", zone: freeZone, x:0, y:0, polygon: "M412.401,343.482l-12.056-3.58l-3.514-12.516c0,0,9.65-1.144,13.185,2.841C413.55,334.213,412.401,343.482,412.401,343.482z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BE", zone: freeZone, x:0, y:0, polygon: "M370.628,404.019l-11.951-3.944c0,0-2.601,8.699,2.027,13.827c4.627,5.133,12.487,2.683,12.487,2.683L370.628,404.019z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BF", zone: freeZone, x:0, y:0, polygon: "M370.628,404.019l9.211-9.016c0,0-6.248-7.064-12.93-5.198c-6.677,1.865-8.232,10.27-8.232,10.27L370.628,404.019z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BG", zone: freeZone, x:0, y:0, polygon: "M373.191,416.584l-2.563-12.565l9.211-9.016c0,0,5.53,8.187,3.734,13.238C381.777,413.295,373.191,416.584,373.191,416.584z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BH", zone: freeZone, x:0, y:0, polygon: "M389.07,386.666l-9.308,8.471c0,0,6.299,6.539,13.039,5.028c6.744-1.508,8.473-9.558,8.473-9.558L389.07,386.666z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BI", zone: freeZone, x:0, y:0, polygon: "M389.07,386.666l-3.326-12.452c0,0-9.223,1.971-10.881,8.707c-1.655,6.731,4.899,12.216,4.899,12.216L389.07,386.666z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BJ", zone: freeZone, x:0, y:0, polygon: "M401.274,390.607l-12.204-3.941l-3.326-12.452c0,0,9.848-0.794,13.365,3.252C402.628,381.514,401.274,390.607,401.274,390.607z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BK", zone: freeZone, x:0, y:0, polygon: "M413.052,393.394l2.617,12.458c0,0,8.708-2.189,10.763-8.858c2.058-6.673-3.959-12.28-3.959-12.28L413.052,393.394z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BL", zone: freeZone, x:0, y:0, polygon: "M413.052,393.394l-12.285-3.421c0,0-2.893,9.077,2.038,13.954c4.928,4.873,12.864,1.925,12.864,1.925L413.052,393.394z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BM", zone: freeZone, x:0, y:0, polygon: "M422.473,384.713l-9.421,8.681l-12.285-3.421c0,0,4.207-9.025,9.408-10.047C415.377,378.904,422.473,384.713,422.473,384.713z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BN", zone: freeZone, x:0, y:0, polygon: "M403.17,436.224l-9.041,8.201c0,0,6.119,6.331,12.666,4.868c6.551-1.46,8.23-9.254,8.23-9.254L403.17,436.224z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BO", zone: freeZone, x:0, y:0, polygon: "M403.17,436.224l-3.23-12.057c0,0-8.959,1.908-10.569,8.431c-1.607,6.518,4.759,11.827,4.759,11.827L403.17,436.224z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BP", zone: freeZone, x:0, y:0, polygon: "M415.025,440.039l-11.855-3.815l-3.23-12.057c0,0,9.565-0.769,12.982,3.148C416.34,431.234,415.025,440.039,415.025,440.039z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BQ", zone: freeZone, x:0, y:0, polygon: "M426.926,443.354l2.201,12.007c0,0,8.606-1.861,10.815-8.196c2.213-6.336-3.523-11.872-3.523-11.872L426.926,443.354z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BR", zone: freeZone, x:0, y:0, polygon: "M426.926,443.354l-11.948-3.612c0,0-3.103,8.619,1.591,13.427c4.69,4.802,12.559,2.192,12.559,2.192L426.926,443.354z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BS", zone: freeZone, x:0, y:0, polygon: "M436.419,435.293l-9.493,8.062l-11.948-3.612c0,0,4.391-8.532,9.521-9.371C429.63,429.533,436.419,435.293,436.419,435.293z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BT", zone: freeZone, x:0, y:0, polygon: "M373.721,455.848l2.732,12.061c0,0,8.688-2.235,10.678-8.736c1.995-6.505-4.073-11.872-4.073-11.872L373.721,455.848z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BU", zone: freeZone, x:0, y:0, polygon: "M373.721,455.848l-12.313-3.167c0,0-2.808,8.85,2.168,13.524c4.973,4.667,12.878,1.703,12.878,1.703L373.721,455.848z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BV", zone: freeZone, x:0, y:0, polygon: "M383.058,447.3l-9.337,8.548l-12.313-3.167c0,0,4.123-8.815,9.312-9.875C375.912,441.749,383.058,447.3,383.058,447.3z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BW", zone: freeZone, x:0, y:0, polygon: "M349.779,449.034l11.818,3.617c0,0,2.372-8.651-2.28-13.606c-4.652-4.962-12.319-2.359-12.319-2.359L349.779,449.034z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BX", zone: freeZone, x:0, y:0, polygon: "M349.779,449.034l-8.857,9.116c0,0,6.284,6.831,12.809,4.833c6.516-1.999,7.867-10.332,7.867-10.332L349.779,449.034z").save(flush: true);*/
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BY", zone: freeZone, x:0, y:0, polygon: "M346.998,436.686l2.781,12.349l-8.857,9.116c0,0-5.601-7.956-3.945-12.986C338.638,440.132,346.998,436.686,346.998,436.686z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "BZ", zone: freeZone, x:0, y:0, polygon: "M331.307,465.932l2.682,12.138c0,0,8.524-2.25,10.478-8.792c1.957-6.547-3.997-11.948-3.997-11.948L331.307,465.932z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "CA", zone: freeZone, x:0, y:0, polygon: "M331.307,465.932l-12.082-3.187c0,0-2.756,8.905,2.127,13.61c4.879,4.697,12.637,1.714,12.637,1.714L331.307,465.932z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "CB", zone: freeZone, x:0, y:0, polygon: "M340.469,457.329l-9.162,8.603l-12.082-3.187c0,0,4.045-8.872,9.137-9.938C333.457,451.743,340.469,457.329,340.469,457.329z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "CC", zone: freeZone, x:0, y:0, polygon: "M49.426,120.142l-12.901-0.394c0,0-3.891,9.215,0.131,13.675c4.023,4.456,12.855,0.68,12.855,0.68L49.426,120.142z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "CD", zone: freeZone, x:0, y:0, polygon: "M80.432,120.83l-12.901-0.394c0,0-2.655,8.354,1.367,12.814c4.023,4.456,11.62,1.541,11.62,1.541L80.432,120.83z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "CE", zone: freeZone, x:0, y:0, polygon: "M81.847,223.878l0.233-14.118c0,0-15.6-0.251-21.24,3.99c-5.308,3.991-8.339,10.285-8.339,10.285L81.847,223.878z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "CF", zone: freeZone, x:0, y:0, polygon: "M117.823,120.163L93.36,120.42c0,0,1.343,9.012,5.52,14.7c3.932,5.353,17.859,9.63,17.859,9.63L117.823,120.163z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "CG", zone: freeZone, x:0, y:0, polygon: "M81.54,120.6l-1.022,14.19c0,0,6.6,1.583,10.922-2.178c4.32-3.765,1.476-11.53,1.476-11.53L81.54,120.6z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "CH", zone: freeZone, x:0, y:0, polygon: "M49.48,120.093l0.062,14.677c0,0,6.6,1.583,10.922-2.178c4.32-3.765-0.264-12.472-0.264-12.472L49.48,120.093z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "CI", zone: freeZone, x:0, y:0, polygon: "M81.96,223.766l21.995,0.502c0,0,0.63-9.547-5.855-12.348c-6.218-2.686-16.02-2.16-16.02-2.16L81.96,223.766z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "CJ", zone: freeZone, x:0, y:0, polygon: "M134.104,207.872l22.837,0.114c0,0,0.832-9.94-5.853-12.732c-6.409-2.678-16.6-1.949-16.6-1.949L134.104,207.872z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "CK", zone: freeZone, x:0, y:0, polygon: "M134.557,208.242l0.233-14.118c0,0-13.56-2.865-19.2,1.376c-5.308,3.991-3.75,12.82-3.75,12.82L134.557,208.242z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "CL", zone: freeZone, x:0, y:0, polygon: "M291.6,440.88l3.36,12l11.525,3.965C306.485,456.845,319.26,431.985,291.6,440.88z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "CM", zone: freeZone, x:0, y:0, polygon: "M306.567,456.129l-8.458,9.151l2.666,11.894C300.775,477.174,328.642,475.021,306.567,456.129z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "CN", zone: freeZone, x:0, y:0, polygon: "M300.883,477.872l3.348,12.003l11.521,3.977C315.752,493.852,328.552,469.005,300.883,477.872z").save(flush: true);

		// Rooms
		new Room(name: "x", number: "1", phone: "000.000.0000", office: cloffice, floor: "12", assignedSeatId: "ZA", zone: freeZone, polygon: "M221.123,7.642 264,8.012 263.878,31.21 220.611,31.895z").save(flush: true);
		new Room(name: "x", number: "1", phone: "000.000.0000", office: cloffice, floor: "12", assignedSeatId: "ZB", zone: freeZone, polygon: "M220.8,32.64 220.8,59.4 260.88,59.4 264.12,54.84 263.88,33.12z").save(flush: true);
		new Room(name: "x", number: "1", phone: "000.000.0000", office: cloffice, floor: "12", assignedSeatId: "ZC", zone: freeZone, polygon: "M220.92,60.6 225,86.52 264.12,86.88 264.12,65.16 260.64,64.5 260.88,60.6z").save(flush: true);
		new Room(name: "x", number: "1", phone: "000.000.0000", office: cloffice, floor: "12", assignedSeatId: "ZD", zone: freeZone, polygon: "M225.72,88.56 220.92,115.32 260.88,115.32 260.88,111.72 264.12,111.72 264.12,88.56z").save(flush: true);
		new Room(name: "x", number: "1", phone: "000.000.0000", office: cloffice, floor: "12", assignedSeatId: "ZE", zone: freeZone, polygon: "M220.92,116.52 225.72,143.28 263.88,142.8 264.12,120.12 261.24,120.12 260.88,116.52z").save(flush: true);
		new Room(name: "x", number: "1", phone: "000.000.0000", office: cloffice, floor: "12", assignedSeatId: "ZF", zone: freeZone, polygon: "M225.6,145.44 220.92,171.12 260.88,171.12 260.88,167.28 264.12,167.28 263.52,144.48z").save(flush: true);
		new Room(name: "x", number: "1", phone: "000.000.0000", office: cloffice, floor: "12", assignedSeatId: "ZG", zone: freeZone, polygon: "M220.92,172.32 225.24,199.68 219.72,227.039 261,227.04 261.24,222.36 264.12,222.36 264.12,175.56 261.08,175.5 260.88,172.32z").save(flush: true);
		new Room(name: "x", number: "1", phone: "000.000.0000", office: cloffice, floor: "12", assignedSeatId: "ZH", zone: freeZone, polygon: "M220.92,228.24 225.24,255.6 220.2,281.76 261.12,281.52 261.24,278.04 264.12,278.04 264.12,232.92 260.64,233.16 261,228.24z").save(flush: true);
		new Room(name: "x", number: "1", phone: "000.000.0000", office: cloffice, floor: "12", assignedSeatId: "ZI", zone: freeZone, polygon: "M197.88,326.28 197.76,287.28 215.64,287.28 215.64,282.72 254.76,282.72 254.76,287.28 264.12,287.28 264.12,337.32z").save(flush: true);
		new Room(name: "x", number: "1", phone: "000.000.0000", office: cloffice, floor: "12", assignedSeatId: "ZJ", zone: freeZone, polygon: "M8.76,121.75 8.76,157.32 32.76,157.32 33.36,119.4z").save(flush: true);
		new Room(name: "x", number: "1", phone: "000.000.0000", office: cloffice, floor: "12", assignedSeatId: "ZK", zone: freeZone, polygon: "M250.68,430.8 229.2,426.48 214.56,463.2 225.72,511.56 229.32,510.72 230.16,514.56 272.04,504.6 272.76,500.28 257.88,442.44 260.28,434.4z").save(flush: true);
		new Room(name: "x", number: "1", phone: "000.000.0000", office: cloffice, floor: "12", assignedSeatId: "ZL", zone: freeZone, polygon: "M454.68,456 442.32,399 505.2,384.12 539.4,435.96 537.24,437.4 539.4,440.64 459,460.56 458.34,457.808 455.64,458.16z").save(flush: true);
		new Room(name: "x", number: "1", phone: "000.000.0000", office: cloffice, floor: "12", assignedSeatId: "ZM", zone: freeZone, polygon: "M428.04,344.64 430.92,358.68 447.72,356.16 455.521,367.92 489.36,360 483.84,351.6 481.33,353.25 478.561,348.36 480.72,346.92 464.88,322.8z").save(flush: true);
		new Room(name: "x", number: "1", phone: "000.000.0000", office: cloffice, floor: "12", assignedSeatId: "ZN", zone: freeZone, polygon: "M424.32,314.597 423.36,310.561 449.64,304.2 451.561,307.258 454.2,307.013 464.28,322.18 434.085,339.925 427.92,341.138 420.96,316.309z").save(flush: true);




		////
		//	14th Floor
		////
	
		new Room(name: "The Grateful Dead", number: "1458", phone: "000.000.0000", office: cloffice, floor: "14", assignedSeatId: "1458", zone: freeZone, x:0, y:0, polygon: "M229.618,426.077l-14.734,36.836l4.954,21.148l-4.7,1.08l6.478,27.311l8.129-2.032l0.889,3.811l42.489-10.161l-14.798-61.986l2.848-8.639c0,0-13.654-5.16-15.841-5.716C241.699,426.806,229.618,426.077,229.618,426.077z").save(flush: true);
		new Room(name: "James Brown", number: "1433", phone: "000.000.0000", office: cloffice, floor: "14", assignedSeatId: "1433", zone: freeZone, x:0, y:0, polygon: "M216.027,282.162 255.15,282.162 255.15,286.86 264.549,286.86 264.549,336.272 206.628,326.619 197.863,325.221 197.418,286.605 216.408,286.86z").save(flush: true);

		new Room(name: "y", number: "4", phone: "000.000.0000", office: cloffice, floor: "14", assignedSeatId: "1416", zone: freeZone, x:0, y:0, polygon: "M9.489,117.095 32.988,117.095 32.988,172.287 9.489,171.2z").save(flush: true);
		new Room(name: "y", number: "4", phone: "000.000.0000", office: cloffice, floor: "14", assignedSeatId: "1418", zone: freeZone, x:0, y:0, polygon: "M59.6,115.761 83.607,115.888 83.607,154.63 59.6,154.63z").save(flush: true);
		new Room(name: "y", number: "4", phone: "000.000.0000", office: cloffice, floor: "14", assignedSeatId: "1419", zone: freeZone, x:0, y:0, polygon: "M84.645,115.761 110.853,115.508 110.344,154.885 84.645,154.63z").save(flush: true);
		new Room(name: "y", number: "4", phone: "000.000.0000", office: cloffice, floor: "14", assignedSeatId: "1417", zone: freeZone, x:0, y:0, polygon: "M34.576,115.952 58.583,116.079 58.583,154.821 34.576,154.821z").save(flush: true);
		new Room(name: "y", number: "4", phone: "000.000.0000", office: cloffice, floor: "14", assignedSeatId: "1413", zone: freeZone, x:0, y:0, polygon: "M84.432,186.259 111.488,186.259 110.344,227.097 84.645,226.842z").save(flush: true);
		new Room(name: "y", number: "4", phone: "000.000.0000", office: cloffice, floor: "14", assignedSeatId: "1414", zone: freeZone, x:0, y:0, polygon: "M59.79,187.402 83.289,187.529 83.289,226.736 59.282,227.097z").save(flush: true);
		new Room(name: "y", number: "4", phone: "000.000.0000", office: cloffice, floor: "14", assignedSeatId: "1431", zone: freeZone, x:0, y:0, polygon: "M220.092,171.143 261.247,171.779 261.247,174.954 264.549,174.954 264.549,221.825 261.375,221.825 261.375,226.525 221.362,226.525 225.681,199.088z").save(flush: true);
		new Room(name: "y", number: "4", phone: "000.000.0000", office: cloffice, floor: "14", assignedSeatId: "1432", zone: freeZone, x:0, y:0, polygon: "M221.362,227.669 260.739,227.669 261.375,232.368 264.549,232.368 264.296,253.327 226.062,253.835z").save(flush: true);
		new Room(name: "y", number: "4", phone: "000.000.0000", office: cloffice, floor: "14", assignedSeatId: "1451", zone: freeZone, x:0, y:0, polygon: "M420.438,428.507 429.424,467.359 407.83,472.44 407.386,468.963 404.083,469.726 394.811,430.094z").save(flush: true);
		new Room(name: "y", number: "4", phone: "000.000.0000", office: cloffice, floor: "14", assignedSeatId: "1448", zone: freeZone, x:0, y:0, polygon: "M540.061,440.431 535.742,442.335 511.481,448.053 510.719,445.003 502.843,446.909 503.479,449.957 482.139,454.91 473.12,413.756 471.86,391.786 505.893,383.779 511.481,392.289 509.486,393.94 512.369,398.612 514.783,396.862 540.061,435.73 537.953,437.095z").save(flush: true);
		new Room(name: "y", number: "4", phone: "000.000.0000", office: cloffice, floor: "14", assignedSeatId: "1453", zone: freeZone, x:0, y:0, polygon: "M368.846,440.756 377.833,479.608 356.237,484.689 355.794,481.213 352.49,481.975 343.219,442.344z").save(flush: true);
		new Room(name: "y", number: "4", phone: "000.000.0000", office: cloffice, floor: "14", assignedSeatId: "1454", zone: freeZone, x:0, y:0, polygon: "M341.78,442.972 351.053,482.602 347.876,483.365 348.639,486.413 303.419,497.082 302.975,493.466 300.751,494.033 292.114,458.85 295.416,458.341 293.893,453.641 317.522,453.804z").save(flush: true);
		new Room(name: "y", number: "4", phone: "000.000.0000", office: cloffice, floor: "14", assignedSeatId: "1444", zone: freeZone, x:0, y:0, polygon: "M395.128,316.838 403.893,354.309 432.98,353.167 445.684,352.531 479.979,344.401 453.114,302.95z").save(flush: true);

		new Desk(office: cloffice, floor: "14", assignedSeatId: "1426", zone: freeZone, x:0, y:0, polygon: "M220.092,31.419 264.549,31.925 264.549,58.221 221.235,58.729z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1427", zone: freeZone, x:0, y:0, polygon: "M220.981,59.493 264.319,59.616 264.231,86.419 226.062,86.674z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1425", zone: freeZone, x:0, y:0, polygon: "M220.497,5.761 265.098,6.14 264.971,29.894 219.965,30.594z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1428", zone: freeZone, x:0, y:0, polygon: "M226.062,87.944 264.549,87.944 264.549,111.062 261.501,111.062 261.247,114.619 221.362,114.619z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1429", zone: freeZone, x:0, y:0, polygon: "M221.362,115.888 261.247,115.888 261.247,120.017 264.549,119.827 264.549,142.183 226.062,142.69z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1430", zone: freeZone, x:0, y:0, polygon: "M226.062,143.834 264.549,143.834 264.549,166.189 261.247,166.698 261.247,169.873 221.362,170.635z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1439", zone: freeZone, x:0, y:0, polygon: "M225.299,255.232 264.549,254.979 264.549,277.588 261.629,277.588 261.501,281.019 221.362,281.019z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455O", zone: freeZone, x:0, y:0, polygon: "M415.471,388.419l4.047-11.003c0,0-7.598-2.431-12.355,1.812c-4.76,4.246-2.947,11.503-2.947,11.503L415.471,388.419z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1440", zone: freeZone, x:0, y:0, polygon: "M302.147,378.571 293.511,340.718 316.501,335.383 326.282,377.682z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1442", zone: freeZone, x:0, y:0, polygon: "M352.025,366.405 343.388,328.551 364.77,323.951 365.659,327.763 368.962,326.999 378.616,365.535z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1443", zone: freeZone, x:0, y:0, polygon: "M370.739,326.626 373.407,325.983 372.519,322.046 393.985,317.092 402.749,354.562 379.632,364.739z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1441", zone: freeZone, x:0, y:0, polygon: "M318.408,338.939 323.995,337.732 323.392,333.668 342.627,328.736 351.239,367.393 327.426,377.301z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1449", zone: freeZone, x:0, y:0, polygon: "M471.946,416.741 481.122,455.292 460.175,460.136 458.894,457.197 455.591,457.96 446.318,418.328z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1450", zone: freeZone, x:0, y:0, polygon: "M444.952,418.825 454.447,458.214 451.396,458.947 451.908,462.152 430.377,467.782 428.597,460.044 421.615,429.215z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1452", zone: freeZone, x:0, y:0, polygon: "M393.095,430.939 402.591,470.328 399.539,471.062 400.051,474.267 378.52,479.897 376.739,472.158 369.758,441.329z").save(flush: true);
		// THERE ISN'T A LETTER FOR THIS DESK BUT IT'S ON THE FLOORPLAN
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455?", zone: freeZone, x:0, y:0, polygon: "M415.471,388.419l7.572,8.532c0,0,6.55-5.735,5.199-11.901c-1.351-6.163-8.725-7.634-8.725-7.634L415.471,388.419z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455N", zone: freeZone, x:0, y:0, polygon: "M404.215,390.73l11.256-2.312l7.572,8.532c0,0-7.504,5.06-11.901,3.384C406.742,398.66,404.215,390.73,404.215,390.73z").save(flush: true);
		// THIS ONE DOESN'T HAVE A DESK ON THE FLOORPLAN BUT HAS A LETTER
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455P", zone: freeZone, x:0, y:0, polygon: "M393.847,393.364l11.227-2.77c0,0-1.309-7.315-7.063-8.77c-5.761-1.456-11.166,3.909-11.166,3.909L393.847,393.364z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455M", zone: freeZone, x:0, y:0, polygon: "M393.847,393.364l-3.925,10.604c0,0,7.833,2.062,12.509-2.346c4.676-4.409,2.643-11.027,2.643-11.027L393.847,393.364z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455Q", zone: freeZone, x:0, y:0, polygon: "M386.844,385.734l7.003,7.63l-3.925,10.604c0,0-5.813-0.177-7.432-7.487S386.844,385.734,386.844,385.734z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455J", zone: freeZone, x:0, y:0, polygon: "M361.663,401.408l3.437-10.749c0,0-7.033-2.152-11.292,2.076c-4.265,4.232-2.419,11.205-2.419,11.205L361.663,401.408z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455I", zone: freeZone, x:0, y:0, polygon: "M361.663,401.408l7.165,8.053c0,0,5.867-5.719,4.475-11.646c-1.395-5.927-8.203-7.155-8.203-7.155L361.663,401.408z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455H", zone: freeZone, x:0, y:0, polygon: "M351.389,403.94l10.274-2.532l7.165,8.053c0,0-6.764,5.092-10.842,3.587C353.907,411.542,351.389,403.94,351.389,403.94z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455L", zone: freeZone, x:0, y:0, polygon: "M340.299,406.596l-7.888-8.071c0,0-5.196,5.206-3.455,10.95c1.742,5.75,8.767,7.386,8.767,7.386L340.299,406.596z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455K", zone: freeZone, x:0, y:0, polygon: "M340.299,406.596l10.47-2.559c0,0-2.305-7.862-8.194-9.409c-5.891-1.543-10.163,3.896-10.163,3.896L340.299,406.596z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455G", zone: freeZone, x:0, y:0, polygon: "M337.723,416.86l2.576-10.265l10.47-2.559c0,0,1.331,8.36-1.909,11.26C345.618,418.195,337.723,416.86,337.723,416.86z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455D", zone: freeZone, x:0, y:0, polygon: "M307.213,415.289l3.001-10.878c0,0-7.114-1.868-11.2,2.529c-4.09,4.4-1.965,11.293-1.965,11.293L307.213,415.289z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455C", zone: freeZone, x:0, y:0, polygon: "M307.213,415.289l7.482,7.757c0,0,5.631-5.95,4.002-11.816c-1.631-5.866-8.483-6.818-8.483-6.818L307.213,415.289z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455B", zone: freeZone, x:0, y:0, polygon: "M297.049,418.233l10.164-2.944l7.482,7.757c0,0-6.553,5.36-10.689,4.021C299.871,425.728,297.049,418.233,297.049,418.233z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455F", zone: freeZone, x:0, y:0, polygon: "M285.585,420.689l-7.888-8.071c0,0-5.196,5.206-3.454,10.95c1.742,5.75,8.767,7.386,8.767,7.386L285.585,420.689z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455E", zone: freeZone, x:0, y:0, polygon: "M285.585,420.689l10.471-2.559c0,0-2.306-7.862-8.194-9.409c-5.891-1.543-10.164,3.896-10.164,3.896L285.585,420.689z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455A", zone: freeZone, x:0, y:0, polygon: "M283.01,430.954l2.575-10.265l10.471-2.559c0,0,1.33,8.36-1.909,11.26C290.905,432.289,283.01,430.954,283.01,430.954z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424X", zone: freeZone, x:0, y:0, polygon: "M187.751,258.465l0.571-10.791c0,0-7.351-0.269-10.384,4.689c-3.04,4.961,0.529,10.963,0.529,10.963L187.751,258.465z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424Y", zone: freeZone, x:0, y:0, polygon: "M187.751,258.465l8.986,5.697c0,0,4.208-6.73,1.345-11.876c-2.865-5.145-9.76-4.612-9.76-4.612L187.751,258.465z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424U", zone: freeZone, x:0, y:0, polygon: "M187.653,201.908l9.36,5.887c0,0,4.352-5.794,1.6-10.737c-2.751-4.947-10.13-5.086-10.13-5.086L187.653,201.908z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424S", zone: freeZone, x:0, y:0, polygon: "M187.653,201.908l-10.136,4.387c0,0,3.685,6.779,9.902,7.057c6.215,0.275,9.594-5.557,9.594-5.557L187.653,201.908z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424R", zone: freeZone, x:0, y:0, polygon: "M188.482,191.972l-0.829,9.937l-10.136,4.387c0,0-2.788-7.429-0.016-10.723C180.275,192.277,188.482,191.972,188.482,191.972z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412J", zone: freeZone, x:0, y:0, polygon: "M132.903,181.563l9.36,5.886c0,0,4.352-5.795,1.6-10.736c-2.751-4.947-10.131-5.086-10.131-5.086L132.903,181.563z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412F", zone: freeZone, x:0, y:0, polygon: "M132.903,181.563l-10.136,4.387c0,0,3.685,6.779,9.902,7.056c6.216,0.276,9.594-5.557,9.594-5.557L132.903,181.563z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412E", zone: freeZone, x:0, y:0, polygon: "M133.732,171.627l-0.829,9.937l-10.136,4.387c0,0-2.788-7.43-0.016-10.723C125.525,171.933,133.732,171.627,133.732,171.627z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424Z", zone: freeZone, x:0, y:0, polygon: "M187.985,235.664l0.35,10.8c0,0,7.346-0.358,9.948-5.559c2.605-5.201-1.462-10.877-1.462-10.877L187.985,235.664z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424W", zone: freeZone, x:0, y:0, polygon: "M187.985,235.664l-9.439-4.912c0,0-3.62,7.064-0.329,11.948c3.292,4.88,10.118,3.764,10.118,3.764L187.985,235.664z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424V", zone: freeZone, x:0, y:0, polygon: "M196.821,230.028l-8.836,5.636l-9.439-4.912c0,0,4.673-6.792,9.019-6.771C191.911,224.007,196.821,230.028,196.821,230.028z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424T", zone: freeZone, x:0, y:0, polygon: "M188.091,179.914l0.35,10.8c0,0,7.346-0.359,9.947-5.56c2.606-5.201-1.461-10.876-1.461-10.876L188.091,179.914z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424Q", zone: freeZone, x:0, y:0, polygon: "M188.091,179.914l-9.44-4.912c0,0-3.619,7.064-0.328,11.947c3.292,4.881,10.118,3.765,10.118,3.765L188.091,179.914z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424P", zone: freeZone, x:0, y:0, polygon: "M196.927,174.278l-8.836,5.636l-9.44-4.912c0,0,4.673-6.793,9.019-6.77C192.017,168.256,196.927,174.278,196.927,174.278z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412H", zone: freeZone, x:0, y:0, polygon: "M151.699,193.251l0.35,10.8c0,0,7.346-0.359,9.947-5.559c2.606-5.202-1.461-10.877-1.461-10.877L151.699,193.251z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412G", zone: freeZone, x:0, y:0, polygon: "M151.699,193.251l-9.44-4.913c0,0-3.619,7.065-0.329,11.948c3.292,4.882,10.119,3.765,10.119,3.765L151.699,193.251z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412I", zone: freeZone, x:0, y:0, polygon: "M160.536,187.615l-8.836,5.636l-9.44-4.913c0,0,4.673-6.793,9.019-6.77C155.625,181.592,160.536,187.615,160.536,187.615z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412K", zone: freeZone, x:0, y:0, polygon: "M132.482,159.209l0.351,10.8c0,0,7.346-0.36,9.947-5.559c2.605-5.202-1.462-10.877-1.462-10.877L132.482,159.209z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412D", zone: freeZone, x:0, y:0, polygon: "M132.482,159.209l-9.44-4.913c0,0-3.619,7.064-0.328,11.948c3.292,4.881,10.119,3.765,10.119,3.765L132.482,159.209z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412C", zone: freeZone, x:0, y:0, polygon: "M141.318,153.573l-8.836,5.636l-9.44-4.913c0,0,4.673-6.793,9.019-6.771C136.408,147.55,141.318,153.573,141.318,153.573z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412A", zone: freeZone, x:0, y:0, polygon: "M151.44,148.061l9.36,6.169c0,0,4.351-6.073,1.6-11.251c-2.751-5.184-10.13-5.329-10.13-5.329L151.44,148.061z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412L", zone: freeZone, x:0, y:0, polygon: "M151.44,148.061l-10.136,4.597c0,0,3.685,7.104,9.902,7.394c6.216,0.29,9.594-5.822,9.594-5.822L151.44,148.061z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412B", zone: freeZone, x:0, y:0, polygon: "M152.27,137.649l-0.83,10.412l-10.136,4.597c0,0-2.788-7.785-0.016-11.236C144.063,137.969,152.27,137.649,152.27,137.649z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424O", zone: freeZone, x:0, y:0, polygon: "M187.653,145.408l9.36,6.023c0,0,4.352-5.929,1.6-10.986c-2.751-5.062-10.13-5.203-10.13-5.203L187.653,145.408z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424M", zone: freeZone, x:0, y:0, polygon: "M187.653,145.408l-10.136,4.488c0,0,3.685,6.937,9.902,7.219c6.215,0.283,9.594-5.684,9.594-5.684L187.653,145.408z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424L", zone: freeZone, x:0, y:0, polygon: "M188.482,135.242l-0.829,10.166l-10.136,4.488c0,0-2.788-7.601-0.016-10.97C180.275,135.555,188.482,135.242,188.482,135.242z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424F", zone: freeZone, x:0, y:0, polygon: "M188.316,89.66l9.36,6.023c0,0,4.352-5.929,1.6-10.986c-2.751-5.062-11.405-4.739-11.405-4.739L188.316,89.66z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424G", zone: freeZone, x:0, y:0, polygon: "M188.316,89.66l-10.136,4.488c0,0,3.685,6.936,9.902,7.219c6.216,0.283,9.594-5.684,9.594-5.684L188.316,89.66z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424I", zone: freeZone, x:0, y:0, polygon: "M187.871,79.958l0.445,9.702l-10.136,4.488c0,0-2.787-7.601-0.016-10.97C180.939,79.807,187.871,79.958,187.871,79.958z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424N", zone: freeZone, x:0, y:0, polygon: "M187.133,122.797l0.229,11.208c0,0,7.47,0.139,10.173-5.065c2.708-5.207-1.358-11.366-1.358-11.366L187.133,122.797z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424K", zone: freeZone, x:0, y:0, polygon: "M187.133,122.797l-9.536-5.74c0,0-3.759,7.065-0.472,12.349c3.288,5.283,10.237,4.598,10.237,4.598L187.133,122.797z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424J", zone: freeZone, x:0, y:0, polygon: "M196.177,117.573l-9.044,5.224l-9.536-5.74c0,0,4.83-6.712,9.245-6.387C191.258,110.997,196.177,117.573,196.177,117.573z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424E", zone: freeZone, x:0, y:0, polygon: "M187.863,66.674l0.23,11.207c0,0,7.47,0.139,10.173-5.065c2.708-5.207-1.358-11.366-1.358-11.366L187.863,66.674z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424H", zone: freeZone, x:0, y:0, polygon: "M187.863,66.674l-9.535-5.739c0,0-3.76,7.064-0.472,12.349c3.288,5.283,10.237,4.598,10.237,4.598L187.863,66.674z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424D", zone: freeZone, x:0, y:0, polygon: "M196.909,61.45l-9.045,5.224l-9.535-5.739c0,0,4.829-6.712,9.244-6.387C191.989,54.874,196.909,61.45,196.909,61.45z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424C", zone: freeZone, x:0, y:0, polygon: "M192.186,29.356l-11.205-0.337c0,0-0.517,7.453,4.543,10.416c5.063,2.969,11.421-0.781,11.421-0.781L192.186,29.356z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424A", zone: freeZone, x:0, y:0, polygon: "M192.186,29.356l6.214-9.233c0,0-6.865-4.113-12.31-1.096c-5.443,3.016-5.11,9.992-5.11,9.992L192.186,29.356z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424B", zone: freeZone, x:0, y:0, polygon: "M196.945,38.653l-4.759-9.297l6.214-9.233c0,0,6.459,5.163,5.911,9.555C203.762,34.073,196.945,38.653,196.945,38.653z").save(flush: true);

    }
    def destroy = {
    }
}
