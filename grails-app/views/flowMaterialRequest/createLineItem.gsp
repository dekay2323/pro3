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
    
    <g:form controller="flowMaterialRequest">
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
                        <td><g:field type="number" name="code-${mr.id}" value="${mr.code}" /></td>
                        <td><g:select name="wbs-${mr.id}.id" from="${com.pro3.Wbs.getAll()}" noSelection="${['null':'']}" value="${mr.wbs?.id}" optionKey="id" optionValue="code"/></td>
                        <td><g:textField name="description-${mr.id}" value="${mr.description}" /></td>
                        <td><g:field type="number" name="quantity-${mr.id}" value="${mr.quantity}" /></td>
                        <td><g:textField name="unitOfMeasure-${mr.id}" value="${mr.unitOfMeasure}" /></td>
                        <td><g:link action="deleteLineItem" id="${materialRequest?.id}" params="[lineItemId: mr?.id]" tabindex="-1">Delete</g:link></td>
                    </tr>
                </g:each>
    
                <g:hiddenField name="request" value="${materialRequest?.id}" />

                <tr class="bg-info">
                    <td><g:field type="number" name="code" value="${code}" /></td>
                    <td><g:select name="wbs.id" from="${com.pro3.Wbs.getAll()}" noSelection="${['null':'']}" value="${wbs}" optionKey="id" optionValue="code"/></td>
                    <td><g:textField name="description" value="${description}" /></td>
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
