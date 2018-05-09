<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
</head>
<body>
    <content tag="nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Application Status <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="#">Environment: ${grails.util.Environment.current.name}</a></li>
                <li><a href="#">App profile: ${grailsApplication.config.grails?.profile}</a></li>
                <li><a href="#">App version:
                    <g:meta name="info.app.version"/></a>
                </li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Grails version:
                    <g:meta name="info.app.grailsVersion"/></a>
                </li>
                <li><a href="#">Groovy version: ${GroovySystem.getVersion()}</a></li>
                <li><a href="#">JVM version: ${System.getProperty('java.version')}</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Artefacts <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="#">Controllers: ${grailsApplication.controllerClasses.size()}</a></li>
                <li><a href="#">Domains: ${grailsApplication.domainClasses.size()}</a></li>
                <li><a href="#">Services: ${grailsApplication.serviceClasses.size()}</a></li>
                <li><a href="#">Tag Libraries: ${grailsApplication.tagLibClasses.size()}</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Installed Plugins <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                    <li><a href="#">${plugin.name} - ${plugin.version}</a></li>
                </g:each>
            </ul>
        </li>
    </content>

    <div class="svg" role="presentation">
        <div class="grails-logo-container">
            <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
        </div>
    </div>

    %{--<div id="content" role="main">--}%
        %{--<section class="row colset-2-its">--}%
            %{--<h1>Ruben's Sample REST API Application</h1>--}%

            %{--<p>--}%
                %{--<strong>Please use a REST Client tool like Postman to reach the API.</strong>--}%
            %{--</p>--}%
            %{--<p>--}%
                %{--<strong>Directions: </strong> Send login POST request to http://104.131.123.81/api/login with the following JSON: <br/>--}%
                %{--<strong>{"username": "email_0@email.com", "password":"P@ssW0rd1"}</strong>--}%
            %{--</p>--}%
            %{--<p>--}%
                %{--Use session token to make subsequent calls to http://104.131.123.81/api/user with the following header:--}%
                %{--<strong>Key</strong> X-Auth-Token, <strong>Value</strong> [token from login response]--}%
            %{--</p>--}%

            %{--<p><strong>GET /user</strong> request will return list of test users.</p>--}%

            %{--<p><strong>POST /user</strong> request will create a new user.</p>--}%

            %{--<p><strong>DELETE /user/[id]</strong> request will delete user [id].</p>--}%

            %{--<p><strong>PUT /user/[id]</strong> request will update user [id].</p>--}%

            %{--<p><strong>Note:</strong> Send raw (text) data with request. (application/json) not required.</p>--}%
        %{--</section>--}%
    %{--</div>--}%
</body>
</html>
