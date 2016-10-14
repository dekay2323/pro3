package com.pro3.list

import com.pro3.Client
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class ListProjectController {
    def authService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def clientList = authService.obtainClients()
        respond clientList.asList(), model:[clientCount: Client.count()]
    }

}
