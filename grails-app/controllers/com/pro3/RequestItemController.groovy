package com.pro3

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RequestItemController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond RequestItem.list(params), model:[requestItemCount: RequestItem.count()]
    }

    def show(RequestItem requestItem) {
        respond requestItem
    }

    def create() {
        respond new RequestItem(params)
    }

    @Transactional
    def save(RequestItem requestItem) {
        if (requestItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (requestItem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond requestItem.errors, view:'create'
            return
        }

        requestItem.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'requestItem.label', default: 'RequestItem'), requestItem.id])
                redirect requestItem
            }
            '*' { respond requestItem, [status: CREATED] }
        }
    }

    def edit(RequestItem requestItem) {
        respond requestItem
    }

    @Transactional
    def update(RequestItem requestItem) {
        if (requestItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (requestItem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond requestItem.errors, view:'edit'
            return
        }

        requestItem.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'requestItem.label', default: 'RequestItem'), requestItem.id])
                redirect requestItem
            }
            '*'{ respond requestItem, [status: OK] }
        }
    }

    @Transactional
    def delete(RequestItem requestItem) {

        if (requestItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        requestItem.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'requestItem.label', default: 'RequestItem'), requestItem.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'requestItem.label', default: 'RequestItem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
