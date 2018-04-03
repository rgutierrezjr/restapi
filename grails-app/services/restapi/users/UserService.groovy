package restapi.users

class UserService {

    /**
     * This service will save a new instance of User.
     * @param params Request params
     * @return user Newly created User instance.
     * @throws Exception
     */
    def save(params) throws Exception {
        return validateAndSaveNewUser(params)
    }

    /**
     * The service will update an existing user with the following params. Any validation error will throw an exception
     * which will bubble up to the Controller exception handler.
     * @param user User to be updated.
     * @param params Request params
     * @return user Updated User instance
     * @throws Exception
     */
    def update(User user, params) throws Exception {
        user.properties(params)

        return validateExistingUser(user)
    }

    /* VALIDATION SERVICES */

    /**
     * This service will validate form data for a new instance of User. It will throw an exception if any validation
     * fails. Exceptions will be caught up at the Controller level by the appropriate exception handler.
     * @param params
     * @return user New User instance
     * @throws Exception
     */
    def validateAndSaveNewUser(params) throws Exception {
        def newUser = new User()

        newUser.username = params?.email
        newUser.firstName = params?.firstName
        newUser.lastName = params?.lastName
        newUser.role = params?.role

        // password is encrypted on "beforeInsert" domain instance event.
        newUser.password = params?.password

        // validate the user as a whole. if underlying  validation issues detected, perform granular validation.
        // User.validate() will run validation against constraints defined in the domain class.
        // TODO: implement and replace generic Exception with UserValidationException
        if (!newUser.validate()) {
            // perform more granular validation
            if (!newUser.firstName) {
                throw new Exception("First name is required.")
            }

            if (!newUser.lastName) {
                throw new Exception(("Last name is required."))
            }

            if (!newUser.username) {
                throw new Exception("Email is required.")
            }

            if (!newUser.validate(["username"])) {
                throw new Exception(("Email is invalid."))
            }

            if (!newUser.password) {
                throw new Exception("Password required")
            }
        }

        if (!newUser.save()) {
            throw new Exception("Failed to create user.")
        } else {
            return newUser
        }
    }

    /**
     * This service validate an existing User with new parameters. If any validation fails, the service will throw
     * an exception that is caught by the controller exception handler.
     * @param user User instance to be updated.
     * @return user The updated User instance
     * @throws Exception
     */
    def validateExistingUser(User user) throws Exception {
        if (user.isDirty("firstName")) {
            if (!user.firstName || user.firstName == "") {
                throw new Exception("First name cannot be blank.")
            }
        }

        if (user.isDirty("lastName")) {
            if (!user.lastName || user.lastName == "") {
                throw new Exception("Last name cannot be blank.")
            }
        }

        if (user.isDirty("role")) {
            if (user.role == "") {
                throw new Exception("Role cannot be blank.")
            }
        }

        if (user.isDirty("password")) {
            // TODO: encrypt new password.
            if (!user.password || user.password == "") {
                throw new Exception("Password invalid. Please enter a valid password.")
            }
        }


        if (!user.save()) {
            throw new Exception("Failed to update user.")
        } else {
            return user
        }
    }
}