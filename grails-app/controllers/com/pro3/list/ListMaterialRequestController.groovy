package com.pro3.list

import com.pro3.Client
import com.pro3.MaterialRequest
import com.pro3.Project
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ListMaterialRequestController {

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MaterialRequest.findAllByProject(Project.get(params.id)), model:[projectId: params.id]
    }

}
