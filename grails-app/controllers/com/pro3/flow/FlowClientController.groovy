package com.pro3.flow

import com.pro3.Client
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
        respond new Client(params), [model: []]
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

        User user = authUserService.obtainCurrentUser()
        user.account.addToClients(client)
        client.save flush:true, failOnError: true
        user.save flush:true, failOnError: true

        flash.message = "Client Created [${client.id}]"
        redirect controller: 'listProject', action: 'index'
    }
}
