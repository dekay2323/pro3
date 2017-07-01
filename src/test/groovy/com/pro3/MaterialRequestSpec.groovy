package com.pro3

import com.pro3.aux.LineItem
import com.pro3.list.RequestStatus
import com.pro3.main.MaterialRequest
import com.pro3.main.Project
import com.pro3.user.User
import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
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
        obj.errors.errorCount == 2
        obj.errors['project']?.objectName == 'com.pro3.main.MaterialRequest'
        obj.errors['status']?.objectName == 'com.pro3.main.MaterialRequest'
    }

    def "can save minimal object"() {
        when:
        def obj = new MaterialRequest(
                id: 1,
                project: Mock(Project),
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
        materialRequest.closingDate = new Date()
        then:
        materialRequest.canCreateRFQ() == true
    }

    def "test getShipDate() not sure what it does yet"() {
        when:
        def materialRequest = new MaterialRequest(id: 1)
        materialRequest.estLeadTime = Date.parse("yyyy-MM-dd", "2014-01-01")
        then:
        materialRequest.getShipDate().format("yyyy-MM-dd") == "2014-01-15"
    }

    def "test readOnlyRFQ() should be true at certain statuses"() {
        expect:
        new MaterialRequest(status: new RequestStatus(name: RequestStatus.RequestStatusEnum.ADD_TO_PLAN.name())).readOnlyRFQ() == true
        new MaterialRequest(status: new RequestStatus(name: RequestStatus.RequestStatusEnum.START.name())).readOnlyRFQ() == true
        new MaterialRequest(status: new RequestStatus(name: RequestStatus.RequestStatusEnum.APPROVED_TO_PLAN.name())).readOnlyRFQ() == true
        new MaterialRequest(status: new RequestStatus(name: RequestStatus.RequestStatusEnum.RFQ_ISSUED.name())).readOnlyRFQ() == false
        new MaterialRequest(status: new RequestStatus(name: RequestStatus.RequestStatusEnum.BIDS_RECIEVED.name())).readOnlyRFQ() == true
        new MaterialRequest(status: new RequestStatus(name: RequestStatus.RequestStatusEnum.EVALUATION_COMPLETE.name())).readOnlyRFQ() == true
        new MaterialRequest(status: new RequestStatus(name: RequestStatus.RequestStatusEnum.PO_ISSUED.name())).readOnlyRFQ() == true
    }
}
