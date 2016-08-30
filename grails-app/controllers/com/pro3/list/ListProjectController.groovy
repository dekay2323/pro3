package com.pro3.list

import com.pro3.Client
import com.pro3.MaterialRequest
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ListProjectController {

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Client.list(params), model:[clientCount: Client.count()]
    }

}
