package com.pro3.flow

import com.pro3.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.NOT_FOUND

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class ShowMaterialRequestController {

    def showMaterialRequest(MaterialRequest materialRequest) {
        log.debug("editMaterialRequest() ${materialRequest}")
        respond materialRequest, [model: [client: materialRequest?.project?.client]]
    }

    protected void notFound() {
        log.warn('notFound')
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialRequest.label', default: 'MaterialRequest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
