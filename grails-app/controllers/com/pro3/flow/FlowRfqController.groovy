package com.pro3.flow

import com.pro3.MaterialRequest
import com.pro3.Pro3Exception
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class FlowRfqController {
    def rfqService

    @Transactional
    def createRfq() {
        MaterialRequest materialRequest = MaterialRequest.get(params.id)
        String projectId = materialRequest?.project?.id
        log.debug "createRfq() ${params}"
        try {
            rfqService.createRfqAndQuotes(params.id)
        } catch (Pro3Exception e) {
            flash.error = e.message
            redirect(controller: 'listMaterialRequest',
                    action: 'index',
                    id: projectId)
            return
        }

        redirect(controller: 'listMaterialRequest',
                action: 'index',
                id: projectId)
    }

}
