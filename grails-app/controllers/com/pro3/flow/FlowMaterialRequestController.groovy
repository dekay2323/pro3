package com.pro3.flow

import com.pro3.NumberAwareSorter
import com.pro3.domain.aux.LineItem
import com.pro3.domain.list.RequestStatus
import com.pro3.domain.list.Vddr
import com.pro3.domain.main.MaterialRequest
import com.pro3.domain.main.Project
import com.pro3.domain.user.User
import com.pro3.service.FileUploadService
import com.pro3.service.AuthUserService
import com.pro3.service.LineItemService
import com.pro3.service.RegistrationStrategyService
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.text.SimpleTemplateEngine
import org.springframework.beans.factory.InitializingBean

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
// @TODO : Too much logic in services
// @TODO : This controller is too large
class FlowMaterialRequestController implements InitializingBean {
    FileUploadService fileUploadService
    AuthUserService authUserService
    LineItemService lineItemService
    
    /** Dependency injection for the 'uiRegistrationCodeStrategy' bean. */
    RegistrationStrategyService registrationStrategyService

    def createMaterialRequest() {
        log.debug("create() ${params}")
        assert params?.projectId
        params.project = Project.get(params?.projectId)
        
        params.status = RequestStatus.findByName(RequestStatus.RequestStatusEnum.START)
        assert params.status
        respond new MaterialRequest(params), [model: [client: params?.project?.client]]
    }

    def editMaterialRequest() {
        log.debug("editMaterialRequest() ${params}")
        assert params?.id
        MaterialRequest materialRequest = MaterialRequest.get(params?.id)
        assert materialRequest
        def filesList = fileUploadService.listFilesForAccount(materialRequest, authUserService.obtainCurrentUser())
        respond materialRequest, [model: [client: materialRequest?.project?.client, files: filesList]]
    }

    @Transactional
    def saveMaterialRequest(MaterialRequest materialRequest) {
        log.debug("saveMaterialRequest() ${materialRequest}")
        if (materialRequest == null) {
            transactionStatus.setRollbackOnly()
            response.sendError(404, 'Could not find MaterialRequest')
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
            response.sendError(404, 'Could not find MaterialRequest')
            return
        }

        if (materialRequest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond materialRequest.errors, view:'editMaterialRequest'
            return
        }
        materialRequest.save flush: true
        
        flash.message = "Material Request Updated [${materialRequest.id}]"
        redirect controller: 'listMaterialRequest', action: 'index', id: materialRequest?.project?.id
    }
    
    def clickEditBidders(MaterialRequest materialRequest) {
        log.debug("clickEditBidders() ${materialRequest}")
        if (materialRequest == null) {
            transactionStatus.setRollbackOnly()
            response.sendError(404, 'Could not find MaterialRequest')
            return
        }

        if (materialRequest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond materialRequest.errors, view:'editMaterialRequest'
            return
        }
        materialRequest.save flush: true

        redirect action: 'addBidder', id: materialRequest?.id 
    }

    def clickEditLineItems(MaterialRequest materialRequest) {
        log.debug("clickEditBidders() ${materialRequest}")
        if (materialRequest == null) {
            transactionStatus.setRollbackOnly()
            response.sendError(404, 'Could not find MaterialRequest')
            return
        }

        if (materialRequest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond materialRequest.errors, view:'editMaterialRequest'
            return
        }
        materialRequest.save flush: true

        redirect action: 'createLineItem', params: [materialRequestId: materialRequest?.id]
    }

    def clickEditVddrs(MaterialRequest materialRequest) {
        log.debug("clickEditVddr() ${materialRequest}")
        if (materialRequest == null) {
            transactionStatus.setRollbackOnly()
            response.sendError(404, 'Could not find MaterialRequest')
            return
        }

        if (materialRequest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond materialRequest.errors, view:'editMaterialRequest'
            return
        }
        materialRequest.save flush: true

        redirect action: 'createVddr', params: [materialRequestId: materialRequest?.id]
    }

    def clickEditFiles(MaterialRequest materialRequest) {
        log.debug("clickEditVddr() \u0024{materialRequest}")
        if (materialRequest == null) {
            transactionStatus.setRollbackOnly()
            response.sendError(404, 'Could not find MaterialRequest')
            return
        }

        if (materialRequest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond materialRequest.errors, view:'editMaterialRequest'
            return
        }
        materialRequest.save flush: true

        redirect controller: 'flowFile', action: 'createFile', id: materialRequest?.id
    }
    
    def createLineItem() {
        log.debug("createLineItem() ${params}")
        assert params?.materialRequestId
        
        params.request = params?.materialRequestId
        MaterialRequest materialRequest = MaterialRequest.get(params?.materialRequestId)
        
        respond materialRequest
    }

