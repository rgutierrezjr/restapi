package restapi.onboarding

import com.sendgrid.*
import restapi.accounts.Account

class OnboardingService {
    def companyService
    def userService

    static public
    final String SEND_GRID_API_DEV_KEY = "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"
    static public
    final String SEND_GRID_API_PROD_KEY = "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"

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

    def sendWelcomeEmail() {
        //TODO: pass user specific details

        // send welcome email here.
        // api key: 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
        Email from = new Email("noreply@datolus.io")
        String subject = "Welcome to Datolus!"
        Email to = new Email("brent.lewis08@gmail.com")
        Content content = new Content("text/plain", "Please verify your email. Thanks!")
        Mail mail = new Mail(from, subject, to, content)

        //TODO: swap key based on app env here.
        SendGrid sg = new SendGrid(SEND_GRID_API_DEV_KEY)
        Request sgRequest = new Request()

        sgRequest.setMethod(Method.POST)
        sgRequest.setEndpoint("mail/send")
        sgRequest.setBody(mail.build())

        Response response = sg.api(sgRequest)
        //TODO: add response handlers here

        return true
    }
}
