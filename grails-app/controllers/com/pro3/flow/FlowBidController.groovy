package com.pro3.flow

import com.pro3.MaterialRequest
import com.pro3.PurchaseOrder
import com.pro3.Quote
import com.pro3.QuoteStatus
import com.pro3.RequestStatus
import com.pro3.Rfq
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