    def createVddr() {
        log.debug("createVddr() ${params}")
        assert params?.materialRequestId

        params.request = params?.materialRequestId
        respond new LineItem(params)
    }

    @Transactional
    def saveVddr(Vddr vddr) {
        log.debug "saveVddr() ${vddr}"
        if (vddr == null) {
            transactionStatus.setRollbackOnly()
            response.sendError(404, 'Could not find VDDR')
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
            response.sendError(404, 'Could not find LineItem')
            return
        }

        if (lineItem.hasErrors()) {
            respond MaterialRequest.get(lineItem?.request?.id), [view:'createLineItem', model: [materialRequestId: lineItem?.request?.id, errors: lineItem.errors]]
            return
        }

        lineItem.save flush:true
        redirect action: 'createLineItem', params: [materialRequestId: lineItem?.request?.id]
    }
    
    @Transactional
    def updateLineItems() {
        log.debug "updateLineItems() ${params}"
        assert params?.request
        
        MaterialRequest materialRequest = MaterialRequest.get(params?.request)
        boolean errors = false;
        materialRequest.lineItems.each { lineItem->
            def code = params.get("code-" + lineItem.id)
            def wbsId = params.get("wbs-" + lineItem.id)
            def description = params.get("name-" + lineItem.id)
            def quantity = params.get("quantity-" + lineItem.id)
            def unitOfMeasure = params.get("unitOfMeasure-" + lineItem.id)
            
            lineItem = lineItemService.updateLineItem(lineItem, code, wbsId, description, quantity, unitOfMeasure)
            if (lineItem.hasErrors()) {
                errors = true
            }
        }
        if (!errors) {
            redirect action: 'createLineItem', params: [materialRequestId: materialRequest?.id]
        } else {
            respond materialRequest, [view:'createLineItem', model: [materialRequestId: materialRequest?.id]]
        }
    }
    
    @Transactional
    def deleteLineItem() {
        log.debug "deleteLineItem() ${params}"
        assert params?.id
        assert params?.lineItemId
        
        LineItem lineItem = LineItem.get(params?.lineItemId)
        lineItem.delete flush: true, failOnError: true
        
        redirect action: 'createLineItem', params: [materialRequestId: params?.id]
    }
    
    def addBidder() {
        log.debug("addBidder() ${params}")
        assert params?.id
        respond MaterialRequest.get(params?.id), [model: [userList: authUserService.obtainVendorsList(), materialRequestId: params?.id]]
    }

    @Transactional
    def saveAddBidder() {
        log.debug("saveAddBidder() ${params}")
        MaterialRequest materialRequest = MaterialRequest.get(params?.materialRequestId)
        materialRequest.bidders.clear()
        if (params?.bidders?.size() > 0) {
            materialRequest.bidders.addAll(params?.bidders?.collect() { User.get(it) })
        }
        materialRequest.save flush:true, failOnError:true
        redirect action: 'editMaterialRequest', id: materialRequest?.id
    }



    // @TODO : This is duplicated code with FlowProjectController
    protected String generateLink(String action, linkParams) {
        createLink(base: "$request.scheme://$request.serverName:$request.serverPort$request.contextPath",
                controller: 'register', action: action, params: linkParams)
    }

    protected String evaluate(s, binding) {
        new SimpleTemplateEngine().createTemplate(s).make(binding)
    }

    protected String forgotPasswordEmailBody
    protected String registerEmailBody
    protected String registerEmailFrom
    protected String registerEmailSubject
    protected String registerPostRegisterUrl
    protected String registerPostResetUrl
    protected String successHandlerDefaultTargetUrl

    protected static int passwordMaxLength
    protected static int passwordMinLength
    protected static String passwordValidationRegex

    void afterPropertiesSet() {
        forgotPasswordEmailBody = conf.ui.forgotPassword.emailBody ?: ''
        registerEmailBody = conf.ui.register.emailBody ?: ''
        registerEmailFrom = conf.ui.register.emailFrom ?: ''
        registerEmailSubject = conf.ui.register.emailSubject ?: ''
        registerPostRegisterUrl = conf.ui.register.postRegisterUrl ?: ''
        registerPostResetUrl = conf.ui.register.postResetUrl ?: ''
        successHandlerDefaultTargetUrl = conf.successHandler.defaultTargetUrl ?: '/'

        passwordMaxLength = conf.ui.password.maxLength instanceof Number ? conf.ui.password.maxLength : 128
        passwordMinLength = conf.ui.password.minLength instanceof Number ? conf.ui.password.minLength : 6
        passwordValidationRegex = conf.ui.password.validationRegex ?: '^.*'
    }

    protected static getConf() {
        SpringSecurityUtils.securityConfig
    }
}
