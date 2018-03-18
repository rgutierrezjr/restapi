package restapi.tasks

import grails.rest.Resource

@Resource(uri = '/project')
class Project {
    Date dateCreated
    Date lastUpdated

    String title

    Date startDate
    Date endDate

    static hasMany = [tasks: Task]

    static constraints = {
        startDate nullable: true
        endDate nullable: true
    }
}
