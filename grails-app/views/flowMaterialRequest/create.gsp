<%@ page import="com.pro3.Vendor" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'materialRequest.label', default: 'MaterialRequest')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-materialRequest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-materialRequest" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.materialRequest}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.materialRequest}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save">
                <fieldset class="form">
                    <f:with bean="materialRequest">
                        <div class="fieldcontain"><label>Client</label>${materialRequest?.project?.client}</div>
                        <div class="fieldcontain"><label>Project</label>${materialRequest?.project}</div>
                        <f:field property="reqNumber" />
                        <f:field property="description" />
                        <f:field property="budget" />
                        <f:field property="rasDate" />
                        <f:field property="estLeadTime" />
                        <f:field property="strategy" />
                        <div class="fieldcontain"><label>Recommended Bidders</label>
                            <g:select name="bidders" from="${com.pro3.Vendor.list()}" value="${materialRequest?.bidders*.id}" optionKey="id" multiple="true" />
                        </div>
                    </f:with>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
