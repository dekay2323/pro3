package com.pro3.user

import com.pro3.main.Project
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
class AddUserController implements InitializingBean {
    def authUserService

    /** Dependency injection for the 'uiRegistrationCodeStrategy' bean. */
    RegistrationCodeStrategy uiRegistrationCodeStrategy

    @Transactional
    def createNewUser() {
        log.debug("createNewUser() ${params}")
        
        if (!request.post) {
            def userList = authUserService.obtainUsersList()
            respond authUserService.obtainAccount(), [model: [userList: userList], view: 'createNewUser']
            return
        }
        
        assert params?.accountId
        assert params?.username
        assert params?.email
        def userRole = Role.findByAuthority('ROLE_USER')
        
        User user = new User(
                username: params?.username,
                email: params?.email,
                password: 'temp',
                account: Account.get(params?.accountId)
        ).save(failOnError: true, flush: true)
        UserRole.findByUser(user) ?: new UserRole(
                user: user,
                role: userRole).save(failOnError: true)
        
        log.debug(user?.username)

        
        RegistrationCode registrationCode = uiRegistrationCodeStrategy.sendForgotPasswordMail(
                user.username, user.email) { String registrationCodeToken ->

            String url = generateLink('resetPassword', [t: registrationCodeToken])
            String body = forgotPasswordEmailBody
            if (body.contains('$')) {
                body = evaluate(body, [user: user, url: url])
            }

            body
        }
        def userList = authUserService.obtainUsersList()
        respond authUserService.obtainAccount(), [model: [userList: userList], view: 'createNewUser']
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
