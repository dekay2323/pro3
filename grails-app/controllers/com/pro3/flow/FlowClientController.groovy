package com.pro3.flow

import com.pro3.Client
import com.pro3.Constants
import com.pro3.Project
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured([Constants.ROLE_ADMIN, Constants.ROLE_USER])
class FlowClientController {

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

        client.save flush:true

        flash.message = "Client Created [${client.id}]"
        redirect controller: 'listProject', action: 'index'
    }
}
