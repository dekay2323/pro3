package com.pro3.flow

import com.pro3.MaterialRequest
import grails.transaction.Transactional

class FlowRfqController {
    def rfqService

    @Transactional
    def createRfq() {
        log.debug "createRfq() ${params}"
        MaterialRequest materialRequest = rfqService.createRfqAndQuotes(params.id)
        redirect(controller: 'listMaterialRequest',
                action: 'index',
                id: materialRequest?.project?.id)
    }

}
