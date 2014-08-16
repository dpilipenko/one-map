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
		new Room(name: "The Beatles", number: "1728", phone: "216.896.6666", office: cloffice, floor: "17", assignedSeatId: "soccer", zone: freeZone, polygon: "M344.099,446.051 354.658,491.652 431.578,473.412 420.658,427.931z", x: "388", y: "460").save(flush: true);
		new Room(name: "Johnny Cash", number: "1726", phone: "216.896.7345", office: cloffice, floor: "17", assignedSeatId: "baseball", zone: freeZone, polygon: "M452.818,387.131 504.658,375.492 525.14,451.435 470.099,464.403z", x: "489", y: "420").save(flush: true);
		new Room(name: "Chuck Berry", number: "1723", phone: "216.896.2324", office: cloffice, floor: "17", assignedSeatId: "football", zone: ahaZone, polygon: "M467.818,303.372 498.118,350.886 446.578,363.011 445.019,367.931 405.898,372.612 385.139,324.011z", x: "442", y: "338").save(flush: true);
		def elvis = new Room(name: "Elvis Presley", number: "1715", phone: "216.896.1041", office: cloffice, floor: "17", assignedSeatId: "tennis", zone:uidZone, polygon: "M280.979,172.452 279.178,227.532 231.778,231.011 199.229,224.863 191.938,221.652 178.802,213.057 181.858,198.372 186.658,175.211 187.138,173.292z", x: "230", y: "202").save(flush: true);
		
		elvis.addToUsers(dan)
		elvis.save(flush:true);
		
		////
		//	11th Floor
		////
		///
		new Desk(office: cloffice, floor: "11", assignedSeatId: "A", zone: freeZone, polygon: "M193.343,41.877l5.924-9.535c0,0-6.758-3.906-12.08-0.909c-5.323,2.998-4.978,10.238-4.978,10.238L193.343,41.877z", x: "191", y: "36").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "B", zone: freeZone, polygon: "M193.343,41.877l5.58,9.665c0,0,7.307-4.029,7.191-10.139c-0.116-6.108-6.847-9.06-6.847-9.06L193.343,41.877z", x: "191", y: "47").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "C", zone: freeZone, polygon: "M182.209,41.672l11.134,0.205l5.58,9.665c0,0-8.086,3.187-11.949,0.67C183.113,49.695,182.209,41.672,182.209,41.672z", x: "200", y: "42").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "D", zone: freeZone, polygon: "M191.348,80.392l9.81-5.457c0,0-4.009-6.697-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L191.348,80.392z", x: "191", y: "36").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "E", zone: freeZone, polygon: "M191.348,80.392l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.246-1.584-11.246L191.348,80.392z", x: "191", y: "36").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "F", zone: freeZone, polygon: "M181.715,74.804l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C178.608,82.256,181.715,74.804,181.715,74.804z", x: "191", y: "36").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "G", zone: freeZone, polygon: "M191.348,136.4l9.81-5.457c0,0-4.009-6.697-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L191.348,136.4z", x: "191", y: "36").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "H", zone: freeZone, polygon: "M191.348,136.4l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.246-1.584-11.246L191.348,136.4z", x: "191", y: "36").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "I", zone: freeZone, polygon: "M181.715,130.812l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C178.608,138.265,181.715,130.812,181.715,130.812z", x: "191", y: "36").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "J", zone: freeZone, polygon: "M191.357,103.22l-9.81,5.457c0,0,4.009,6.697,10.118,6.663c6.109-0.035,9.325-6.531,9.325-6.531L191.357,103.22z", x: "191", y: "36").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "K", zone: freeZone, polygon: "M191.357,103.22l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L191.357,103.22z", x: "191", y: "36").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "L", zone: freeZone, polygon: "M200.989,108.808l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.218C204.096,101.355,200.989,108.808,200.989,108.808z", x: "191", y: "36").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "M", zone: freeZone, polygon: "M191.348,172.766l9.81-5.457c0,0-4.009-6.697-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L191.348,172.766z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "N", zone: freeZone, polygon: "M191.348,172.766l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.245-1.584-11.245L191.348,172.766z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "o", zone: freeZone, polygon: "M181.715,167.178l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C178.608,174.63,181.715,167.178,181.715,167.178z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "P", zone: freeZone, polygon: "M191.357,195.594l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L191.357,195.594z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "Q", zone: freeZone, polygon: "M191.357,195.594l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L191.357,195.594z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "R", zone: freeZone, polygon: "M200.989,201.182l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C204.096,193.729,200.989,201.182,200.989,201.182z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "S", zone: freeZone, polygon: "M191.348,228.893l9.81-5.456c0,0-4.009-6.696-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L191.348,228.893z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "T", zone: freeZone, polygon: "M191.348,228.893l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.245-1.584-11.245L191.348,228.893z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "U", zone: freeZone, polygon: "M181.715,223.305l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C178.608,230.758,181.715,223.305,181.715,223.305z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "V", zone: freeZone, polygon: "M191.357,251.721l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L191.357,251.721z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "W", zone: freeZone, polygon: "M191.357,251.721l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L191.357,251.721z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "X", zone: freeZone, polygon: "M200.989,257.309l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C204.096,249.856,200.989,257.309,200.989,257.309z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "Y", zone: freeZone, polygon: "M327.754,402.229l8.535,9.246c0,0,6.83-5.636,5.4-12.091c-1.426-6.456-9.666-8.507-9.666-8.507L327.754,402.229z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "Z", zone: freeZone, polygon: "M327.754,402.229l-12.855,2.517c0,0,1.865,8.834,8.753,10.744c6.888,1.909,12.638-4.015,12.638-4.015L327.754,402.229z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AA", zone: freeZone, polygon: "M332.023,390.878l-4.27,11.352l-12.855,2.517c0,0-0.635-9.354,3.585-12.48C322.704,389.142,332.023,390.878,332.023,390.878z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AB", zone: freeZone, polygon: "M300.404,409.214l-8.669-9.318c0,0-6.934,5.681-5.483,12.188c1.451,6.508,9.817,8.574,9.817,8.574L300.404,409.214z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AC", zone: freeZone, polygon: "M300.404,409.214l13.055-2.534c0,0-1.894-8.905-8.89-10.831c-6.992-1.924-12.834,4.047-12.834,4.047L300.404,409.214z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AD", zone: freeZone, polygon: "M296.069,420.657l4.335-11.443l13.055-2.534c0,0,0.646,9.428-3.64,12.579C305.532,422.409,296.069,420.657,296.069,420.657z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AE", zone: freeZone, polygon: "M410.608,381.38l8.535,9.246c0,0,6.83-5.636,5.4-12.091c-1.426-6.456-9.666-8.507-9.666-8.507L410.608,381.38z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AF", zone: freeZone, polygon: "M410.608,381.38l-12.855,2.517c0,0,1.865,8.834,8.753,10.744c6.888,1.909,12.638-4.015,12.638-4.015L410.608,381.38z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AG", zone: freeZone, polygon: "M414.878,370.028l-4.27,11.352l-12.855,2.517c0,0-0.635-9.354,3.585-12.48C405.559,368.292,414.878,370.028,414.878,370.028z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AH", zone: freeZone, polygon: "M383.259,388.364l-8.669-9.318c0,0-6.934,5.681-5.483,12.188c1.451,6.508,9.817,8.574,9.817,8.574L383.259,388.364z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AI", zone: freeZone, polygon: "M383.259,388.364l13.055-2.534c0,0-1.894-8.905-8.89-10.831c-6.992-1.924-12.834,4.047-12.834,4.047L383.259,388.364z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AJ", zone: freeZone, polygon: "M378.924,399.808l4.335-11.443l13.055-2.534c0,0,0.646,9.428-3.64,12.579C388.387,401.56,378.924,399.808,378.924,399.808z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AK", zone: freeZone, polygon:"M35.345,143.126l0.466-11.215c0,0-7.805-0.076-10.964,5.152c-3.158,5.229,0.704,11.363,0.704,11.363L35.345,143.126z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AL", zone: freeZone, polygon:"M35.345,143.126l9.612,5.668c0,0,4.379-7.103,1.272-12.366c-3.106-5.26-10.418-4.518-10.418-4.518L35.345,143.126z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AM", zone: freeZone, polygon:"M25.551,148.425l9.794-5.299l9.612,5.668c0,0-5.471,6.752-10.072,6.463C30.286,154.965,25.551,148.425,25.551,148.425z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AN", zone: freeZone, polygon:"M54.821,155.034l-0.466,11.216c0,0,7.805,0.076,10.964-5.152c3.158-5.229-0.704-11.363-0.704-11.363L54.821,155.034z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AO", zone: freeZone, polygon:"M54.821,155.034l-9.613-5.668c0,0-4.379,7.103-1.272,12.365c3.107,5.261,10.419,4.519,10.419,4.519L54.821,155.034z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AP", zone: freeZone, polygon:"M64.615,149.734l-9.794,5.299l-9.613-5.668c0,0,5.471-6.753,10.073-6.462C59.881,143.194,64.615,149.734,64.615,149.734z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AQ", zone: freeZone, polygon:"M154.867,157.886l9.81-5.457c0,0-4.009-6.697-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L154.867,157.886z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AR", zone: freeZone, polygon:"M154.867,157.886l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.245-1.584-11.245L154.867,157.886z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AS", zone: freeZone, polygon:"M145.234,152.298l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C142.127,159.75,145.234,152.298,145.234,152.298z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AT", zone: freeZone, polygon:"M59.236,187l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L59.236,187z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AU", zone: freeZone, polygon:"M59.236,187l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L59.236,187z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AV", zone: freeZone, polygon:"M68.868,192.588L59.236,187l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C71.975,185.136,68.868,192.588,68.868,192.588z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AW", zone: freeZone, polygon:"M107.905,187.375l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L107.905,187.375z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AX", zone: freeZone, polygon:"M107.905,187.375l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L107.905,187.375z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AY", zone: freeZone, polygon:"M117.537,192.963l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C120.644,185.511,117.537,192.963,117.537,192.963z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "AZ", zone: freeZone, polygon:"M155.996,184.375l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L155.996,184.375z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BA", zone: freeZone, polygon:"M155.996,184.375l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L155.996,184.375z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BB", zone: freeZone, polygon:"M165.628,189.963l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C168.735,182.511,165.628,189.963,165.628,189.963z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BC", zone: freeZone, polygon:"M88.252,199.241l9.627-5.773c0,0-4.225-6.562-10.329-6.33c-6.104,0.234-9.108,6.831-9.108,6.831L88.252,199.241z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BD", zone: freeZone, polygon:"M88.252,199.241l0.545,11.146c0,0,8.341-0.244,11.031-5.73c2.688-5.487-1.949-11.189-1.949-11.189L88.252,199.241z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BE", zone: freeZone, polygon:"M78.443,193.969l9.809,5.272l0.545,11.146c0,0-8.648-0.862-10.934-4.866C75.581,201.518,78.443,193.969,78.443,193.969z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BF", zone: freeZone, polygon:"M127.425,199.241l9.627-5.773c0,0-4.225-6.562-10.329-6.33c-6.104,0.234-9.108,6.831-9.108,6.831L127.425,199.241z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BG", zone: freeZone, polygon:"M127.425,199.241l0.545,11.146c0,0,8.341-0.244,11.031-5.73c2.688-5.487-1.949-11.189-1.949-11.189L127.425,199.241z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BH", zone: freeZone, polygon:"M117.616,193.969l9.809,5.272l0.545,11.146c0,0-8.648-0.862-10.934-4.866C114.753,201.518,117.616,193.969,117.616,193.969z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BI", zone: freeZone, polygon:"M324.351,352.96l-8.865,8.628c0,0,5.905,6.278,12.286,4.602c6.382-1.68,8.083-9.68,8.083-9.68L324.351,352.96z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BJ", zone: freeZone, polygon:"M324.351,352.96l-3.045-12.207c0,0-8.734,2.211-10.354,8.908c-1.615,6.696,4.534,11.927,4.534,11.927L324.351,352.96z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BK", zone: freeZone, polygon:"M335.854,356.51l-11.504-3.55l-3.045-12.207c0,0,9.304-1.06,12.6,2.84C337.199,347.49,335.854,356.51,335.854,356.51z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BL", zone: freeZone, polygon:"M302.921,372.381l8.832-9.167c0,0-6.266-6.237-12.766-4.292c-6.501,1.948-7.996,10.217-7.996,10.217L302.921,372.381z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BM", zone: freeZone, polygon:"M302.921,372.381l3.516,12.423c0,0,8.9-2.575,10.352-9.507c1.447-6.931-5.035-12.083-5.035-12.083L302.921,372.381z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BN", zone: freeZone, polygon:"M290.991,369.139l11.93,3.242l3.516,12.423c0,0-9.522,1.412-13.032-2.474C289.898,378.443,290.991,369.139,290.991,369.139z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BO", zone: freeZone, polygon:"M350.573,361.006l8.547-9.024c0,0-6.064-6.141-12.355-4.227c-6.291,1.919-7.738,10.059-7.738,10.059L350.573,361.006z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BP", zone: freeZone, polygon:"M350.573,361.006l3.402,12.231c0,0,8.615-2.536,10.019-9.359c1.4-6.823-4.874-11.896-4.874-11.896L350.573,361.006z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BQ", zone: freeZone, polygon:"M339.026,357.813l11.547,3.192l3.402,12.231c0,0-9.217,1.391-12.612-2.436C337.969,366.975,339.026,357.813,339.026,357.813z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BR", zone: freeZone, polygon:"M419.556,328.663l-8.865,8.628c0,0,5.905,6.278,12.286,4.602c6.382-1.68,8.083-9.68,8.083-9.68L419.556,328.663z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BS", zone: freeZone, polygon:"M419.556,328.663l-3.045-12.207c0,0-8.734,2.211-10.354,8.908c-1.615,6.696,4.534,11.927,4.534,11.927L419.556,328.663z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BT", zone: freeZone, polygon:"M431.06,332.213l-11.504-3.55l-3.045-12.207c0,0,9.304-1.06,12.6,2.84C432.404,323.193,431.06,332.213,431.06,332.213z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BU", zone: freeZone, polygon:"M398.126,348.084l8.832-9.167c0,0-6.266-6.237-12.766-4.292c-6.501,1.948-7.996,10.217-7.996,10.217L398.126,348.084z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BV", zone: freeZone, polygon:"M398.126,348.084l3.516,12.423c0,0,8.9-2.575,10.352-9.507c1.447-6.931-5.035-12.083-5.035-12.083L398.126,348.084z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BW", zone: freeZone, polygon:"M386.196,344.842l11.93,3.242l3.516,12.423c0,0-9.522,1.412-13.032-2.474C385.104,354.146,386.196,344.842,386.196,344.842z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BX", zone: freeZone, polygon:"M445.778,336.709l8.547-9.024c0,0-6.064-6.141-12.355-4.227c-6.291,1.919-7.738,10.059-7.738,10.059L445.778,336.709z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BY", zone: freeZone, polygon:"M445.778,336.709l3.402,12.231c0,0,8.615-2.536,10.019-9.359c1.4-6.823-4.874-11.896-4.874-11.896L445.778,336.709z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "BZ", zone: freeZone, polygon:"M434.231,333.517l11.547,3.192l3.402,12.231c0,0-9.217,1.391-12.612-2.436C433.174,342.678,434.231,333.517,434.231,333.517z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CA", zone: freeZone, polygon:"M107.915,153.747l9.81-5.457c0,0-4.009-6.696-10.118-6.662c-6.109,0.035-9.325,6.531-9.325,6.531L107.915,153.747z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CB", zone: freeZone, polygon:"M107.915,153.747l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.246-1.584-11.246L107.915,153.747z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CD", zone: freeZone, polygon:"M98.282,148.159l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.219C95.175,155.611,98.282,148.159,98.282,148.159z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CE", zone: freeZone, polygon:"M127.567,141.881l-9.627,5.773c0,0,4.225,6.562,10.329,6.33c6.104-0.234,9.108-6.831,9.108-6.831L127.567,141.881z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CF", zone: freeZone, polygon:"M127.567,141.881l-0.545-11.146c0,0-8.341,0.244-11.031,5.73c-2.688,5.487,1.949,11.189,1.949,11.189L127.567,141.881z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CG", zone: freeZone, polygon:"M137.376,147.153l-9.809-5.272l-0.545-11.146c0,0,8.648,0.862,10.934,4.866C140.239,139.604,137.376,147.153,137.376,147.153z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CH", zone: freeZone, polygon:"M88.394,141.881l-9.627,5.773c0,0,4.225,6.562,10.329,6.33c6.104-0.234,9.108-6.831,9.108-6.831L88.394,141.881z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CI", zone: freeZone, polygon:"M88.394,141.881l-0.545-11.146c0,0-8.341,0.244-11.031,5.73c-2.688,5.487,1.949,11.189,1.949,11.189L88.394,141.881z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CJ", zone: freeZone, polygon:"M98.203,147.153l-9.809-5.272l-0.545-11.146c0,0,8.648,0.862,10.934,4.866C101.066,139.604,98.203,147.153,98.203,147.153z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CK", zone: freeZone, polygon:"M416.678,421.211l3.873,10.108l11.495,1.038l6.996-8.332c0,0-7.624-3.794-11.864-4.429C422.938,418.961,416.678,421.211,416.678,421.211z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CL", zone: freeZone, polygon:"M469.117,407.87l3.873,10.108l11.495,1.038l6.996-8.332c0,0-7.624-3.794-11.864-4.429C475.377,405.62,469.117,407.87,469.117,407.87z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CM", zone: freeZone, polygon:"M415.14,441.098l6.36-8.76l-5.4-10.2l-10.8-1.319c0,0,0.979,8.459,2.76,12.359S415.14,441.098,415.14,441.098z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CN", zone: freeZone, polygon:"M482.94,439.34l6.36-8.76l-5.4-10.2l-10.8-1.319c0,0,0.979,8.459,2.76,12.359S482.94,439.34,482.94,439.34z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CO", zone: freeZone, polygon:"M430.56,451.237l6.36-8.76l-5.4-10.2l-10.8-1.319c0,0,0.979,8.459,2.76,12.359S430.56,451.237,430.56,451.237z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CP", zone: freeZone, polygon:"M466.94,426.859l6.36-8.76l-5.4-10.2l-10.8-1.319c0,0,0.979,8.459,2.76,12.359S466.94,426.859,466.94,426.859z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CQ", zone: freeZone, polygon:"M504.901,441.44l-4.325-9.923l-11.53-0.517l-6.612,8.641c0,0,7.787,3.444,12.052,3.887S504.901,441.44,504.901,441.44z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CR", zone: freeZone, polygon:"M452.834,453.299l-4.325-9.923l-11.53-0.517l-6.612,8.641c0,0,7.787,3.444,12.052,3.887S452.834,453.299,452.834,453.299z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CS", zone: freeZone, polygon:"M455.121,433.408l-6.828,8.4l4.836,10.48l10.711,1.905c0,0-0.516-8.5-2.081-12.491C460.194,437.711,455.121,433.408,455.121,433.408z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CT", zone: freeZone, polygon:"M490.935,410.946l-6.828,8.4l4.836,10.48l10.711,1.905c0,0-0.516-8.5-2.081-12.491C496.008,415.249,490.935,410.946,490.935,410.946z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CU", zone: freeZone, polygon:"M438.795,423.545l-6.828,8.4l4.836,10.48l10.711,1.905c0,0-0.516-8.5-2.081-12.491C443.868,427.848,438.795,423.545,438.795,423.545z", x: "0", y: "0").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "CV", zone: freeZone, polygon:"M507.255,421.928l-6.828,8.4l4.836,10.48l10.711,1.905c0,0-0.516-8.5-2.081-12.491C512.328,426.23,507.255,421.928,507.255,421.928z", x: "0", y: "0").save(flush: true);
		
    }
    def destroy = {
    }
}
