package restapi.accounts

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import org.springframework.security.access.prepost.PreAuthorize

class CompanyController extends RestfulController {
    def springSecurityService
    def companyService

    static responseFormats = ['json']

    CompanyController() {
        super(Company)
    }

    def index() {
        // TODO: add pagination capabilities to this api action.
        respond Company.list()
    }


    def save(Company company) {
        // fetch the current user
        def admin = springSecurityService.loadCurrentUser()

        // assign by default, the current user as this new company's admin.
        company.admin = admin

        company = companyService.save(company)

        response.status = HttpURLConnection.HTTP_CREATED

        respond company
    }

    def update(Company company) {

        println springSecurityService.loadCurrentUser()

    }

    def handleException(Exception e) {
        response.status = 400

        respond(
                message: "Failed to create or update company.",
                error: e.message
        )
    }
}
