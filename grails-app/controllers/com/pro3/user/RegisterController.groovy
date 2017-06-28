package com.pro3.user

import grails.plugin.springsecurity.authentication.dao.NullSaltSource
import grails.plugin.springsecurity.ui.RegisterCommand
import grails.plugin.springsecurity.ui.RegistrationCode

class RegisterController extends grails.plugin.springsecurity.ui.RegisterController {
    def register(RegisterCommand registerCommand) {

        if (!request.post) {
            return [registerCommand: new RegisterCommand()]
        }

        if (registerCommand.hasErrors()) {
            return [registerCommand: registerCommand]
        }

        User user = uiRegistrationCodeStrategy.createUser(registerCommand)
        user.accountLocked = false
        user.passwordExpired = false
        user.accountExpired = false
        user.account = Account.findByName('Swat')
        
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
