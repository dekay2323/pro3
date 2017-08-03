package com.pro3.open

import com.pro3.domain.user.User
import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class ContactController {
    def authUserService
    
    def index() {
        log.debug("index()")
        User user = authUserService.obtainCurrentUser()

        render view: 'index', model: [user: user ]
    }

    def complete() {
        log.debug("complete()")
        User user = authUserService.obtainCurrentUser()

        render view: 'complete', model: [user: user ]
    }

}
