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
		
		"/admin"{
			controller="oneMap"
			action="showAdmin"
		}
		
        "500"(view:'/error')
	}
}
