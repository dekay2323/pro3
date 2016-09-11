package com.pro3.flow

import com.pro3.LineItem
import com.pro3.MaterialRequest
import com.pro3.Project
import com.pro3.RequestStatus
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class FlowMaterialRequestController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def createMaterialRequest() {
        log.debug("create() ${params}")
        if (params?.projectId) {
            params.project = Project.get(params?.projectId)
        }
        params.status = RequestStatus.findByName('Start')
        respond new MaterialRequest(params), [model: [client: params?.project?.client]]
    }

    def editMaterialRequest(MaterialRequest materialRequest) {
        respond materialRequest
    }

    @Transactional
    def saveMaterialRequest(MaterialRequest materialRequest) {
        if (materialRequest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (materialRequest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond materialRequest.errors, view:'create'
            return
        }

        materialRequest.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'materialRequest.label', default: 'MaterialRequest'), materialRequest.id])
                redirect materialRequest
            }
            '*' { respond materialRequest, [status: CREATED] }
        }
        redirect controller: 'listMaterialRequest', action: 'index', id: materialRequest?.project?.id
    }

    def createLineItem() {
        log.debug("createLineItem() ${params}")
        respond new LineItem(params)
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialRequest.label', default: 'MaterialRequest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
