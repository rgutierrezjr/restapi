package restapi.accounts

import restapi.addresses.Address
import restapi.users.User

class Account {
    Date dateCreated
    Date lastUpdated

    String status = STATUS_ACTIVE

    User admin
    Company company

    Address billingAddress

    public static final STATUS_ACTIVE = "Active"
    public static final STATUS_INACTIVE = "Inactive"
    public static final STATUS_PROVISIONING = "Provisioning"

    static constraints = {
        billingAddress nullable: true
    }

    static hasOne = [company: Company]

    static hasMany = [accountUsers: User]
}
