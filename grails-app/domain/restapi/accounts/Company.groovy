package restapi.accounts

import grails.rest.Resource
import restapi.addresses.Address
import restapi.users.User

@Resource(uri = 'api/company')
class Company {
    Date dateCreated
    Date lastUpdated

    String name
    String officeNumber

    Address officeAddress
    Address billingAddress

    User admin

    static constraints = {
        officeAddress nullable: true
        billingAddress nullable: true
        officeNumber nullable: true
        preferences nullable: true
    }

    static hasMany = [companyUsers: User]

    static hasOne = [preferences: CompanyPreferences]
}
