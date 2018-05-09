package restapi.accounts

class CompanyPreferences {
    Date lastUpdated
    Date dateCreated

    static belongsTo = [company: Company]

    static constraints = {
    }
}
