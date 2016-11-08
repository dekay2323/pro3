package com.pro3.flow

import com.pro3.Client
import com.pro3.Quote
import com.pro3.Rfq
import com.pro3.user.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class FlowBidController {
    def authUserService

    def index() {
        log.debug("flowBid() ${params}")

        Rfq rfq = Rfq.get(params.id)

        respond rfq
    }
}
