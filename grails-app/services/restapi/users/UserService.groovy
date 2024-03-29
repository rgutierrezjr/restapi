package restapi.users

class UserService {

    /**
     * This service will save a new instance of User.
     * @param params Request params
     * @return user Newly created User instance.
     * @throws Exception
     */
    def save(User newUser) throws Exception {

        // validate a new user. "validateNewUser" will return a validated instance of User.
        // any validation exception will bubble up back to the controller exception handler.
        def validatedUser = validateNewUser(newUser)

        if (!validatedUser.save()) {
            throw new Exception("Failed to create user.")
        } else {
            return validatedUser
        }
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

        // validate an existing user. "validateExistingUser" will return a validated instance of User.
        // any validation exception will bubble up back to the controller exception handler.
        def validatedUser = validateExistingUser(user, params)

        // save validated existing user.
        if (!validatedUser.save(flush: true)) {
            throw new Exception("Failed to update user.")
        } else {
            return user
        }
    }

    /* VALIDATION SERVICES */

    /**
     * This service will validate form data for a new instance of User. It will throw an exception if any validation
     * fails. Exceptions will be caught up at the Controller level by the appropriate exception handler.
     * @param params Request params
     * @return user New User instance
     * @throws Exception
     */
    def validateNewUser(User newUser) throws Exception {
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
                throw new Exception("Email is invalid or already in use.")
            }

            if (!newUser.password) {
                throw new Exception("Password required.")
            }
        }

        return newUser
    }

    /**
     * This service validate an existing User with new parameters. If any validation fails, the service will throw
     * an exception that is caught by the controller exception handler.
     * @param user User instance being updated.
     * @return user The updated User instance
     * @throws Exception
     */
    def validateExistingUser(User user, params) throws Exception {
        // update fields
        user.firstName = params?.firstName
        user.lastName = params?.lastName
        user.role = params?.role
        user.password = params?.password

        // password is encrypted on "beforeInsert" domain instance event.
        user.password = params?.password

        // validate each "dirty" (updated) instance field

        // TODO: implement and replace generic Exception with UserValidationException
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
            if (!User.ROLE_TYPES.contains(user.role)) {
                throw new Exception("Invalid role.")
            }
        }

        if (user.isDirty("password")) {
            // new password is encrypted during domain level event "beforeUpdate"
            if (!user.password || user.password == "") {
                throw new Exception("Password invalid. Please enter a valid password.")
            }
        }

        return user
    }

    /**
     * This service will delete a User, set any necessary statuses, and remove any associations.
     * @param user
     * @return true If successfully deletes the User instance.
     * @throws Exception
     */
    def deleteUser(User user) throws Exception {
        // all future associations will need to be deleted here as well. or the necessary
        // cascade deletes will need to be configured under domain constraints.

        // delete user roles. (spring security)
        def roles = UserRole.findAllWhere(user: user)

        if (roles) {
            try {
                roles*.delete(flush: true)
            } catch (Exception e) {
                throw new Exception("Failed to delete user roles.")
            }
        }

        // delete user
        try {
            user.delete(flush: true)
        } catch (Exception e) {
            throw new Exception("Failed to delete user.")
        }

        return true
    }
}