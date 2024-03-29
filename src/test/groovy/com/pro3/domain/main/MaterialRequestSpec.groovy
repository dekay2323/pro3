package com.pro3.domain.main

import com.pro3.domain.aux.LineItem
import com.pro3.domain.list.RequestStatus
import com.pro3.domain.user.User
import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import groovy.time.TimeCategory
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(DomainClassUnitTestMixin)
@Mock(MaterialRequest)
class MaterialRequestSpec extends Specification {
    def setup() {
    }

    def cleanup() {
    }

    def "constraint errors"() {
        when:
        def obj = new MaterialRequest()
        then:
        obj.validate() == false
        obj.hasErrors() == true
        obj.errors.errorCount == 3
        obj.errors['project']?.objectName == 'com.pro3.domain.main.MaterialRequest'
        obj.errors['status']?.objectName == 'com.pro3.domain.main.MaterialRequest'
        obj.errors['name']?.objectName == 'com.pro3.domain.main.MaterialRequest'
    }

    def "can save minimal object"() {
        when:
        def obj = new MaterialRequest(
                id: 1,
                project: Mock(Project),
                name: 'name',
                status: Mock(RequestStatus))
        then:
        obj.validate() == true
        obj.hasErrors() == false
        obj.errors.errorCount == 0
    }

    @Unroll
    def "test canCreateRFQ() fails with #materialRequest"() {
        expect:
        materialRequest.canCreateRFQ() == result
        where:
        result  || materialRequest
        false   || new MaterialRequest()
        false   || new MaterialRequest(bidders: [Mock(User)])
        false   || new MaterialRequest(lineItems: [Mock(LineItem)])
        false   || new MaterialRequest(status: RequestStatus.RequestStatusEnum.START)
    }

    def "test canCreateRFQ() pass"() {
        when:
        def materialRequest = new MaterialRequest(id: 1)
        materialRequest.status = new RequestStatus(name: RequestStatus.RequestStatusEnum.START.name())
        materialRequest.bidders = [Mock(User)]
        materialRequest.lineItems = [Mock(LineItem)]
        then:
        materialRequest.canCreateRFQ() == true
    }

    def "test daysLeftTillRas() display correctly"() {
        setup:
        def materialRequest = new MaterialRequest(id: 1)
        def currentDate = new Date()
        use(TimeCategory) {
            currentDate = currentDate + 23.days
        }
        materialRequest.rasDate = currentDate.format('yyyy-MM-dd')
        when:
        def daysLeftTillRas = materialRequest.daysLeftTillRas()
        then:
        daysLeftTillRas == '22 days left'
    }

    def "test readOnlyRFQ() should be true at certain statuses"() {
        expect:
        new MaterialRequest(status: new RequestStatus(name: RequestStatus.RequestStatusEnum.ADD_TO_PLAN.name())).readOnlyRFQ() == false
        new MaterialRequest(status: new RequestStatus(name: RequestStatus.RequestStatusEnum.START.name())).readOnlyRFQ() == false
        new MaterialRequest(status: new RequestStatus(name: RequestStatus.RequestStatusEnum.APPROVED_TO_PLAN.name())).readOnlyRFQ() == false
        new MaterialRequest(status: new RequestStatus(name: RequestStatus.RequestStatusEnum.RFQ_ISSUED.name())).readOnlyRFQ() == true
        new MaterialRequest(status: new RequestStatus(name: RequestStatus.RequestStatusEnum.BIDS_RECIEVED.name())).readOnlyRFQ() == true
        new MaterialRequest(status: new RequestStatus(name: RequestStatus.RequestStatusEnum.EVALUATION_COMPLETE.name())).readOnlyRFQ() == true
        new MaterialRequest(status: new RequestStatus(name: RequestStatus.RequestStatusEnum.PO_ISSUED.name())).readOnlyRFQ() == true
    }
}
