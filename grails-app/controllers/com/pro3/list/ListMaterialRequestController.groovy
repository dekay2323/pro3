package com.pro3.list

import com.pro3.embedded.Client
import com.pro3.embedded.MaterialRequest
import com.pro3.Project
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class ListMaterialRequestController {
    def authService

    // @TODO : Check user can see this project
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if (params?.id) {
            Project project = Project.get(params.id)
            def materialRequestList = project.requests ?: []
            respond materialRequestList,
                    model: [project: project, client: Client.get(project?.client?.id)]
        } else {
            redirect controller: 'listHome', action:'index'
        }
    }

}
