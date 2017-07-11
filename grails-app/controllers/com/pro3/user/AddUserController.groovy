package com.pro3.user

import com.pro3.domain.user.Account
import com.pro3.domain.user.Role
import com.pro3.domain.user.User
import com.pro3.domain.user.UserRole
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.ui.RegisterCommand
import grails.plugin.springsecurity.ui.RegistrationCode
import grails.plugin.springsecurity.ui.strategy.RegistrationCodeStrategy
import grails.transaction.Transactional
import groovy.text.SimpleTemplateEngine
import org.springframework.beans.factory.InitializingBean

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
// @TODO : Too much logic in services
class AddUserController implements InitializingBean {
    def authUserService
    def userService
    def emailService
    
    RegistrationCodeStrategy uiRegistrationCodeStrategy

    @Transactional
    def createNewUser() {
        log.debug("createNewUser() ${params}")
        
        if (!request.post) {
            def userList = authUserService.obtainUsersList()
            respond authUserService.obtainAccount(), [model: [userList: userList], view: 'createNewUser']
            return
        }
        assert params?.username
        assert params?.email
        assert params?.accountId
        Account account = authUserService.obtainAccount()
        
        User user = userService.createNewUser(params?.username, params?.email, params?.accountId)
            
        RegisterController registerController = new RegisterController()
        RegisterCommand registerCommand = new RegisterCommand(username: params?.username, email: params?.email)
        registerController.registerForAccount(registerCommand, account)
        
        def userList = authUserService.obtainUsersList()

        flash.message = "User Created [${user.username}]"
        respond account, [model: [userList: userList], view: 'createNewUser']
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
