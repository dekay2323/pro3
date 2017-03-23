<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Create Client</title>
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
                    <g:eachError bean="${this.project}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                                error="${error}"/></li>
                    </g:eachError>
                </div>
            </g:hasErrors>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <form class="form-horizontal">
                <g:form action="saveClient">
                    <fieldset>
                        <legend>Create Client</legend>

                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">Name</label>

                            <div class="col-sm-10">
                                <g:textField class="form-control" name="name" value="${client?.name}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="contactName" class="col-sm-2 control-label">Contact Name</label>

                            <div class="col-sm-10">
                                <g:textField class="form-control" name="contactName" value="${client?.contactName}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="address" class="col-sm-2 control-label">Address</label>

                            <div class="col-sm-10">
                                <g:textField class="form-control" name="address" value="${client?.address}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="phoneNumber" class="col-sm-2 control-label">Phone Number</label>

                            <div class="col-sm-10">
                                <g:textField class="form-control" name="phoneNumber" value="${client?.phoneNumber}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-10 col-lg-offset-2">
                                <g:submitButton class="btn btn-primary" name="create" value="Submit"/>
                            </div>
                        </div>
                    </fieldset>
                </g:form>
            </form>
        </div>
    </div>
</div>