package com.pro3.flow

import com.pro3.main.Rfq
import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.*

import javax.servlet.http.HttpServletResponse

@TestFor(FlowBidController)
@Build(Rfq)
class FlowBidControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test index cannot find"() {
        when:
        params.id = '1'
        controller.index()

        then:
        response.getStatus() == HttpServletResponse.SC_NOT_FOUND
    }

    void "test index"() {
        setup:
        Rfq rfq = Rfq.build(name: 'rfqName')

        when:
        params.id = rfq.id
        controller.index()

        then:
        response.getStatus() == HttpServletResponse.SC_OK
        model?.rfq != null
        model?.rfq?.id == 1
        model?.rfq?.name == 'rfqName'
    }
}
