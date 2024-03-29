package com.pro3.domain.user

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='authority')
@ToString(includes='authority', includeNames=true, includePackage=false)
class Role implements Serializable {

	private static final long serialVersionUID = 1

	String authority
	String name

	static constraints = {
		authority blank: false, unique: true
		name blank: false, nullable: false
		
	}

	static mapping = {
		cache true
	}
}
