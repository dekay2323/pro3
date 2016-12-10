package com.pro3.flow

import com.pro3.*
import com.pro3.user.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.NOT_FOUND

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class FlowMaterialRequestController {
    AmazonService amazonService
    AuthUserService authUserService
    
    def createMaterialRequest() {
        log.debug("create() ${params}")
        if (params?.projectId) {
            params.project = Project.get(params?.projectId)
        }
        params.status = RequestStatus.findByName(RequestStatus.RequestStatusEnum.START)
        respond new MaterialRequest(params), [model: [client: params?.project?.client]]
    }

    def editMaterialRequest(MaterialRequest materialRequest) {
        log.debug("editMaterialRequest() ${materialRequest}")
        def filesList = amazonService.listFilesForAccount(materialRequest.obtainFileDirectory(authUserService.obtainCurrentUser()?.account?.name))
        respond materialRequest, [model: [client: materialRequest?.project?.client, files: filesList]]
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
            respond materialRequest.errors, view:'createMaterialRequest'
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

    @Transactional
    def saveVddr(Vddr vddr) {
        log.debug "saveVddr() ${vddr}"
        if (vddr == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (vddr.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond vddr.errors, view:'createVddr'
            return
        }

        vddr.save flush:true
        redirect action: 'editMaterialRequest', id: vddr?.request?.id
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
            respond lineItem.errors, view:'createLineItem'
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
