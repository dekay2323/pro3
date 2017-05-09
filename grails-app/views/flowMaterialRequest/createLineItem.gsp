<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Line Items</title>
</head>
<body>
<g:render template="/template/dropdownNav" />
<g:render template="/template/topNavUser" />

<div id="create-lineItem" class="content scaffold-create" role="main">
    <h1>Line Items</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:hasErrors bean="${this?.lineItem}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.lineItem}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <fieldset class="form">
        <table>
            <thead>
            <tr>
            <tr>
                <g:sortableColumn property="code" title="Line ID" />
                <g:sortableColumn property="wbs" title="WBS" />
                <g:sortableColumn property="description" title="Description" />
                <g:sortableColumn property="quantity" title="Qty" />
                <g:sortableColumn property="unitOfMeasure" title="UoM" />
                <th></th>
            </tr>
            </tr>
            </thead>
            <tbody>
            <g:each in="${this?.materialRequest?.lineItems}" var="mr" status="i">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td>${mr.code}</td>
                    <td>${mr.wbs}</td>
                    <td>${mr.description}</td>
                    <td>${mr.quantity}</td>
                    <td>${mr.unitOfMeasure}</td>
                    <td><g:link action="deleteLineItem" id="${materialRequest?.id}" params="[lineItemId: mr?.id]">Delete</g:link></td>
                </tr>
            </g:each>
            
            <g:form controller="flowMaterialRequest" action="saveLineItem">
                <g:hiddenField name="request" value="${materialRequest?.id}" />
                <tr class="bg-info">
                    <td></td>
                    <td><g:select name="wbs.id" from="${com.pro3.Wbs.getAll()}" noSelection="${['null':'']}" value="${wbs}" optionKey="id" optionValue="code"/></td>
                    <td><g:textField name="description" value="${description}" /></td>
                    <td><g:field type="number" name="quantity" value="${quantity}" /></td>
                    <td><g:textField name="unitOfMeasure" value="${unitOfMeasure}" /></td>
                    <td><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></td>
                </tr>
            </g:form>
            
            </tbody>
        </table>
    </fieldset>
    
    <fieldset class="buttons">
        <g:link controller="flowMaterialRequest" action="editMaterialRequest" id="${lineItem?.request?.id}">Back</g:link>
    </fieldset>
</div>
</body>
</html>
