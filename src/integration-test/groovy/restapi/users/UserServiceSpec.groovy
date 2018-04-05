package restapi.users

import grails.testing.mixin.integration.Integration
import grails.transaction.Rollback
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class UserServiceSpec extends Specification {

    @Autowired
    UserService userService

    def setup() {
        // existing user used to test "non-unique" username / email test.
        def existingUser = new User(username: "test@email.com", firstName: "test", lastName: "test last", role: User.ROLE_MANAGER)
        existingUser.save()
    }

    def cleanup() {
    }

    /** Pass scenarios **/

    void "validateNewUser - best case"() {
        when: "valid data passed"
        def params = [
                email    : "valid@email.com",
                firstName: "valid_first",
                lastName : "valid_last",
                role     : User.ROLE_DEVELOPER,
                password : "SoMeValIdP@ssw0rd"
        ]

        def validatedUser = userService.validateNewUser(params)

        then: "validated instance of User returned"
        assert validatedUser instanceof User
    }

    void "validateNewUser - save validated new user"() {
        when: "valid data passed"
        def params = [
                email    : "valid@email.com",
                firstName: "valid_first",
                lastName : "valid_last",
                role     : User.ROLE_DEVELOPER,
                password : "SoMeValIdP@ssw0rd"
        ]

        def validatedUser = userService.save(params)

        then: "persisted user returned. existence of record id."
        assert validatedUser.id != null
    }

    void "validateExistingUser - best case"() {
        when: "valid data passed"

        def existingUser = User.findWhere(username: "test@email.com")
        def params = [
                firstName: "valid_first",
                lastName : "valid_last",
                role     : User.ROLE_DEVELOPER,
                password : "SoMeValIdP@ssw0rd"
        ]

        def validatedUser = userService.validateExistingUser(existingUser, params)

        then:
        assert validatedUser instanceof User
    }

    void "deleteUser - best case"() {
        when: "delete existing user"
        def existingUser = User.findWhere(username: "test@email.com")

        then: "deleteUser returns true"
        assert userService.deleteUser(existingUser)
    }

    /** Fail tests **/

    void "validateNewUser - non unique email"() {
        when: "use non-unique email to create new user"
        def params = [
                email    : "test@email.com",
                firstName: "valid_first",
                lastName : "valid_last",
                role     : User.ROLE_DEVELOPER,
                password : "SoMeValIdP@ssw0rd"
        ]

        userService.validateNewUser(params)

        then: "throws exception - Email is invalid or already in use."
        Exception e = thrown()
        assert e.message == "Email is invalid or already in use."
    }

    void "validateNewUser - blank first name"() {
        when: "pass blank first name to create new user"
        def params = [
                email    : "unique@email.com",
                firstName: "",
                lastName : "valid_last",
                role     : User.ROLE_DEVELOPER,
                password : "SoMeValIdP@ssw0rd"
        ]

        userService.validateNewUser(params)

        then: "throws exception - First name is required."
        Exception e = thrown()
        assert e.message == "First name is required."
    }

    void "validateNewUser - blank last name"() {
        when: "pass blank last name to create new user"
        def params = [
                email    : "unique@email.com",
                firstName: "valid_first",
                lastName : "",
                role     : User.ROLE_DEVELOPER,
                password : "SoMeValIdP@ssw0rd"
        ]

        userService.validateNewUser(params)

        then: "throws exception - Last name is required."
        Exception e = thrown()
        assert e.message == "Last name is required."
    }

    void "validateNewUser - username is blank"() {
        when: "pass blank username to create new user"
        def params = [
                email    : "",
                firstName: "valid_first",
                lastName : "valid_last",
                role     : User.ROLE_DEVELOPER,
                password : "SoMeValIdP@ssw0rd"
        ]

        userService.validateNewUser(params)

        then: "throws exception - Email is required."
        Exception e = thrown()
        assert e.message == "Email is required."
    }

    void "validateNewUser - password is blank"() {
        when: "pass blank password to create new user"
        def params = [
                email    : "valid@email.com",
                firstName: "valid_first",
                lastName : "valid_last",
                role     : User.ROLE_DEVELOPER,
                password : ""
        ]

        userService.validateNewUser(params)

        then: "throws exception - Password is required."
        Exception e = thrown()
        assert e.message == "Password required."
    }

    void "validateExistingUser - update to blank first name"() {
        when: "blank first name passed"

        def existingUser = User.findWhere(username: "test@email.com")
        def params = [
                firstName: "",
                lastName : "valid_last",
                role     : User.ROLE_DEVELOPER,
                password : "SoMeValIdP@ssw0rd"
        ]

        userService.validateExistingUser(existingUser, params)

        then: "throws exception - First name cannot be blank."
        Exception e = thrown()
        assert e.message == "First name cannot be blank."
    }

    void "validateExistingUser - update to blank last name"() {
        when: "blank last name passed"

        def existingUser = User.findWhere(username: "test@email.com")
        def params = [
                firstName: "valid_first",
                lastName : "",
                role     : User.ROLE_DEVELOPER,
                password : "SoMeValIdP@ssw0rd"
        ]

        userService.validateExistingUser(existingUser, params)

        then: "throws exception - Lase name cannot be blank."
        Exception e = thrown()
        assert e.message == "Last name cannot be blank."
    }

    void "validateExistingUser - update to invalid role"() {
        when: "invalid role passed"

        def existingUser = User.findWhere(username: "test@email.com")
        def params = [
                firstName: "valid_first",
                lastName : "valid_last",
                role     : "some random role",
                password : "SoMeValIdP@ssw0rd"
        ]

        userService.validateExistingUser(existingUser, params)

        then: "throws exception - Invalid role."
        Exception e = thrown()
        assert e.message == "Invalid role."
    }
}
