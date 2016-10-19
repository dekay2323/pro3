package com.pro3.list

import com.pro3.embedded.Client
import com.pro3.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class ListProjectController {
    def authService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        User user = authService.obtainCurrentUser()
        def clientList = user.account.clients ?: []
        respond clientList.asList(), model:[clientCount: clientList.size()]
    }

}
