<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
</head>
<body>

<div id="create-lineItem" class="container" role="main">
    <h1>Line Items</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    
    <g:form controller="flowMaterialRequest">
        <fieldset class="form">
            <table>
                <thead>
                <tr>
                <tr>
                    <g:sortableColumn property="code" title="Line ID" />
                    <g:sortableColumn property="wbs" title="WBS" />
                    <g:sortableColumn property="name" title="Name" />
                    <g:sortableColumn property="quantity" title="Qty" />
                    <g:sortableColumn property="unitOfMeasure" title="UoM" />
                    <th></th>
                </tr>
                </tr>
                </thead>
                <tbody>
                <g:each in="${this?.materialRequest?.lineItems}" var="lineItem" status="i">
                    <g:hasErrors bean="${lineItem}">
                        <tr class="bg-danger">
                            <td colspan="6">
                                <ul class="errors" role="alert">
                                    <g:eachError bean="${lineItem}" var="error">
                                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                                    </g:eachError>
                                    </ul>
                            </td>
                        </tr>
                    </g:hasErrors>
                        
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td><g:field type="number" name="code-${lineItem.id}" value="${lineItem.code}" /></td>
                        <td><g:select name="wbs-${lineItem.id}.id" from="${com.pro3.domain.list.Wbs.getAll()}" noSelection="${['null':'']}" value="${lineItem.wbs?.id}" optionKey="id" optionValue="code"/></td>
                        <td><g:textField name="name-${lineItem.id}" value="${lineItem.name}" /></td>
                        <td><g:field type="number" name="quantity-${lineItem.id}" value="${lineItem.quantity}" /></td>
                        <td><g:textField name="unitOfMeasure-${lineItem.id}" value="${lineItem.unitOfMeasure}" /></td>
                        <td><g:link action="deleteLineItem" id="${materialRequest?.id}" params="[lineItemId: lineItem?.id]" tabindex="-1">Delete</g:link></td>
                    </tr>
                </g:each>
    
                <g:hiddenField name="request" value="${materialRequest?.id}" />

                <g:if test="${errors}">
                    <tr class="bg-danger">
                        <td colspan="6">
                            <ul class="errors" role="alert">
                                <g:eachError bean="${errors}" var="error">
                                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                                </g:eachError>
                            </ul>
                        </td>
                    </tr>
                </g:if>
                
                <tr class="bg-info">
                    <td><g:field type="number" name="code" value="${code}" /></td>
                    <td><g:select name="wbs.id" from="${com.pro3.domain.list.Wbs.getAll()}" noSelection="${['null':'']}" value="${wbs}" optionKey="id" optionValue="code"/></td>
                    <td><g:textField name="name" value="${name}" /></td>
                    <td><g:field type="number" name="quantity" value="${quantity}" /></td>
                    <td><g:textField name="unitOfMeasure" value="${unitOfMeasure}" /></td>
                    <td><g:actionSubmit name="create" action="saveLineItem" class="save" value="Create" tabindex="-1"/></td>
                </tr>
                
                </tbody>
            </table>
        </fieldset>
        
        <fieldset class="buttons">
            <g:link controller="flowMaterialRequest" action="editMaterialRequest" id="${this?.materialRequest?.id}">Back</g:link>
            <g:actionSubmit name="update" class="save" action="updateLineItems" value="Update"/>
        </fieldset>     
    </g:form>
</div>
</body>
</html>
