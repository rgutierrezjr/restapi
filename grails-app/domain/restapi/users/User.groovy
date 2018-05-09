package restapi.users

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.compiler.GrailsCompileStatic
import restapi.tasks.Task
import grails.rest.Resource

@GrailsCompileStatic
@Resource(uri = '/api/user')
class User implements Serializable {

    private static final long serialVersionUID = 1

    transient SpringSecurityService springSecurityService

    Date dateCreated
    Date lastUpdated

    String firstName
    String lastName
    String role

    String username
    String password = "P@ssW0rd1" // we can update this later during security sweep, for now plain text.
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    /* User roles */
    static public final String ROLE_DEVELOPER = "Developer"
    static public final String ROLE_TEAM_LEAD = "Team Lead"
    static public final String ROLE_MANAGER = "Manager"
    static public final String ROLE_EXECUTIVE = "Executive"

    static public final ROLE_TYPES = [ROLE_DEVELOPER, ROLE_TEAM_LEAD, ROLE_MANAGER, ROLE_EXECUTIVE]

    static hasMany = [tasks: Task]

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
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
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }

    static transients = ['springSecurityService']

    static constraints = {
        password blank: false, password: true
        username blank: false, unique: true
        firstName blank: false
        lastName blank: false
        role nullable: true
    }

    static mapping = {
	    password column: '`password`'
    }
}
