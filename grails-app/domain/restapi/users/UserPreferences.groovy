package restapi.users

class UserPreferences {
    Date lastUpdated
    Date dateCreated

    static belongsTo = [user: User]

    static constraints = {
    }
}
