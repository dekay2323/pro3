package com.pro3

import com.pro3.list.QuoteStatus
import com.pro3.main.Quote
import com.pro3.main.Rfq
import com.pro3.user.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(AuthVendorService)
@Mock(Quote)
class AuthVendorServiceSpec extends Specification {
    def setup() {
        service.springSecurityService = Mock(SpringSecurityService)
    }

    def cleanup() {
    }

    void "obtainCurrentUser() should return logged on user"() {
        setup:
        User user = new User()
        user.id = 1
        when:
        service.obtainCurrentUser()
        2*service.springSecurityService.getCurrentUser() >> user
        then:
        service.obtainCurrentUser() == user
        service.obtainCurrentUser()?.id == 1
    }


    void "obtainCurrentUser() should null if no user logged on"() {
        when:
        service.obtainCurrentUser()
        then:
        service.obtainCurrentUser() == null
    }

    void "obtainAllQuotes() should return all quotes on a user"() {
        setup:
        User user = new User()
        user.id = 1
        Quote quote = new Quote()
        quote.vendor = Mock(User)
        quote.status = new QuoteStatus(name: QuoteStatus.QuoteStatusEnum.START.name())
        quote.rfq = new Rfq()
        quote.bidNumber = '1'
        quote.save(failOnError: true)
        when:
        1*service.springSecurityService.getCurrentUser() >> user
        then:
        service.obtainAllQuotes()?.size() == 1
    }

    void "obtainAllQuotes() should return empty on quotes"() {
        setup:
        User user = new User()
        user.id = 1
        when:
        1*service.springSecurityService.getCurrentUser() >> user
        then:
        service.obtainAllQuotes()?.size() == 0
    }
}
