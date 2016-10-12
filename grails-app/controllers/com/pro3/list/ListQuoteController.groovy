package com.pro3.list

import com.pro3.Quote
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class ListQuoteController {

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Quote.list(params), model:[quoteCount: Quote.count()]
    }

}
