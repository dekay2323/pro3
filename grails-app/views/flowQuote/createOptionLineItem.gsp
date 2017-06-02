<!DOCTYPE html>
<html>
<head>
    <sec:ifAnyGranted roles='ROLE_VENDOR'>
        <meta name="layout" content="vendor" />
    </sec:ifAnyGranted>
    <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_USER'>
        <meta name="layout" content="main" />
    </sec:ifAnyGranted>
    <title>Create Option Line Item</title>
</head>
<body>

<div id="create-lineItem" class="content scaffold-create" role="main">
    <h1>Create Option Line Item</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.optionLineItem}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.optionLineItem}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form action="saveOptionLineItem">
        <fieldset class="form">
            <f:with bean="optionLineItem">
                <g:hiddenField name="quote" value="${optionLineItem?.quote?.id}" />
                <div class="fieldcontain">
                    <label>Line Item</label>
                    <g:select id="type" name='lineItem' value="${optionLineItem?.lineItem?.id}"
                              noSelection="${['null':'None']}"
                              from='${optionLineItem?.getLineItemsList()}'
                              optionKey="id"
                              optionValue="description"></g:select>
                </div>
                <f:field property="description" />
                <f:field property="quantity" />
                <f:field property="unitOfMeasure" />
                <f:field property="price" />
                <f:field property="leadTime" />
            </f:with>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
