package com.pro3

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class StrategyController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Strategy.list(params), model:[strategyCount: Strategy.count()]
    }

    def show(Strategy strategy) {
        respond strategy
    }

    def create() {
        respond new Strategy(params)
    }

    @Transactional
    def save(Strategy strategy) {
        if (strategy == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (strategy.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond strategy.errors, view:'create'
            return
        }

        strategy.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'strategy.label', default: 'Strategy'), strategy.id])
                redirect strategy
            }
            '*' { respond strategy, [status: CREATED] }
        }
    }

    def edit(Strategy strategy) {
        respond strategy
    }

    @Transactional
    def update(Strategy strategy) {
        if (strategy == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (strategy.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond strategy.errors, view:'edit'
            return
        }

        strategy.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'strategy.label', default: 'Strategy'), strategy.id])
                redirect strategy
            }
            '*'{ respond strategy, [status: OK] }
        }
    }

    @Transactional
    def delete(Strategy strategy) {

        if (strategy == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        strategy.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'strategy.label', default: 'Strategy'), strategy.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'strategy.label', default: 'Strategy'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
