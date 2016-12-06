package com.pro3

import grails.plugin.awssdk.s3.AmazonS3Service
import grails.transaction.Transactional

import javax.annotation.PostConstruct

class AmazonService extends AmazonS3Service {

    @PostConstruct
    def init() {
        init('p3app')
    }
    
    def listAllBuckets() {
        this.listBucketNames()
    }
    
    def listFiles() {
        this.listObjects()
    }
}
