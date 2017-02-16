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
    def authUserService
    
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
        def userList = User.findAllByAccount(authUserService.obtainAccount())
        respond project, [model: [userList: userList]]
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

        params?.managers?.each() {
            project.addToManagers(User.get(it))
        }
        project.save flush:true, failOnError:true
        redirect controller: 'listProject', action: 'index'
    }
}
