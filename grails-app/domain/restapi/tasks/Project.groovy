package restapi.tasks

import grails.rest.Resource
import restapi.products.Product

@Resource(uri = '/project')
class Project {
    Date dateCreated
    Date lastUpdated

    String title

    Date startDate
    Date endDate

    static hasMany = [tasks: Task, products: Product]

    static constraints = {
        startDate nullable: true
        endDate nullable: true
    }
}
