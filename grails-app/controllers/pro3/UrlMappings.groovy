package pro3

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'home', action: 'index')
        "500"(controller: 'error', action: 'error500')
        "404"(controller: 'error', action: 'error404')
        "403"(controller: 'error', action: 'error403')
    }
}
