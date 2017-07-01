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
        log.debug("createClient() ${params}")

        User user = authUserService.obtainCurrentUser()
        Client client = new Client(params)
        client.account = user.account
        def clientList = authUserService.obtainAllClients()

        respond client, [model: [clientList: clientList]]
    }

    @Transactional
    def saveClient(Client client) {
        log.debug("saveClient() ${client}")
        
        if (client == null) {
            transactionStatus.setRollbackOnly()
            response.sendError(404, 'Could not find client')
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
        def clientList = authUserService.obtainAllClients()
        
        flash.message = "Client Created [${client.id}]"
        respond client, [model: [clientList: clientList], view: 'createClient']
    }
}
