package restapi.company

import restapi.accounts.Company

class CompanyService {
    def userService

    def save(Company company) throws Exception {
        def validatedCompany = validateNewCompany(company)

        if (!validatedCompany.save()) {
            println validatedCompany.errors.each {
                println itN
            }

            throw new Exception("Failed to create user.")
        } else {
            return validatedCompany
        }
    }

    def validateNewCompany(Company newCompany) throws Exception {
        // validate new company
        if(!newCompany.validate()) {
            if(!newCompany.name) {
                throw new Exception("Company name is required.")
            }
        }

        return newCompany
    }
}
