package com.pro3.list

import com.pro3.Rfq
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class ListRfqController {
    def authService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        render view: 'index', model:[rfqList: authService.obtainAllRfqs(), rfqCount: Rfq.count()]
    }

}
