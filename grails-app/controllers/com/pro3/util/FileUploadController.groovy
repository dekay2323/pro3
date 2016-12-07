package com.pro3.util

import com.pro3.user.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class FileUploadController {
    def amazonService
    def authUserService
    
    @Transactional
    def upload() {
        log.debug "upload() ${params}"
        assert params?.file
        assert params?.fileName
        User user = authUserService.obtainCurrentUser()
        assert user
        if (user?.account) {
            def createdUrl = amazonService.storeMultiPartFileForAccount(user?.account?.name, params?.fileName, params.file)
            render createdUrl
        }
        
    }

}
