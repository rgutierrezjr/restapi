package restapi.accounts

import grails.rest.Resource
import restapi.addresses.Address
import restapi.users.User

@Resource(uri = '/company')
class Company {
    Date dateCreated
    Date lastUpdated

    String companyName
    String officeNumber

    Address officeAddress
    Address billingAddress

    static constraints = {
        officeAddress nullabele: true
        billingAddress nullabele: true
        address2 nullable: true
        officeNumber nullable: true
    }

    static hasMany = [companyUsers: User]
}
