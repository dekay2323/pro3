package com.pro3

import com.pro3.aux.LineItem
import com.pro3.aux.QuoteLineItem
import com.pro3.main.Quote
import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification

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
        def obj = new QuoteLineItem()
        then:
        obj.validate() == false
        obj.hasErrors() == true
        obj.errors.errorCount == 3
        obj.errors['quote']?.objectName == 'com.pro3.aux.QuoteLineItem'
        obj.errors['lineItem']?.objectName == 'com.pro3.aux.QuoteLineItem'
        obj.errors['code']?.objectName == 'com.pro3.aux.QuoteLineItem'
    }


    def "can save minimal object"() {
        when:
        def obj = new QuoteLineItem()
        obj.quote = Mock(Quote)
        obj.lineItem = Mock(LineItem)
        obj.code = 'code'
        then:
        obj.validate() == true
        obj.hasErrors() == false
        obj.errors.errorCount == 0
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