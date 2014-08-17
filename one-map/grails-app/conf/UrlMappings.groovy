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
		
        "500"(view:'/error')
	}
}
