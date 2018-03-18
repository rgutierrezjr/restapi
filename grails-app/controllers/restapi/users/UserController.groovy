package restapi.users

import grails.rest.RestfulController

/**
 * Extended "User" restful api. This allows us to customize our backend resource logic and
 * provide user friendly validation and messaging.
 */
class UserController extends RestfulController {
    static responseFormats = ['json', 'xml']

    UserService userService

    UserController() {
        super(User)
    }

    /**
     * index simulates GET /user
     */
    def index() {
        // TODO: we might want to think about paginated results here. send all for now.
        respond User.list()
    }

    /**
     * By specifying the domain instance as a parameter to the action Grails will automatically attempt to
     * lookup the domain instance using the id parameter of the request.
     * @param user being fetched
     */
    def show(User user) {
        if (!user) {
            // TODO: add detailed messaging here? are status code sufficient?
            respond status: 404
        } else {
            respond user
        }
    }

    /**
     * "save" will simulate "posting" a new User record. POST /users
     */
    def save(User user) {
        userService.save(params)

        // if execution has reached this point then validation has passed and a user has been created.
        // set status code and respond success message.

        response.status = 201

        respond message: "User created."
    }
    /**
     * "update" will simulate PUT /users/${id}* @param user User being updated
     */
    def update(User user) {
        if (!user) {
            response.status = 404
            respond message: "User not found."
        } else {

            // update user
            userService.update(params)

            response.status = 202

            respond message: "User updated."
        }
    }

    /* EXCEPTION HANDLERS */

    /**
     * Highest level of exception handling. All other exception handlers declared below this one.
     * @param e
     * @return
     */
    def handleException(Exception e) {
        response.status = 400

        respond(
                message: "Failed to create or update user",
                error: e.message
        )
    }

    // TODO: create custom UserValidationException and then a handler to catch them from services.
}
