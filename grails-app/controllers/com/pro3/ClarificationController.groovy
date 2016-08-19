package com.pro3

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ClarificationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Clarification.list(params), model:[clarificationCount: Clarification.count()]
    }

    def show(Clarification clarification) {
        respond clarification
    }

    def create() {
        respond new Clarification(params)
    }

    @Transactional
    def save(Clarification clarification) {
        if (clarification == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (clarification.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond clarification.errors, view:'create'
            return
        }

        clarification.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'clarification.label', default: 'Clarification'), clarification.id])
                redirect clarification
            }
            '*' { respond clarification, [status: CREATED] }
        }
    }

    def edit(Clarification clarification) {
        respond clarification
    }

    @Transactional
    def update(Clarification clarification) {
        if (clarification == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (clarification.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond clarification.errors, view:'edit'
            return
        }

        clarification.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'clarification.label', default: 'Clarification'), clarification.id])
                redirect clarification
            }
            '*'{ respond clarification, [status: OK] }
        }
    }

    @Transactional
    def delete(Clarification clarification) {

        if (clarification == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        clarification.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'clarification.label', default: 'Clarification'), clarification.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'clarification.label', default: 'Clarification'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
