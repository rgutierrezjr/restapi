package restapi.teams

import grails.rest.RestfulController

class TeamController extends RestfulController {
	static responseFormats = ['json', 'xml']

    TeamService teamService

    TeamController(Team) {
        super(Team)
    }

    def index() { }
}
