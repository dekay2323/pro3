package com.pro3.service

import grails.plugin.springsecurity.ui.RegistrationCode
import grails.plugin.springsecurity.ui.SpringSecurityUiService
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SecurityUiService extends SpringSecurityUiService {

    @Transactional
    RegistrationCode sendEmailLink(String username, String emailAddress, String subject,
                                            Closure emailBodyGenerator) {

        RegistrationCode registrationCode = save(username: username, RegistrationCode,
                'sendForgotPasswordMail', transactionStatus)
        if (!registrationCode.hasErrors()) {
            String body = emailBodyGenerator(registrationCode.token)
            // @TODO: forgotPasswordEmailFrom should come from config
            uiMailStrategy.sendForgotPasswordMail(to: emailAddress, from: 'contact@procurableapp.com',
                    subject: subject, html: body)
        }

        registrationCode
    }
}
