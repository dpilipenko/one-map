class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }

		"/onemap"{
			controller="oneMap"
			action="show"
		}
		
        "/"(view:"/home")
        "500"(view:'/error')
	}
}
