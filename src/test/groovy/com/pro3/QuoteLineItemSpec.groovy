package com.pro3

import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification

import javax.sound.sampled.Line

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(DomainClassUnitTestMixin)
@Mock([QuoteLineItem])
class QuoteLineItemSpec extends Specification {
    def setup() {
    }

    def cleanup() {
    }

    def "constraint errors"() {
        when:
        def validateable = new QuoteLineItem()
        then:
        validateable.validate() == false
        validateable.hasErrors() == true
        validateable.errors.errorCount == 3
        validateable.errors['quote']?.objectName == 'com.pro3.QuoteLineItem'
        validateable.errors['lineItem']?.objectName == 'com.pro3.QuoteLineItem'
        validateable.errors['code']?.objectName == 'com.pro3.QuoteLineItem'
    }


    def "can save minimal object"() {
        when:
        def validateable = new QuoteLineItem()
        validateable.quote = Mock(Quote)
        validateable.lineItem = Mock(LineItem)
        validateable.code = 'code'
        then:
        validateable.validate() == true
        validateable.hasErrors() == false
        validateable.errors.errorCount == 0
    }

    def "getExtendedPrice() should take the price*lineItem.quantity"() {
        setup:
        def obj = new QuoteLineItem()
        when:
        obj.price = 100
        obj.lineItem = new LineItem(quantity: 3)
        then:
        obj.getExtendedPrice() == 300
    }

    def "getExtendedPrice() should handle nulls in price*lineItem.quantity resulting in 0"() {
        when:
        def obj = new QuoteLineItem()
        then:
        obj.getExtendedPrice() == 0
        when:
        obj.price = 100
        then:
        obj.getExtendedPrice() == 0
    }
}