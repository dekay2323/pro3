package com.pro3.list

import com.pro3.Client
import com.pro3.Constants
import com.pro3.MaterialRequest
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured([Constants.ROLE_ADMIN, Constants.ROLE_USER])
@Transactional(readOnly = true)
class ListProjectController {

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Client.list(params), model:[clientCount: Client.count()]
    }

}
