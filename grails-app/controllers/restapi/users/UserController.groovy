package restapi.users

import grails.rest.RestfulController

/**
 * Extended "User" restful api. This allows us to customize our backend resource logic and validation
 * as well as provide user friendly response messaging.
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
        // TODO: add pagination capabilities to this api action.
        respond User.list()
    }

    /**
     * By specifying the domain instance as a parameter to the action Grails will automatically attempt to
     * lookup the domain instance using the id parameter of the request.
     * @param user being fetched
     */
    def show(User user) {
        if (!user) {
            response.status = 404
            respond message: "User not found."
        } else {
            respond user
        }
    }

    /**
     * "save" will simulate "posting" a new User record. POST /users
     */
    def save(User user) {

        // "save" will run backend validation and then create a new instance of "user"
        // all validation and system exceptions will bubble up to and be handled by "handleException"
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

        // if th user is not found, response with appropriate response code and message
        if (!user) {
            response.status = 404
            respond message: "User not found."
        } else {

            // if user found, invoke update.
            // all validation and system exceptions will bubble up to and be handled by "handleException"
            userService.update(params)

            response.status = 202

            respond message: "User updated."
        }
    }

    /* EXCEPTION HANDLERS */

    /**
     * Highest level of exception handling. All other exception handlers declared below this one.
     * @param e Exception
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
