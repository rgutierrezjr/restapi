package restapi.tasks

import grails.gorm.transactions.Transactional

@Transactional
class ProjectService {

    def get() throws Exception {
        return Project.list()
    }

    def save(params) throws Exception {
        def project = new Project(title: params.title)
        if (project.validate()) {
            project.save()
        } else {
            throw new Exception("Not implemented")
        }
    }

    def update(project) throws Exception {
        throw new Exception("Not implemented")
    }

    def delete(project) throws Exception {
        throw new Exception("Not implemented")
    }
}
