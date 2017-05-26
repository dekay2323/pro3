package com.pro3.flow

import com.pro3.user.Client
import com.pro3.user.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
// @TODO : Too much logic in services
class FlowClientController {
    def authUserService

    def createClient() {
        log.debug("create() ${params}")

        User user = authUserService.obtainCurrentUser()
        Client client = new Client(params)
        client.account = user.account

        respond client, [model: []]
    }

    @Transactional
    def saveClient(Client client) {
        log.debug("saveClient() ${client}")
        
        if (client == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        
        if (client.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond client.errors, view:'createClient'
            return
        }

        client.save flush:true, failOnError: true
        
        User user = authUserService.obtainCurrentUser()
        user.account.addToClients(client)
        user.save flush:true, failOnError: true

        flash.message = "Client Created [${client.id}]"
        redirect controller: 'listProject', action: 'index'
    }
}
