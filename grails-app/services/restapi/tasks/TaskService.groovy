package restapi.tasks

import grails.gorm.transactions.Transactional

@Transactional
class TaskService {

    /**
     * This service will save a new instance of User.
     * @param params
     * @return
     * @throws Exception
     */
    def save(params) throws Exception {
        // back-end validation.
        validateNewTask(params)

        return true
    }

    /**
     * This service will validate form data for a new instance of Task. It will throw an exception if any validation fails.
     * Exceptions will be caught up at the Controller level by the appropriate exception handler.
     * @param params
     * @return
     * @throws Exception
     */
    def validateNewTask(params) throws Exception {
        def newTask = new Task()

        newTask.title = params?.title
        newTask.description = params?.description

        // validate the task as a whole. if underlying  validation issues detected, perform granular validation.
        // Task.validate() will run validation against constraints defined in the domain class.
        // TODO: figure out a way to surface gorm validation up to the exception handler in the controller. JSON pretty
        if (!newTask.validate()) {
            return false
            // do a more granular validation
            if (!newTask.title) {
                throw new Exception("Title is required.")
            }
        }

        newTask.save()

        return newTask
    }
}
