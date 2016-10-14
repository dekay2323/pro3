package com.pro3.flow

import com.pro3.Client
import com.pro3.user.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class FlowClientController {
    def authService

    def createClient() {
        log.debug("create() ${params}")
        respond new Client(params), [model: []]
    }

    def saveClient(Client client) {
        log.debug("saveClient() ${client}")
        if (client == null) {
            notFound()
            return
        }
        User user = authService.obtainCurrentUser()
        user.account.addToClients(client)

        if (client.hasErrors()) {
            respond client.errors, view:'createClient'
            return
        }

        client.save failOnError: true
        user.save failOnError: true

        flash.message = "Client Created [${client.id}]"
        redirect controller: 'listProject', action: 'index'
    }
}
