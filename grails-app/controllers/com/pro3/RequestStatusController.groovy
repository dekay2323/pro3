package com.pro3

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RequestStatusController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond RequestStatus.list(params), model:[requestStatusCount: RequestStatus.count()]
    }

    def show(RequestStatus requestStatus) {
        respond requestStatus
    }

    def create() {
        respond new RequestStatus(params)
    }

    @Transactional
    def save(RequestStatus requestStatus) {
        if (requestStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (requestStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond requestStatus.errors, view:'create'
            return
        }

        requestStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'requestStatus.label', default: 'RequestStatus'), requestStatus.id])
                redirect requestStatus
            }
            '*' { respond requestStatus, [status: CREATED] }
        }
    }

    def edit(RequestStatus requestStatus) {
        respond requestStatus
    }

    @Transactional
    def update(RequestStatus requestStatus) {
        if (requestStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (requestStatus.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond requestStatus.errors, view:'edit'
            return
        }

        requestStatus.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'requestStatus.label', default: 'RequestStatus'), requestStatus.id])
                redirect requestStatus
            }
            '*'{ respond requestStatus, [status: OK] }
        }
    }

    @Transactional
    def delete(RequestStatus requestStatus) {

        if (requestStatus == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        requestStatus.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'requestStatus.label', default: 'RequestStatus'), requestStatus.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'requestStatus.label', default: 'RequestStatus'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
