package restapi.tasks

import grails.rest.Resource
import restapi.users.User

@Resource(uri = '/task')
class Task {
    Date dateCreated
    Date lastUpdated

    String title
    String description

    static constraints = {
        assignee nullable: true
        description nullable: true
        project nullable: true
        assignee nullable: true
        description type: 'text'
    }

    static belongsTo = [project: Project, assignee: User]
}