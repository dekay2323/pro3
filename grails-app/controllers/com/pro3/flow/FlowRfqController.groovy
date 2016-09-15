package com.pro3.flow

import com.pro3.LineItem
import com.pro3.MaterialRequest
import com.pro3.RequestStatus
import com.pro3.Rfq

class FlowRfqController {

    def createRfq() {
        log.debug("createRfq() ${params}")
        params.request = params?.id
        def materialRequest = MaterialRequest.get(params?.id)
        respond materialRequest, [model: [vendors:materialRequest?.bidders]]
/*        log.debug "createRfq() ${params}"
        def materialRequest = MaterialRequest.get(params?.id)
        materialRequest.setStatus(RequestStatus.findByName('RFQ Issued'))
        materialRequest.save flush:true

        def rfq = new Rfq(

        )

        flash.message = "Quote created [${materialRequest.id}]"
        redirect controller: 'listMaterialRequest', action: 'index', id: materialRequest?.project?.id*/
    }

    def saveRfq() {
        log.debug("saveRfq() ${params}")
    }
}
