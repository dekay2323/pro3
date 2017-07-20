package com.pro3.domain.user

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String email
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	Account account
	
	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	List<String> getAuthorityNames() {
		getAuthorities()*.name
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		assert springSecurityService
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		password nullable: false, blank: false, password: true
		username nullable: false, blank: false, unique: true
		email nullable: false, blank: false, email: true
		account nullable: true
	}

	static mapping = {
		password column: '`password`'
		sort username: 'asc'
	}

	public String toString() {
		"${username}"
	}
}
