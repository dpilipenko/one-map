import com.rosetta.onemap.Hotspot
import com.rosetta.onemap.User
import com.rosetta.onemap.Office
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
		def testUser = new User(firstName:"Tess", lastName:"Ting", username:"test@rosetta.com", password: "test", enabled: true,
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
		//	17th Floor
		////
		def hp1 = new Hotspot( floor: "17", type: "room", polygon: "M344.099,446.051 354.658,491.652 431.578,473.412 420.658,427.931z", width: "87", height: "64", x: "388", y: "460").save(flush: true); //1
		def hp2 = new Hotspot( floor: "17", type: "room", polygon: "M452.818,387.131 504.658,375.492 525.14,451.435 470.099,464.403z", width: "72", height: "89", x: "489", y: "420").save(flush: true); //2
		def hp3 = new Hotspot( floor: "17", type: "room", polygon: "M467.818,303.372 498.118,350.886 446.578,363.011 445.019,367.931 405.898,372.612 385.139,324.011z", width: "113", height: "69", x: "442", y: "338").save(flush: true); //3
		def hp4 = new Hotspot( floor: "17", type: "room", polygon: "M280.979,172.452 279.178,227.532 231.778,231.011 199.229,224.863 191.938,221.652 178.802,213.057 181.858,198.372 186.658,175.211 187.138,173.292z", width: "102", height: "59", x: "230", y: "202").save(flush: true); //4
		new Room(name: "The Beatles", number: "1728", phone: "216.896.6666", hotspot: hp1, office: cloffice).save(flush: true);
		new Room(name: "Johnny Cash", number: "1726", phone: "216.896.7345", hotspot: hp2, office: cloffice).save(flush: true);
		new Room(name: "Chuck Berry", number: "1723", phone: "216.896.2324", hotspot: hp3, office: cloffice).save(flush: true);
		def elvis = new Room(name: "Elvis Presley", number: "1715", phone: "216.896.1041", hotspot: hp4, office: cloffice).save(flush: true);
		
		elvis.addToUsers(dan)
		elvis.save(flush:true);
		
		
		
		////
		//	14th Floor
		/* 
		new Hotspot( floor: "14", type: "room", polygon: "M7.48,112.42 29.68,112.42 29.68,164.56 7.48,163.533z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M54.82,111.16 77.5,111.279 77.5,147.88 54.82,147.88z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M78.481,111.16 103.24,110.92 102.76,148.12 78.481,147.88z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M31.18,111.34 53.86,111.459 53.86,148.06 31.18,148.06z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M78.28,177.76 103.84,177.76 102.76,216.34 78.481,216.1z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M55,178.84 77.2,178.96 77.2,216 54.52,216.34z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M206.44,31.48 248.44,31.958 248.44,56.8 207.52,57.28z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M207.28,58.001 248.222,58.119 248.14,83.44 212.08,83.68z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M206.823,7.24 248.958,7.599 248.838,30.039 206.32,30.7z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M212.08,84.88 248.44,84.88 248.44,106.72 245.56,106.72 245.32,110.08z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M207.64,111.28 245.32,111.28 245.32,115.18 248.44,115 248.44,136.12 212.08,136.6z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M212.08,137.68 248.44,137.68 248.44,158.8 245.32,159.28 245.32,162.28 207.64,163z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M206.44,163.48 245.32,164.08 245.32,167.08 248.44,167.08 248.44,211.36 245.44,211.36 245.44,215.8 207.64,215.8 211.72,189.88z").save(flush: true);
//		new Hotspot( floor: "14", type: "room", polygon: "M206.44,163.48 245.32,164.08 245.32,167.08 248.44,167.08 248.44,211.36z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M207.64,216.88 244.84,216.88 245.44,221.32 248.44,221.32 248.2,241.12 212.08,241.6z").save(flush: true);
		
		def hp7 = new Hotspot( floor: "14", type: "desk", polygon: "M211.36,242.92 248.44,242.68 248.44,264.04 245.68,264.04 245.56,267.28 207.64,267.28z").save(flush: true);
		new Desk(user:liz, hotspot:hp7, name:"Lizs Desk").save(flush:true);
		
		def hp8 = new Hotspot( floor: "14", type: "desk", polygon: "M202.6,268.36 239.56,268.36 239.56,272.8 248.44,272.8 248.44,319.48 193.72,310.36 185.44,309.04 185.02,272.56 202.96,272.8z").save(flush: true);
		new Desk(user:becky, hotspot:hp8, name:"Beckys Desk").save(flush:true);
		
		new Hotspot( floor: "14", type: "room", polygon: "M283.96,359.44 275.8,323.68 297.52,318.64 306.76,358.6z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M331.08,347.946 322.92,312.186 343.12,307.84 343.96,311.44 347.08,310.72 356.2,347.125 z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M348.76,310.367 351.28,309.76 350.44,306.04 370.72,301.36 379,336.76 357.16,346.373z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M299.32,322 304.6,320.859 304.029,317.02 322.2,312.36 330.337,348.88 307.84,358.24z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M444.371,395.5 453.04,431.92 433.251,436.496 432.04,433.72 428.92,434.44 420.16,397z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M395.711,406.615 404.2,443.32 383.8,448.12 383.38,444.835 380.26,445.556 371.5,408.115z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M418.869,397.469 427.84,434.68 424.957,435.373 425.44,438.4 405.1,443.72 403.418,436.409 396.822,407.284 z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M508.72,417.88 504.64,419.68 481.72,425.08 481,422.2 473.56,424 474.16,426.88 454,431.56 445.48,392.68 444.29,371.925 476.44,364.36 481.72,372.4 479.836,373.96 482.56,378.373 484.84,376.72 508.72,413.44 506.729,414.729z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M369.879,408.914 378.85,446.125 375.967,446.818 376.45,449.846 356.109,455.165 354.428,447.854 347.832,418.729z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M346.971,418.188 355.46,454.893 335.06,459.692 334.64,456.407 331.52,457.128 322.76,419.688z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M321.4,420.28 330.16,457.72 327.16,458.44 327.88,461.32 285.16,471.4 284.74,467.983 282.64,468.52 274.48,435.28 277.6,434.8 276.16,430.36 298.484,430.514z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M215.44,404.32l-13.92,34.8l4.68,19.979l-4.44,1.021l6.12,25.8l7.68-1.92l0.84,3.6l40.14-9.6l-13.98-58.56l2.69-8.16c0,0-12.899-4.875-14.965-5.4C226.853,405.008,215.44,404.32,215.44,404.32z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M371.8,301.12 380.08,336.52 407.56,335.44 419.56,334.84 451.96,327.16 426.58,288z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M391.018,368.744l3.823-10.395c0,0-7.178-2.297-11.672,1.711c-4.497,4.011-2.785,10.867-2.785,10.867L391.018,368.744z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M391.018,368.744l7.154,8.061c0,0,6.188-5.418,4.911-11.243c-1.275-5.823-8.242-7.212-8.242-7.212 L391.018,368.744z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M380.384,370.928l10.634-2.184l7.154,8.061c0,0-7.09,4.78-11.244,3.196 C382.771,378.419,380.384,370.928,380.384,370.928z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M370.39,373.846l2.803-9.583c0,0-5.731-1.918-9.203,1.852c-3.475,3.773-1.971,9.989-1.971,9.989 L370.39,373.846z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M370.39,373.846l5.839,7.179c0,0,4.781-5.099,3.646-10.383c-1.137-5.284-6.683-6.379-6.683-6.379 L370.39,373.846z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M362.019,376.104l8.371-2.258l5.839,7.179c0,0-5.511,4.539-8.834,3.197 C364.07,382.88,362.019,376.104,362.019,376.104z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M340.185,381.016l3.247-10.155c0,0-6.645-2.033-10.668,1.962c-4.029,3.998-2.285,10.585-2.285,10.585 L340.185,381.016z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M340.185,381.016l6.769,7.607c0,0,5.543-5.403,4.228-11.003c-1.317-5.6-7.749-6.76-7.749-6.76 L340.185,381.016z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M330.479,383.407l9.706-2.392l6.769,7.607c0,0-6.39,4.81-10.242,3.388 C332.857,390.589,330.479,383.407,330.479,383.407z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M320.001,385.916l-7.451-7.625c0,0-4.909,4.918-3.264,10.344c1.646,5.433,8.282,6.978,8.282,6.978 L320.001,385.916z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M320.001,385.916l9.892-2.417c0,0-2.178-7.428-7.741-8.889c-5.564-1.459-9.602,3.681-9.602,3.681 L320.001,385.916z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M317.568,395.612l2.433-9.696l9.892-2.417c0,0,1.257,7.898-1.804,10.637 C325.027,396.874,317.568,395.612,317.568,395.612z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M288.744,394.129l2.835-10.277c0,0-6.721-1.765-10.58,2.39c-3.864,4.157-1.856,10.669-1.856,10.669 L288.744,394.129z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M288.744,394.129l7.069,7.328c0,0,5.32-5.622,3.781-11.164c-1.542-5.542-8.016-6.441-8.016-6.441 L288.744,394.129z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M279.143,396.91l9.602-2.781l7.069,7.328c0,0-6.19,5.063-10.098,3.799 C281.809,403.989,279.143,396.91,279.143,396.91z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M268.312,399.23l-7.451-7.625c0,0-4.909,4.918-3.264,10.344c1.646,5.433,8.282,6.978,8.282,6.978 L268.312,399.23z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M268.312,399.23l9.892-2.417c0,0-2.178-7.428-7.741-8.889c-5.564-1.459-9.602,3.681-9.602,3.681 L268.312,399.23z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M265.88,408.927l2.433-9.696l9.892-2.417c0,0,1.257,7.898-1.804,10.637 C273.339,410.188,265.88,408.927,265.88,408.927z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M175.887,245.974l0.54-10.194c0,0-6.944-0.254-9.811,4.431c-2.872,4.687,0.5,10.356,0.5,10.356 L175.887,245.974z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M175.887,245.974l8.49,5.383c0,0,3.975-6.358,1.271-11.22c-2.706-4.859-9.221-4.357-9.221-4.357 L175.887,245.974z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M167.116,250.566l8.771-4.593l8.49,5.383c0,0-4.946,6.018-9.034,5.646 C171.254,256.63,167.116,250.566,167.116,250.566z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M175.794,192.544l8.843,5.562c0,0,4.111-5.475,1.511-10.144c-2.599-4.674-9.57-4.805-9.57-4.805 L175.794,192.544z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M175.794,192.544l-9.576,4.145c0,0,3.481,6.404,9.355,6.666c5.872,0.261,9.063-5.249,9.063-5.249 L175.794,192.544z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M176.578,183.157l-0.784,9.387l-9.576,4.145c0,0-2.633-7.019-0.015-10.13 C168.825,183.446,176.578,183.157,176.578,183.157z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M124.071,173.323l8.843,5.562c0,0,4.111-5.475,1.511-10.144c-2.599-4.674-9.57-4.805-9.57-4.805 L124.071,173.323z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M124.071,173.323l-9.576,4.145c0,0,3.481,6.404,9.355,6.666c5.872,0.261,9.063-5.249,9.063-5.249 L124.071,173.323z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M124.854,163.937l-0.784,9.387l-9.576,4.145c0,0-2.633-7.019-0.015-10.13 C117.102,164.226,124.854,163.937,124.854,163.937z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M176.108,224.434l0.331,10.203c0,0,6.94-0.339,9.397-5.251c2.461-4.914-1.381-10.276-1.381-10.276 L176.108,224.434z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M176.108,224.434l-8.918-4.641c0,0-3.419,6.674-0.31,11.288c3.11,4.61,9.559,3.556,9.559,3.556 L176.108,224.434z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M184.456,219.109l-8.348,5.324l-8.918-4.641c0,0,4.415-6.417,8.521-6.396 C179.817,213.421,184.456,219.109,184.456,219.109z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M176.208,171.766l0.331,10.203c0,0,6.94-0.34,9.397-5.252c2.461-4.914-1.381-10.275-1.381-10.275 L176.208,171.766z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M176.208,171.766l-8.918-4.641c0,0-3.419,6.674-0.31,11.287c3.11,4.611,9.559,3.557,9.559,3.557 L176.208,171.766z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M184.556,166.441l-8.348,5.324l-8.918-4.641c0,0,4.415-6.417,8.521-6.396 C179.917,160.752,184.556,166.441,184.556,166.441z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M141.828,184.365l0.331,10.203c0,0,6.94-0.34,9.397-5.252c2.461-4.914-1.381-10.275-1.381-10.275 L141.828,184.365z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M141.828,184.365l-8.918-4.641c0,0-3.419,6.674-0.31,11.287c3.11,4.611,9.559,3.557,9.559,3.557 L141.828,184.365z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M150.176,179.041l-8.348,5.324l-8.918-4.641c0,0,4.415-6.418,8.521-6.396 C145.537,173.352,150.176,179.041,150.176,179.041z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M123.673,152.205l0.331,10.203c0,0,6.94-0.34,9.397-5.252c2.461-4.914-1.381-10.275-1.381-10.275 L123.673,152.205z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M123.673,152.205l-8.918-4.641c0,0-3.419,6.674-0.31,11.287c3.11,4.611,9.559,3.557,9.559,3.557 L123.673,152.205z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M132.021,146.881l-8.348,5.324l-8.918-4.641c0,0,4.415-6.418,8.521-6.396 C127.382,141.191,132.021,146.881,132.021,146.881z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M141.584,141.673l8.843,5.828c0,0,4.111-5.737,1.511-10.629c-2.599-4.897-9.57-5.035-9.57-5.035 L141.584,141.673z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M141.584,141.673l-9.576,4.343c0,0,3.481,6.711,9.355,6.985c5.872,0.273,9.063-5.5,9.063-5.5 L141.584,141.673z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M142.368,131.837l-0.784,9.836l-9.576,4.343c0,0-2.633-7.354-0.015-10.615 C134.615,132.14,142.368,131.837,142.368,131.837z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M175.794,139.167l8.843,5.69c0,0,4.111-5.602,1.511-10.379c-2.599-4.782-9.57-4.916-9.57-4.916 L175.794,139.167z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M175.794,139.167l-9.576,4.24c0,0,3.481,6.553,9.355,6.82c5.872,0.267,9.063-5.37,9.063-5.37 L175.794,139.167z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M176.578,129.563l-0.784,9.604l-9.576,4.24c0,0-2.633-7.181-0.015-10.364 C168.825,129.858,176.578,129.563,176.578,129.563z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M176.421,86.501l8.843,5.69c0,0,4.111-5.602,1.511-10.379C184.176,77.031,176,77.335,176,77.335 L176.421,86.501z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M176.421,86.501l-9.576,4.24c0,0,3.481,6.553,9.355,6.82c5.872,0.267,9.063-5.37,9.063-5.37 L176.421,86.501z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M176,77.335l0.42,9.166l-9.576,4.24c0,0-2.633-7.181-0.015-10.364C169.452,77.192,176,77.335,176,77.335z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M175.303,117.807l0.217,10.588c0,0,7.057,0.131,9.611-4.785c2.559-4.919-1.283-10.738-1.283-10.738 L175.303,117.807z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M175.303,117.807l-9.008-5.422c0,0-3.552,6.674-0.446,11.667c3.106,4.991,9.671,4.344,9.671,4.344 L175.303,117.807z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M183.848,112.872l-8.545,4.935l-9.008-5.422c0,0,4.562-6.341,8.733-6.034 C179.2,106.659,183.848,112.872,183.848,112.872z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M175.993,64.786l0.217,10.588c0,0,7.057,0.131,9.611-4.785c2.559-4.919-1.283-10.738-1.283-10.738 L175.993,64.786z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M175.993,64.786l-9.008-5.422c0,0-3.552,6.674-0.446,11.667c3.106,4.991,9.671,4.344,9.671,4.344 L175.993,64.786z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M184.538,59.851l-8.545,4.935l-9.008-5.422c0,0,4.562-6.341,8.733-6.034 C179.891,53.638,184.538,59.851,184.538,59.851z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M180.077,29.531l-10.585-0.319c0,0-0.488,7.041,4.292,9.841c4.783,2.804,10.789-0.738,10.789-0.738 L180.077,29.531z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M180.077,29.531l5.871-8.722c0,0-6.486-3.885-11.629-1.036c-5.142,2.849-4.828,9.439-4.828,9.439 L180.077,29.531z").save(flush: true);
		new Hotspot( floor: "14", type: "room", polygon: "M184.573,38.314l-4.496-8.784l5.871-8.722c0,0,6.102,4.877,5.584,9.027 C191.013,33.987,184.573,38.314,184.573,38.314z").save(flush: true);
		
		
		////
		//	11th Floor
		////
		def hp5 = new Hotspot( floor: "11", type: "desk", polygon: "M189.618,228.271l-9.731-5.594c0,0-3.673,6.887-0.494,12.104 c3.179,5.218,10.403,4.622,10.403,4.622L189.618,228.271z").save(flush: true);
		new Desk(user:dave, hotspot:hp5, name:"Daves Desk").save(flush:true);
		
		def hp6 = new Hotspot( floor: "11", type: "desk", polygon: "M189.618,228.271l9.468-5.909c0,0-4.278-7.164-10.38-6.838 c-6.101,0.326-8.819,7.153-8.819,7.153L189.618,228.271z").save(flush: true);
		new Desk(user:dima, hotspot:hp6, name:"Dimas Desk").save(flush:true);

				new Hotspot( floor: "11", type: "desk", polygon: "M189.796,239.403l-0.178-11.133l9.468-5.909 c0,0,3.462,7.974,1.08,11.919C197.784,238.226,189.796,239.403,189.796,239.403z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M189.265,251.08l-9.84,5.399c0,0,3.97,6.72,10.08,6.72 s9.36-6.479,9.36-6.479L189.265,251.08z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M189.265,251.08l-0.12-11.16c0,0-8.345-0.074-11.24,5.307 c-2.896,5.38,1.521,11.253,1.521,11.253L189.265,251.08z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M189.435,194.845l0.304-11.221c0,0-7.805,0.037-10.888,5.312 s0.87,11.351,0.87,11.351L189.435,194.845z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M189.435,194.845l9.695,5.528c0,0,4.275-7.166,1.092-12.382 c-3.183-5.215-10.482-4.367-10.482-4.367L189.435,194.845z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M179.721,200.286l9.714-5.441l9.695,5.528 c0,0-5.375,6.832-9.978,6.608C184.549,206.757,179.721,200.286,179.721,200.286z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M153.04,184.165l9.695,5.528c0,0,4.275-7.166,1.092-12.382 c-3.183-5.215-10.482-4.367-10.482-4.367L153.04,184.165z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M143.326,189.606l9.714-5.441l9.695,5.528 c0,0-5.375,6.832-9.978,6.608C148.154,196.077,143.326,189.606,143.326,189.606z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M104.938,186.498l0.304-11.221c0,0-7.805,0.037-10.888,5.312 s0.87,11.351,0.87,11.351L104.938,186.498z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M104.938,186.498l9.695,5.528c0,0,4.275-7.166,1.092-12.382 c-3.183-5.215-10.482-4.367-10.482-4.367L104.938,186.498z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M95.223,191.939l9.714-5.441l9.695,5.528 c0,0-5.375,6.832-9.978,6.608C100.051,198.41,95.223,191.939,95.223,191.939z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M86.44,141.085l0.304-11.221c0,0-7.805,0.037-10.888,5.312 s0.87,11.351,0.87,11.351L86.44,141.085z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M86.44,141.085l9.695,5.528c0,0,4.275-7.166,1.092-12.382 c-3.183-5.215-10.482-4.367-10.482-4.367L86.44,141.085z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M76.726,146.526l9.714-5.441l9.695,5.528 c0,0-5.375,6.832-9.978,6.608C81.554,152.997,76.726,146.526,76.726,146.526z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M105.163,152.623l-9.573-5.862c0,0-3.862,6.783-0.829,12.086 c3.033,5.303,10.271,4.909,10.271,4.909L105.163,152.623z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M105.163,152.623l9.628-5.644c0,0-4.078-7.281-10.187-7.124 c-6.107,0.157-9.015,6.906-9.015,6.906L105.163,152.623z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M105.032,163.756l0.131-11.134l9.628-5.644 c0,0,3.24,8.066,0.749,11.944C113.049,162.8,105.032,163.756,105.032,163.756z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M124.455,141.324l0.304-11.221c0,0-7.805,0.037-10.888,5.312 s0.87,11.351,0.87,11.351L124.455,141.324z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M124.455,141.324l9.695,5.528c0,0,4.275-7.166,1.092-12.382 c-3.183-5.215-10.482-4.367-10.482-4.367L124.455,141.324z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M114.741,146.766l9.714-5.441l9.695,5.528 c0,0-5.375,6.832-9.978,6.608C119.569,153.236,114.741,146.766,114.741,146.766z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M189.435,102.145l0.304-11.221c0,0-7.805,0.037-10.888,5.312 s0.87,11.351,0.87,11.351L189.435,102.145z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M179.721,107.586l9.714-5.441l9.695,5.528 c0,0-5.375,6.832-9.978,6.608C184.549,114.057,179.721,107.586,179.721,107.586z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M179.721,107.586l9.714-5.441l9.695,5.528 c0,0-5.375,6.832-9.978,6.608C184.549,114.057,179.721,107.586,179.721,107.586z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M188.683,172.079l-9.573-5.862c0,0-3.862,6.783-0.829,12.086 c3.033,5.303,10.271,4.91,10.271,4.91L188.683,172.079z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M188.683,172.079l9.628-5.644c0,0-4.078-7.281-10.187-7.124 c-6.107,0.157-9.015,6.906-9.015,6.906L188.683,172.079z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M188.552,183.213l0.131-11.134l9.628-5.644 c0,0,3.24,8.066,0.749,11.944C196.569,182.256,188.552,183.213,188.552,183.213z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M125.042,197.895l-9.573-5.861c0,0-3.862,6.783-0.829,12.086 s10.271,4.91,10.271,4.91L125.042,197.895z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M124.91,209.029l0.131-11.135l9.628-5.644 c0,0,3.24,8.065,0.749,11.944C132.927,208.072,124.91,209.029,124.91,209.029z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M86.765,197.914l-9.573-5.861c0,0-3.862,6.783-0.829,12.086 s10.271,4.91,10.271,4.91L86.765,197.914z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M86.765,197.914l9.628-5.644c0,0-4.078-7.28-10.187-7.124 c-6.107,0.156-9.015,6.906-9.015,6.906L86.765,197.914z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M86.633,209.049l0.131-11.135l9.628-5.644 c0,0,3.24,8.065,0.749,11.944C94.65,208.092,86.633,209.049,86.633,209.049z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M152.844,157.574l-9.573-5.861c0,0-3.862,6.783-0.829,12.086 s10.271,4.91,10.271,4.91L152.844,157.574z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M152.844,157.574l9.628-5.644c0,0-4.078-7.28-10.187-7.124 c-6.107,0.156-9.015,6.906-9.015,6.906L152.844,157.574z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M152.713,168.709l0.131-11.135l9.628-5.644 c0,0,3.24,8.065,0.749,11.944C160.73,167.752,152.713,168.709,152.713,168.709z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M190.082,136.155l-9.573-5.861c0,0-3.862,6.783-0.829,12.086 s10.271,4.91,10.271,4.91L190.082,136.155z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M190.082,136.155l9.628-5.644c0,0-4.078-7.28-10.187-7.124 c-6.107,0.156-9.015,6.906-9.015,6.906L190.082,136.155z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M189.95,147.29l0.131-11.135l9.628-5.644 c0,0,3.24,8.065,0.749,11.944C197.967,146.333,189.95,147.29,189.95,147.29z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M52.285,153.734l-9.573-5.861c0,0-3.862,6.783-0.829,12.086 s10.271,4.91,10.271,4.91L52.285,153.734z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M52.285,153.734l9.628-5.644c0,0-4.078-7.28-10.187-7.124 c-6.107,0.156-9.015,6.906-9.015,6.906L52.285,153.734z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M52.153,164.869l0.131-11.135l9.628-5.644 c0,0,3.24,8.065,0.749,11.944C60.17,163.912,52.153,164.869,52.153,164.869z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M190.082,80.294l-9.573-5.861c0,0-3.862,6.783-0.829,12.086 s10.271,4.91,10.271,4.91L190.082,80.294z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M189.95,91.429l0.131-11.135l9.628-5.644 c0,0,3.24,8.065,0.749,11.944C197.967,90.472,189.95,91.429,189.95,91.429z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M191.343,40.877l5.924-9.535c0,0-6.758-3.906-12.08-0.909 c-5.323,2.998-4.978,10.238-4.978,10.238L191.343,40.877z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M191.343,40.877l5.58,9.665c0,0,7.307-4.029,7.191-10.139 c-0.116-6.108-6.847-9.06-6.847-9.06L191.343,40.877z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M180.209,40.672l11.134,0.205l5.58,9.665 c0,0-8.086,3.187-11.949,0.67C181.113,48.695,180.209,40.672,180.209,40.672z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M33.7,142.765l0.304-11.221c0,0-7.805,0.037-10.888,5.312 s0.87,11.351,0.87,11.351L33.7,142.765z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M33.7,142.765l9.695,5.528c0,0,4.275-7.166,1.092-12.382 c-3.183-5.215-10.482-4.367-10.482-4.367L33.7,142.765z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M23.986,148.206l9.714-5.441l9.695,5.528 c0,0-5.375,6.832-9.978,6.608C28.814,154.677,23.986,148.206,23.986,148.206z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M57.555,185.965l0.304-11.221c0,0-7.805,0.037-10.888,5.312 s0.87,11.351,0.87,11.351L57.555,185.965z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M57.555,185.965l9.695,5.528c0,0,4.275-7.166,1.092-12.382 c-3.183-5.215-10.482-4.367-10.482-4.367L57.555,185.965z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M47.84,191.406l9.714-5.441l9.695,5.528 c0,0-5.375,6.832-9.978,6.608C52.668,197.877,47.84,191.406,47.84,191.406z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M302.481,371.038l-11.849-3.96c0,0-2.491,8.321,2.126,13.313 c4.618,4.993,12.367,2.753,12.367,2.753L302.481,371.038z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M302.481,371.038l9.026-8.53c0,0-6.245-6.876-12.829-5.175 c-6.585,1.698-8.046,9.745-8.046,9.745L302.481,371.038z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M305.126,383.145l-2.645-12.106l9.026-8.53 c0,0,5.531,7.936,3.802,12.763C313.58,380.101,305.126,383.145,305.126,383.145z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M416.579,328.285l-2.595-11.981c0,0-8.271,2.069-10.168,8.465 c-1.898,6.4,3.875,11.814,3.875,11.814L416.579,328.285z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M416.579,328.285l11.722,3.342c0,0,2.672-8.713-2.062-13.417 c-4.732-4.703-12.254-1.906-12.254-1.906L416.579,328.285z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M407.691,336.583l8.888-8.298l11.722,3.342 c0,0-3.924,8.645-8.863,9.604C414.495,342.193,407.691,336.583,407.691,336.583z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M348.948,360.123l9.479-8.453c0,0-6.086-6.401-12.821-4.849 c-6.74,1.554-8.676,9.521-8.676,9.521L348.948,360.123z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M348.948,360.123l2.967,12.272c0,0,7.982-2.324,9.805-8.995 c1.826-6.666-3.293-11.73-3.293-11.73L348.948,360.123z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M336.93,356.343l12.019,3.78l2.967,12.272 c0,0-9.795,0.875-13.182-3.089C335.345,365.341,336.93,356.343,336.93,356.343z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M298.398,407.67l12.133-2.303c0,0-1.871-8.384-8.263-10.458 c-6.396-2.078-11.998,3.596-11.998,3.596L298.398,407.67z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M298.398,407.67l-3.668,11.718c0,0,8.707,2.916,13.566-1.731 c4.858-4.643,2.234-12.289,2.234-12.289L298.398,407.67z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M290.271,398.505l8.128,9.165l-3.668,11.718 c0,0-8.604-4.172-9.444-9.174C284.447,405.214,290.271,398.505,290.271,398.505z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M326.134,401.694l8.488,9.903c0,0,6.695-6.122,5.234-13.068 c-1.457-6.951-9.602-9.1-9.602-9.1L326.134,401.694z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M326.134,401.694l-12.664,2.8c0,0,1.906,9.509,8.719,11.517 c6.809,2.009,12.434-4.413,12.434-4.413L326.134,401.694z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M330.255,389.43l-4.121,12.265l-12.664,2.8 c0,0-0.7-10.075,3.44-13.474C321.051,387.621,330.255,389.43,330.255,389.43z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M381.526,386.92l12.367-2.312c0,0-1.908-7.437-8.413-9.568 c-6.511-2.137-12.207,2.52-12.207,2.52L381.526,386.92z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M381.526,386.92l-3.771,11.928c0,0,8.862,2.995,13.824-1.726 c4.965-4.716,2.313-12.514,2.313-12.514L381.526,386.92z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M373.273,377.56l8.253,9.36l-3.771,11.928 c0,0-8.754-4.276-9.595-9.373C367.321,384.376,373.273,377.56,373.273,377.56z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M408.106,381.349l8.289,9.48c0,0,6.406-5.971,4.933-12.663 c-1.475-6.698-9.358-8.695-9.358-8.695L408.106,381.349z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M408.106,381.349l-12.196,2.825c0,0,2.053,8.014,8.649,9.887 c6.592,1.876,11.836-3.231,11.836-3.231L408.106,381.349z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M411.97,369.471l-3.863,11.878l-12.196,2.825 c0,0-0.773-9.722,3.189-13.041C403.065,367.812,411.97,369.471,411.97,369.471z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M396.248,346.944l2.731,11.938c0,0,8.238-2.164,10.058-8.575 c1.822-6.415-4.008-11.756-4.008-11.756L396.248,346.944z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M396.248,346.944l-11.748-3.2c0,0-2.566,8.735,2.219,13.379 c4.781,4.642,12.261,1.759,12.261,1.759L396.248,346.944z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M405.029,338.551l-8.781,8.394l-11.748-3.2 c0,0,3.816-8.681,8.741-9.698S405.029,338.551,405.029,338.551z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M322.668,351.872l-2.594-11.979c0,0-8.269,2.068-10.167,8.463 c-1.897,6.398,3.875,11.812,3.875,11.812L322.668,351.872z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M322.668,351.872l11.721,3.341c0,0,2.671-8.712-2.062-13.415 c-4.731-4.702-12.252-1.904-12.252-1.904L322.668,351.872z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M313.782,360.169l8.886-8.297l11.721,3.341 c0,0-3.924,8.644-8.862,9.604C320.585,365.777,313.782,360.169,313.782,360.169z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M443.371,335.936l2.778,11.721c0,0,8.377-2.124,10.227-8.419 c1.853-6.3-4.075-11.545-4.075-11.545L443.371,335.936z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M443.371,335.936l-11.945-3.143c0,0-2.609,8.577,2.256,13.137 c4.862,4.56,12.468,1.727,12.468,1.727L443.371,335.936z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M452.301,327.692l-8.93,8.243l-11.945-3.143 c0,0,3.881-8.523,8.889-9.523C445.321,322.27,452.301,327.692,452.301,327.692z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M414.1,421.138l4.451,9.182l11.495,1.038l6.996-8.332 c0,0-7.624-3.794-11.864-4.429C420.938,417.961,414.1,421.138,414.1,421.138z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M467.117,406.87l3.873,10.108l11.495,1.038l6.449-8.07 c0,0-7.077-4.056-11.317-4.69C473.377,404.62,467.117,406.87,467.117,406.87z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M413.14,440.098l5.94-9.73l-4.98-9.229l-10.8-1.319 c0,0,0.979,8.459,2.76,12.359S413.14,440.098,413.14,440.098z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M480.94,438.34l6.36-8.76l-5.194-11.233l-10.806-1.247 c0,0,0.779,9.42,2.56,13.32S480.94,438.34,480.94,438.34z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M428.56,450.237l6.36-8.76l-5.4-10.2l-10.8-1.319 c0,0,0.979,8.459,2.76,12.359S428.56,450.237,428.56,450.237z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M464.94,425.859l6.36-8.76l-4.184-10.229l-11.977-0.967 c0,0,0.939,8.136,2.72,12.036S464.94,425.859,464.94,425.859z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M503.263,439.809l-4.687-9.291l-11.53-0.517l-6.105,8.339 c0,0,7.28,3.746,11.545,4.188S503.263,439.809,503.263,439.809z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M450.28,451.6l-3.771-9.224l-11.53-0.517l-6.419,8.378 c0,0,7.594,3.707,11.858,4.149S450.28,451.6,450.28,451.6z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M453.121,432.408l-6.441,9.251l4.449,9.63l10.711,1.905 c0,0-0.516-8.5-2.081-12.491C458.194,436.711,453.121,432.408,453.121,432.408z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M488.935,409.946l-6.828,8.4l5.194,11.233l10.859,0.54 c0,0-1.022-7.888-2.588-11.879C494.008,414.249,488.935,409.946,488.935,409.946z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M436.6,422.92l-6.633,8.025l5.012,10.914l11.53,0.517 c0,0-1.511-7.545-3.076-11.536C441.868,426.848,436.6,422.92,436.6,422.92z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M504.4,420.76l-5.974,8.568l4.836,10.48l10.636,0.871 c0,0-0.44-7.466-2.006-11.457C510.328,425.23,504.4,420.76,504.4,420.76z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M219.88,30.52 263.2,30.52 263.2,57.28 220.96,57.28z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M221,58.49 263.2,58.72 263.2,85.24 224.32,85.12z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M224.32,86.86 263.2,86.32 263.2,112.84 220.3,112.55z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M220,114.28 263.2,113.68 263.2,168.16 220,168.16 224.13,142.72z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M220,170.2 263.2,169.66 263.2,224.8 220,224.8 224.299,197.44z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M220,226 263.2,226.72 263.2,252.04 224.68,253z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M224.68,253.96 263.073,253.84 263.2,279.881 220,279.28z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M217.38,283.96 263.18,283.96 264.64,348.28 242.08,352.84 233.56,355.6z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M317.2,451.48 326.2,489.52 350.8,483.598 340.36,441.04z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M368.44,439.48 377.44,477.4 402.52,471.4 392.561,427.96z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M367.526,439.55 376.615,477.76 351.641,483.598 341.306,440.409z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M316.12,451.72 325.12,489.76 272.08,502.359 259.36,449.56 272.44,446.44 283.24,444.16 285.838,453.939 292.72,452.56z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M3207.16,460.36l13.08,54.066l51.3-12.067l-14.46-62.039l2.76-8.641l-9.66-3.239 c0,0-20.6-5.111-21.9-4.2L213.4,458.8L207.16,460.36z").save(flush: true);
		new Hotspot( floor: "11", type: "desk", polygon: "M219.88,4.96 263.20,4.96 263.20,29.32 219.88,29.32 219.88,4.96z").save(flush: true);
		*/
		
    }
    def destroy = {
    }
}
