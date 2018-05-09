package restapi.products

import restapi.tasks.Project

class Product {
    Date dateCreated
    Date lastUpdated

    static hasMany = [projects: Project]

    static constraints = {
    }
}
