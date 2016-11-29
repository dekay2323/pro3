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
            <td>Ship Date</td>
            <td>Check Off</td>
        </tr>
        </thead>
        <tbody>
        <g:each in="${quote?.quoteLineItems}" var="quoteLineItem" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${quoteLineItem?.lineItem?.code}</td>
                <td>${quoteLineItem?.lineItem?.wbs}</td>
                <td>${quoteLineItem?.lineItem?.description}</td>
                <td>${quoteLineItem?.lineItem?.quantity}</td>
                <td>${quoteLineItem?.lineItem?.unitOfMeasure}</td>
                <td><g:field type="text" name="price-${quoteLineItem?.id}" value="${quoteLineItem?.price}"/></td>
                <td>${quoteLineItem?.extendedPrice}</td>
                <td><g:datePicker default='none' precision="day" relativeYears="[0..25]" noSelection="['':'']" name="shipDate-${quoteLineItem?.id}" value="${quoteLineItem.shipDate}"/></td>
                <td><g:checkBox name="checkOff" value="${quoteLineItem?.checkOff}" /></td>
            </tr>
        </g:each>
        </tbody>
    </table>
</fieldset>
