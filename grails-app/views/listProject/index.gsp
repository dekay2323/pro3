<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Project List</title>
</head>

<body>
<g:render template="/template/topNavUser"/>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <g:if test="${flash.message}">
                <div class="alert alert-info">
                    ${flash.message}
                </div>
            </g:if>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <h2>All Projects</h2>
        </div>
    </div>


    <g:each in="${this.clientList}" var="client">
        <div class="row">
            <div class="col-sm-12">
                <h3>Client: ${client?.name}</h3>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>Project</th>
                        <th>Name</th>
                        <th>Budget</th>
                        <th>Committed</th>
                        <th>Accrued</th>
                        <th>Incurred</th>
                        <th>Interested Users</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${client?.projects}" var="project">
                        <tr>
                            <td>${project?.id}</td>
                            <td><g:link controller="listMaterialRequest" action="index"
                                        id="${project?.id}">${project?.name}</g:link></td>
                            <td><f:display bean="${project}" property="budget"/></td>
                            <td><f:display bean="${project}" property="committed"/></td>
                            <td><f:display bean="${project}" property="accrued"/></td>
                            <td><f:display bean="${project}" property="incurred"/></td>
                            <td><g:link controller="flowProject" action="editProject"
                                        id="${project?.id}">Edit</g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-9">
                <g:link class="btn btn-primary" controller="flowProject" action="createProject"
                        params="[clientId: client?.id]">+Project</g:link>
            </div>
        </div>
    </g:each>

    <br />
    <br />

    <div class="row">
        <div class="col-sm-12">
            <g:link class="btn btn-primary" controller="flowClient" action="createClient">Create Client</g:link>
        </div>
    </div>

</div>
</body>
</html>