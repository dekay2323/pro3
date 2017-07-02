package com.pro3.list

import com.pro3.domain.main.Rfq
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class ListRfqController {
    def authUserService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        render view: 'index', model:[rfqList: authUserService.obtainAllRfqs(), rfqCount: Rfq.count()]
    }

}
