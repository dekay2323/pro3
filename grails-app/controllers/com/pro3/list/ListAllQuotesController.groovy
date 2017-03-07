package com.pro3.list

import com.pro3.Quote
import com.pro3.user.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class ListAllQuotesController {
    def authUserService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        List<User> vendorList = authUserService.obtainVendorsList()
        
        def quoteList = vendorList.collect {user->
            Quote.findAllByVendor(user)
        }.flatten()
        render view: "index", model: [
                quoteList: quoteList]
    }

}