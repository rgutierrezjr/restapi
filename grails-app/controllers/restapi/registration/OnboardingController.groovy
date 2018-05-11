package restapi.registration

import restapi.accounts.Account

class OnboardingController {
    static responseFormats = ['json']

    def onboardingService

    def register(Account account) {
        def newAccount = onboardingService.createNewAccount(account)

        respond newAccount.admin
    }

    def handleException(Exception e) {
        response.status = 400

        respond(
                message: "Failed to create or update account.",
                error: e.message
        )
    }
}
