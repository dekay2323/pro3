package com.pro3.flow

import com.pro3.Client
import com.pro3.MaterialRequest
import com.pro3.Project
import com.pro3.RequestStatus
import grails.transaction.Transactional

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
