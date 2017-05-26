package com.pro3.show

import com.pro3.main.MaterialRequest
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class ShowMaterialRequestController {
    def showMaterialRequest(MaterialRequest materialRequest) {
        log.debug("show() ${materialRequest}")
        respond materialRequest, [model: [client: materialRequest?.project?.client]]
    }
}
