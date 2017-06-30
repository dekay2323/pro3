package com.pro3.flow

import com.pro3.user.Account
import com.pro3.user.Client
import com.pro3.main.Project
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

import static org.springframework.http.HttpStatus.OK

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
// @TODO : Too much logic in services
class FlowProjectController implements InitializingBean {
    def authUserService

    /** Dependency injection for the 'uiRegistrationCodeStrategy' bean. */
    RegistrationCodeStrategy uiRegistrationCodeStrategy
    
    def createProject() {
        log.debug("create() ${params}")
        if (params?.clientId) {
            params.client = Client.get(params?.clientId)
        }
        respond new Project(params), [model: []]
    }

    @Transactional
    def saveProject(Project project) {
        log.debug("saveProject() ${project}")
        if (project == null) {
            transactionStatus.setRollbackOnly()
            response.sendError(404, 'Could not find Project')
            return
        }

        if (project.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond project.errors, view:'createProject'
            return
        }

        project.save flush:true

        flash.message = "Project Created [${project.id}]"
        redirect controller: 'listProject', action: 'index'
    }

    def editProject(Project project) {
        def userList = authUserService.obtainUsersList()
        respond project, [model: [userList: userList]]
    }

    @Transactional
    def updateProject(Project project) {
        if (project == null) {
            transactionStatus.setRollbackOnly()
            response.sendError(404, 'Could not find Project')
            return
        }

        if (project.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond project.errors, view:'edit'
            return
        }

        project.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'project.label', default: 'Project'), project.id])
                redirect project
            }
            '*'{ respond project, [status: OK] }
        }
    }

    @Transactional
    def saveEditManagers() {
        log.debug("saveEditManagers() ${params}")
        Project project = Project.get(params.projectId)

        project.managers.clear()

        params?.managers?.each() {
            project.addToManagers(User.get(it))
        }
        project.save flush:true, failOnError:true
        redirect controller: 'listProject', action: 'index'
    }
    
    @Transactional
    def createNewUser() {
        log.debug("createNewUser() ${params}")
        assert params?.projectId
        Project project = Project.get(params?.projectId)
        assert project
        
        if (!request.post) {
            respond project, [model: [account: authUserService.obtainAccount()]]
            return
        }
        
        assert params?.accountId
        assert params?.username
        assert params?.email
        def userRole = Role.findByAuthority('ROLE_USER')
        
        User user = new User(
                username: params?.username,
                password: 'temp',
                account: Account.get(params?.accountId)
        ).save(failOnError: true, flush: true)
        UserRole.findByUser(user) ?: new UserRole(
                user: user,
                role: userRole).save(failOnError: true)
        
        log.debug(user?.username)

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
        def userList = authUserService.obtainUsersList()
        respond project, [model: [userList: userList], view: 'editProject']
    }

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
