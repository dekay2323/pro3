package com.pro3.util

import com.pro3.MaterialRequest
import com.pro3.user.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class FileUploadController {
    def amazonService
    def authUserService
    


}
