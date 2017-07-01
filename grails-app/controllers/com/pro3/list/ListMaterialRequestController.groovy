package com.pro3.list

import com.pro3.main.MaterialRequest
import com.pro3.main.Project
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class ListMaterialRequestController {

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if (params?.id) {
            def project = Project.get(params.id)
            respond MaterialRequest.findAllByProject(Project.get(params.id)), model: [project: project, client: project?.client]
        } else {
            redirect controller: 'listHome', action:'index'
        }
    }

}
