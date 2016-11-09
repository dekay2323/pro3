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