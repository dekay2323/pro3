package com.pro3.flow

import com.pro3.*
import com.pro3.aux.LineItem
import com.pro3.list.RequestStatus
import com.pro3.list.Vddr
import com.pro3.list.Wbs
import com.pro3.main.MaterialRequest
import com.pro3.main.Project
import com.pro3.user.Account
import com.pro3.user.Role
import com.pro3.user.User
import com.pro3.user.UserRole
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.ui.RegistrationCode
import grails.plugin.springsecurity.ui.strategy.RegistrationCodeStrategy
import grails.transaction.Transactional
import groovy.text.SimpleTemplateEngine
import org.springframework.beans.factory.InitializingBean

import static org.springframework.http.HttpStatus.NOT_FOUND

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
// @TODO : Too much logic in services
// @TODO : This controller is too large
class FlowMaterialRequestController implements InitializingBean {
    AmazonService amazonService
    AuthUserService authUserService
    
    /** Dependency injection for the 'uiRegistrationCodeStrategy' bean. */
    RegistrationCodeStrategy uiRegistrationCodeStrategy

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
        redirect action: 'createLineItem', params: [materialRequestId: lineItem?.request?.id]
    }
    
    @Transactional
    def updateLineItems() {
        log.debug "updateLineItems() ${params}"
        assert params?.request
        
        MaterialRequest materialRequest = MaterialRequest.get(params?.request)
        materialRequest.lineItems.each { lineItem->
            def code = params.get("code-" + lineItem.id)
            def wbsId = params.get("wbs-" + lineItem.id)
            def description = params.get("description-" + lineItem.id)
            def quantity = params.get("quantity-" + lineItem.id)
            def unitOfMeasure = params.get("unitOfMeasure-" + lineItem.id)
            
/*
            // @TODO : Request needs to be attached also
            def tempLineItem = new LineItem(code: code, wbs: Wbs.get(wbsId?.id), description: description, quantity: quantity, unitOfMeasure: unitOfMeasure)
            def valid = tempLineItem.validate()
            if (!valid) {
                // @TODO : This error message does not work yet
                respond tempLineItem.errors, view:'createLineItem'
                return
            } 
*/
            lineItem.code = code
            if (wbsId?.id != 'null') {
                lineItem.wbs = Wbs.get(wbsId?.id)
            }
            lineItem.description = description
            if (quantity) {
                lineItem.quantity = new Integer(quantity)
            } 
            lineItem.unitOfMeasure = unitOfMeasure
            
            lineItem.validate()
            if (lineItem.hasErrors()) {
                // @TODO : No idea why I cannot get the errors to work
            } else {
                lineItem.save(failOnError: true, flush: true)
            }
        }
        redirect action: 'createLineItem', params: [materialRequestId: materialRequest?.id] 
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
        materialRequest.bidders.addAll(params?.bidders?.collect() {User.get(it)})
        materialRequest.save flush:true, failOnError:true
        redirect action: 'editMaterialRequest', id: materialRequest?.id
    }

    @Transactional
    // @TODO : DUplicated in FlowProjectController
    def createNewUser() {
        log.debug("createNewUser() ${params}")
        assert params?.projectId
        assert params?.materialRequestId
        
        Project project = Project.get(params?.projectId)
        assert project

        if (!request.post) {
            respond project, [model: [account: authUserService.obtainAccount(), materialRequestId: params?.materialRequestId]]
            return
        }

        assert params?.accountId
        assert params?.username
        assert params?.email
        def userRole = Role.findByAuthority('ROLE_VENDOR')

        User user = new User(
                username: params?.username,
                password: 'temp',
                account: Account.get(params?.accountId)
        ).save(failOnError: true, flush: true)
        UserRole.findByUser(user) ?: new UserRole(
                user: user,
                role: userRole).save(failOnError: true)

        log.debug("Created new user ${user?.username}")

        String email = params?.email

        RegistrationCode registrationCode = uiRegistrationCodeStrategy.sendForgotPasswordMail(
                user.username, email) { String registrationCodeToken ->

            String url = generateLink('resetPassword', [t: registrationCodeToken])
            String body = forgotPasswordEmailBody
            if (body.contains('$')) {
                body = evaluate(body, [user: user, url: url])
            }

            body
        }
        redirect action: 'addBidder', id: params?.materialRequestId
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

        passwordMaxLength = conf.ui.password.maxLength instanceof Number ? conf.ui.password.maxLength : 64
        passwordMinLength = conf.ui.password.minLength instanceof Number ? conf.ui.password.minLength : 8
        passwordValidationRegex = conf.ui.password.validationRegex ?: '^.*(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&]).*$'
    }

    protected static getConf() {
        SpringSecurityUtils.securityConfig
    }
}
