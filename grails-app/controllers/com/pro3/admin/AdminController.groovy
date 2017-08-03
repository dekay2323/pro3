package com.pro3.admin

import com.pro3.domain.user.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class AdminController {
    def index() { 
        respond User.list()
    }
}
