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
            <td>Price</td>
            <td>Ship Date</td>
        </tr>
        </thead>
        <tbody>
        <g:each in="${quote?.quoteLineItems}" var="lineItem" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${lineItem?.lineItem?.code}</td>
                <td>${lineItem?.lineItem?.wbs}</td>
                <td>${lineItem?.lineItem?.description}</td>
                <td>${lineItem?.lineItem?.quantity}</td>
                <td>${lineItem?.lineItem?.unitOfMeasure}</td>
                <td></td>
                <td></td>
                <td>${lineItem?.price}</td>
                <td>${lineItem?.shipDate}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</fieldset>