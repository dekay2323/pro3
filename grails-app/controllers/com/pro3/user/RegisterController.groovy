package com.pro3.user

import com.pro3.domain.main.Project
import com.pro3.domain.user.Account
import com.pro3.domain.user.User
import com.pro3.domain.user.UserRole
import com.pro3.service.RegistrationStrategyService
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.authentication.dao.NullSaltSource
import grails.plugin.springsecurity.ui.RegisterCommand
import grails.plugin.springsecurity.ui.RegistrationCode
import grails.plugin.springsecurity.ui.strategy.RegistrationCodeStrategy
import grails.transaction.Transactional

class RegisterController extends grails.plugin.springsecurity.ui.RegisterController {
    def authUserService
    RegistrationStrategyService registrationStrategyService

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def registerForAccount(RegisterCommand registerCommand) {
        def userList = authUserService.obtainAccount().users
        def account = authUserService.obtainAccount()
        
        if (!request.post) {
            return [registerCommand: new RegisterCommand(), userList: userList, account: account]
        }
        registerCommand.password = 'temp123'
        registerCommand.password2 = 'temp123'
        registerCommand.validate()
        
        if (registerCommand.hasErrors()) {
            return [registerCommand: registerCommand, userList: userList]
        }

        User user = registrationStrategyService.createUser(registerCommand)
        user.account = authUserService.obtainAccount()
        user.accountLocked = false
        user.password = 'temp123'
        user.passwordExpired = false
        user.accountExpired = false
        user.save(failOnError: true, flush: true)
        new UserRole(
                user: user,
                role: Role.findByAuthority('ROLE_USER')).save(failOnError: true, flush: true)

        String salt = saltSource instanceof NullSaltSource ? null : registerCommand.username
        RegistrationCode registrationCode = registrationStrategyService.sendEmailLink (
                user.username, user.email) { String registrationCodeToken ->

            String url = generateLink('resetPassword', [t: registrationCodeToken])
            String body = "Please create an account for <strong>${account?.name}</strong><br />" +
                    "You username is <strong>${user?.username}</strong><br />" +
                    "${url}<br /><br /><br />" +
                    "Procurable App http://run.procurableapp.com"
            if (body.contains('$')) {
                body = evaluate(body, [user: user, url: url])
            }

            body
        }

        if (registrationCode == null || registrationCode.hasErrors()) {
            // null means problem creating the user
            flash.error = message(code: 'spring.security.ui.register.miscError')
            return [registerCommand: registerCommand]
        }

        flash.message = "User ${user.username} created"
        [emailSent: true, registerCommand: registerCommand, userList: userList]
    }
    
    def register(RegisterCommand registerCommand) {
        if (!request.post) {
            return [registerCommand: new RegisterCommand()]
        }
   
        if (registerCommand.hasErrors()) {
            return [registerCommand: registerCommand]
        }
        
        Account account = new Account(name: params?.account)
        account.save(failOnError: true, flush: true)

        User user = registrationStrategyService.createUser(registerCommand)
        user.account = account
        user.accountLocked = false
        user.passwordExpired = false
        user.accountExpired = false

        String salt = saltSource instanceof NullSaltSource ? null : registerCommand.username
        RegistrationCode registrationCode = registrationStrategyService.register(user, registerCommand.password, salt)

        if (registrationCode == null || registrationCode.hasErrors()) {
            // null means problem creating the user
            flash.error = message(code: 'spring.security.ui.register.miscError')
            return [registerCommand: registerCommand]
        }

        sendVerifyRegistrationMail registrationCode, account?.name, user, registerCommand.email
        user.save(failOnError: true, flush: true)
        new UserRole(
                user: user,
                role: Role.findByAuthority('ROLE_USER')).save(failOnError: true, flush: true)

        [emailSent: true, registerCommand: registerCommand]
    }

    protected void sendVerifyRegistrationMail(RegistrationCode registrationCode, String account, User user, String email) {
        String body = "You created the Account <strong>${account}</strong><br />" +
                "You username is <strong>${user?.username}</strong><br /><br />" +
                "Procurable App http://run.procurableapp.com"

        uiMailStrategy.sendVerifyRegistrationMail(
                to: email,
                from: registerEmailFrom,
                subject: "Create Account",
                html: body.toString())
    }

    @Transactional
    def createNewVendor() {
        log.debug("createNewVendor() ${params}")
        assert params?.projectId
        assert params?.materialRequestId

        Project project = Project.get(params?.projectId)
        assert project

        Account account = authUserService.obtainAccount()
        assert account

        if (!request.post) {
            respond project, [model: [account: account, materialRequestId: params?.materialRequestId]]
            return
        }

        assert params?.accountId
        assert params?.username
        assert params?.email
        def userRole = com.pro3.domain.user.Role.findByAuthority('ROLE_VENDOR')

        User user = new User(
                username: params?.username,
                password: 'temp',
                email: params?.email,
                account: account
        ).save(failOnError: true, flush: true)
        UserRole.findByUser(user) ?: new UserRole(
                user: user,
                role: userRole).save(failOnError: true)

        log.debug("Created new user ${user?.username}")

        String email = params?.email
        String subject = "Create account to bid"
        
        RegistrationCode registrationCode = registrationStrategyService.sendEmailLink(
                user.username, email, subject) { String registrationCodeToken ->

            String url = generateLink('resetPassword', [t: registrationCodeToken])
            String body = "Please create a vendor in order to bid on RFQ for <strong>${account?.name}</strong><br />" +
                    "You username is <strong>${user?.username}</strong><br />" +
                    "${url}<br /><br /><br />" +
                    "Procurable App http://run.procurableapp.com"
            if (body.contains('$')) {
                body = evaluate(body, [user: user, url: url])
            }

            body
        }
        redirect controller: 'flowMaterialRequest', action: 'addBidder', id: params?.materialRequestId
    }

}

