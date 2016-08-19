package com.pro3

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CriteriaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Criteria.list(params), model:[criteriaCount: Criteria.count()]
    }

    def show(Criteria criteria) {
        respond criteria
    }

    def create() {
        respond new Criteria(params)
    }

    @Transactional
    def save(Criteria criteria) {
        if (criteria == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (criteria.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond criteria.errors, view:'create'
            return
        }

        criteria.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'criteria.label', default: 'Criteria'), criteria.id])
                redirect criteria
            }
            '*' { respond criteria, [status: CREATED] }
        }
    }

    def edit(Criteria criteria) {
        respond criteria
    }

    @Transactional
    def update(Criteria criteria) {
        if (criteria == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (criteria.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond criteria.errors, view:'edit'
            return
        }

        criteria.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'criteria.label', default: 'Criteria'), criteria.id])
                redirect criteria
            }
            '*'{ respond criteria, [status: OK] }
        }
    }

    @Transactional
    def delete(Criteria criteria) {

        if (criteria == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        criteria.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'criteria.label', default: 'Criteria'), criteria.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'criteria.label', default: 'Criteria'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
