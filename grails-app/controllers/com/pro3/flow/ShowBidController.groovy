package com.pro3.flow

import com.pro3.Client
import com.pro3.Quote
import com.pro3.Rfq
import com.pro3.user.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class ShowBidController {
    def authService

    def index() {
        log.debug("showBid() ${params}")

        Rfq rfq = Rfq.get(params.id)

        respond rfq
    }
}
