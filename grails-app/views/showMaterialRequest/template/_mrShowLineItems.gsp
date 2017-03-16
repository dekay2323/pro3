<h2>Scope of Supply</h2>
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
        </tr>
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
            </tr>
        </g:each>
        </tbody>
    </table>
</fieldset>