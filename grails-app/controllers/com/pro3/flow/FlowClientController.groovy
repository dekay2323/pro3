package com.pro3.flow

import com.pro3.embedded.Client
import com.pro3.User
import grails.plugin.springsecurity.annotation.Secured

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
        user.save failOnError: true

        flash.message = "Client Created [${client.id}]"
        redirect controller: 'listProject', action: 'index'
    }
}
