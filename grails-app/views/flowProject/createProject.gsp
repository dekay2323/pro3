<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Create Project</title>
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
            <g:hasErrors>
                <div class="alert alert-danger">
                    <ul>
                        <g:eachError bean="${client}" var="error">
                            <li><g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                </div>
            </g:hasErrors>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-6">
            <div class="well bs-component">
                <g:form action="saveProject" class="form-horizontal">
                    <fieldset>
                        <div class="row">
                            <legend class="col-sm-12">Create Project</legend>
                        </div>

                        <div class="row form-group ${hasErrors(bean: project, field: 'projectNumber', 'has-error')}">
                            <label for="name" class="col-sm-3 control-label">Project Number</label>

                            <div class="col-sm-9">
                                <g:textField class="form-control" name="projectNumber"
                                             value="${project?.projectNumber}"/>
                            </div>
                        </div>

                        <div class="row form-group ${hasErrors(bean: project, field: 'name', 'has-error')}">
                            <label for="name" class="col-sm-3 control-label">Name</label>

                            <div class="col-sm-9">
                                <g:textField class="form-control" name="name" value="${project?.name}"/>
                            </div>
                        </div>

                        <div class="row form-group ${hasErrors(bean: project, field: 'shortDescription', 'has-error')}">
                            <label for="name" class="col-sm-3 control-label">Short Description</label>

                            <div class="col-sm-9">
                                <g:textField class="form-control" name="shortDescription"
                                             value="${project?.shortDescription}"/>
                            </div>
                        </div>

                        <div class="row form-group">
                            <label class="col-sm-3 control-label">Client</label>

                            <div class="col-sm-9 form-control-static">
                                ${project?.client}
                            </div>
                        </div>

                        <g:hiddenField name="client" value="${project?.client?.id}"/>

                        <div class="row form-group">
                            <div class="col-sm-3">
                            </div>

                            <div class="col-sm-9">
                                <g:submitButton class="btn btn-primary" name="create" value="Submit"/>
                            </div>
                        </div>
                    </fieldset>
                </g:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
