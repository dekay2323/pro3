package com.pro3

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class WbsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Wbs.list(params), model:[wbsCount: Wbs.count()]
    }

    def show(Wbs wbs) {
        respond wbs
    }

    def create() {
        respond new Wbs(params)
    }

    @Transactional
    def save(Wbs wbs) {
        if (wbs == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (wbs.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond wbs.errors, view:'create'
            return
        }

        wbs.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'wbs.label', default: 'Wbs'), wbs.id])
                redirect wbs
            }
            '*' { respond wbs, [status: CREATED] }
        }
    }

    def edit(Wbs wbs) {
        respond wbs
    }

    @Transactional
    def update(Wbs wbs) {
        if (wbs == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (wbs.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond wbs.errors, view:'edit'
            return
        }

        wbs.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'wbs.label', default: 'Wbs'), wbs.id])
                redirect wbs
            }
            '*'{ respond wbs, [status: OK] }
        }
    }

    @Transactional
    def delete(Wbs wbs) {

        if (wbs == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        wbs.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'wbs.label', default: 'Wbs'), wbs.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'wbs.label', default: 'Wbs'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
