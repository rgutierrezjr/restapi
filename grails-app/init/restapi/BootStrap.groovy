package restapi

import restapi.users.User
import restapi.users.Role
import restapi.users.UserRole

class BootStrap {

    def init = { servletContext ->
        // create some test dat here
        for(int i = 0; i < 10; i++) {
            def newUser = User.findOrSaveWhere(firstName: "user ${i}", lastName: "last name ${i}", username: "email_${i}@email.com")

            def role = Role.findOrSaveWhere(authority: "ROLE_SUPER_USER")
            UserRole.findOrSaveWhere(user: newUser, role: role)
        }
    }
    def destroy = {
    }
}
