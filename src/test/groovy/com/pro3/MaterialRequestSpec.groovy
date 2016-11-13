package com.pro3

import grails.test.hibernate.HibernateSpec
import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
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
        def validateable = new MaterialRequest()
        then:
        validateable.validate() == false
        validateable.hasErrors() == true
        validateable.errors.errorCount > 0
    }

    def "can save minimal object"() {
        when:
        def validateable = new MaterialRequest(
                id: 1,
                project: Mock(Project),
                status: Mock(RequestStatus))
        then:
        validateable.validate() == true
        validateable.hasErrors() == false
        validateable.errors.errorCount == 0
    }

    @Unroll
    def "test canCreateRFQ() fails with #materialRequest"() {
        expect:
        materialRequest.canCreateRFQ() == result
        where:
        result  || materialRequest
        false   || new MaterialRequest()
        false   || new MaterialRequest(bidders: [Mock(Vendor)])
        false   || new MaterialRequest(lineItems: [Mock(LineItem)])
        false   || new MaterialRequest(status: RequestStatus.RequestStatusEnum.START)
    }

    def "test canCreateRFQ() pass"() {
        when:
        def materialRequest = new MaterialRequest(id: 1)
        materialRequest.status = new RequestStatus(name: RequestStatus.RequestStatusEnum.START.name())
        materialRequest.bidders = [Mock(Vendor)]
        materialRequest.lineItems = [Mock(LineItem)]
        then:
        materialRequest.canCreateRFQ() == true
    }

    def "test getShipDate() pass"() {
        when:
        def materialRequest = new MaterialRequest(id: 1)
        materialRequest.estLeadTime = Date.parse("yyyy-MM-dd", "2014-01-01")
        then:
        materialRequest.getShipDate().format("yyyy-MM-dd") == "2014-01-15"
    }

    def "test readOnlyRFQ()"() {
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