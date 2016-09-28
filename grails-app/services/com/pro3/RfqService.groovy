package com.pro3

import grails.transaction.Transactional
import grails.validation.ValidationException

@Transactional
class RfqService {

    def MaterialRequest createRfqAndQuotes(def materialRequestId) throws ValidationException {
        log.debug("createRfqs ${materialRequestId}")
        MaterialRequest materialRequest = MaterialRequest.get(materialRequestId)

        def rfq = new Rfq(materialRequest: materialRequest)
        if (materialRequest?.bidders?.isEmpty()) {
            throw new ValidationException("No bidders")
        }
        materialRequest?.bidders?.each {vendor->
            def quote = new Quote(rfq: rfq, vendor: vendor)
            rfq.name = "${materialRequest.reqNumber} - ${materialRequest.description}"
            rfq.addToQuotes(quote)
            rfq.save failOnError: true
            quote.save failOnError: true
        }
        materialRequest.setStatus(RequestStatus.findByName('RFQ Issued'))
        materialRequest.save failOnError: true
        materialRequest
    }
}
