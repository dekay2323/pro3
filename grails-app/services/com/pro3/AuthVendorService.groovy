package com.pro3

import com.pro3.user.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

/**
 * Authenticating obtains for user
 */
@Secured(['ROLE_ADMIN', 'ROLE_VENDOR'])
@Transactional(readOnly = true)
class AuthVendorService {
    def springSecurityService

    def obtainCurrentUser() {
        springSecurityService.getCurrentUser()
    }

    def obtainAllQuotes() {
        User user = obtainCurrentUser()
        def quotes = Quote.findAllByVendor(user)
        quotes.asList()
    }

}
