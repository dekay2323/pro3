package com.pro3.list

import com.pro3.Quote
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class ListQuoteController {
    def authVendorService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        render view: 'index', model:[quoteList: authVendorService.obtainAllQuotes(), quoteCount: Quote.count()]
    }

}
