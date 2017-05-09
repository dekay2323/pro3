package com.pro3.flow

import com.pro3.MaterialRequest
import com.pro3.user.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
// @TODO : Too much logic in services
class FlowFileController {
    def amazonService
    def authUserService

    def createFile(MaterialRequest materialRequest) {
        log.debug("createFile() ${params}")

        assert materialRequest
        
        def filesList = amazonService.listFilesForAccount(materialRequest.obtainFileDirectory(authUserService.obtainCurrentUser()?.account?.name))
        render view: "uploadFile", model: [materialRequestId: materialRequest?.id, files: filesList]
    }

    def uploadFile() {
        log.debug "uploadFile() ${params}"

        assert params?.materialRequestId
        assert params?.file[0]
        assert params?.file?.filename[0]

        MaterialRequest materialRequest = MaterialRequest.get(params?.materialRequestId)
        assert materialRequest
        User user = authUserService.obtainCurrentUser()
        assert user
        assert user.account
        
        def file = params?.file[0]
        String directory = materialRequest.obtainFileDirectory(user?.account?.name)
        String filename = params?.file?.filename[0]
        String createdUrl = amazonService.storeMultiPartFileForAccount(directory, filename, file)
        log.debug("Created URL for file ${createdUrl}")
        flash.message = "File created"

        redirect(action: "createFile", id: params?.materialRequestId)
    }
    
    def deleteFile() {
        log.debug "uploadFile() ${params}"

        assert params?.key
        assert params?.id
        
        User user = authUserService.obtainCurrentUser()
        assert user
        assert user.account
        if (amazonService.removeFileForAccount(params?.key)) {
            flash.message = "File removed"
        } else {
            flash.error = "Problem: File ${params.key} not removed"
        }
        
        redirect(action: "createFile", id: params?.id)
    }

}
