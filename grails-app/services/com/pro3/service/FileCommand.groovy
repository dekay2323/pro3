package com.pro3.service

import grails.validation.Validateable

class FileCommand implements Validateable { 
    String url
    String key
    String filename
    Integer size
    Date lastModified
}
