package com.pro3.flow

import com.pro3.domain.main.Rfq
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class FlowBidController {

    def index() {
        log.debug("flowBid() ${params}")

        Rfq rfq = Rfq.get(params.id)

        respond rfq
    }
}
