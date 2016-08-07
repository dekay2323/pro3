package com.pro3.type

import grails.transaction.Transactional

@Transactional(readOnly = true)
class LeadTimeController {
    static scaffold = LeadTime
}
