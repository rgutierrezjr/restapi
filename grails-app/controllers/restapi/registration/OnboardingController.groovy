package restapi.registration

import restapi.accounts.Account

class OnboardingController {
    static responseFormats = ['json']

    def onboardingService

    def register(Account account) {
        def newAccount = onboardingService.createNewAccount(account)

        // send welcome email
        onboardingService.sendWelcomeEmail()

        response.status = HttpURLConnection.HTTP_CREATED

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
