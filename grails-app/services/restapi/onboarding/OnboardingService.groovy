package restapi.onboarding

import restapi.accounts.Account
import restapi.accounts.Company

class OnboardingService {
    def companyService
    def userService

    def createNewAccount(Account account)  throws Exception {

        if(!account.company) {
            throw new Exception("Company required for provisioning.")
        } else {
            companyService.validateNewCompany(account.company)
        }

        if(!account.admin) {
            throw new Exception("Admin user required for provisioning.")
        } else {
            userService.validateNewUser(account.admin)
        }

        def validatedAccount = validateAccount(account)

        if(!validatedAccount.save()) {
            throw new Exception("Failed to create account.")
        }

        return account
    }

    def validateAccount(Account account) {
        return account
    }
}
