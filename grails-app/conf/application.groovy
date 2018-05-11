// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'restapi.users.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'restapi.users.UserRole'
grails.plugin.springsecurity.authority.className = 'restapi.users.Role'
grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"

// TODO: add explicit lock-down here. all but login/logout is currently "fullyAuthenticated".
// for instance, if we wanted to open all but certain api resources we would remove the last patten '/**' and explicitly
// define said resources to lock-down.
grails.plugin.springsecurity.interceptUrlMap = [
        [pattern: '/', access: ['permitAll']],
        [pattern: '/login', access: ['permitAll']],
        [pattern: '/login/auth', access: ['permitAll']],
        [pattern: '/register', access: ['permitAll']],
        [pattern: '/forgot-password', access: ['permitAll']],
        [pattern: '/pricing', access: ['permitAll']],
        [pattern: '/error', access: ['permitAll']],
        [pattern: '/index', access: ['permitAll']],
        [pattern: '/index.gsp', access: ['permitAll']],
        [pattern: '/assets/**', access: ['permitAll']],
        [pattern: '/shutdown', access: ['permitAll']],
        [pattern: '/**/js/**', access: ['permitAll']],
        [pattern: '/**/css/**', access: ['permitAll']],
        [pattern: '/**/images/**', access: ['permitAll']],
        [pattern: '/**/fonts/**', access: ['permitAll']],
        [pattern: '/**/favicon.ico', access: ['permitAll']],
        [pattern: '/onboarding/**', access: ['permitAll']],
        [pattern: '/api/login', access: ['permitAll']],
        [pattern: '/api/**', access: ['isFullyAuthenticated()']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
        [pattern: '/api/**', filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter'],
        [pattern: '/**', filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter']
]

grails.plugin.springsecurity.rest.logout.endpointUrl = '/api/logout'
grails.plugin.springsecurity.rest.token.validation.useBearerToken = false
grails.plugin.springsecurity.rest.token.validation.headerName = 'X-Auth-Token'
grails.plugin.springsecurity.rest.token.validation.enableAnonymousAccess = true
grails.plugin.springsecurity.rest.token.storage.memcached.hosts = 'localhost:11211'
grails.plugin.springsecurity.rest.token.storage.memcached.username = ''
grails.plugin.springsecurity.rest.token.storage.memcached.password = ''
grails.plugin.springsecurity.rest.token.storage.memcached.expiration = 86400

