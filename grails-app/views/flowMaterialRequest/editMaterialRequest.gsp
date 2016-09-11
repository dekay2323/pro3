<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'materialRequest.label', default: 'MaterialRequest')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-materialRequest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="edit-materialRequest" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
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


            <g:form action="updateMaterialRequest" controller="flowMaterialRequest" id="${materialRequest.id}"  method="PUT">
                <g:hiddenField name="version" value="${materialRequest?.version}" />

                <g:render template="mrEditGeneral" model="[materialRequest: materialRequest, client: client]" />

                <h2>Bidders</h2>
                <fieldset class="form">
                    <div class="fieldcontain">
                        <label>Recommended Bidders</label>
                        <g:select name="bidders" from="${com.pro3.Vendor.list()}" value="${materialRequest?.bidders*.id}" optionKey="id" multiple="true" />
                    </div>
                </fieldset>
                <h2>Scope of Supply</h2>
                <fieldset class="form">
                    <table>
                        <thead>
                        <tr>
                            <td>Line ID</td>
                            <td>WBS</td>
                            <td>Description</td>
                            <td>Qty</td>
                            <td>UoM</td>
                            <td>Unit Price</td>
                            <td>Extended Price</td>
                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${materialRequest?.lineItems}" var="mr" status="i">
                            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                <td>${mr.code}</td>
                                <td>${mr.wbs}</td>
                                <td>${mr.description}</td>
                                <td>${mr.quantity}</td>
                                <td>${mr.unitOfMeasure}</td>
                                <td></td>
                                <td></td>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>
                </fieldset>
                <div class="nav" role="navigation">
                    <ul>
                        <li><g:link class="create" action="createLineItem" params="[materialRequestId: materialRequest?.id]">Create Line Item</g:link></li>
                    </ul>
                </div>

                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
