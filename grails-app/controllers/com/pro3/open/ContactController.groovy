package com.pro3.open

import com.pro3.user.User

class ContactController {
    def authUserService
    
    def index() {
        log.debug("index()")
        User user = authUserService.obtainCurrentUser()

        render view: 'index', model: [email: user?.email]
    }
}
