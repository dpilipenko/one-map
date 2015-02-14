class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }

		"/"{
			controller="oneMap"
			action="show"
		}
				
		"/login/$action?"(controller: "login")
		"/logout/$action?"(controller: "logout")
		
		"/admin"{
			controller="admin"
			action="showAdmin"
		}
		"/admin2" {
			controller="admin"
			action="showAdmin2"
		}
		"/admin/seatchart" (controller: "admin") {
			action = [GET: "seatChartExport", POST: "seatChartImport"]
		}
		
        "500"(view:'/error')
	}
}
