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

    static constraints = {
        officeAddress nullable: true
        officeNumber nullable: true
        preferences nullable: true
    }

    static belongsTo = [account: Account]
    static hasOne = [preferences: CompanyPreferences]
}
