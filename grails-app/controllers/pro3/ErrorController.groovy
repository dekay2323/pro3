package pro3

import grails.converters.JSON

class ErrorController {

    // Forbidden
    def error403() {
        withFormat {
            html { render(view: 'error403') }
            json {
                render(status: 403, text:'', contentType: 'application/json')
            }
        }
    }

    // Not Found
    def error404() {
        def message = request?.getAttribute('javax.servlet.error.message')

        withFormat {
            html {
                //String missingPage = request.getAttribute('javax.servlet.error.message')
                //log.info("404 when trying to access page ${missingPage}")
                render(view: '/error404', model: [message: message])
            }
            json {
                render(status: 404, text:'', contentType: 'application/json')
            }
        }
    }

    // Conflict
    def error409() {
        withFormat {
            html { render(view: 'error409') }
            json {
                render(status: 409, text:'', contentType: 'application/json')
            }
        }
    }

    def error500() {
        def exception = request.exception
        withFormat {
            html { render(view: '/error') }
            json { render exception as JSON}
        }
    }
}