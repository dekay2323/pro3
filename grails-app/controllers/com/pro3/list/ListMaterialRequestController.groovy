package com.pro3.list

import com.pro3.Constants
import com.pro3.MaterialRequest
import com.pro3.Project
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured([Constants.ROLE_ADMIN, Constants.ROLE_USER])
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
