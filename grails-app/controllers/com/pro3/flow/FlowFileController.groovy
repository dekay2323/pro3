package com.pro3.flow

import com.pro3.domain.main.MaterialRequest
import com.pro3.domain.user.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
// @TODO : Too much logic in services
class FlowFileController {
    def fileUploadService
    def authUserService

    def createFile(MaterialRequest materialRequest) {
        log.debug("createFile() ${materialRequest}")

        assert materialRequest
        
        def filesList = fileUploadService.listFilesForAccount(
                materialRequest, 
                authUserService.obtainCurrentUser())
        
        render view: "uploadFile", model: [materialRequestId: materialRequest?.id, files: filesList]
    }

    def uploadFile() {
        log.debug "uploadFile() ${params}"

        assert params?.materialRequestId
        assert params?.file

        User user = authUserService.obtainCurrentUser()
        assert user
        MaterialRequest materialRequest = MaterialRequest.get(params?.materialRequestId)
        assert materialRequest
        
        fileUploadService.uploadFile(params?.file, materialRequest.obtainFileDirectory(user?.account?.name))
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
        if (fileUploadService.removeFileForAccount(params?.key)) {
            flash.message = "File removed"
        } else {
            flash.error = "Problem: File ${params.key} not removed"
        }
        
        redirect(action: "createFile", id: params?.id)
    }

}
