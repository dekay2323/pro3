package com.pro3.domain.main

import com.pro3.domain.list.QuoteStatus
import com.pro3.domain.user.User
import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(DomainClassUnitTestMixin)
@Mock([Quote])
class QuoteSpec extends Specification {
    def setup() {
    }

    def cleanup() {
    }

    def "constraint errors"() {
        when:
        def obj = new Quote()
        then:
        obj.validate() == false
        obj.hasErrors() == true
        obj.errors.errorCount == 3
        obj.errors['vendor']?.objectName == 'com.pro3.domain.main.Quote'
        obj.errors['status']?.objectName == 'com.pro3.domain.main.Quote'
        obj.errors['rfq']?.objectName == 'com.pro3.domain.main.Quote'
    }


    def "can save minimal object"() {
        when:
        def obj = new Quote()
        obj.vendor = Mock(User)
        obj.status = new QuoteStatus(name: QuoteStatus.QuoteStatusEnum.START.name())
        obj.rfq = Mock(Rfq)
        then:
        obj.validate() == true
        obj.hasErrors() == false
        obj.errors.errorCount == 0
    }
}
