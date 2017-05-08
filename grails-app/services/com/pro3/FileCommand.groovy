package com.pro3

import grails.validation.Validateable

class FileCommand implements Validateable { 
    String url
    String key
    String filename
    Integer size
    Date lastModified
}
