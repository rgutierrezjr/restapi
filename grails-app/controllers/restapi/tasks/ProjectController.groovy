package restapi.tasks

import grails.rest.RestfulController

class ProjectController extends RestfulController {

    static allowedMethods = [index: "GET", show: "GET", save: "POST", update: "PUT", delete: "DELETE"]

    ProjectService projectService;

    ProjectController() {
        super(Project)
    }

    // GET /projects
    def index() {
        // TODO: we might want to think about paginated results here. send all for now.
        respond projectService.get()
    }

    // GET /projects/${id}
    def show(Project project) {
        if (project) {
            respond project
        } else {
            // TODO: add detailed messaging here? are status code sufficient?
            respond status: 404
        }
    }

    // POST /projects
    def save() {
        projectService.save(params)
        respond status: 201
    }

    // PUT /projects/${id}
    def update(Project project) {
        if (project) {
            projectService.update(project)
            // TODO: send params to service method to update project. messaging?
            respond status: 204
        } else {
            // TODO: validate / return validation error message(s)
            respond status: 404
        }
    }

    // DELETE /projects/${id}
    def delete() {
        projectService.delete(params.projectId)
        respond status: 202
    }
}
