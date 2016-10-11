package com.pro3.flow

import com.pro3.Constants
import com.pro3.MaterialRequest
import com.pro3.Pro3Exception
import com.pro3.Rfq
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured([Constants.ROLE_ADMIN, Constants.ROLE_USER])
@Transactional(readOnly = true)
class FlowRfqController {
    def rfqService

    @Transactional
    def createRfq() {
        log.debug "createRfq() ${params}"
        rfqService.createRfqAndQuotes(params.id)

        MaterialRequest materialRequest = MaterialRequest.get(params.id)
        redirect(controller: 'listMaterialRequest',
                action: 'index',
                id: materialRequest?.project?.id)
    }

}
