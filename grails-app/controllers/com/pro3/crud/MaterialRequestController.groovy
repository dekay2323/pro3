package com.pro3.crud

import com.pro3.MaterialRequest

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MaterialRequestController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MaterialRequest.list(params), model:[materialRequestCount: MaterialRequest.count()]
    }

    def show(MaterialRequest materialRequest) {
        respond materialRequest
    }

    def create() {
        respond new MaterialRequest(params)
    }

    @Transactional
    def save(MaterialRequest materialRequest) {
        if (materialRequest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (materialRequest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond materialRequest.errors, view:'create'
            return
        }

        materialRequest.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'materialRequest.label', default: 'MaterialRequest'), materialRequest.id])
                redirect materialRequest
            }
            '*' { respond materialRequest, [status: CREATED] }
        }
    }

    def edit(MaterialRequest materialRequest) {
        respond materialRequest
    }

    @Transactional
    def update(MaterialRequest materialRequest) {
        if (materialRequest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (materialRequest.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond materialRequest.errors, view:'edit'
            return
        }

        materialRequest.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'materialRequest.label', default: 'MaterialRequest'), materialRequest.id])
                redirect materialRequest
            }
            '*'{ respond materialRequest, [status: OK] }
        }
    }

    @Transactional
    def delete(MaterialRequest materialRequest) {

        if (materialRequest == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        materialRequest.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'materialRequest.label', default: 'MaterialRequest'), materialRequest.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'materialRequest.label', default: 'MaterialRequest'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
