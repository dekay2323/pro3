package com.pro3.flow

import com.pro3.*
import com.pro3.embedded.LineItem
import com.pro3.embedded.MaterialRequest
import com.pro3.selectors.RequestStatus
import com.pro3.embedded.Vddr
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.NOT_FOUND

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
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

    def saveMaterialRequest(MaterialRequest materialRequest) {
        log.debug("saveMaterialRequest() ${materialRequest}")
        if (materialRequest == null) {
            notFound()
            return
        }

        if (materialRequest.hasErrors()) {
            respond materialRequest.errors, view:'createMaterialRequest'
            return
        }

        materialRequest.save flush:true

        flash.message = "Material Request Created [${materialRequest.id}]"
        redirect controller: 'listMaterialRequest', action: 'index', id: materialRequest?.project?.id
    }

    def updateMaterialRequest(MaterialRequest materialRequest) {
        log.debug("updateMaterialRequest() ${materialRequest}")
        if (materialRequest == null) {
            notFound()
            return
        }

        if (materialRequest.hasErrors()) {
            respond materialRequest.errors, view:'editMaterialRequest'
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

    def createVddr() {
        log.debug("createVddr() ${params}")
        params.request = params?.materialRequestId
        respond new LineItem(params)
    }

    def saveVddr(Vddr vddr) {
        log.debug "saveVddr() ${vddr}"
        if (vddr == null) {
            notFound()
            return
        }

        if (vddr.hasErrors()) {
            respond vddr.errors, view:'createVddr'
            return
        }

        vddr.save flush:true
        redirect action: 'editMaterialRequest', id: vddr?.request?.id
    }

    def saveLineItem(LineItem lineItem) {
        log.debug "saveLineItem() ${lineItem}"
        if (lineItem == null) {
            notFound()
            return
        }

        if (lineItem.hasErrors()) {
            respond lineItem.errors, view:'createLineItem'
            return
        }
        lineItem?.request.addToLineItems(lineItem)
        lineItem.save failOnError: true
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
