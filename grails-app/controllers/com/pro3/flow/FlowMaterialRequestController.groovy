package com.pro3.flow

import com.pro3.LineItem
import com.pro3.MaterialRequest
import com.pro3.Project
import com.pro3.RequestStatus
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class FlowMaterialRequestController {

    def createMaterialRequest() {
        log.debug("create() ${params}")
        if (params?.projectId) {
            params.project = Project.get(params?.projectId)
        }
        params.status = RequestStatus.findByName('Start')
        respond new MaterialRequest(params), [model: [client: params?.project?.client]]
    }

    def editMaterialRequest(MaterialRequest materialRequest) {
        log.debug("editMaterialRequest() ${materialRequest}")
        respond materialRequest, [model: [client: materialRequest?.project?.client]]
    }

    @Transactional
    def saveMaterialRequest(MaterialRequest materialRequest) {
        log.debug("saveMaterialRequest() ${materialRequest}")
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

        flash.message = "Material Request Created [${materialRequest.id}]"
        redirect controller: 'listMaterialRequest', action: 'index', id: materialRequest?.project?.id
    }

    @Transactional
    def updateMaterialRequest(MaterialRequest materialRequest) {
        log.debug("updateMaterialRequest() ${materialRequest}")
        if (materialRequest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (materialRequest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond materialRequest.errors, view:'edit'
            return
        }

        flash.message = "Material Request Updated [${materialRequest.id}]"
        redirect controller: 'listMaterialRequest', action: 'index', id: materialRequest?.project?.id
    }

    def createLineItem() {
        log.debug("createLineItem() ${params}")
        params.request = params?.materialRequestId
        respond new LineItem(params)
    }

    @Transactional
    def saveLineItem(LineItem lineItem) {
        log.debug "saveLineItem() ${lineItem}"
        if (lineItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (lineItem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond lineItem.errors, view:'create'
            return
        }

        lineItem.save flush:true
        redirect action: 'editMaterialRequest', id: lineItem?.request?.id
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
