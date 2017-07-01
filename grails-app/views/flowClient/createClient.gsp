<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
</head>
<body>

<a href="#create-project" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div id="create-project" class="content scaffold-create" role="main">
    <h1>Existing Clients</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <table>
        <thead>
        <tr>
            <g:sortableColumn property="name" title="Name"/>
            <g:sortableColumn property="contactName" title="Contact Name"/>
            <g:sortableColumn property="phoneNumber" title="Phone Number"/>
        </tr>
        </thead>
        <tbody>
        <g:each in="${clientList}" var="client" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${client?.name}</td>
                <td>${client?.contactName}</td>
                <td>${client?.phoneNumber}</td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <h1>Create Client</h1>
    <g:hasErrors bean="${this.client}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.client}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form action="saveClient">
        <g:hiddenField name="account" value="${client?.account?.id}" />
        <fieldset class="form">
            <f:with bean="client">
                <f:field property="name" />
                <f:field property="contactName" />
                <div class="fieldcontain">
                    <label for="address">Address</label>
                    <g:textArea name="address" value="${quote?.notBiddingReason}"/>
                </div>
                <f:field property="address" />
                <f:field property="phoneNumber" />
            </f:with>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
