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
			controller="oneMap"
			action="showAdmin"
		}
		
        "500"(view:'/error')
	}
}
