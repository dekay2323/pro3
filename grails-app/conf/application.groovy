// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.pro3.domain.user.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.pro3.domain.user.UserRole'
grails.plugin.springsecurity.authority.className = 'com.pro3.domain.user.Role'
grails.plugin.springsecurity.securityConfigType = "Annotation"

grails.plugin.springsecurity.ui.forgotPassword.emailFrom = 'contact@procurableapp.com'

grails.plugin.springsecurity.ui.password.minLength = 6
grails.plugin.springsecurity.ui.password.maxLength = 128
grails.plugin.springsecurity.ui.password.validationRegex = '^.*'

grails.plugin.springsecurity.ui.register.emailBody = 'Welcome to Procurable.\n http://run.procurableapp.com'
grails.plugin.springsecurity.ui.register.emailFrom = 'contact@procurableapp.com'
grails.plugin.springsecurity.ui.register.emailSubject = 'Procurable: $user.username'

grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/img/**',      access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]