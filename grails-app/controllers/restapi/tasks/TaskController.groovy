package restapi.tasks

import grails.rest.RestfulController

/**
 * Extended "Task" restful api. This allows us to customize out backend resource logic and 
 * provide task friendly validation and messaging.
 */
class TaskController extends RestfulController {
    TaskService taskService

    static responseFormats = ['json', 'xml']

    TaskController() {
        super(Task)
    }

    /**
     * index simulates GET /task
     */
    def index() {
        // TODO: we might want to think about paginated results here. send all for now.
        respond Task.list()
    }

    /**
     * By specifying the domain instance as a parameter to the action Grails will automatically attempt to
     * lookup the domain instance using the id parameter of the request.
     * @param task being fetched
     */
    def show(Task task) {
        if (!task) {
            // TODO: add detailed messaging here? are status code sufficient?
            response.status = 404

            respond message: "Task not found."
        } else {
            respond task
        }
    }

    /**
     * "save" will simulate a "posting" a new task record. POST /tasks
     */
    def save() {
        taskService.save(params)

        // if execution has reached this point then validation has passed and task has been created.

        response.status = 201

        respond message: "Task created."
    }

    /**
     * "update" will simulate PUT /tasks/${id}* @param task task being updated
     */
    def update(Task task) {
        if (!task) {
            respond status: 404
        } else {
            // TODO: send params to service method to update task. messaging?
            respond status: 202
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
                message: "Failed create or update task",
                error: e.message
        )
    }

    // TODO: create custom TaskValidationException and then a handler to catch them from services.
}
