package com.pro3.user

import com.pro3.domain.user.Account
import com.pro3.domain.user.User
import com.pro3.domain.user.UserRole
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.authentication.dao.NullSaltSource
import grails.plugin.springsecurity.ui.RegisterCommand
import grails.plugin.springsecurity.ui.RegistrationCode

class RegisterController extends grails.plugin.springsecurity.ui.RegisterController {
    def authUserService

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def registerForAccount(RegisterCommand registerCommand) {
        def userList = authUserService.obtainAccount().users
        
        if (!request.post) {
            return [registerCommand: new RegisterCommand(), userList: userList]
        }
        registerCommand.password = 'temp123'
        registerCommand.password2 = 'temp123'
        registerCommand.validate()
        
        if (registerCommand.hasErrors()) {
            return [registerCommand: registerCommand, userList: userList]
        }

        User user = uiRegistrationCodeStrategy.createUser(registerCommand)
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
        RegistrationCode registrationCode = uiRegistrationCodeStrategy.sendForgotPasswordMail(
                user.username, user.email) { String registrationCodeToken ->

            String url = generateLink('resetPassword', [t: registrationCodeToken])
            String body = "An user ${user.username} has been created for account ${user.account} please use this link ${url} to login"
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

        User user = uiRegistrationCodeStrategy.createUser(registerCommand)
        user.account = account
        user.accountLocked = false
        user.passwordExpired = false
        user.accountExpired = false
        
        String salt = saltSource instanceof NullSaltSource ? null : registerCommand.username
        RegistrationCode registrationCode = uiRegistrationCodeStrategy.register(user, registerCommand.password, salt)

        if (registrationCode == null || registrationCode.hasErrors()) {
            // null means problem creating the user
            flash.error = message(code: 'spring.security.ui.register.miscError')
            return [registerCommand: registerCommand]
        }

        sendVerifyRegistrationMail registrationCode, user, registerCommand.email
        user.save(failOnError: true, flush: true)
        new UserRole(
                user: user,
                role: Role.findByAuthority('ROLE_USER')).save(failOnError: true, flush: true)

        [emailSent: true, registerCommand: registerCommand]
    }

    protected void sendVerifyRegistrationMail(RegistrationCode registrationCode, User user, String email) {
        String url = generateLink('verifyRegistration', [t: registrationCode.token])

        def body = registerEmailBody
        if (body.contains('$')) {
            body = evaluate(body, [user: user, url: url])
        }

        uiMailStrategy.sendVerifyRegistrationMail(
                to: email,
                from: registerEmailFrom,
                subject: "Welcome, ${user.username}",
                html: body.toString())
    }
}

