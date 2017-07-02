package com.pro3.open

import com.pro3.domain.user.User

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
