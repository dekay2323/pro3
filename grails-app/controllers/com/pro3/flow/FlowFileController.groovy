package com.pro3.flow

import com.pro3.MaterialRequest
import com.pro3.user.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class FlowFileController {
    def amazonService
    def authUserService

    def createFile(MaterialRequest materialRequest) {
        assert materialRequest
        log.debug("createFile() ${params}")
        def filesList = amazonService.listFilesForAccount(materialRequest.obtainFileDirectory(authUserService.obtainCurrentUser()?.account?.name))
        render view: "uploadFile", model: [materialRequestId: materialRequest?.id, files: filesList]
    }
    
    def uploadFile() {
        log.debug "upload() ${params}"
        assert params?.materialRequestId
        assert params?.file
        assert params?.fileName
        MaterialRequest materialRequest = MaterialRequest.get(params?.materialRequestId)
        assert materialRequest
        User user = authUserService.obtainCurrentUser()
        assert user
        if (user?.account) {
            def createdUrl = amazonService.storeMultiPartFileForAccount(materialRequest.obtainFileDirectory(user?.account?.name), params?.fileName, params.file)
        }
        redirect controller: "flowMaterialRequest", action: 'editMaterialRequest', id: materialRequest?.id
    }

}
