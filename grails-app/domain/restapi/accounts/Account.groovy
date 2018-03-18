package restapi.accounts

import restapi.addresses.Address
import restapi.users.User

class Account {
    Date dateCreated
    Date lastUpdated

    String status = STATUS_ACTIVE
    User accountMain
    Address billingAddress

    public static final STATUS_ACTIVE = "Active"
    public static final STATUS_INACTIVE = "Inactive"

    static constraints = {
    }

    static hasMany = [accountUsers: User]
}
