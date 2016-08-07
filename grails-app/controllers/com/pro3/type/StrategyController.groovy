package com.pro3.type

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class StrategyController {
    static scaffold = Strategy
}
