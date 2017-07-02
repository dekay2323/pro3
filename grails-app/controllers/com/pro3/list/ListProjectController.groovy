package com.pro3.list

import com.pro3.domain.user.Client
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class ListProjectController {
    def authUserService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def clientList = authUserService.obtainAllClients()
        respond clientList, model:[clientCount: Client.count()]
    }

}
