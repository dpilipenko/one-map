import com.rosetta.onemap.Office
import com.rosetta.onemap.User
import com.rosetta.onemap.Zone
import com.rosetta.onemap.pintypes.Desk
import com.rosetta.onemap.pintypes.Room

class BootStrap {
	
	private Office cloffice;
	private Zone freeZone;
	private Zone ahaZone;
	private Zone uidZone;
	
	def init = { servletContext ->
		
		////
		//	Offices
		////
		saveOffices()
		
		////
		//	Users
		////
		saveUsers()

		////
		//  Zones
		////
		saveZones()
		
		////
		//	Cleveland Office
		////
		saveCleveland11()
		saveCleveland12()
		saveCleveland13()
		saveCleveland14()
		saveCleveland15()
		saveCleveland17()
		
	}
	
	def destroy = {
	}
	
	private saveCleveland11() {
		new Room(name: "Nirvana", number: "1127", phone: "000.000.0000", office: cloffice, floor: "11", assignedSeatId: "1127", zone: freeZone, x: 243, y: 321, polygon: "M219.38,284.96 265.18,284.96 266.64,349.28 244.08,353.84 235.56,356.6z").save(flush: true);
		new Room(name: "Beastie Boys", number: "1141", phone: "000.000.0000", office: cloffice, floor: "11", assignedSeatId: "1141", zone: freeZone, x: 241, y: 470, polygon: "M209.16,461.36l13.08,54.066l51.3-12.067l-14.46-62.039l2.76-8.641l-9.66-3.239c0,0-20.6-5.111-21.9-4.2L215.4,459.8L209.16,461.36z").save(flush: true);

		new Desk(office: cloffice, floor: "11", assignedSeatId: "1139", zone: freeZone, x: 33, y: 29, polygon: "M318.12,452.72 327.12,490.76 274.08,503.359 261.36,450.56 274.44,447.44 285.24,445.16 287.838,454.939 294.72,453.56z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1121", zone: freeZone, x: 244, y: 142, polygon: "M222,115.28 265.2,114.68 265.2,169.16 222,169.16 226.13,143.72z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1117", zone: freeZone, x: 244, y: 18, polygon: "M221.88,5.96 265.20,5.96 265.20,30.32 221.88,30.32z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1118", zone: freeZone, x: 244, y: 45, polygon: "M221.88,31.52 265.2,31.52 265.2,58.28 222.96,58.28z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1119", zone: freeZone, x: 244, y: 73, polygon: "M223,59.49 265.2,59.72 265.2,86.24 226.32,86.12z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1120", zone: freeZone, x: 244, y: 101, polygon: "M226.32,87.86 265.2,87.32 265.2,113.84 222.3,113.55z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1122", zone: freeZone, x: 244, y: 198, polygon: "M222,171.2 265.2,170.66 265.2,225.8 222,225.8 226.299,198.44z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1123", zone: freeZone, x: 244, y: 241, polygon: "M222,227 265.2,227.72 265.2,253.04 226.68,254z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1124", zone: freeZone, x: 244, y: 268, polygon: "M226.68,254.96 265.073,254.84 265.2,280.881 222,280.28z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1138", zone: freeZone, x: 336, y: 466, polygon: "M319.2,452.48 328.2,490.52 352.8,484.598 342.36,442.04z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1136", zone: freeZone, x: 387, y: 454, polygon: "M370.44,440.48 379.44,478.4 404.52,472.4 394.561,428.96z").save(flush: true);
		new Desk(office: cloffice, floor: "11", assignedSeatId: "1137", zone: freeZone, x: 361, y: 463, polygon: "M369.526,440.55 378.615,478.76 353.641,484.598 343.306,441.409z").save(flush: true);
		if (Desk.findByAssignedSeatId("1125A") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125A", zone: freeZone, x: 191, y: 36, polygon: "M193.343,41.877l5.924-9.535c0,0-6.758-3.906-12.08-0.909c-5.323,2.998-4.978,10.238-4.978,10.238L193.343,41.877z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125B") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125B", zone: freeZone, x: 200, y: 42, polygon: "M193.343,41.877l5.58,9.665c0,0,7.307-4.029,7.191-10.139c-0.116-6.108-6.847-9.06-6.847-9.06L193.343,41.877z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125C") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125C", zone: freeZone, x: 191, y: 47, polygon: "M182.209,41.672l11.134,0.205l5.58,9.665c0,0-8.086,3.187-11.949,0.67C183.113,49.695,182.209,41.672,182.209,41.672z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125D") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125D", zone: freeZone, x: 191, y: 74, polygon: "M191.348,80.392l9.81-5.457c0,0-4.009-6.697-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L191.348,80.392z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125I") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125I", zone: freeZone, x: 198, y: 83, polygon: "M191.348,80.392l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.246-1.584-11.246L191.348,80.392z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125E") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125E", zone: freeZone, x: 186, y: 83, polygon: "M181.715,74.804l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C178.608,82.256,181.715,74.804,181.715,74.804z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125J") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125J", zone: freeZone, x: 191, y: 130, polygon: "M191.348,136.4l9.81-5.457c0,0-4.009-6.697-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L191.348,136.4z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125L") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125L", zone: freeZone, x: 198, y: 139, polygon: "M191.348,136.4l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.246-1.584-11.246L191.348,136.4z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125K") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125K", zone: freeZone, x: 186, y: 139, polygon: "M181.715,130.812l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C178.608,138.265,181.715,130.812,181.715,130.812z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125G") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125G", zone: freeZone, x: 191, y: 109, polygon: "M191.357,103.22l-9.81,5.457c0,0,4.009,6.697,10.118,6.663c6.109-0.035,9.325-6.531,9.325-6.531L191.357,103.22z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125F") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125F", zone: freeZone, x: 185, y: 100, polygon: "M191.357,103.22l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L191.357,103.22z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125H") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125H", zone: freeZone, x: 197, y: 100, polygon: "M200.989,108.808l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.218C204.096,101.355,200.989,108.808,200.989,108.808z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125O") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125O", zone: freeZone, x: 191, y: 167, polygon: "M191.348,172.766l9.81-5.457c0,0-4.009-6.697-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L191.348,172.766z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125P") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125P", zone: freeZone, x: 198, y: 176, polygon: "M191.348,172.766l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.245-1.584-11.245L191.348,172.766z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125N") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125N", zone: freeZone, x: 186, y: 176, polygon: "M181.715,167.178l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C178.608,174.63,181.715,167.178,181.715,167.178z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125R") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125R", zone: freeZone, x: 191, y: 202, polygon: "M191.357,195.594l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L191.357,195.594z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125M") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125M", zone: freeZone, x: 185, y: 193, polygon: "M191.357,195.594l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L191.357,195.594z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125Q") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Q", zone: freeZone, x: 197, y: 193, polygon: "M200.989,201.182l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C204.096,193.729,200.989,201.182,200.989,201.182z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125S") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125S", zone: freeZone, x: 191, y: 223, polygon: "M191.348,228.893l9.81-5.456c0,0-4.009-6.696-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L191.348,228.893z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125T") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125T", zone: freeZone, x: 198, y: 232, polygon: "M191.348,228.893l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.245-1.584-11.245L191.348,228.893z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125X") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125X", zone: freeZone, x: 186, y: 232, polygon: "M181.715,223.305l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C178.608,230.758,181.715,223.305,181.715,223.305z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125V") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125V", zone: freeZone, x: 191, y: 258, polygon: "M191.357,251.721l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L191.357,251.721z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125W") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125W", zone: freeZone, x: 185, y: 249, polygon: "M191.357,251.721l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L191.357,251.721z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125U") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125U", zone: freeZone, x: 197, y: 249, polygon: "M200.989,257.309l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C204.096,249.856,200.989,257.309,200.989,257.309z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132D") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132D", zone: freeZone, x: 335, y: 401, polygon: "M327.754,402.229l8.535,9.246c0,0,6.83-5.636,5.4-12.091c-1.426-6.456-9.666-8.507-9.666-8.507L327.754,402.229z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132C") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132C", zone: freeZone, x: 326, y: 409, polygon: "M327.754,402.229l-12.855,2.517c0,0,1.865,8.834,8.753,10.744c6.888,1.909,12.638-4.015,12.638-4.015L327.754,402.229z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132E") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132E", zone: freeZone, x: 323, y: 398, polygon: "M332.023,390.878l-4.27,11.352l-12.855,2.517c0,0-0.635-9.354,3.585-12.48C322.704,389.142,332.023,390.878,332.023,390.878z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132A") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132A", zone: freeZone, x: 293, y: 410, polygon: "M300.404,409.214l-8.669-9.318c0,0-6.934,5.681-5.483,12.188c1.451,6.508,9.817,8.574,9.817,8.574L300.404,409.214z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132F") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132F", zone: freeZone, x: 303, y: 402, polygon: "M300.404,409.214l13.055-2.534c0,0-1.894-8.905-8.89-10.831c-6.992-1.924-12.834,4.047-12.834,4.047L300.404,409.214z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132B") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132B", zone: freeZone, x: 305, y: 414, polygon: "M296.069,420.657l4.335-11.443l13.055-2.534c0,0,0.646,9.428-3.64,12.579C305.532,422.409,296.069,420.657,296.069,420.657z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1133D") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133D", zone: freeZone, x: 418, y: 380, polygon: "M410.608,381.38l8.535,9.246c0,0,6.83-5.636,5.4-12.091c-1.426-6.456-9.666-8.507-9.666-8.507L410.608,381.38z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133E") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133E", zone: freeZone, x: 408, y: 388, polygon: "M410.608,381.38l-12.855,2.517c0,0,1.865,8.834,8.753,10.744c6.888,1.909,12.638-4.015,12.638-4.015L410.608,381.38z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133C") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133C", zone: freeZone, x: 406, y: 377, polygon: "M414.878,370.028l-4.27,11.352l-12.855,2.517c0,0-0.635-9.354,3.585-12.48C405.559,368.292,414.878,370.028,414.878,370.028z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133A") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133A", zone: freeZone, x: 376, y: 389, polygon: "M383.259,388.364l-8.669-9.318c0,0-6.934,5.681-5.483,12.188c1.451,6.508,9.817,8.574,9.817,8.574L383.259,388.364z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133B") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133B", zone: freeZone, x: 385, y: 381, polygon: "M383.259,388.364l13.055-2.534c0,0-1.894-8.905-8.89-10.831c-6.992-1.924-12.834,4.047-12.834,4.047L383.259,388.364z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133F") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133F", zone: freeZone, x: 388, y: 393, polygon: "M378.924,399.808l4.335-11.443l13.055-2.534c0,0,0.646,9.428-3.64,12.579C388.387,401.56,378.924,399.808,378.924,399.808z").save(flush: true);
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
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Z1", zone: freeZone, x:155, y:152, polygon:"M154.867,157.886l9.81-5.457c0,0-4.009-6.697-10.118-6.663c-6.109,0.035-9.325,6.531-9.325,6.531L154.867,157.886z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125Z2") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Z2", zone: freeZone, x:161, y:161, polygon:"M154.867,157.886l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.245-1.584-11.245L154.867,157.886z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125Z") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Z", zone: freeZone, x:149, y:161, polygon:"M145.234,152.298l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.218C142.127,159.75,145.234,152.298,145.234,152.298z").save(flush: true);
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
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112L", zone: freeZone, x:108, y:193, polygon:"M107.905,187.375l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L107.905,187.375z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112Q") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112Q", zone: freeZone, x:102, y:105, polygon:"M107.905,187.375l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L107.905,187.375z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112P") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112P", zone: freeZone, x:113, y:185, polygon:"M117.537,192.963l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C120.644,185.511,117.537,192.963,117.537,192.963z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125Z4") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Z4", zone: freeZone, x:156, y:190, polygon:"M155.996,184.375l-9.81,5.457c0,0,4.009,6.696,10.118,6.662c6.109-0.035,9.325-6.531,9.325-6.531L155.996,184.375z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125Y") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Y", zone: freeZone, x:150, y:182, polygon:"M155.996,184.375l-0.183-11.158c0,0-8.344-0.027-11.211,5.369c-2.865,5.396,1.584,11.246,1.584,11.246L155.996,184.375z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1125Z3") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1125Z3", zone: freeZone, x:162, y:182, polygon:"M165.628,189.963l-9.632-5.588l-0.183-11.158c0,0,8.616,1.143,10.77,5.219C168.735,182.511,165.628,189.963,165.628,189.963z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112R") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112R", zone: freeZone, x:88, y:193, polygon:"M88.252,199.241l9.627-5.773c0,0-4.225-6.562-10.329-6.33c-6.104,0.234-9.108,6.831-9.108,6.831L88.252,199.241z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112K") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112K", zone: freeZone, x:94, y:202, polygon:"M88.252,199.241l0.545,11.146c0,0,8.341-0.244,11.031-5.73c2.688-5.487-1.949-11.189-1.949-11.189L88.252,199.241z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112J") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112J", zone: freeZone, x:83, y:202, polygon:"M78.443,193.969l9.809,5.272l0.545,11.146c0,0-8.648-0.862-10.934-4.866C75.581,201.518,78.443,193.969,78.443,193.969z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112O") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112O", zone: freeZone, x:127, y:193, polygon:"M127.425,199.241l9.627-5.773c0,0-4.225-6.562-10.329-6.33c-6.104,0.234-9.108,6.831-9.108,6.831L127.425,199.241z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112N") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112N", zone: freeZone, x:134, y:202, polygon:"M127.425,199.241l0.545,11.146c0,0,8.341-0.244,11.031-5.73c2.688-5.487-1.949-11.189-1.949-11.189L127.425,199.241z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1112M") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112M", zone: freeZone, x:122, y:202, polygon:"M117.616,193.969l9.809,5.272l0.545,11.146c0,0-8.648-0.862-10.934-4.866C114.753,201.518,117.616,193.969,117.616,193.969z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1132N") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132N", zone: freeZone, x:326, y:360, polygon:"M324.351,352.96l-8.865,8.628c0,0,5.905,6.278,12.286,4.602c6.382-1.68,8.083-9.68,8.083-9.68L324.351,352.96z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1132I") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132I", zone: freeZone, x:318, y:351, polygon:"M324.351,352.96l-3.045-12.207c0,0-8.734,2.211-10.354,8.908c-1.615,6.696,4.534,11.927,4.534,11.927L324.351,352.96z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1132J") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132J", zone: freeZone, x:329, y:349, polygon:"M335.854,356.51l-11.504-3.55l-3.045-12.207c0,0,9.304-1.06,12.6,2.84C337.199,347.49,335.854,356.51,335.854,356.51z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132H") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132H", zone: freeZone, x:301, y:365, polygon:"M302.921,372.381l8.832-9.167c0,0-6.266-6.237-12.766-4.292c-6.501,1.948-7.996,10.217-7.996,10.217L302.921,372.381z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132O") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132O", zone: freeZone, x:310, y:374, polygon:"M302.921,372.381l3.516,12.423c0,0,8.9-2.575,10.352-9.507c1.447-6.931-5.035-12.083-5.035-12.083L302.921,372.381z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132G") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132G", zone: freeZone, x:299, y:377, polygon:"M290.991,369.139l11.93,3.242l3.516,12.423c0,0-9.522,1.412-13.032-2.474C289.898,378.443,290.991,369.139,290.991,369.139z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132K") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132K", zone: freeZone, x:349, y:354, polygon:"M350.573,361.006l8.547-9.024c0,0-6.064-6.141-12.355-4.227c-6.291,1.919-7.738,10.059-7.738,10.059L350.573,361.006z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132L") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132L", zone: freeZone, x:357, y:363, polygon:"M350.573,361.006l3.402,12.231c0,0,8.615-2.536,10.019-9.359c1.4-6.823-4.874-11.896-4.874-11.896L350.573,361.006z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1132M") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1132M", zone: freeZone, x:346, y:366, polygon:"M339.026,357.813l11.547,3.192l3.402,12.231c0,0-9.217,1.391-12.612-2.436C337.969,366.975,339.026,357.813,339.026,357.813z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133N") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133N", zone: freeZone, x:321, y:335, polygon:"M419.556,328.663l-8.865,8.628c0,0,5.905,6.278,12.286,4.602c6.382-1.68,8.083-9.68,8.083-9.68L419.556,328.663z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133I") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133I", zone: freeZone, x:413, y:327, polygon:"M419.556,328.663l-3.045-12.207c0,0-8.734,2.211-10.354,8.908c-1.615,6.696,4.534,11.927,4.534,11.927L419.556,328.663z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133J") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133J", zone: freeZone, x:424, y:324, polygon:"M431.06,332.213l-11.504-3.55l-3.045-12.207c0,0,9.304-1.06,12.6,2.84C432.404,323.193,431.06,332.213,431.06,332.213z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133H") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133H", zone: freeZone, x:397, y:341, polygon:"M398.126,348.084l8.832-9.167c0,0-6.266-6.237-12.766-4.292c-6.501,1.948-7.996,10.217-7.996,10.217L398.126,348.084z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133O") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133O", zone: freeZone, x:405, y:350, polygon:"M398.126,348.084l3.516,12.423c0,0,8.9-2.575,10.352-9.507c1.447-6.931-5.035-12.083-5.035-12.083L398.126,348.084z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133G") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133G", zone: freeZone, x:394, y:353, polygon:"M386.196,344.842l11.93,3.242l3.516,12.423c0,0-9.522,1.412-13.032-2.474C385.104,354.146,386.196,344.842,386.196,344.842z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1133K") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133K", zone: freeZone, x:444, y:330, polygon:"M445.778,336.709l8.547-9.024c0,0-6.064-6.141-12.355-4.227c-6.291,1.919-7.738,10.059-7.738,10.059L445.778,336.709z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1133L") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133L", zone: freeZone, x:453, y:338, polygon:"M445.778,336.709l3.402,12.231c0,0,8.615-2.536,10.019-9.359c1.4-6.823-4.874-11.896-4.874-11.896L445.778,336.709z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1133M") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1133M", zone: freeZone, x:442, y:341, polygon:"M434.231,333.517l11.547,3.192l3.402,12.231c0,0-9.217,1.391-12.612-2.436C433.174,342.678,434.231,333.517,434.231,333.517z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1112Y") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112Y", zone: freeZone, x:108, y:148, polygon:"M107.915,153.747l9.81-5.457c0,0-4.009-6.696-10.118-6.662c-6.109,0.035-9.325,6.531-9.325,6.531L107.915,153.747z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1112U") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112U", zone: freeZone, x:114, y:157, polygon:"M107.915,153.747l0.183,11.158c0,0,8.344,0.027,11.211-5.369c2.865-5.396-1.584-11.246-1.584-11.246L107.915,153.747z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1112T") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112T", zone: freeZone, x:102, y:157, polygon:"M98.282,148.159l9.632,5.588l0.183,11.158c0,0-8.616-1.143-10.77-5.219C95.175,155.611,98.282,148.159,98.282,148.159z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1112V") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112V", zone: freeZone, x:128, y:148, polygon:"M127.567,141.881l-9.627,5.773c0,0,4.225,6.562,10.329,6.33c6.104-0.234,9.108-6.831,9.108-6.831L127.567,141.881z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1112X") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112X", zone: freeZone, x:121, y:139, polygon:"M127.567,141.881l-0.545-11.146c0,0-8.341,0.244-11.031,5.73c-2.688,5.487,1.949,11.189,1.949,11.189L127.567,141.881z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1112W") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112W", zone: freeZone, x:133, y:139, polygon:"M137.376,147.153l-9.809-5.272l-0.545-11.146c0,0,8.648,0.862,10.934,4.866C140.239,139.604,137.376,147.153,137.376,147.153z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1112S") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112S", zone: freeZone, x:88, y:148, polygon:"M88.394,141.881l-9.627,5.773c0,0,4.225,6.562,10.329,6.33c6.104-0.234,9.108-6.831,9.108-6.831L88.394,141.881z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1112Z1") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112Z1", zone: freeZone, x:82, y:139, polygon:"M88.394,141.881l-0.545-11.146c0,0-8.341,0.244-11.031,5.73c-2.688,5.487,1.949,11.189,1.949,11.189L88.394,141.881z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1112Z") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1112Z", zone: freeZone, x:94, y:139, polygon:"M98.203,147.153l-9.809-5.272l-0.545-11.146c0,0,8.648,0.862,10.934,4.866C101.066,139.604,98.203,147.153,98.203,147.153z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135D") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135D", zone: freeZone, x:428, y:426, polygon:"M416.678,421.211l3.873,10.108l11.495,1.038l6.996-8.332c0,0-7.624-3.794-11.864-4.429C422.938,418.961,416.678,421.211,416.678,421.211z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135J") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135J", zone: freeZone, x:480, y:413, polygon:"M469.117,407.87l3.873,10.108l11.495,1.038l6.996-8.332c0,0-7.624-3.794-11.864-4.429C475.377,405.62,469.117,407.87,469.117,407.87z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135C") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135C", zone: freeZone, x:413, y:431, polygon:"M415.14,441.098l6.36-8.76l-5.4-10.2l-10.8-1.319c0,0,0.979,8.459,2.76,12.359S415.14,441.098,415.14,441.098z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135H") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135H", zone: freeZone, x:481, y:429, polygon:"M482.94,439.34l6.36-8.76l-5.4-10.2l-10.8-1.319c0,0,0.979,8.459,2.76,12.359S482.94,439.34,482.94,439.34z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135B") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135B", zone: freeZone, x:429, y:441, polygon:"M430.56,451.237l6.36-8.76l-5.4-10.2l-10.8-1.319c0,0,0.979,8.459,2.76,12.359S430.56,451.237,430.56,451.237z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135I") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135I", zone: freeZone, x:465, y:417, polygon:"M466.94,426.859l6.36-8.76l-5.4-10.2l-10.8-1.319c0,0,0.979,8.459,2.76,12.359S466.94,426.859,466.94,426.859z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135G") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135G", zone: freeZone, x:494, y:437, polygon:"M504.901,441.44l-4.325-9.923l-11.53-0.517l-6.612,8.641c0,0,7.787,3.444,12.052,3.887S504.901,441.44,504.901,441.44z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135A") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135A", zone: freeZone, x:442, y:449, polygon:"M452.834,453.299l-4.325-9.923l-11.53-0.517l-6.612,8.641c0,0,7.787,3.444,12.052,3.887S452.834,453.299,452.834,453.299z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135F") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135F", zone: freeZone, x:456, y:444, polygon:"M455.121,433.408l-6.828,8.4l4.836,10.48l10.711,1.905c0,0-0.516-8.5-2.081-12.491C460.194,437.711,455.121,433.408,455.121,433.408z").save(flush: true);
		} 
		if (Desk.findByAssignedSeatId("1135K") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135K", zone: freeZone, x:492, y:421, polygon:"M490.935,410.946l-6.828,8.4l4.836,10.48l10.711,1.905c0,0-0.516-8.5-2.081-12.491C496.008,415.249,490.935,410.946,490.935,410.946z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1135E") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135E", zone: freeZone, x:440, y:434, polygon:"M438.795,423.545l-6.828,8.4l4.836,10.48l10.711,1.905c0,0-0.516-8.5-2.081-12.491C443.868,427.848,438.795,423.545,438.795,423.545z").save(flush: true);
		}
		if (Desk.findByAssignedSeatId("1135L") == null) {
			new Desk(office: cloffice, floor: "11", assignedSeatId: "1135L", zone: freeZone, x:508, y:432, polygon:"M507.255,421.928l-6.828,8.4l4.836,10.48l10.711,1.905c0,0-0.516-8.5-2.081-12.491C512.328,426.23,507.255,421.928,507.255,421.928z").save(flush: true);
		}
	}
	
	private saveCleveland12() {
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219F", zone: freeZone, x:181, y:70, polygon: "M187.989,67.63l-10.955-6.057c0,0-4.288,7.195-0.765,12.744c3.524,5.555,11.736,5.047,11.736,5.047L187.989,67.63z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219A", zone: freeZone, x:188, y:61, polygon: "M187.989,67.63l10.848-6.066c0,0-4.734-7.621-11.667-7.381c-6.933,0.239-10.135,7.39-10.135,7.39L187.989,67.63z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219B", zone: freeZone, x:194, y:70, polygon: "M188.006,79.364l-0.017-11.734l10.848-6.066c0,0,3.799,8.464,1.029,12.579C197.095,78.261,188.006,79.364,188.006,79.364z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219E", zone: freeZone, x:181, y:89, polygon: "M187.676,92.21l-0.216-12.676c0,0-8.601-0.131-11.737,5.764c-3.142,5.898,1.515,12.852,1.515,12.852L187.676,92.21z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219C", zone: freeZone, x:194, y:89, polygon: "M187.676,92.21l10.955,6.461c0,0,4.361-8.004,0.598-13.97c-3.763-5.965-11.769-5.167-11.769-5.167L187.676,92.21z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219D", zone: freeZone, x:188, y:99, polygon: "M177.238,98.149l10.438-5.939l10.955,6.461c0,0-5.59,7.608-10.672,7.255C182.874,105.572,177.238,98.149,177.238,98.149z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219K", zone: freeZone, x:181, y:133, polygon: "M187.83,130.255l-11.02-6.469c0,0-4.491,7.573-1.014,13.458c3.477,5.891,11.831,5.408,11.831,5.408L187.83,130.255z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219L", zone: freeZone, x:188, y:123, polygon: "M187.83,130.255l11.136-6.337c0,0-4.668-8.081-11.718-7.873c-7.049,0.208-10.438,7.741-10.438,7.741L187.83,130.255z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219M", zone: freeZone, x:194, y:133, polygon: "M187.627,142.651l0.203-12.396l11.136-6.337c0,0,3.703,8.966,0.812,13.295C196.884,141.544,187.627,142.651,187.627,142.651z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219O", zone: freeZone, x:188, y:162, polygon: "M187.85,155l-11.076,6.372c0,0,4.356,7.651,11.191,7.544c6.84-0.104,10.556-7.602,10.556-7.602L187.85,155z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219J", zone: freeZone, x:181, y:152, polygon: "M187.85,155l0.007-12.813c0,0-9.332,0.055-12.642,6.284c-3.309,6.228,1.559,12.901,1.559,12.901L187.85,155z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219N", zone: freeZone, x:194, y:152, polygon: "M198.521,161.313L187.85,155l0.007-12.813c0,0,9.623,1.222,11.954,5.877C202.142,152.722,198.521,161.313,198.521,161.313z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219U", zone: freeZone, x:195, y:195, polygon: "M187.931,192.215l-0.022,12.367c0,0,8.804,0.054,12.13-5.728c3.33-5.782-1.304-12.525-1.304-12.525L187.931,192.215z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219Q", zone: freeZone, x:181, y:195, polygon: "M187.931,192.215l-11.091-6.207c0,0-4.62,7.847-0.882,13.637c3.738,5.787,11.951,4.938,11.951,4.938L187.931,192.215z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219P", zone: freeZone, x:188, y:186, polygon: "M198.735,186.329l-10.804,5.886l-11.091-6.207c0,0,5.87-7.475,11.068-7.174C193.107,179.135,198.735,186.329,198.735,186.329z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219T", zone: freeZone, x:194, y:214, polygon: "M187.876,217.485l10.547,6.325c0,0,4.39-7.738,1.096-13.619c-3.293-5.887-11.336-5.237-11.336-5.237L187.876,217.485z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219S", zone: freeZone, x:188, y:225, polygon: "M187.876,217.485l-10.772,6.62c0,0,4.42,8.076,11.207,7.731c6.785-0.348,10.112-8.026,10.112-8.026L187.876,217.485z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219R", zone: freeZone, x:182, y:215, polygon: "M188.183,204.954l-0.306,12.531l-10.772,6.62c0,0-3.484-8.989-0.663-13.422C179.264,206.251,188.183,204.954,188.183,204.954z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219X", zone: freeZone, x:195, y:258, polygon: "M188.136,254.3l-0.023,12.606c0,0,9.028,0.055,12.44-5.838c3.415-5.895-1.337-12.769-1.337-12.769L188.136,254.3z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219W", zone: freeZone, x:181, y:257, polygon: "M188.136,254.3l-11.375-6.327c0,0-4.738,7.998-0.905,13.9c3.834,5.899,12.256,5.033,12.256,5.033L188.136,254.3z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219V", zone: freeZone, x:188, y:247, polygon: "M199.216,248.3l-11.08,6l-11.375-6.327c0,0,6.02-7.62,11.351-7.313C193.444,240.966,199.216,248.3,199.216,248.3z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219G", zone: freeZone, x:155, y:154, polygon: "M151.681,147.489l-5.888,11.123c0,0,7.835,4.26,13.548,0.646c5.717-3.612,4.779-11.899,4.779-11.899L151.681,147.489z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219H", zone: freeZone, x:145, y:148, polygon: "M151.681,147.489l-6.958-10.893c0,0-7.848,4.853-7.258,11.854c0.592,6.998,8.328,10.162,8.328,10.162L151.681,147.489z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1219I", zone: freeZone, x:154, y:141, polygon: "M164.12,147.359l-12.439,0.13l-6.958-10.893c0,0,8.788-3.921,13.286-1.165C162.508,138.191,164.12,147.359,164.12,147.359z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1212O", zone: freeZone, x:141, y:183, polygon: "M134.043,179.387l-0.025,13.147c0,0,9.762,0.058,13.45-6.089c3.692-6.147-1.446-13.316-1.446-13.316L134.043,179.387z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1212Q", zone: freeZone, x:127, y:183, polygon: "M134.043,179.387l-12.298-6.599c0,0-5.123,8.342-0.978,14.497c4.145,6.152,13.251,5.249,13.251,5.249L134.043,179.387z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1212P", zone: freeZone, x:134, y:172, polygon: "M146.022,173.129l-11.979,6.257l-12.298-6.599c0,0,6.509-7.947,12.272-7.627C139.782,165.481,146.022,173.129,146.022,173.129z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1212A", zone: freeZone, x:89, y:199, polygon: "M81.647,195.415l-0.025,12.999c0,0,9.763,0.056,13.452-6.021c3.693-6.077-1.446-13.165-1.446-13.165L81.647,195.415z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1212D", zone: freeZone, x:74, y:199, polygon: "M81.647,195.415l-12.3-6.523c0,0-5.124,8.247-0.979,14.332c4.146,6.083,13.253,5.19,13.253,5.19L81.647,195.415z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1212E", zone: freeZone, x:81, y:188, polygon: "M93.628,189.229l-11.981,6.187l-12.3-6.523c0,0,6.51-7.857,12.273-7.542C87.387,181.667,93.628,189.229,93.628,189.229z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1212J", zone: freeZone, x:81, y:156, polygon: "M80.719,148.842l-11.884,6.006c0,0,4.905,8.168,12.327,8.428c7.424,0.262,11.288-7.31,11.288-7.31L80.719,148.842z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1212I", zone: freeZone, x:73, y:145, polygon: "M80.719,148.842l-0.288-13.282c0,0-10.132-0.449-13.584,5.829c-3.45,6.278,1.988,13.459,1.988,13.459L80.719,148.842z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1212K", zone: freeZone, x:87, y:146, polygon: "M92.449,155.966l-11.73-7.124l-0.288-13.282c0,0,10.479,1.786,13.117,6.74C96.185,147.255,92.449,155.966,92.449,155.966z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234M", zone: freeZone, x:305, y:369, polygon: "M303.735,361.855l-9.443,8.627c0,0,6.317,6.609,13.127,5.052c6.812-1.556,8.605-9.728,8.605-9.728L303.735,361.855z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234N", zone: freeZone, x:296, y:360, polygon: "M303.735,361.855l-3.281-12.618c0,0-9.317,2.035-11.03,8.873c-1.711,6.835,4.868,12.372,4.868,12.372L303.735,361.855z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234O", zone: freeZone, x:308, y:357, polygon: "M316.024,365.807l-12.289-3.951l-3.281-12.618c0,0,9.938-0.843,13.463,3.248C317.443,356.577,316.024,365.807,316.024,365.807z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234L", zone: freeZone, x:323, y:374, polygon: "M327.53,369.719l-12.023-3.722c0,0-2.437,8.747,2.286,13.788c4.722,5.045,12.535,2.449,12.535,2.449L327.53,369.719z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234P", zone: freeZone, x:326, y:362, polygon: "M327.53,369.719l9.041-9.187c0,0-6.38-6.946-13.025-4.956c-6.641,1.99-8.039,10.421-8.039,10.421L327.53,369.719z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234Q", zone: freeZone, x:334, y:371, polygon: "M330.328,382.234l-2.798-12.516l9.041-9.187c0,0,5.682,8.081,3.98,13.166C338.851,378.784,330.328,382.234,330.328,382.234z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234D", zone: freeZone, x:294, y:417, polygon: "M292.996,409.831l-9.443,8.627c0,0,6.317,6.609,13.127,5.052c6.812-1.556,8.605-9.728,8.605-9.728L292.996,409.831z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234K", zone: freeZone, x:298, y:405, polygon: "M305.285,413.782l-12.289-3.951l-3.281-12.618c0,0,9.938-0.843,13.463,3.248C306.704,404.553,305.285,413.782,305.285,413.782z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234G", zone: freeZone, x:337, y:406, polygon: "M335.366,399.523l-9.288,8.476c0,0,6.293,6.535,13.02,5.021c6.729-1.512,8.453-9.562,8.453-9.562L335.366,399.523z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234I", zone: freeZone, x:328, y:398, polygon: "M335.366,399.523l-3.326-12.451c0,0-9.206,1.977-10.858,8.714c-1.647,6.732,4.896,12.213,4.896,12.213L335.366,399.523z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234H", zone: freeZone, x:340, y:395, polygon: "M347.551,403.459l-12.185-3.936l-3.326-12.451c0,0,9.827-0.799,13.342,3.246C348.896,394.363,347.551,403.459,347.551,403.459z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234F", zone: freeZone, x:323, y:418, polygon: "M316.412,416.194l2.397,12.696c0,0,8.712-1.96,10.87-8.653c2.159-6.695-3.739-12.555-3.739-12.555L316.412,416.194z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234E", zone: freeZone, x:311, y:421, polygon: "M316.412,416.194l-12.179-3.83c0,0-3.032,9.108,1.797,14.195c4.827,5.082,12.779,2.331,12.779,2.331L316.412,416.194z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234J", zone: freeZone, x:315, y:409, polygon: "M325.94,407.683l-9.528,8.512l-12.179-3.83c0,0,4.341-9.015,9.539-9.896C318.97,401.587,325.94,407.683,325.94,407.683z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235A", zone: freeZone, x:359, y:357, polygon: "M357.898,350.166l-9.308,8.471c0,0,6.299,6.539,13.039,5.028c6.744-1.508,8.473-9.558,8.473-9.558L357.898,350.166z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235B", zone: freeZone, x:351, y:348, polygon: "M357.898,350.166l-3.326-12.452c0,0-9.223,1.971-10.881,8.707c-1.655,6.731,4.899,12.216,4.899,12.216L357.898,350.166z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235C", zone: freeZone, x:362, y:346, polygon: "M370.103,354.107l-12.204-3.941l-3.326-12.452c0,0,9.848-0.794,13.365,3.252C371.456,345.014,370.103,354.107,370.103,354.107z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235I", zone: freeZone, x:377, y:362, polygon: "M381.922,357.415l-11.975-3.8c0,0-2.547,8.558,2.113,13.564c4.657,5.013,12.502,2.559,12.502,2.559L381.922,357.415z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235D", zone: freeZone, x:381, y:350, polygon: "M381.922,357.415l9.153-8.908c0,0-6.29-6.898-12.961-5.025c-6.663,1.873-8.167,10.134-8.167,10.134L381.922,357.415z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235H", zone: freeZone, x:389, y:359, polygon: "M384.562,369.738l-2.641-12.323l9.153-8.908c0,0,5.581,8.005,3.817,12.978C393.127,366.457,384.562,369.738,384.562,369.738z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235G", zone: freeZone, x:402, y:347, polygon: "M400.346,339.902l-8.968,8.913c0,0,6.312,6.416,12.897,4.652c6.59-1.759,8.126-9.985,8.126-9.985L400.346,339.902z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235E", zone: freeZone, x:393, y:338, polygon: "M400.346,339.902l-3.514-12.516c0,0-9.012,2.313-10.506,9.204c-1.49,6.886,5.052,12.225,5.052,12.225L400.346,339.902z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235F", zone: freeZone, x:405, y:335, polygon: "M412.401,343.482l-12.056-3.58l-3.514-12.516c0,0,9.65-1.144,13.185,2.841C413.55,334.213,412.401,343.482,412.401,343.482z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235J", zone: freeZone, x:366, y:409, polygon: "M370.628,404.019l-11.951-3.944c0,0-2.601,8.699,2.027,13.827c4.627,5.133,12.487,2.683,12.487,2.683L370.628,404.019z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235K", zone: freeZone, x:369, y:397, polygon: "M370.628,404.019l9.211-9.016c0,0-6.248-7.064-12.93-5.198c-6.677,1.865-8.232,10.27-8.232,10.27L370.628,404.019z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235R", zone: freeZone, x:377, y:406, polygon: "M373.191,416.584l-2.563-12.565l9.211-9.016c0,0,5.53,8.187,3.734,13.238C381.777,413.295,373.191,416.584,373.191,416.584z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235Q", zone: freeZone, x:391, y:394, polygon: "M389.07,386.666l-9.308,8.471c0,0,6.299,6.539,13.039,5.028c6.744-1.508,8.473-9.558,8.473-9.558L389.07,386.666z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235L", zone: freeZone, x:382, y:385, polygon: "M389.07,386.666l-3.326-12.452c0,0-9.223,1.971-10.881,8.707c-1.655,6.731,4.899,12.216,4.899,12.216L389.07,386.666z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235M", zone: freeZone, x:394, y:382, polygon: "M401.274,390.607l-12.204-3.941l-3.326-12.452c0,0,9.848-0.794,13.365,3.252C402.628,381.514,401.274,390.607,401.274,390.607z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235O", zone: freeZone, x:420, y:395, polygon: "M413.052,393.394l2.617,12.458c0,0,8.708-2.189,10.763-8.858c2.058-6.673-3.959-12.28-3.959-12.28L413.052,393.394z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235P", zone: freeZone, x:408, y:398, polygon: "M413.052,393.394l-12.285-3.421c0,0-2.893,9.077,2.038,13.954c4.928,4.873,12.864,1.925,12.864,1.925L413.052,393.394z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235N", zone: freeZone, x:412, y:387, polygon: "M422.473,384.713l-9.421,8.681l-12.285-3.421c0,0,4.207-9.025,9.408-10.047C415.377,378.904,422.473,384.713,422.473,384.713z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235X", zone: freeZone, x:405, y:443, polygon: "M403.17,436.224l-9.041,8.201c0,0,6.119,6.331,12.666,4.868c6.551-1.46,8.23-9.254,8.23-9.254L403.17,436.224z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235S", zone: freeZone, x:396, y:434, polygon: "M403.17,436.224l-3.23-12.057c0,0-8.959,1.908-10.569,8.431c-1.607,6.518,4.759,11.827,4.759,11.827L403.17,436.224z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235T", zone: freeZone, x:408, y:432, polygon: "M415.025,440.039l-11.855-3.815l-3.23-12.057c0,0,9.565-0.769,12.982,3.148C416.34,431.234,415.025,440.039,415.025,440.039z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235V", zone: freeZone, x:434, y:445, polygon: "M426.926,443.354l2.201,12.007c0,0,8.606-1.861,10.815-8.196c2.213-6.336-3.523-11.872-3.523-11.872L426.926,443.354z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235W", zone: freeZone, x:422, y:448, polygon: "M426.926,443.354l-11.948-3.612c0,0-3.103,8.619,1.591,13.427c4.69,4.802,12.559,2.192,12.559,2.192L426.926,443.354z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1235U", zone: freeZone, x:426, y:437, polygon: "M436.419,435.293l-9.493,8.062l-11.948-3.612c0,0,4.391-8.532,9.521-9.371C429.63,429.533,436.419,435.293,436.419,435.293z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234V", zone: freeZone, x:381, y:458, polygon: "M373.721,455.848l2.732,12.061c0,0,8.688-2.235,10.678-8.736c1.995-6.505-4.073-11.872-4.073-11.872L373.721,455.848z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234W", zone: freeZone, x:369, y:461, polygon: "M373.721,455.848l-12.313-3.167c0,0-2.808,8.85,2.168,13.524c4.973,4.667,12.878,1.703,12.878,1.703L373.721,455.848z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234U", zone: freeZone, x:372, y:449, polygon: "M383.058,447.3l-9.337,8.548l-12.313-3.167c0,0,4.123-8.815,9.312-9.875C375.912,441.749,383.058,447.3,383.058,447.3z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234T", zone: freeZone, x:355, y:444, polygon: "M349.779,449.034l11.818,3.617c0,0,2.372-8.651-2.28-13.606c-4.652-4.962-12.319-2.359-12.319-2.359L349.779,449.034z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234X", zone: freeZone, x:351, y:456, polygon: "M349.779,449.034l-8.857,9.116c0,0,6.284,6.831,12.809,4.833c6.516-1.999,7.867-10.332,7.867-10.332L349.779,449.034z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234S", zone: freeZone, x:343, y:447, polygon: "M346.998,436.686l2.781,12.349l-8.857,9.116c0,0-5.601-7.956-3.945-12.986C338.638,440.132,346.998,436.686,346.998,436.686z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234Y", zone: freeZone, x:338, y:468, polygon: "M331.307,465.932l2.682,12.138c0,0,8.524-2.25,10.478-8.792c1.957-6.547-3.997-11.948-3.997-11.948L331.307,465.932z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234Z", zone: freeZone, x:326, y:471, polygon: "M331.307,465.932l-12.082-3.187c0,0-2.756,8.905,2.127,13.61c4.879,4.697,12.637,1.714,12.637,1.714L331.307,465.932z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234R", zone: freeZone, x:330, y:459, polygon: "M340.469,457.329l-9.162,8.603l-12.082-3.187c0,0,4.045-8.872,9.137-9.938C333.457,451.743,340.469,457.329,340.469,457.329z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1212F", zone: freeZone, x:42, y:128, polygon: "M49.426,120.142l-12.901-0.394c0,0-3.891,9.215,0.131,13.675c4.023,4.456,12.855,0.68,12.855,0.68L49.426,120.142z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1212H", zone: freeZone, x:74, y:128, polygon: "M80.432,120.83l-12.901-0.394c0,0-2.655,8.354,1.367,12.814c4.023,4.456,11.62,1.541,11.62,1.541L80.432,120.83z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1212C", zone: freeZone, x:67, y:217, polygon: "M81.847,223.878l0.233-14.118c0,0-15.6-0.251-21.24,3.99c-5.308,3.991-8.339,10.285-8.339,10.285L81.847,223.878z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1212M", zone: freeZone, x:106, y:132, polygon: "M117.823,120.163L93.36,120.42c0,0,1.343,9.012,5.52,14.7c3.932,5.353,17.859,9.63,17.859,9.63L117.823,120.163z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1212L", zone: freeZone, x:87, y:128, polygon: "M81.54,120.6l-1.022,14.19c0,0,6.6,1.583,10.922-2.178c4.32-3.765,1.476-11.53,1.476-11.53L81.54,120.6z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1212G", zone: freeZone, x:56, y:128, polygon: "M49.48,120.093l0.062,14.677c0,0,6.6,1.583,10.922-2.178c4.32-3.765-0.264-12.472-0.264-12.472L49.48,120.093z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1212B", zone: freeZone, x:93, y:217, polygon: "M81.96,223.766l21.995,0.502c0,0,0.63-9.547-5.855-12.348c-6.218-2.686-16.02-2.16-16.02-2.16L81.96,223.766z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1212N", zone: freeZone, x:146, y:201, polygon: "M134.104,207.872l22.837,0.114c0,0,0.832-9.94-5.853-12.732c-6.409-2.678-16.6-1.949-16.6-1.949L134.104,207.872z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1212R", zone: freeZone, x:123, y:201, polygon: "M134.557,208.242l0.233-14.118c0,0-13.56-2.865-19.2,1.376c-5.308,3.991-3.75,12.82-3.75,12.82L134.557,208.242z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234C", zone: freeZone, x:301, y:448, polygon: "M291.6,440.88l3.36,12l11.525,3.965C306.485,456.845,319.26,431.985,291.6,440.88z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234B", zone: freeZone, x:307, y:467, polygon: "M306.567,456.129l-8.458,9.151l2.666,11.894C300.775,477.174,328.642,475.021,306.567,456.129z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1234A", zone: freeZone, x:310, y:485, polygon: "M300.883,477.872l3.348,12.003l11.521,3.977C315.752,493.852,328.552,469.005,300.883,477.872z").save(flush: true);
	
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1221", zone: freeZone, x:242, y:20, polygon: "M221.123,7.642 264,8.012 263.878,31.21 220.611,31.895z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1222", zone: freeZone, x:242, y:46, polygon: "M220.8,32.64 220.8,59.4 260.88,59.4 264.12,54.84 263.88,33.12z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1223", zone: freeZone, x:243, y:74, polygon: "M220.92,60.6 225,86.52 264.12,86.88 264.12,65.16 260.64,64.5 260.88,60.6z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1224", zone: freeZone, x:243, y:102, polygon: "M225.72,88.56 220.92,115.32 260.88,115.32 260.88,111.72 264.12,111.72 264.12,88.56z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1225", zone: freeZone, x:243, y:130, polygon: "M220.92,116.52 225.72,143.28 263.88,142.8 264.12,120.12 261.24,120.12 260.88,116.52z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1226", zone: freeZone, x:243, y:158, polygon: "M225.6,145.44 220.92,171.12 260.88,171.12 260.88,167.28 264.12,167.28 263.52,144.48z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1216", zone: freeZone, x:21, y:138, polygon: "M8.76,121.75 8.76,157.32 32.76,157.32 33.36,119.4z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1236", zone: freeZone, x:443, y:323, polygon: "M424.32,314.597 423.36,310.561 449.64,304.2 451.561,307.258 454.2,307.013 464.28,322.18 434.085,339.925 427.92,341.138 420.96,316.309z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1227", zone: freeZone, x:242, y:200, polygon: "M220.92,172.32 225.24,199.68 219.72,227.039 261,227.04 261.24,222.36 264.12,222.36 264.12,175.56 261.08,175.5 260.88,172.32z").save(flush: true);
		new Desk(office: cloffice, floor: "12", assignedSeatId: "1228", zone: freeZone, x:242, y:255, polygon: "M220.92,228.24 225.24,255.6 220.2,281.76 261.12,281.52 261.24,278.04 264.12,278.04 264.12,232.92 260.64,233.16 261,228.24z").save(flush: true);
		
		new Room(name: "U2", number: "1231", phone: "000.000.0000", office: cloffice, floor: "12", assignedSeatId: "1231", zone: freeZone, x:231, y:310, polygon: "M197.88,326.28 197.76,287.28 215.64,287.28 215.64,282.72 254.76,282.72 254.76,287.28 264.12,287.28 264.12,337.32z").save(flush: true);
		new Room(name: "Billy Joel", number: "1243", phone: "000.000.0000", office: cloffice, floor: "12", assignedSeatId: "1243", zone: freeZone, x:244, y:471, polygon: "M250.68,430.8 229.2,426.48 214.56,463.2 225.72,511.56 229.32,510.72 230.16,514.56 272.04,504.6 272.76,500.28 257.88,442.44 260.28,434.4z").save(flush: true);
		new Room(name: "Van Halen", number: "1239", phone: "000.000.0000", office: cloffice, floor: "12", assignedSeatId: "1239", zone: freeZone, x:491, y:422, polygon: "M454.68,456 442.32,399 505.2,384.12 539.4,435.96 537.24,437.4 539.4,440.64 459,460.56 458.34,457.808 455.64,458.16z").save(flush: true);
		new Room(name: "Run DMC", number: "1237", phone: "000.000.0000", office: cloffice, floor: "12", assignedSeatId: "1237", zone: freeZone, x:459, y:345, polygon: "M428.04,344.64 430.92,358.68 447.72,356.16 455.521,367.92 489.36,360 483.84,351.6 481.33,353.25 478.561,348.36 480.72,346.92 464.88,322.8z").save(flush: true);
	}
	
	private saveCleveland13() {
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317I", zone: freeZone, x:191, y:89, polygon: "M191.197,82.538l-10.495,6.423c0,0,4.028,7.224,10.466,6.931c6.443-0.291,10.017-7.591,10.017-7.591L191.197,82.538z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317G", zone: freeZone, x:185, y:80, polygon: "M191.197,82.538l0.132-12.3c0,0-8.791,0.312-11.969,6.384c-3.177,6.069,1.342,12.34,1.342,12.34L191.197,82.538z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317H", zone: freeZone, x:197, y:79, polygon: "M201.184,88.301l-9.987-5.764l0.132-12.3c0,0,9.05,0.906,11.2,5.31C204.678,79.954,201.184,88.301,201.184,88.301z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317K", zone: freeZone, x:186, y:122, polygon: "M191.903,118.991l-10.362-6.025c0,0-4.127,7.159-0.823,12.68c3.307,5.526,11.111,5.021,11.111,5.021L191.903,118.991z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317L", zone: freeZone, x:192, y:112, polygon: "M191.903,118.991l10.351-6.038c0,0-4.439-7.582-11.029-7.342c-6.587,0.239-9.684,7.354-9.684,7.354L191.903,118.991z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317M", zone: freeZone, x:198, y:122, polygon: "M191.83,130.666l0.073-11.675l10.351-6.038c0,0,3.544,8.42,0.882,12.516C200.473,129.566,191.83,130.666,191.83,130.666z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317O", zone: freeZone, x:191, y:148, polygon: "M191.377,141.692l-10.277,5.638c0,0,3.941,7.021,10.244,7.019c6.309,0.001,9.81-6.765,9.81-6.765L191.377,141.692z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317J", zone: freeZone, x:185, y:139, polygon: "M191.377,141.692l0.131-11.654c0,0-8.606-0.082-11.72,5.538c-3.112,5.616,1.312,11.754,1.312,11.754L191.377,141.692z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317N", zone: freeZone, x:197, y:139, polygon: "M201.154,147.584l-9.777-5.892l0.131-11.654c0,0,8.861,1.247,10.964,5.514C204.576,139.822,201.154,147.584,201.154,147.584z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317Q", zone: freeZone, x:185, y:179, polygon: "M190.836,176.086l-9.847-5.896c0,0-3.977,6.815-0.855,12.145c3.122,5.336,10.568,4.94,10.568,4.94L190.836,176.086z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317R", zone: freeZone, x:191, y:170, polygon: "M190.836,176.086l9.903-5.668c0,0-4.191-7.319-10.477-7.166c-6.283,0.154-9.274,6.938-9.274,6.938L190.836,176.086z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317S", zone: freeZone, x:197, y:179, polygon: "M190.702,187.275l0.134-11.189l9.903-5.668c0,0,3.334,8.111,0.773,12.007C198.949,186.321,190.702,187.275,190.702,187.275z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317P", zone: freeZone, x:185, y:196, polygon: "M190.945,198.659l-0.296-11.314c0,0-7.978,0.286-10.84,5.7c-2.868,5.418,1.503,11.41,1.503,11.41L190.945,198.659z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317T", zone: freeZone, x:197, y:196, polygon: "M190.945,198.659l10.208,5.254c0,0,3.984-7.354,0.447-12.508c-3.535-5.151-10.951-4.061-10.951-4.061L190.945,198.659z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317U", zone: freeZone, x:191, y:205, polygon: "M181.311,204.455l9.634-5.796l10.208,5.254c0,0-5.124,7.061-9.84,6.983C186.594,210.819,181.311,204.455,181.311,204.455z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317X", zone: freeZone, x:191, y:226, polygon: "M190.723,232.167l9.729-5.391c0,0-3.769-7.03-9.75-7.147c-5.986-0.119-9.28,6.518-9.28,6.518L190.723,232.167z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317Y", zone: freeZone, x:197, y:235, polygon: "M190.723,232.167l-0.076,11.54c0,0,8.167,0.244,11.097-5.264c2.93-5.505-1.292-11.667-1.292-11.667L190.723,232.167z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317W", zone: freeZone, x:185, y:235, polygon: "M181.421,226.146l9.301,6.021l-0.076,11.54c0,0-8.413-1.401-10.427-5.669C178.206,233.77,181.421,226.146,181.421,226.146z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317V", zone: freeZone, x:185, y:252, polygon: "M190.979,254.833L191,243.431c0,0-7.76-0.081-10.689,5.234c-2.934,5.32,1.148,11.554,1.148,11.554L190.979,254.833z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317Z", zone: freeZone, x:197, y:252, polygon: "M190.979,254.833l9.774,5.761c0,0,4.072-7.218,0.777-12.566c-3.294-5.35-10.53-4.597-10.53-4.597L190.979,254.833z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317Z1", zone: freeZone, x:191, y:261, polygon: "M181.46,260.219l9.52-5.386l9.774,5.761c0,0-5.173,6.867-9.753,6.572C186.418,266.868,181.46,260.219,181.46,260.219z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317C", zone: freeZone, x:150, y:160, polygon: "M156.62,157.72l-10.466-5.684c0,0-4.247,6.646-0.937,11.813c3.311,5.173,11.234,4.753,11.234,4.753L156.62,157.72z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317D", zone: freeZone, x:157, y:151, polygon: "M156.62,157.72l10.552-5.561c0,0-4.444-7.096-11.133-6.916c-6.686,0.18-9.885,6.792-9.885,6.792L156.62,157.72z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317E", zone: freeZone, x:163, y:160, polygon: "M156.452,168.602l0.168-10.882l10.552-5.561c0,0,3.53,7.871,0.795,11.672C165.23,167.633,156.452,168.602,156.452,168.602z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317B", zone: freeZone, x:151, y:181, polygon: "M156.32,184.339l0.146-11.59c0,0-7.513-0.643-10.41,4.55c-2.901,5.196,0.985,11.829,0.985,11.829L156.32,184.339z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317F", zone: freeZone, x:162, y:182, polygon: "M156.32,184.339l9.404,6.563c0,0,4.023-7.044,0.891-12.723c-3.131-5.675-10.149-5.431-10.149-5.431L156.32,184.339z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1317A", zone: freeZone, x:156, y:191, polygon: "M147.041,189.128l9.279-4.789l9.404,6.563c0,0-5.085,6.609-9.519,5.978C151.771,196.248,147.041,189.128,147.041,189.128z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312R", zone: freeZone, x:120, y:201, polygon: "M126.433,198.658l-10.14-5.616c0,0-4.124,6.313-0.92,11.323c3.203,5.016,10.885,4.733,10.885,4.733L126.433,198.658z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312T", zone: freeZone, x:126, y:193, polygon: "M126.433,198.658l10.233-5.176c0,0-4.299-6.879-10.784-6.809c-6.481,0.07-9.589,6.368-9.589,6.368L126.433,198.658z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312S", zone: freeZone, x:132, y:201, polygon: "M126.257,209.099l0.175-10.44l10.233-5.176c0,0,3.414,7.608,0.758,11.215C134.768,208.304,126.257,209.099,126.257,209.099z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312M", zone: freeZone, x:102, y:184, polygon: "M107.243,187.821l-0.036-11.606c0,0-7.471-0.524-10.267,4.721c-2.799,5.248,1.163,11.826,1.163,11.826L107.243,187.821z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312U", zone: freeZone, x:113, y:185, polygon: "M107.243,187.821l9.44,6.423c0,0,3.885-7.115,0.686-12.751c-3.197-5.633-10.162-5.278-10.162-5.278L107.243,187.821z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312Q", zone: freeZone, x:107, y:194, polygon: "M98.104,192.762l9.14-4.94l9.44,6.423c0,0-4.946,6.697-9.358,6.135C102.912,199.815,98.104,192.762,98.104,192.762z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312N", zone: freeZone, x:88, y:193, polygon: "M87.67,198.846l9.606-6.188c0,0-3.729-6.181-9.637-5.604c-5.913,0.574-9.161,7.194-9.161,7.194L87.67,198.846z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312P", zone: freeZone, x:94, y:201, polygon: "M87.67,198.846l-0.067,10.866c0,0,8.066-0.707,10.958-6.226c2.89-5.515-1.285-10.829-1.285-10.829L87.67,198.846z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312O", zone: freeZone, x:82, y:202, polygon: "M78.479,194.248l9.191,4.598l-0.067,10.866c0,0-8.311-0.354-10.303-4.137C75.308,201.789,78.479,194.248,78.479,194.248z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312B", zone: freeZone, x:55, y:211, polygon: "M60.966,211.073l-5.603-9.985c0,0-6.304,3.411-6.048,9.377c0.252,5.973,6.62,9.593,6.62,9.593L60.966,211.073z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312C", zone: freeZone, x:64, y:205, polygon: "M60.966,211.073l10.731,0.655c0,0-0.27-8.143-5.567-11.346c-5.294-3.201-10.767,0.705-10.767,0.705L60.966,211.073z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312A", zone: freeZone, x:64, y:217, polygon: "M55.935,220.058l5.031-8.984l10.731,0.655c0,0-0.791,8.33-4.634,10.126C63.217,223.65,55.935,220.058,55.935,220.058z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312I", zone: freeZone, x:55, y:149, polygon: "M54.072,155.361l10.133-5.832c0,0-3.465-6.557-9.521-6.288c-6.061,0.265-9.734,6.89-9.734,6.89L54.072,155.361z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312D", zone: freeZone, x:60, y:158, polygon: "M54.072,155.361l-0.661,11.164c0,0,8.265-0.283,11.514-5.796c3.248-5.509-0.72-11.199-0.72-11.199L54.072,155.361z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312E", zone: freeZone, x:48, y:158, polygon: "M44.95,150.131l9.123,5.229l-0.661,11.164c0,0-8.456-0.82-10.282-4.817C41.305,157.707,44.95,150.131,44.95,150.131z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312G", zone: freeZone, x:29, y:142, polygon: "M34.704,144.883l-0.326-11.644c0,0-7.416-0.06-10.053,5.379c-2.642,5.44,1.447,11.793,1.447,11.793L34.704,144.883z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312H", zone: freeZone, x:40, y:142, polygon: "M34.704,144.883l9.514,5.855c0,0,3.671-7.381,0.36-12.837c-3.309-5.452-10.2-4.662-10.2-4.662L34.704,144.883z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312F", zone: freeZone, x:35, y:151, polygon: "M25.772,150.412l8.932-5.529l9.514,5.855c0,0-4.733,7.029-9.118,6.741C30.712,157.189,25.772,150.412,25.772,150.412z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312J", zone: freeZone, x:82, y:141, polygon: "M87.648,143.634l-0.125-11.63c0,0-7.52-0.188-10.291,5.196c-2.775,5.387,1.26,11.8,1.26,11.8L87.648,143.634z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312Z1", zone: freeZone, x:93, y:141, polygon: "M87.648,143.634l9.545,6.011c0,0,3.854-7.307,0.592-12.811c-3.259-5.5-10.263-4.83-10.263-4.83L87.648,143.634z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312K", zone: freeZone, x:88, y:150, polygon: "M78.493,149l9.156-5.366l9.545,6.011c0,0-4.925,6.936-9.367,6.572C83.383,155.852,78.493,149,78.493,149z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312Z", zone: freeZone, x:107, y:149, polygon: "M106.603,155.23l10.816-5.817c0,0-3.698-6.541-10.163-6.272c-6.47,0.264-10.391,6.873-10.391,6.873L106.603,155.23z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312V", zone: freeZone, x:113, y:158, polygon: "M106.603,155.23l-0.705,11.136c0,0,8.821-0.283,12.29-5.782c3.466-5.495-0.769-11.171-0.769-11.171L106.603,155.23z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312L", zone: freeZone, x:100, y:158, polygon: "M96.866,150.014l9.737,5.216l-0.705,11.136c0,0-9.027-0.818-10.976-4.805C92.975,157.57,96.866,150.014,96.866,150.014z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312Y", zone: freeZone, x:121, y:140, polygon: "M126.461,143.495l-0.256-12.127c0,0-7.415-0.108-10.084,5.539c-2.675,5.649,1.376,12.29,1.376,12.29L126.461,143.495z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312X", zone: freeZone, x:132, y:140, polygon: "M126.461,143.495l9.479,6.157c0,0,3.716-7.665,0.438-13.366c-3.276-5.698-10.172-4.918-10.172-4.918L126.461,143.495z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1312W", zone: freeZone, x:127, y:150, polygon: "M117.497,149.196l8.964-5.702l9.479,6.157c0,0-4.775,7.289-9.158,6.962C122.396,156.284,117.497,149.196,117.497,149.196z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331U", zone: freeZone, x:287, y:380, polygon: "M291.267,376.437l-11.103-3.185c0,0-2.296,7.323,2.046,11.57c4.345,4.255,11.584,2.121,11.584,2.121L291.267,376.437z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331V", zone: freeZone, x:290, y:370, polygon: "M291.267,376.437l8.404-7.652c0,0-5.868-5.858-12.021-4.224c-6.152,1.633-7.486,8.691-7.486,8.691L291.267,376.437z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331T", zone: freeZone, x:297, y:378, polygon: "M293.794,386.943l-2.527-10.507l8.404-7.652c0,0,5.212,6.803,3.616,11.057C301.688,384.096,293.794,386.943,293.794,386.943z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331S", zone: freeZone, x:309, y:367, polygon: "M307.989,361.195l-8.503,7.878c0,0,5.132,5.736,11.048,4.2c5.925-1.534,7.835-8.837,7.835-8.837L307.989,361.195z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331W", zone: freeZone, x:302, y:360, polygon: "M307.989,361.195l-2.249-11.142c0,0-8.098,2.015-9.875,8.13c-1.779,6.113,3.621,10.89,3.621,10.89L307.989,361.195z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331X", zone: freeZone, x:312, y:357, polygon: "M318.369,364.437l-10.38-3.241l-2.249-11.142c0,0,8.573-0.966,11.417,2.591C320.002,356.204,318.369,364.437,318.369,364.437z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331R", zone: freeZone, x:325, y:371, polygon: "M329.229,367.262l-11.103-3.185c0,0-2.296,7.323,2.046,11.57c4.345,4.255,11.584,2.121,11.584,2.121L329.229,367.262z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331Y", zone: freeZone, x:328, y:361, polygon: "M329.229,367.262l8.404-7.652c0,0-5.868-5.858-12.021-4.224c-6.152,1.633-7.486,8.691-7.486,8.691L329.229,367.262z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331Z", zone: freeZone, x:335, y:369, polygon: "M331.757,377.769l-2.527-10.507l8.404-7.652c0,0,5.212,6.803,3.616,11.057C339.651,374.921,331.757,377.769,331.757,377.769z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331M", zone: freeZone, x:301, y:415, polygon: "M299.95,409.031l-8.019,7.66c0,0,4.851,5.565,10.435,4.067c5.592-1.498,7.387-8.592,7.387-8.592L299.95,409.031z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331N", zone: freeZone, x:294, y:407, polygon: "M299.95,409.031l-2.136-10.818c0,0-7.644,1.966-9.315,7.906c-1.673,5.939,3.433,10.572,3.433,10.572L299.95,409.031z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331O", zone: freeZone, x:304, y:405, polygon: "M309.753,412.167l-9.803-3.136l-2.136-10.818c0,0,8.093-0.948,10.782,2.502C311.286,404.17,309.753,412.167,309.753,412.167z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331L", zone: freeZone, x:317, y:419, polygon: "M321.292,415.262l-11.103-3.185c0,0-2.296,7.323,2.046,11.57c4.345,4.255,11.584,2.121,11.584,2.121L321.292,415.262z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331P", zone: freeZone, x:320, y:409, polygon: "M321.292,415.262l8.404-7.652c0,0-5.868-5.858-12.021-4.224c-6.152,1.633-7.486,8.691-7.486,8.691L321.292,415.262z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331K", zone: freeZone, x:327, y:417, polygon: "M323.819,425.769l-2.527-10.507l8.404-7.652c0,0,5.212,6.803,3.616,11.057C331.714,422.921,323.819,425.769,323.819,425.769z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331J", zone: freeZone, x:338, y:406, polygon: "M337.425,400.093l-8.53,7.682c0,0,5.041,5.706,10.937,4.251c5.902-1.454,7.875-8.635,7.875-8.635L337.425,400.093z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331Q", zone: freeZone, x:331, y:398, polygon: "M337.425,400.093l-2.123-11.006c0,0-8.067,1.905-9.896,7.916c-1.829,6.01,3.489,10.771,3.489,10.771L337.425,400.093z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331Z1", zone: freeZone, x:342, y:396, polygon: "M347.706,403.391l-10.281-3.298l-2.123-11.006c0,0,8.529-0.867,11.321,2.667C349.412,395.291,347.706,403.391,347.706,403.391z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331D", zone: freeZone, x:314, y:470, polygon: "M318.771,465.722l-11.103-3.185c0,0-2.296,7.323,2.046,11.57c4.345,4.255,11.584,2.121,11.584,2.121L318.771,465.722z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331E", zone: freeZone, x:317, y:460, polygon: "M318.771,465.722l8.404-7.652c0,0-5.868-5.858-12.021-4.224c-6.152,1.633-7.486,8.691-7.486,8.691L318.771,465.722z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331C", zone: freeZone, x:325, y:467, polygon: "M321.299,476.229l-2.527-10.507l8.404-7.652c0,0,5.212,6.803,3.616,11.057C329.193,473.381,321.299,476.229,321.299,476.229z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331F", zone: freeZone, x:329, y:448, polygon: "M334.789,449.772l-2.943-11.36c0,0-7.564,1.778-9.029,7.773c-1.47,6.001,4.118,11.231,4.118,11.231L334.789,449.772z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331G", zone: freeZone, x:339, y:445, polygon: "M334.789,449.772l10.997,3.396c0,0,2.083-8.163-2.511-12.7c-4.592-4.539-11.43-2.056-11.43-2.056L334.789,449.772z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331B", zone: freeZone, x:336, y:456, polygon: "M326.935,457.417l7.854-7.645l10.997,3.396c0,0-3.244,8.078-7.772,8.882C333.481,462.851,326.935,457.417,326.935,457.417z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331A", zone: freeZone, x:352, y:460, polygon: "M356.093,456.513l-11.103-3.185c0,0-2.296,7.323,2.046,11.57c4.345,4.255,11.584,2.121,11.584,2.121L356.093,456.513z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331H", zone: freeZone, x:355, y:450, polygon: "M356.093,456.513l8.404-7.652c0,0-5.868-5.858-12.021-4.224c-6.152,1.633-7.486,8.691-7.486,8.691L356.093,456.513z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1331I", zone: freeZone, x:362, y:458, polygon: "M358.62,467.02l-2.527-10.507l8.404-7.652c0,0,5.212,6.803,3.616,11.057C366.515,464.172,358.62,467.02,358.62,467.02z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333A", zone: freeZone, x:389, y:448, polygon: "M387.673,441.39l-8.738,7.824c0,0,5.134,5.845,11.161,4.374c6.035-1.468,8.072-8.799,8.072-8.799L387.673,441.39z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333I", zone: freeZone, x:381, y:440, polygon: "M387.673,441.39l-2.136-11.251c0,0-8.25,1.923-10.136,8.06c-1.888,6.135,3.533,11.016,3.533,11.016L387.673,441.39z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333H", zone: freeZone, x:392, y:437, polygon: "M398.168,444.789l-10.495-3.399l-2.136-11.251c0,0,8.717-0.862,11.559,2.757C399.936,436.52,398.168,444.789,398.168,444.789z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333C", zone: freeZone, x:416, y:450, polygon: "M409.425,447.62l2.653,11.702c0,0,7.732-1.695,9.396-7.819c1.67-6.131-3.85-11.592-3.85-11.592L409.425,447.62z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333B", zone: freeZone, x:405, y:452, polygon: "M409.425,447.62l-11.065-3.67c0,0-2.357,8.339,2.174,13.071c4.527,4.732,11.545,2.301,11.545,2.301L409.425,447.62z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333G", zone: freeZone, x:408, y:441, polygon: "M417.625,439.911l-8.2,7.709l-11.065-3.67c0,0,3.532-8.23,8.154-8.979C411.139,434.229,417.625,439.911,417.625,439.911z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333E", zone: freeZone, x:430, y:428, polygon: "M425.674,431.999l11.021,3.757c0,0,2.445-7.51-1.801-12.096c-4.251-4.594-11.525-2.674-11.525-2.674L425.674,431.999z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333D", zone: freeZone, x:427, y:438, polygon: "M425.674,431.999l-8.553,7.602c0,0,5.737,6.323,11.917,4.876c6.176-1.445,7.657-8.721,7.657-8.721L425.674,431.999z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333F", zone: freeZone, x:420, y:430, polygon: "M423.369,420.986l2.305,11.013l-8.553,7.602c0,0-5.062-7.274-3.38-11.627C415.429,423.622,423.369,420.986,423.369,420.986z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333K", zone: freeZone, x:378, y:404, polygon: "M372.137,402.234l2.653,11.702c0,0,7.732-1.695,9.396-7.819c1.67-6.131-3.85-11.592-3.85-11.592L372.137,402.234z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333J", zone: freeZone, x:368, y:407, polygon: "M372.137,402.234l-11.065-3.67c0,0-2.357,8.339,2.174,13.071c4.527,4.732,11.545,2.301,11.545,2.301L372.137,402.234z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333R", zone: freeZone, x:371, y:396, polygon: "M380.337,394.525l-8.2,7.709l-11.065-3.67c0,0,3.532-8.23,8.154-8.979C373.851,388.843,380.337,394.525,380.337,394.525z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333N", zone: freeZone, x:416, y:395, polygon: "M409.396,392.874l2.653,11.702c0,0,7.732-1.695,9.396-7.819c1.67-6.131-3.85-11.592-3.85-11.592L409.396,392.874z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333M", zone: freeZone, x:405, y:397, polygon: "M409.396,392.874l-11.065-3.67c0,0-2.357,8.339,2.174,13.071c4.527,4.732,11.545,2.301,11.545,2.301L409.396,392.874z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333O", zone: freeZone, x:408, y:387, polygon: "M417.597,385.165l-8.2,7.709l-11.065-3.67c0,0,3.532-8.23,8.154-8.979C411.11,379.482,417.597,385.165,417.597,385.165z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333U", zone: freeZone, x:387, y:360, polygon: "M381.257,357.955l2.653,11.702c0,0,7.732-1.695,9.396-7.819c1.67-6.131-3.85-11.592-3.85-11.592L381.257,357.955z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333T", zone: freeZone, x:377, y:362, polygon: "M381.257,357.955l-11.065-3.67c0,0-2.357,8.339,2.174,13.071c4.527,4.732,11.545,2.301,11.545,2.301L381.257,357.955z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333Y", zone: freeZone, x:380, y:352, polygon: "M389.457,350.246l-8.2,7.709l-11.065-3.67c0,0,3.532-8.23,8.154-8.979C382.971,344.563,389.457,350.246,389.457,350.246z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333V", zone: freeZone, x:399, y:348, polygon: "M397.942,342.345l-8.53,7.682c0,0,5.041,5.706,10.937,4.251c5.902-1.454,7.875-8.635,7.875-8.635L397.942,342.345z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333X", zone: freeZone, x:392, y:341, polygon: "M397.942,342.345l-2.123-11.006c0,0-8.067,1.905-9.896,7.916c-1.829,6.01,3.489,10.771,3.489,10.771L397.942,342.345z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333W", zone: freeZone, x:402, y:338, polygon: "M408.224,345.643l-10.281-3.298l-2.123-11.006c0,0,8.529-0.867,11.321,2.667C409.93,337.543,408.224,345.643,408.224,345.643z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333S", zone: freeZone, x:361, y:357, polygon: "M359.723,351.285l-8.53,7.682c0,0,5.041,5.706,10.937,4.251c5.902-1.454,7.875-8.635,7.875-8.635L359.723,351.285z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333Z1", zone: freeZone, x:354, y:350, polygon: "M359.723,351.285l-2.123-11.006c0,0-8.067,1.905-9.896,7.916c-1.829,6.01,3.489,10.771,3.489,10.771L359.723,351.285z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333Z", zone: freeZone, x:364, y:347, polygon: "M370.004,354.583l-10.281-3.298l-2.123-11.006c0,0,8.529-0.867,11.321,2.667C371.71,346.483,370.004,354.583,370.004,354.583z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333L", zone: freeZone, x:389, y:393, polygon: "M388.243,386.745l-8.53,7.682c0,0,5.041,5.706,10.937,4.251c5.902-1.454,7.875-8.635,7.875-8.635L388.243,386.745z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333Q", zone: freeZone, x:382, y:385, polygon: "M388.243,386.745l-2.123-11.006c0,0-8.067,1.905-9.896,7.916c-1.829,6.01,3.489,10.771,3.489,10.771L388.243,386.745z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1333P", zone: freeZone, x:393, y:383, polygon: "M398.524,390.043l-10.281-3.298l-2.123-11.006c0,0,8.529-0.867,11.321,2.667C400.23,381.943,398.524,390.043,398.524,390.043z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1320", zone: freeZone, x:244, y:73, polygon: "M222.24,60.04 227.04,86.8 264.84,86.68 265.32,64.6 262.019,64.666 262.08,60.04z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1321", zone: freeZone, x:244, y:101, polygon: "M227.04,88 222.24,114.76 262.08,114.76 262.08,111.04 265.32,111.04 265.32,88.12z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1322", zone: freeZone, x:243, y:143, polygon: "M222.24,115.84 226.56,143.2 221.28,169.84 262.08,170.56 262.08,166.6 265.32,166.6 265.32,119.56 262.151,119.56 262.08,115.84z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1323", zone: freeZone, x:244, y:185, polygon: "M222.24,171.76 227.04,198.52 265.32,198.28 265.32,175 261.96,175 262.08,171.76z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1324", zone: freeZone, x:244, y:213, polygon: "M227.04,199.72 222.96,222.64 222.24,226.48 262.2,226.48 262.2,221.8 265.32,221.8 265.32,199.72z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1325", zone: freeZone, x:244, y:241, polygon: "M222.24,227.56 227.04,253.72 265.32,253.72 265.32,232.24 262.02,232.166 262.2,227.56z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1326", zone: freeZone, x:244, y:268, polygon: "M227.04,254.8 222.6,276.88 222.24,280.96 262.32,280.96 262.32,277.48 265.32,277.48 264.84,254.92z").save(flush: true);
		new Desk(office: cloffice, floor: "13", assignedSeatId: "1335", zone: freeZone, x:444, y:322, polygon: "M421.92,314.56 429.48,341.2 437.28,338.56 465.48,321.28 454.92,305.2 452.494,306.58 450.84,303.64 424.68,309.88 425.76,313.721z").save(flush: true);
	}
	
	private saveCleveland14() {
		new Room(name: "The Grateful Dead", number: "1458", phone: "000.000.0000", office: cloffice, floor: "14", assignedSeatId: "1458", zone: freeZone, x:244, y:470, polygon: "M229.618,426.077l-14.734,36.836l4.954,21.148l-4.7,1.08l6.478,27.311l8.129-2.032l0.889,3.811l42.489-10.161l-14.798-61.986l2.848-8.639c0,0-13.654-5.16-15.841-5.716C241.699,426.806,229.618,426.077,229.618,426.077z").save(flush: true);
		new Room(name: "James Brown", number: "1433", phone: "000.000.0000", office: cloffice, floor: "14", assignedSeatId: "1433", zone: freeZone, x:231, y:309, polygon: "M216.027,282.162 255.15,282.162 255.15,286.86 264.549,286.86 264.549,336.272 206.628,326.619 197.863,325.221 197.418,286.605 216.408,286.86z").save(flush: true);
		
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1416", zone: freeZone, x:21, y:145, polygon: "M9.489,117.095 32.988,117.095 32.988,172.287 9.489,171.2z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1418", zone: freeZone, x:72, y:135, polygon: "M59.6,115.761 83.607,115.888 83.607,154.63 59.6,154.63z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1419", zone: freeZone, x:98, y:135, polygon: "M84.645,115.761 110.853,115.508 110.344,154.885 84.645,154.63z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1417", zone: freeZone, x:47, y:135, polygon: "M34.576,115.952 58.583,116.079 58.583,154.821 34.576,154.821z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1413", zone: freeZone, x:98, y:207, polygon: "M84.432,186.259 111.488,186.259 110.344,227.097 84.645,226.842z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1414", zone: freeZone, x:71, y:207, polygon: "M59.79,187.402 83.289,187.529 83.289,226.736 59.282,227.097z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1431", zone: freeZone, x:242, y:199, polygon: "M220.092,171.143 261.247,171.779 261.247,174.954 264.549,174.954 264.549,221.825 261.375,221.825 261.375,226.525 221.362,226.525 225.681,199.088z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1432", zone: freeZone, x:243, y:241, polygon: "M221.362,227.669 260.739,227.669 261.375,232.368 264.549,232.368 264.296,253.327 226.062,253.835z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1451", zone: freeZone, x:412, y:450, polygon: "M420.438,428.507 429.424,467.359 407.83,472.44 407.386,468.963 404.083,469.726 394.811,430.094z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1448", zone: freeZone, x:506, y:419, polygon: "M540.061,440.431 535.742,442.335 511.481,448.053 510.719,445.003 502.843,446.909 503.479,449.957 482.139,454.91 473.12,413.756 471.86,391.786 505.893,383.779 511.481,392.289 509.486,393.94 512.369,398.612 514.783,396.862 540.061,435.73 537.953,437.095z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1453", zone: freeZone, x:361, y:463, polygon: "M368.846,440.756 377.833,479.608 356.237,484.689 355.794,481.213 352.49,481.975 343.219,442.344z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1454", zone: freeZone, x:322, y:470, polygon: "M341.78,442.972 351.053,482.602 347.876,483.365 348.639,486.413 303.419,497.082 302.975,493.466 300.751,494.033 292.114,458.85 295.416,458.341 293.893,453.641 317.522,453.804z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1444", zone: freeZone, x:438, y:329, polygon: "M395.128,316.838 403.893,354.309 432.98,353.167 445.684,352.531 479.979,344.401 453.114,302.95z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1426", zone: freeZone, x:242, y:45, polygon: "M220.092,31.419 264.549,31.925 264.549,58.221 221.235,58.729z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1427", zone: freeZone, x:243, y:73, polygon: "M220.981,59.493 264.319,59.616 264.231,86.419 226.062,86.674z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1425", zone: freeZone, x:243, y:18, polygon: "M220.497,5.761 265.098,6.14 264.971,29.894 219.965,30.594z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1428", zone: freeZone, x:243, y:101, polygon: "M226.062,87.944 264.549,87.944 264.549,111.062 261.501,111.062 261.247,114.619 221.362,114.619z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1429", zone: freeZone, x:243, y:129, polygon: "M221.362,115.888 261.247,115.888 261.247,120.017 264.549,119.827 264.549,142.183 226.062,142.69z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1430", zone: freeZone, x:243, y:157, polygon: "M226.062,143.834 264.549,143.834 264.549,166.189 261.247,166.698 261.247,169.873 221.362,170.635z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1439", zone: freeZone, x:243, y:268, polygon: "M225.299,255.232 264.549,254.979 264.549,277.588 261.629,277.588 261.501,281.019 221.362,281.019z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455O", zone: freeZone, x:412, y:384, polygon: "M415.471,388.419l4.047-11.003c0,0-7.598-2.431-12.355,1.812c-4.76,4.246-2.947,11.503-2.947,11.503L415.471,388.419z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1440", zone: freeZone, x:310, y:357, polygon: "M302.147,378.571 293.511,340.718 316.501,335.383 326.282,377.682z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1442", zone: freeZone, x:361, y:345, polygon: "M352.025,366.405 343.388,328.551 364.77,323.951 365.659,327.763 368.962,326.999 378.616,365.535z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1443", zone: freeZone, x:387, y:341, polygon: "M370.739,326.626 373.407,325.983 372.519,322.046 393.985,317.092 402.749,354.562 379.632,364.739z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1441", zone: freeZone, x:335, y:353, polygon: "M318.408,338.939 323.995,337.732 323.392,333.668 342.627,328.736 351.239,367.393 327.426,377.301z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1449", zone: freeZone, x:464, y:438, polygon: "M471.946,416.741 481.122,455.292 460.175,460.136 458.894,457.197 455.591,457.96 446.318,418.328z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1450", zone: freeZone, x:438, y:443, polygon: "M444.952,418.825 454.447,458.214 451.396,458.947 451.908,462.152 430.377,467.782 428.597,460.044 421.615,429.215z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1452", zone: freeZone, x:386, y:455, polygon: "M393.095,430.939 402.591,470.328 399.539,471.062 400.051,474.267 378.52,479.897 376.739,472.158 369.758,441.329z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455N", zone: freeZone, x:414, y:395, polygon: "M404.215,390.73l11.256-2.312l7.572,8.532c0,0-7.504,5.06-11.901,3.384C406.742,398.66,404.215,390.73,404.215,390.73z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455P", zone: freeZone, x:396, y:387, polygon: "M393.847,393.364l11.227-2.77c0,0-1.309-7.315-7.063-8.77c-5.761-1.456-11.166,3.909-11.166,3.909L393.847,393.364z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455M", zone: freeZone, x:398, y:398, polygon: "M393.847,393.364l-3.925,10.604c0,0,7.833,2.062,12.509-2.346c4.676-4.409,2.643-11.027,2.643-11.027L393.847,393.364z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455Q", zone: freeZone, x:388, y:395, polygon: "M386.844,385.734l7.003,7.63l-3.925,10.604c0,0-5.813-0.177-7.432-7.487S386.844,385.734,386.844,385.734z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455J", zone: freeZone, x:358, y:397, polygon: "M361.663,401.408l3.437-10.749c0,0-7.033-2.152-11.292,2.076c-4.265,4.232-2.419,11.205-2.419,11.205L361.663,401.408z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455I", zone: freeZone, x:368, y:400, polygon: "M361.663,401.408l7.165,8.053c0,0,5.867-5.719,4.475-11.646c-1.395-5.927-8.203-7.155-8.203-7.155L361.663,401.408z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455H", zone: freeZone, x:360, y:407, polygon: "M351.389,403.94l10.274-2.532l7.165,8.053c0,0-6.764,5.092-10.842,3.587C353.907,411.542,351.389,403.94,351.389,403.94z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455L", zone: freeZone, x:334, y:408, polygon: "M340.299,406.596l-7.888-8.071c0,0-5.196,5.206-3.455,10.95c1.742,5.75,8.767,7.386,8.767,7.386L340.299,406.596z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455K", zone: freeZone, x:342, y:390, polygon: "M340.299,406.596l10.47-2.559c0,0-2.305-7.862-8.194-9.409c-5.891-1.543-10.163,3.896-10.163,3.896L340.299,406.596z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455G", zone: freeZone, x:344, y:411, polygon: "M337.723,416.86l2.576-10.265l10.47-2.559c0,0,1.331,8.36-1.909,11.26C345.618,418.195,337.723,416.86,337.723,416.86z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455D", zone: freeZone, x:303, y:411, polygon: "M307.213,415.289l3.001-10.878c0,0-7.114-1.868-11.2,2.529c-4.09,4.4-1.965,11.293-1.965,11.293L307.213,415.289z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455C", zone: freeZone, x:313, y:414, polygon: "M307.213,415.289l7.482,7.757c0,0,5.631-5.95,4.002-11.816c-1.631-5.866-8.483-6.818-8.483-6.818L307.213,415.289z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455B", zone: freeZone, x:306, y:421, polygon: "M297.049,418.233l10.164-2.944l7.482,7.757c0,0-6.553,5.36-10.689,4.021C299.871,425.728,297.049,418.233,297.049,418.233z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455F", zone: freeZone, x:280, y:422, polygon: "M285.585,420.689l-7.888-8.071c0,0-5.196,5.206-3.454,10.95c1.742,5.75,8.767,7.386,8.767,7.386L285.585,420.689z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455E", zone: freeZone, x:287, y:415, polygon: "M285.585,420.689l10.471-2.559c0,0-2.306-7.862-8.194-9.409c-5.891-1.543-10.164,3.896-10.164,3.896L285.585,420.689z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1455A", zone: freeZone, x:290, y:425, polygon: "M283.01,430.954l2.575-10.265l10.471-2.559c0,0,1.33,8.36-1.909,11.26C290.905,432.289,283.01,430.954,283.01,430.954z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424X", zone: freeZone, x:183, y:255, polygon: "M187.751,258.465l0.571-10.791c0,0-7.351-0.269-10.384,4.689c-3.04,4.961,0.529,10.963,0.529,10.963L187.751,258.465z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424Y", zone: freeZone, x:193, y:256, polygon: "M187.751,258.465l8.986,5.697c0,0,4.208-6.73,1.345-11.876c-2.865-5.145-9.76-4.612-9.76-4.612L187.751,258.465z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424U", zone: freeZone, x:194, y:200, polygon: "M187.653,201.908l9.36,5.887c0,0,4.352-5.794,1.6-10.737c-2.751-4.947-10.13-5.086-10.13-5.086L187.653,201.908z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424S", zone: freeZone, x:187, y:208, polygon: "M187.653,201.908l-10.136,4.387c0,0,3.685,6.779,9.902,7.057c6.215,0.275,9.594-5.557,9.594-5.557L187.653,201.908z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424R", zone: freeZone, x:182, y:199, polygon: "M188.482,191.972l-0.829,9.937l-10.136,4.387c0,0-2.788-7.429-0.016-10.723C180.275,192.277,188.482,191.972,188.482,191.972z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412J", zone: freeZone, x:139, y:180, polygon: "M132.903,181.563l9.36,5.886c0,0,4.352-5.795,1.6-10.736c-2.751-4.947-10.131-5.086-10.131-5.086L132.903,181.563z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412F", zone: freeZone, x:133, y:187, polygon: "M132.903,181.563l-10.136,4.387c0,0,3.685,6.779,9.902,7.056c6.216,0.276,9.594-5.557,9.594-5.557L132.903,181.563z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412E", zone: freeZone, x:128, y:179, polygon: "M133.732,171.627l-0.829,9.937l-10.136,4.387c0,0-2.788-7.43-0.016-10.723C125.525,171.933,133.732,171.627,133.732,171.627z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424Z", zone: freeZone, x:194, y:238, polygon: "M187.985,235.664l0.35,10.8c0,0,7.346-0.358,9.948-5.559c2.605-5.201-1.462-10.877-1.462-10.877L187.985,235.664z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424W", zone: freeZone, x:183, y:239, polygon: "M187.985,235.664l-9.439-4.912c0,0-3.62,7.064-0.329,11.948c3.292,4.88,10.118,3.764,10.118,3.764L187.985,235.664z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424V", zone: freeZone, x:188, y:230, polygon: "M196.821,230.028l-8.836,5.636l-9.439-4.912c0,0,4.673-6.792,9.019-6.771C191.911,224.007,196.821,230.028,196.821,230.028z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424T", zone: freeZone, x:194, y:182, polygon: "M188.091,179.914l0.35,10.8c0,0,7.346-0.359,9.947-5.56c2.606-5.201-1.461-10.876-1.461-10.876L188.091,179.914z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424Q", zone: freeZone, x:183, y:183, polygon: "M188.091,179.914l-9.44-4.912c0,0-3.619,7.064-0.328,11.947c3.292,4.881,10.118,3.765,10.118,3.765L188.091,179.914z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424P", zone: freeZone, x:188, y:174, polygon: "M196.927,174.278l-8.836,5.636l-9.44-4.912c0,0,4.673-6.793,9.019-6.77C192.017,168.256,196.927,174.278,196.927,174.278z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412H", zone: freeZone, x:157, y:196, polygon: "M151.699,193.251l0.35,10.8c0,0,7.346-0.359,9.947-5.559c2.606-5.202-1.461-10.877-1.461-10.877L151.699,193.251z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412G", zone: freeZone, x:146, y:196, polygon: "M151.699,193.251l-9.44-4.913c0,0-3.619,7.065-0.329,11.948c3.292,4.882,10.119,3.765,10.119,3.765L151.699,193.251z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412I", zone: freeZone, x:151, y:187, polygon: "M160.536,187.615l-8.836,5.636l-9.44-4.913c0,0,4.673-6.793,9.019-6.77C155.625,181.592,160.536,187.615,160.536,187.615z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412K", zone: freeZone, x:138, y:162, polygon: "M132.482,159.209l0.351,10.8c0,0,7.346-0.36,9.947-5.559c2.605-5.202-1.462-10.877-1.462-10.877L132.482,159.209z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412D", zone: freeZone, x:127, y:162, polygon: "M132.482,159.209l-9.44-4.913c0,0-3.619,7.064-0.328,11.948c3.292,4.881,10.119,3.765,10.119,3.765L132.482,159.209z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412C", zone: freeZone, x:138, y:153, polygon: "M141.318,153.573l-8.836,5.636l-9.44-4.913c0,0,4.673-6.793,9.019-6.771C136.408,147.55,141.318,153.573,141.318,153.573z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412A", zone: freeZone, x:157, y:146, polygon: "M151.44,148.061l9.36,6.169c0,0,4.351-6.073,1.6-11.251c-2.751-5.184-10.13-5.329-10.13-5.329L151.44,148.061z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412L", zone: freeZone, x:151, y:154, polygon: "M151.44,148.061l-10.136,4.597c0,0,3.685,7.104,9.902,7.394c6.216,0.29,9.594-5.822,9.594-5.822L151.44,148.061z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1412B", zone: freeZone, x:146, y:145, polygon: "M152.27,137.649l-0.83,10.412l-10.136,4.597c0,0-2.788-7.785-0.016-11.236C144.063,137.969,152.27,137.649,152.27,137.649z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424O", zone: freeZone, x:194, y:143, polygon: "M187.653,145.408l9.36,6.023c0,0,4.352-5.929,1.6-10.986c-2.751-5.062-10.13-5.203-10.13-5.203L187.653,145.408z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424M", zone: freeZone, x:187, y:151, polygon: "M187.653,145.408l-10.136,4.488c0,0,3.685,6.937,9.902,7.219c6.215,0.283,9.594-5.684,9.594-5.684L187.653,145.408z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424L", zone: freeZone, x:182, y:378, polygon: "M188.482,135.242l-0.829,10.166l-10.136,4.488c0,0-2.788-7.601-0.016-10.97C180.275,135.555,188.482,135.242,188.482,135.242z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424F", zone: freeZone, x:194, y:88, polygon: "M188.316,89.66l9.36,6.023c0,0,4.352-5.929,1.6-10.986c-2.751-5.062-11.405-4.739-11.405-4.739L188.316,89.66z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424G", zone: freeZone, x:188, y:96, polygon: "M188.316,89.66l-10.136,4.488c0,0,3.685,6.936,9.902,7.219c6.216,0.283,9.594-5.684,9.594-5.684L188.316,89.66z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424I", zone: freeZone, x:183, y:87, polygon: "M187.871,79.958l0.445,9.702l-10.136,4.488c0,0-2.787-7.601-0.016-10.97C180.939,79.807,187.871,79.958,187.871,79.958z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424N", zone: freeZone, x:193, y:126, polygon: "M187.133,122.797l0.229,11.208c0,0,7.47,0.139,10.173-5.065c2.708-5.207-1.358-11.366-1.358-11.366L187.133,122.797z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424K", zone: freeZone, x:182, y:126, polygon: "M187.133,122.797l-9.536-5.74c0,0-3.759,7.065-0.472,12.349c3.288,5.283,10.237,4.598,10.237,4.598L187.133,122.797z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424J", zone: freeZone, x:187, y:117, polygon: "M196.177,117.573l-9.044,5.224l-9.536-5.74c0,0,4.83-6.712,9.245-6.387C191.258,110.997,196.177,117.573,196.177,117.573z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424E", zone: freeZone, x:194, y:70, polygon: "M187.863,66.674l0.23,11.207c0,0,7.47,0.139,10.173-5.065c2.708-5.207-1.358-11.366-1.358-11.366L187.863,66.674z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424H", zone: freeZone, x:182, y:69, polygon: "M187.863,66.674l-9.535-5.739c0,0-3.76,7.064-0.472,12.349c3.288,5.283,10.237,4.598,10.237,4.598L187.863,66.674z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424D", zone: freeZone, x:188, y:61, polygon: "M196.909,61.45l-9.045,5.224l-9.535-5.739c0,0,4.829-6.712,9.244-6.387C191.989,54.874,196.909,61.45,196.909,61.45z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424C", zone: freeZone, x:189, y:35, polygon: "M192.186,29.356l-11.205-0.337c0,0-0.517,7.453,4.543,10.416c5.063,2.969,11.421-0.781,11.421-0.781L192.186,29.356z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424A", zone: freeZone, x:190, y:24, polygon: "M192.186,29.356l6.214-9.233c0,0-6.865-4.113-12.31-1.096c-5.443,3.016-5.11,9.992-5.11,9.992L192.186,29.356z").save(flush: true);
		new Desk(office: cloffice, floor: "14", assignedSeatId: "1424B", zone: freeZone, x:198, y:29, polygon: "M196.945,38.653l-4.759-9.297l6.214-9.233c0,0,6.459,5.163,5.911,9.555C203.762,34.073,196.945,38.653,196.945,38.653z").save(flush: true);
	}
	
	private saveCleveland15() {
		new Room(name: "Usability Lab 1", number: "1516", phone: "000.000.0000", office: cloffice, floor: "15", assignedSeatId: "1516", zone: freeZone, x:106, y:145, polygon: "M126.88,121.32 126.88,173.76 84.64,173.76 84.64,116.28 102.64,116.28 102.58,117.75 105.88,117.6 105.88,121.02z").save(flush: true);
		new Room(name: "Usability Lab 2", number: "1515", phone: "000.000.0000", office: cloffice, floor: "15", assignedSeatId: "1515", zone: freeZone, x:71, y:146, polygon: "M63.76,175.44 62.2,174.72 62.2,168.96 59.32,168.96 59.32,119.52 63.28,119.52 63.28,116.28 82.6,116.28 82.6,174.72z").save(flush: true);
		new Room(name: "Usability Lab Waiting Room", number: "1513", phone: "000.000.0000", office: cloffice, floor: "15", assignedSeatId: "1513", zone: freeZone, x:86, y:202, polygon: "M130,180.96 121.24,192.36 121.24,228.24 42.52,227.52 41.44,176.52 121.6,175.8z").save(flush: true);
		new Room(name: "Usability Lab 3", number: "1514", phone: "000.000.0000", office: cloffice, floor: "15", assignedSeatId: "1514", zone: freeZone, x:34, y:146, polygon: "M42.28,175.56 55.24,175.8 55.24,168.96 58.12,168.96 58.12,119.52 55.12,119.52 55.12,116.28 14.8,116.28 14.8,120.28 10.12,120.6 10.72,172.2 42.52,171.72z").save(flush: true);
		new Room(name: "Billie Holiday", number: "1501", phone: "000.000.0000", office: cloffice, floor: "15", assignedSeatId: "1501", zone: freeZone, x:219, y:490, polygon: "M240.08,461.88 247.568,460.188 258.32,508.56 207.4,520.32 205.48,517.56 202.96,518.88 179.44,476.339 182.42,474.5 181.272,473.367 200.36,469.08 201.68,472.92 211.94,472.26 212.48,473.517 217.48,472.173 216.76,467.04z").save(flush: true);

		new Desk(office: cloffice, floor: "15", assignedSeatId: "1552", zone: freeZone, x:310, y:476, polygon: "M292.48,456.176 301.36,494.88 303.52,494.4 304.24,497.52 326.8,492.24 317.92,454.2 305.321,454.179 295.84,454.8z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1551", zone: freeZone, x:335, y:468, polygon: "M319,453.96 328.12,491.64 349.48,486.84 348.76,483.72 351.88,483 342.52,443.4 329.44,448.68z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1550", zone: freeZone, x:361, y:463, polygon: "M343.72,443.16 353.08,482.76 356.2,482.04 356.92,485.04 378.4,480 369.52,442.08 356.32,441.48z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1549", zone: freeZone, x:387, y:455, polygon: "M370.6,441.84 380.08,479.4 401.08,474.72 400.36,471.6 403.6,470.88 394.24,431.28 382.12,435.36z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1548", zone: freeZone, x:413, y:451, polygon: "M395.32,430.92 405.64,470.4 407.92,469.8 408.64,472.92 430.12,467.88 421.12,429.96 409.36,429.84z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1546", zone: freeZone, x:464, y:439, polygon: "M447.16,419.4 456.28,458.4 459.52,457.68 460.24,460.68 481.72,455.64 472.84,417.72 458.92,417.6 450.52,418.08z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1545", zone: freeZone, x:491, y:433, polygon: "M474.28,417.48 482.8,455.16 504.16,450.36 503.44,447.36 507.28,446.4 499.12,411.6 483.04,414.24z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1544", zone: freeZone, x:513, y:416, polygon: "M490.48,410.52 485.32,389.28 506.56,384.24 540.64,436.08 538.48,437.52 540.64,440.76 536.44,442.68 512.08,448.56 511.061,445.141 508.48,446.16 499.48,410.16 490.84,412.44z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1540", zone: freeZone, x:452, y:330, polygon: "M435.16,354.72 424.72,310.56 450.88,304.44 452.56,307.8 454.96,306 480.16,344.16z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1525", zone: freeZone, x:244, y:20, polygon: "M222.16,7.32 222.16,31.68 265.48,31.68 265.48,11.52 261.817,11.5 262.24,7.32z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1526", zone: freeZone, x:244, y:46, polygon: "M222.16,32.76 221.8,56.16 222.16,59.64 262.24,59.64 262.24,58.68 265.24,58.68 265.48,32.76z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1527", zone: freeZone, x:244, y:74, polygon: "M222.28,60.72 227.08,87.48 265.48,87.12 265.48,65.28 261.817,65.25 262.24,60.72z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1528", zone: freeZone, x:244, y:102, polygon: "M226.84,88.64 222.16,111.48 222.28,115.44 262.24,115.44 262.24,111.84 265.48,111.84 265.48,89.04z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1529", zone: freeZone, x:244, y:144, polygon: "M222.28,116.64 226.6,144 223.12,167.04 222.28,171.36 262.24,171.36 262.24,167.4 265.48,167.4 265.48,120.24 261.817,120.5 262.24,116.64z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1530", zone: freeZone, x:244, y:198, polygon: "M222.28,172.44 226.6,199.8 222.52,222.48 222.88,224.28 262.24,224.28 262.24,222.48 265.48,222.48 265.48,175.68 261.817,175.75 262.24,172.44z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1524A", zone: freeZone, x:189, y:51, polygon: "M179.2,54.36l9.72,5.64l9.72-4.623L198.52,43.2C198.52,43.2,180.521,37.44,179.2,54.36z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1524D", zone: freeZone, x:197, y:66, polygon: "M197.87,54.428l-9.067,6.639l0.333,10.759l9.984,5.316C199.12,77.141,212.837,62.428,197.87,54.428z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1524B", zone: freeZone, x:181, y:66, polygon: "M180.144,77.099l9.003-6.592l-0.331-10.683l-9.914-5.279C178.902,54.545,165.281,69.155,180.144,77.099z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1524C", zone: freeZone, x:189, y:80, polygon: "M199.271,77.019l-10.951-5.222l-9.515,5.032l0.638,10.161C179.443,86.99,198.671,93.98,199.271,77.019z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1524E", zone: freeZone, x:189, y:107, polygon: "M180.012,99.196l-0.772,11.077l8.52,6.319l10.97-5.751C198.729,110.841,195.919,92.5,180.012,99.196z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1524G", zone: freeZone, x:197, y:121, polygon: "M197.87,109.963l-9.067,6.639l0.333,10.759l9.984,5.316C199.12,132.676,212.837,117.963,197.87,109.963z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1524F", zone: freeZone, x:181, y:122, polygon: "M180.144,132.837l9.003-6.592l-0.331-10.683l-9.914-5.279C178.902,110.283,165.281,124.894,180.144,132.837z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1524H", zone: freeZone, x:190, y:135, polygon: "M200.081,142.474l-1.71-11.448l-9.752-5.369l-9.466,7.314C179.152,132.972,185.989,151.398,200.081,142.474z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1524I", zone: freeZone, x:189, y:156, polygon: "M176.834,157.372l12.431,6.908L202,157.559C202,157.559,188.558,137.4,176.834,157.372z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1524J", zone: freeZone, x:182, y:167, polygon: "M176.921,157.88l12.324,6.88l0.515,11.76C189.76,176.52,166.875,180,176.921,157.88z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1524L", zone: freeZone, x:197, y:167, polygon: "M189.707,176.953l0.785-12.628l11.423-6.581C201.915,157.744,212.999,179.158,189.707,176.953z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1524K", zone: freeZone, x:182, y:188, polygon: "M190.055,176.08l0.257,16.537l-13.625,7.424C176.688,200.04,166.625,180.721,190.055,176.08z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1524M", zone: freeZone, x:197, y:188, polygon: "M202.187,199.109l-13.359-6.943l0.607-14.895C189.435,177.271,211.12,178.5,202.187,199.109z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539A", zone: freeZone, x:295, y:411, polygon: "M291.996,398.882l10.88,11.454l-5.604,13.469C297.272,423.805,277.11,417.56,291.996,398.882z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539F", zone: freeZone, x:304, y:401, polygon: "M316.812,405.112l-15.104,3.63l-9.557-11.021C292.15,397.721,306.732,383.459,316.812,405.112z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539B", zone: freeZone, x:307, y:415, polygon: "M297.686,424.073l4.327-14.92l14.308-2.845C316.32,406.309,321.489,426.04,297.686,424.073z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539C", zone: freeZone, x:327, y:410, polygon: "M314.467,405.264l15.103-3.639l10.563,12.016C340.133,413.641,324.559,426.911,314.467,405.264z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539E", zone: freeZone, x:324, y:396, polygon: "M333.836,387.644l-4.826,14.754l-14.358,2.331C314.651,404.729,310.18,384.828,333.836,387.644z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539D", zone: freeZone, x:337, y:401, polygon: "M340.002,414.394l-10.803-11.147l4.631-15.789C333.83,387.457,353.521,394.777,340.002,414.394z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539I", zone: freeZone, x:325, y:362, polygon: "M312.102,365.148l10.579-11.376l15.442,4.181C338.123,357.953,332.472,377.618,312.102,365.148z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539H", zone: freeZone, x:315, y:352, polygon: "M318.514,339.762l4.127,14.964l-10.681,9.875C311.96,364.601,297.239,350.482,318.514,339.762z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539M", zone: freeZone, x:328, y:349, polygon: "M338.43,358.653l-15.162-3.325l-4.862-15.719C318.405,339.609,338.862,334.834,338.43,358.653z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539J", zone: freeZone, x:345, y:367, polygon: "M335.444,357.813l15.148,3.444l4.132,15.456C354.725,376.714,334.878,381.691,335.444,357.813z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539L", zone: freeZone, x:348, y:354, polygon: "M360.622,350.623l-10.874,11.078l-13.9-4.285C335.848,357.416,340.674,337.599,360.622,350.623z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539K", zone: freeZone, x:358, y:364, polygon: "M354.273,377.33l-4.732-14.784l11.158-12.093C360.699,350.453,375.095,365.753,354.273,377.33z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539O", zone: freeZone, x:384, y:347, polygon: "M370.558,350.069l10.84-11.127l15.342,4.537C396.739,343.479,390.634,363.009,370.558,350.069z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539N", zone: freeZone, x:374, y:337, polygon: "M377.557,324.838l3.779,15.056l-10.907,9.625C370.429,349.519,356.039,335.062,377.557,324.838z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539P", zone: freeZone, x:387, y:334, polygon: "M397.03,344.187l-15.082-3.676l-4.496-15.828C377.452,324.683,398.015,320.384,397.03,344.187z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539R", zone: freeZone, x:389, y:397, polygon: "M376.176,400.779l10.65-11.31l15.416,4.277C402.242,393.747,396.469,413.377,376.176,400.779z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539Q", zone: freeZone, x:379, y:388, polygon: "M382.746,375.433l4.034,14.99l-10.743,9.808C376.037,400.23,361.405,386.021,382.746,375.433z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539V", zone: freeZone, x:393, y:385, polygon: "M402.545,394.448l-15.142-3.419l-4.764-15.749C382.64,375.28,403.126,370.633,402.545,394.448z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539S", zone: freeZone, x:408, y:403, polygon: "M398.056,393.153l15.252,2.951l4.631,15.313C417.938,411.418,398.264,417.037,398.056,393.153z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539U", zone: freeZone, x:411, y:389, polygon: "M422.986,385.15l-10.509,11.425l-14.032-3.832C398.445,392.743,402.627,372.78,422.986,385.15z").save(flush: true);
		new Desk(office: cloffice, floor: "15", assignedSeatId: "1539T", zone: freeZone, x:420, y:399, polygon: "M417.507,412.049l-5.209-14.623l10.761-12.448C423.059,384.978,437.942,399.803,417.507,412.049z").save(flush: true);
	}
	
	private saveCleveland17() {
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
	}
	
	private saveOffices() {
		cloffice = Office.findByName("Cleveland")
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
	}
	
	private saveUsers() {
		def dan = User.findByUsername("dpadgett")
		if (dan == null) {
			dan = new User(firstName: "Dan", lastName: "Padgett", username: "dpadgett", enabled: true,
			accountExpired: false, accountLocked: false, passwordExpired: false, office: cloffice, phone: "555-555-5555",
			level: "Senior Associate", craft: "Software Engineering").save(flush: true);
		}
		def dima = User.findByUsername("dpilipen")
		if (dima == null) {
			dima = new User(firstName: "Dmitriy", lastName: "Pilipenko", username: "dpilipen", enabled: true,
			accountExpired: false, accountLocked: false, passwordExpired: false, office: cloffice, phone: "555-555-5555",
			level: "Associate", craft: "Software Engineering").save(flush: true);
		}
		def liz = User.findByUsername("ljudd5")
		if (liz == null) {
			liz = new User(firstName: "Liz", lastName: "Judd", username: "ljudd5", enabled: true,
			accountExpired: false, accountLocked: false, passwordExpired: false, office: cloffice, phone: "555-555-5555",
			level: "Senior Associate", craft: "Creative Engineering").save(flush: true);
		}
		def dave = User.findByUsername("dfagan5")
		if (dave == null) {
			dave = new User(firstName: "Dave", lastName: "Fagan", username: "dfagan5", enabled: true,
			accountExpired: false, accountLocked: false, passwordExpired: false, office: cloffice, phone: "555-555-5555",
			level: "Senior Associate", craft: "Creative Engineering").save(flush: true);
		}
		def becky = User.findByUsername("bhorvath5")
		if (becky == null) {
			becky = new User(firstName: "Becky", lastName: "Horvath", username: "bhorvath5", enabled: true,
			accountExpired: false, accountLocked: false, passwordExpired: false, office: cloffice, phone: "555-555-5555",
			level: "Senior Associate", craft: "CEO").save(flush: true);
		}
	}
	
	private saveZones() {
		freeZone = Zone.findByName("Free Zone")
		if (freeZone == null) {
			freeZone = Zone.getFreeZone().save(flush:true)
		}
		ahaZone = Zone.findByName("AHA")
		if (ahaZone == null) {
			ahaZone = new Zone(name: "AHA", color: "#880088").save(flush:true)
		}
		uidZone = Zone.findByName("UID Pod")
		if (uidZone == null) {
			uidZone = new Zone(name: "UID Pod", color: "#00FF00").save(flush:true)
		}
	}
	
}
