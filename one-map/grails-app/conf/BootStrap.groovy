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
		def cloffice = new Office(name: "Cleveland").save(flush:true);
		def njoffice = new Office(name: "Princeton").save(flush:true);
		def nyhoffice = new Office(name: "New York - Hudson Street").save(flush:true);
		def ny5office = new Office(name: "New York - 5th Avenue").save(flush:true);
		def chioffice = new Office(name: "Chicago").save(flush:true);
		def sfoffice = new Office(name: "San Francisco").save(flush:true);
		def slooffice = new Office(name: "San Luis Obispo").save(flush:true);
		def laoffice = new Office(name: "Los Angeles").save(flush:true);
		def ldoffice = new Office(name: "London").save(flush:true);
		
		////
		//	Users
		////
		def testUser = new User(firstName:"Tess", lastName:"Ting", username:"test@rosetta.com", password: "password", enabled: true,
			accountExpired: false, accountLocked: false, passwordExpired: false, office: cloffice, phone: "216-867-5309",
			level: "Associate", craft: "Quality Assurance").save(flush:true);
		def dan = new User(firstName: "Dan", lastName: "Padgett", username: "dan.padgett@rosetta.com", password: "passw0rd", enabled: true, 
			accountExpired: false, accountLocked: false, passwordExpired: false, office: cloffice, phone: "555-555-5555",
			level: "Senior Associate", craft: "Software Engineering").save(flush: true);
		def dima = new User(firstName: "Dmitriy", lastName: "Pilipenko", username: "dmitriy.pilipenko@rosetta.com", password: "passw0rd", enabled: true, 
			accountExpired: false, accountLocked: false, passwordExpired: false, office: cloffice, phone: "555-555-5555",
			level: "Associate", craft: "Software Engineering").save(flush: true);
		def liz = new User(firstName: "Liz", lastName: "Judd", username: "liz.judd@rosetta.com", password: "passw0rd", enabled: true, 
			accountExpired: false, accountLocked: false, passwordExpired: false, office: cloffice, phone: "555-555-5555",
			level: "Senior Associate", craft: "Creative Engineering").save(flush: true);
		def dave = new User(firstName: "Dave", lastName: "Fagan", username: "dave.fagan@rosetta.com", password: "passw0rd", enabled: true, 
			accountExpired: false, accountLocked: false, passwordExpired: false, office: cloffice, phone: "555-555-5555",
			level: "Senior Associate", craft: "Creative Engineering").save(flush: true);
		def becky = new User(firstName: "Becky", lastName: "Horvath", username: "becky.horvath@rosetta.com", password: "passw0rd", enabled: true, 
			accountExpired: false, accountLocked: false, passwordExpired: false, office: cloffice, phone: "555-555-5555",
			level: "Senior Associate", craft: "CEO").save(flush: true);

		////
		//  Zones
		////
		Zone freeZone = Zone.getFreeZone().save(flush:true)
		Zone ahaZone = new Zone(name: "AHA", color: "#880088").save(flush:true)
		Zone uidZone = new Zone(name: "UID Pod", color: "#00FF00").save(flush:true)
		
		////
		//	17th Floor
		////
		new Room(name: "The Beatles", number: "1728", phone: "216.896.6666", office: cloffice, floor: "17", assignedSeatId: "1728", zone: freeZone, polygon: "M344.099,446.051 354.658,491.652 431.578,473.412 420.658,427.931z", x: "388", y: "460").save(flush: true);
		new Room(name: "Johnny Cash", number: "1726", phone: "216.896.7345", office: cloffice, floor: "17", assignedSeatId: "1726", zone: freeZone, polygon: "M452.818,387.131 504.658,375.492 525.14,451.435 470.099,464.403z", x: "489", y: "420").save(flush: true);
		new Room(name: "Chuck Berry", number: "1723", phone: "216.896.2324", office: cloffice, floor: "17", assignedSeatId: "1723", zone: ahaZone, polygon: "M467.818,303.372 498.118,350.886 446.578,363.011 445.019,367.931 405.898,372.612 385.139,324.011z", x: "442", y: "338").save(flush: true);
		def elvis = new Room(name: "Elvis Presley", number: "1715", phone: "216.896.1041", office: cloffice, floor: "17", assignedSeatId: "1715", zone:uidZone, polygon: "M280.979,172.452 279.178,227.532 231.778,231.011 199.229,224.863 191.938,221.652 178.802,213.057 181.858,198.372 186.658,175.211 187.138,173.292z", x: "230", y: "202").save(flush: true);
		
		elvis.addToUsers(dan)
		elvis.save(flush:true);
		
		////
		//	11th Floor
		////
		///
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125A", zone: freeZone, x:0, y:0, polygon: "M193.343,41.877l5.924-9.535c0,0-6.758-3.906-12.08-0.909c-5.323,2.998-4.978,10.238-4.978,10.238L193.343,41.877z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125B", zone: freeZone, x:0, y:0, polygon: "M193.343,41.877l5.58,9.665c0,0,7.307-4.029,7.191-10.139c-0.116-6.108-6.847-9.06-6.847-9.06L193.343,41.877z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125C", zone: freeZone, x:0, y:0, polygon: "M182.209,41.672l11.134,0.205l5.58,9.665c0,0-8.086,3.187-11.949,0.67C183.113,49.695,182.209,41.672,182.209,41.672z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125D", zone: freeZone, x:0, y:0, polygon: "M191.348,80.392l9.81-5.457c0,0-4.009-6.697-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L191.348,80.392z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125I", zone: freeZone, x:0, y:0, polygon: "M191.348,80.392l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.246-1.584-11.246L191.348,80.392z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125E", zone: freeZone, x:0, y:0, polygon: "M181.715,74.804l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C178.608,82.256,181.715,74.804,181.715,74.804z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125J", zone: freeZone, x:0, y:0, polygon: "M191.348,136.4l9.81-5.457c0,0-4.009-6.697-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L191.348,136.4z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125L", zone: freeZone, x:0, y:0, polygon: "M191.348,136.4l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.246-1.584-11.246L191.348,136.4z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125K", zone: freeZone, x:0, y:0, polygon: "M181.715,130.812l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C178.608,138.265,181.715,130.812,181.715,130.812z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125G", zone: freeZone, x:0, y:0, polygon: "M191.357,103.22l-9.81,5.457c0,0,4.009,6.697,10.118,6.663c6.109-0.035,9.325-6.531,9.325-6.531L191.357,103.22z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125F", zone: freeZone, x:0, y:0, polygon: "M191.357,103.22l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L191.357,103.22z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125H", zone: freeZone, x:0, y:0, polygon: "M200.989,108.808l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.218C204.096,101.355,200.989,108.808,200.989,108.808z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125O", zone: freeZone, x:0, y:0, polygon: "M191.348,172.766l9.81-5.457c0,0-4.009-6.697-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L191.348,172.766z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125P", zone: freeZone, x:0, y:0, polygon: "M191.348,172.766l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.245-1.584-11.245L191.348,172.766z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125N", zone: freeZone, x:0, y:0, polygon: "M181.715,167.178l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C178.608,174.63,181.715,167.178,181.715,167.178z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125R", zone: freeZone, x:0, y:0, polygon: "M191.357,195.594l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L191.357,195.594z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125M", zone: freeZone, x:0, y:0, polygon: "M191.357,195.594l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L191.357,195.594z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Q", zone: freeZone, x:0, y:0, polygon: "M200.989,201.182l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C204.096,193.729,200.989,201.182,200.989,201.182z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125S", zone: freeZone, x:0, y:0, polygon: "M191.348,228.893l9.81-5.456c0,0-4.009-6.696-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L191.348,228.893z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125T", zone: freeZone, x:0, y:0, polygon: "M191.348,228.893l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.245-1.584-11.245L191.348,228.893z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125X", zone: freeZone, x:0, y:0, polygon: "M181.715,223.305l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C178.608,230.758,181.715,223.305,181.715,223.305z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125V", zone: freeZone, x:0, y:0, polygon: "M191.357,251.721l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L191.357,251.721z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125W", zone: freeZone, x:0, y:0, polygon: "M191.357,251.721l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L191.357,251.721z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125U", zone: freeZone, x:0, y:0, polygon: "M200.989,257.309l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C204.096,249.856,200.989,257.309,200.989,257.309z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1132D", zone: freeZone, x:0, y:0, polygon: "M327.754,402.229l8.535,9.246c0,0,6.83-5.636,5.4-12.091c-1.426-6.456-9.666-8.507-9.666-8.507L327.754,402.229z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1132C", zone: freeZone, x:0, y:0, polygon: "M327.754,402.229l-12.855,2.517c0,0,1.865,8.834,8.753,10.744c6.888,1.909,12.638-4.015,12.638-4.015L327.754,402.229z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1132E", zone: freeZone, x:0, y:0, polygon: "M332.023,390.878l-4.27,11.352l-12.855,2.517c0,0-0.635-9.354,3.585-12.48C322.704,389.142,332.023,390.878,332.023,390.878z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1132A", zone: freeZone, x:0, y:0, polygon: "M300.404,409.214l-8.669-9.318c0,0-6.934,5.681-5.483,12.188c1.451,6.508,9.817,8.574,9.817,8.574L300.404,409.214z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1132F", zone: freeZone, x:0, y:0, polygon: "M300.404,409.214l13.055-2.534c0,0-1.894-8.905-8.89-10.831c-6.992-1.924-12.834,4.047-12.834,4.047L300.404,409.214z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1132B", zone: freeZone, x:0, y:0, polygon: "M296.069,420.657l4.335-11.443l13.055-2.534c0,0,0.646,9.428-3.64,12.579C305.532,422.409,296.069,420.657,296.069,420.657z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1133D", zone: freeZone, x:0, y:0, polygon: "M410.608,381.38l8.535,9.246c0,0,6.83-5.636,5.4-12.091c-1.426-6.456-9.666-8.507-9.666-8.507L410.608,381.38z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1133E", zone: freeZone, x:0, y:0, polygon: "M410.608,381.38l-12.855,2.517c0,0,1.865,8.834,8.753,10.744c6.888,1.909,12.638-4.015,12.638-4.015L410.608,381.38z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1133C", zone: freeZone, x:0, y:0, polygon: "M414.878,370.028l-4.27,11.352l-12.855,2.517c0,0-0.635-9.354,3.585-12.48C405.559,368.292,414.878,370.028,414.878,370.028z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1133A", zone: freeZone, x:0, y:0, polygon: "M383.259,388.364l-8.669-9.318c0,0-6.934,5.681-5.483,12.188c1.451,6.508,9.817,8.574,9.817,8.574L383.259,388.364z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1133B", zone: freeZone, x:0, y:0, polygon: "M383.259,388.364l13.055-2.534c0,0-1.894-8.905-8.89-10.831c-6.992-1.924-12.834,4.047-12.834,4.047L383.259,388.364z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1133F", zone: freeZone, x:0, y:0, polygon: "M378.924,399.808l4.335-11.443l13.055-2.534c0,0,0.646,9.428-3.64,12.579C388.387,401.56,378.924,399.808,378.924,399.808z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112C", zone: freeZone, x:30, y:140, polygon:"M35.345,143.126l0.466-11.215c0,0-7.805-0.076-10.964,5.152c-3.158,5.229,0.704,11.363,0.704,11.363L35.345,143.126z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112D", zone: freeZone, x:41, y:140, polygon:"M35.345,143.126l9.612,5.668c0,0,4.379-7.103,1.272-12.366c-3.106-5.26-10.418-4.518-10.418-4.518L35.345,143.126z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112B", zone: freeZone, x:35, y:149, polygon:"M25.551,148.425l9.794-5.299l9.612,5.668c0,0-5.471,6.752-10.072,6.463C30.286,154.965,25.551,148.425,25.551,148.425z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112F", zone: freeZone, x:60, y:158, polygon:"M54.821,155.034l-0.466,11.216c0,0,7.805,0.076,10.964-5.152c3.158-5.229-0.704-11.363-0.704-11.363L54.821,155.034z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112A", zone: freeZone, x:49, y:158, polygon:"M54.821,155.034l-9.613-5.668c0,0-4.379,7.103-1.272,12.365c3.107,5.261,10.419,4.519,10.419,4.519L54.821,155.034z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112E", zone: freeZone, x:55, y:149, polygon:"M64.615,149.734l-9.794,5.299l-9.613-5.668c0,0,5.471-6.753,10.073-6.462C59.881,143.194,64.615,149.734,64.615,149.734z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Z1", zone: freeZone, x:0, y:0, polygon:"M154.867,157.886l9.81-5.457c0,0-4.009-6.697-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L154.867,157.886z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Z2", zone: freeZone, x:0, y:0, polygon:"M154.867,157.886l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.245-1.584-11.245L154.867,157.886z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Z", zone: freeZone, x:0, y:0, polygon:"M145.234,152.298l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C142.127,159.75,145.234,152.298,145.234,152.298z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112H", zone: freeZone, x:59, y:193, polygon:"M59.236,187l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L59.236,187z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112I", zone: freeZone, x:53, y:184, polygon:"M59.236,187l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L59.236,187z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112G", zone: freeZone, x:65, y:184, polygon:"M68.868,192.588L59.236,187l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C71.975,185.136,68.868,192.588,68.868,192.588z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112L", zone: freeZone, x:0, y:0, polygon:"M107.905,187.375l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L107.905,187.375z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112Q", zone: freeZone, x:0, y:0, polygon:"M107.905,187.375l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L107.905,187.375z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112P", zone: freeZone, x:0, y:0, polygon:"M117.537,192.963l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C120.644,185.511,117.537,192.963,117.537,192.963z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Z4", zone: freeZone, x:0, y:0, polygon:"M155.996,184.375l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L155.996,184.375z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Y", zone: freeZone, x:0, y:0, polygon:"M155.996,184.375l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L155.996,184.375z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Z3", zone: freeZone, x:0, y:0, polygon:"M165.628,189.963l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C168.735,182.511,165.628,189.963,165.628,189.963z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112R", zone: freeZone, x:0, y:0, polygon:"M88.252,199.241l9.627-5.773c0,0-4.225-6.562-10.329-6.33c-6.104,0.234-9.108,6.831-9.108,6.831L88.252,199.241z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112K", zone: freeZone, x:0, y:0, polygon:"M88.252,199.241l0.545,11.146c0,0,8.341-0.244,11.031-5.73c2.688-5.487-1.949-11.189-1.949-11.189L88.252,199.241z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112J", zone: freeZone, x:0, y:0, polygon:"M78.443,193.969l9.809,5.272l0.545,11.146c0,0-8.648-0.862-10.934-4.866C75.581,201.518,78.443,193.969,78.443,193.969z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112O", zone: freeZone, x:0, y:0, polygon:"M127.425,199.241l9.627-5.773c0,0-4.225-6.562-10.329-6.33c-6.104,0.234-9.108,6.831-9.108,6.831L127.425,199.241z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112N", zone: freeZone, x:0, y:0, polygon:"M127.425,199.241l0.545,11.146c0,0,8.341-0.244,11.031-5.73c2.688-5.487-1.949-11.189-1.949-11.189L127.425,199.241z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112M", zone: freeZone, x:0, y:0, polygon:"M117.616,193.969l9.809,5.272l0.545,11.146c0,0-8.648-0.862-10.934-4.866C114.753,201.518,117.616,193.969,117.616,193.969z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1132N", zone: freeZone, x:0, y:0, polygon:"M324.351,352.96l-8.865,8.628c0,0,5.905,6.278,12.286,4.602c6.382-1.68,8.083-9.68,8.083-9.68L324.351,352.96z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1132I", zone: freeZone, x:0, y:0, polygon:"M324.351,352.96l-3.045-12.207c0,0-8.734,2.211-10.354,8.908c-1.615,6.696,4.534,11.927,4.534,11.927L324.351,352.96z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1132J", zone: freeZone, x:0, y:0, polygon:"M335.854,356.51l-11.504-3.55l-3.045-12.207c0,0,9.304-1.06,12.6,2.84C337.199,347.49,335.854,356.51,335.854,356.51z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1132H", zone: freeZone, x:0, y:0, polygon:"M302.921,372.381l8.832-9.167c0,0-6.266-6.237-12.766-4.292c-6.501,1.948-7.996,10.217-7.996,10.217L302.921,372.381z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1132O", zone: freeZone, x:0, y:0, polygon:"M302.921,372.381l3.516,12.423c0,0,8.9-2.575,10.352-9.507c1.447-6.931-5.035-12.083-5.035-12.083L302.921,372.381z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1132G", zone: freeZone, x:0, y:0, polygon:"M290.991,369.139l11.93,3.242l3.516,12.423c0,0-9.522,1.412-13.032-2.474C289.898,378.443,290.991,369.139,290.991,369.139z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1132K", zone: freeZone, x:0, y:0, polygon:"M350.573,361.006l8.547-9.024c0,0-6.064-6.141-12.355-4.227c-6.291,1.919-7.738,10.059-7.738,10.059L350.573,361.006z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1132L", zone: freeZone, x:0, y:0, polygon:"M350.573,361.006l3.402,12.231c0,0,8.615-2.536,10.019-9.359c1.4-6.823-4.874-11.896-4.874-11.896L350.573,361.006z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1132M", zone: freeZone, x:0, y:0, polygon:"M339.026,357.813l11.547,3.192l3.402,12.231c0,0-9.217,1.391-12.612-2.436C337.969,366.975,339.026,357.813,339.026,357.813z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1133N", zone: freeZone, x:0, y:0, polygon:"M419.556,328.663l-8.865,8.628c0,0,5.905,6.278,12.286,4.602c6.382-1.68,8.083-9.68,8.083-9.68L419.556,328.663z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1133I", zone: freeZone, x:0, y:0, polygon:"M419.556,328.663l-3.045-12.207c0,0-8.734,2.211-10.354,8.908c-1.615,6.696,4.534,11.927,4.534,11.927L419.556,328.663z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1133J", zone: freeZone, x:0, y:0, polygon:"M431.06,332.213l-11.504-3.55l-3.045-12.207c0,0,9.304-1.06,12.6,2.84C432.404,323.193,431.06,332.213,431.06,332.213z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1133H", zone: freeZone, x:0, y:0, polygon:"M398.126,348.084l8.832-9.167c0,0-6.266-6.237-12.766-4.292c-6.501,1.948-7.996,10.217-7.996,10.217L398.126,348.084z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1133O", zone: freeZone, x:0, y:0, polygon:"M398.126,348.084l3.516,12.423c0,0,8.9-2.575,10.352-9.507c1.447-6.931-5.035-12.083-5.035-12.083L398.126,348.084z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1133G", zone: freeZone, x:0, y:0, polygon:"M386.196,344.842l11.93,3.242l3.516,12.423c0,0-9.522,1.412-13.032-2.474C385.104,354.146,386.196,344.842,386.196,344.842z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1133K", zone: freeZone, x:0, y:0, polygon:"M445.778,336.709l8.547-9.024c0,0-6.064-6.141-12.355-4.227c-6.291,1.919-7.738,10.059-7.738,10.059L445.778,336.709z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1133L", zone: freeZone, x:0, y:0, polygon:"M445.778,336.709l3.402,12.231c0,0,8.615-2.536,10.019-9.359c1.4-6.823-4.874-11.896-4.874-11.896L445.778,336.709z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1133M", zone: freeZone, x:0, y:0, polygon:"M434.231,333.517l11.547,3.192l3.402,12.231c0,0-9.217,1.391-12.612-2.436C433.174,342.678,434.231,333.517,434.231,333.517z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112Y", zone: freeZone, x:0, y:0, polygon:"M107.915,153.747l9.81-5.457c0,0-4.009-6.696-10.118-6.662c-6.109,0.035-9.325,6.531-9.325,6.531L107.915,153.747z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112U", zone: freeZone, x:0, y:0, polygon:"M107.915,153.747l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.246-1.584-11.246L107.915,153.747z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112T", zone: freeZone, x:0, y:0, polygon:"M98.282,148.159l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.219C95.175,155.611,98.282,148.159,98.282,148.159z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112V", zone: freeZone, x:0, y:0, polygon:"M127.567,141.881l-9.627,5.773c0,0,4.225,6.562,10.329,6.33c6.104-0.234,9.108-6.831,9.108-6.831L127.567,141.881z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112X", zone: freeZone, x:0, y:0, polygon:"M127.567,141.881l-0.545-11.146c0,0-8.341,0.244-11.031,5.73c-2.688,5.487,1.949,11.189,1.949,11.189L127.567,141.881z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112W", zone: freeZone, x:0, y:0, polygon:"M137.376,147.153l-9.809-5.272l-0.545-11.146c0,0,8.648,0.862,10.934,4.866C140.239,139.604,137.376,147.153,137.376,147.153z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112S", zone: freeZone, x:0, y:0, polygon:"M88.394,141.881l-9.627,5.773c0,0,4.225,6.562,10.329,6.33c6.104-0.234,9.108-6.831,9.108-6.831L88.394,141.881z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112Z1", zone: freeZone, x:0, y:0, polygon:"M88.394,141.881l-0.545-11.146c0,0-8.341,0.244-11.031,5.73c-2.688,5.487,1.949,11.189,1.949,11.189L88.394,141.881z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1112Z", zone: freeZone, x:0, y:0, polygon:"M98.203,147.153l-9.809-5.272l-0.545-11.146c0,0,8.648,0.862,10.934,4.866C101.066,139.604,98.203,147.153,98.203,147.153z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1135D", zone: freeZone, x:0, y:0, polygon:"M416.678,421.211l3.873,10.108l11.495,1.038l6.996-8.332c0,0-7.624-3.794-11.864-4.429C422.938,418.961,416.678,421.211,416.678,421.211z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1135J", zone: freeZone, x:0, y:0, polygon:"M469.117,407.87l3.873,10.108l11.495,1.038l6.996-8.332c0,0-7.624-3.794-11.864-4.429C475.377,405.62,469.117,407.87,469.117,407.87z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1135C", zone: freeZone, x:0, y:0, polygon:"M415.14,441.098l6.36-8.76l-5.4-10.2l-10.8-1.319c0,0,0.979,8.459,2.76,12.359S415.14,441.098,415.14,441.098z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1135H", zone: freeZone, x:0, y:0, polygon:"M482.94,439.34l6.36-8.76l-5.4-10.2l-10.8-1.319c0,0,0.979,8.459,2.76,12.359S482.94,439.34,482.94,439.34z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1135B", zone: freeZone, x:0, y:0, polygon:"M430.56,451.237l6.36-8.76l-5.4-10.2l-10.8-1.319c0,0,0.979,8.459,2.76,12.359S430.56,451.237,430.56,451.237z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1135I", zone: freeZone, x:0, y:0, polygon:"M466.94,426.859l6.36-8.76l-5.4-10.2l-10.8-1.319c0,0,0.979,8.459,2.76,12.359S466.94,426.859,466.94,426.859z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1135G", zone: freeZone, x:0, y:0, polygon:"M504.901,441.44l-4.325-9.923l-11.53-0.517l-6.612,8.641c0,0,7.787,3.444,12.052,3.887S504.901,441.44,504.901,441.44z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1135A", zone: freeZone, x:0, y:0, polygon:"M452.834,453.299l-4.325-9.923l-11.53-0.517l-6.612,8.641c0,0,7.787,3.444,12.052,3.887S452.834,453.299,452.834,453.299z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1135F", zone: freeZone, x:0, y:0, polygon:"M455.121,433.408l-6.828,8.4l4.836,10.48l10.711,1.905c0,0-0.516-8.5-2.081-12.491C460.194,437.711,455.121,433.408,455.121,433.408z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1135K", zone: freeZone, x:0, y:0, polygon:"M490.935,410.946l-6.828,8.4l4.836,10.48l10.711,1.905c0,0-0.516-8.5-2.081-12.491C496.008,415.249,490.935,410.946,490.935,410.946z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1135E", zone: freeZone, x:0, y:0, polygon:"M438.795,423.545l-6.828,8.4l4.836,10.48l10.711,1.905c0,0-0.516-8.5-2.081-12.491C443.868,427.848,438.795,423.545,438.795,423.545z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1135L", zone: freeZone, x:0, y:0, polygon:"M507.255,421.928l-6.828,8.4l4.836,10.48l10.711,1.905c0,0-0.516-8.5-2.081-12.491C512.328,426.23,507.255,421.928,507.255,421.928z").save(flush: true);
		
    }
    def destroy = {
    }
}
