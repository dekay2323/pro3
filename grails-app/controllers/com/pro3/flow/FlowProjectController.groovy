package com.pro3.flow

import com.pro3.Client
import com.pro3.Project
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class FlowProjectController {

    def createProject() {
        log.debug("create() ${params}")
        if (params?.clientId) {
            params.client = Client.get(params?.clientId)
        }
        respond new Project(params), [model: []]
    }

    @Transactional
    def saveProject(Project project) {
        log.debug("saveProject() ${project}")
        if (project == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (project.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond project.errors, view:'createProject'
            return
        }

        project.save flush:true

        flash.message = "Project Created [${project.id}]"
        redirect controller: 'listProject', action: 'index'
    }
}
