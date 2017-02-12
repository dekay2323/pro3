package com.pro3.flow

import com.pro3.Client
import com.pro3.MaterialRequest
import com.pro3.Project
import com.pro3.user.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.OK

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

    def editProject(Project project) {
        respond project, [model: [userList: User.list()]]
    }

    @Transactional
    def updateProject(Project project) {
        if (project == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (project.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond project.errors, view:'edit'
            return
        }

        project.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'project.label', default: 'Project'), project.id])
                redirect project
            }
            '*'{ respond project, [status: OK] }
        }
    }


    def saveEditManagers() {
        log.debug("saveEditManagers() ${params}")
        Project project = Project.get(params.projectId)
        project.managers.clear()
        //project.managers.addAll(params?.managers?.collect() {User.get(it)})
        project.internalApprovers.clear()
        //project.internalApprovers.addAll(params?.internalApprovers?.collect() {User.get(it)})
        project.save flush:true, failOnError:true
        redirect action: 'editProject', id: project?.id
    }
}
