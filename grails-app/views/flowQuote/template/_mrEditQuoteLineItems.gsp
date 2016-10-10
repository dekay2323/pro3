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
        <g:each in="${quote?.quoteLineItems}" var="qlineItem" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${qlineItem?.lineItem?.code}</td>
                <td>${qlineItem?.lineItem?.wbs}</td>
                <td>${qlineItem?.lineItem?.description}</td>
                <td>${qlineItem?.lineItem?.quantity}</td>
                <td>${qlineItem?.lineItem?.unitOfMeasure}</td>
                <td></td>
                <td></td>
                <td><g:field type="text" name="price-${qlineItem?.id}" value="${qlineItem?.price}"/></td>
                <td><g:datePicker default='none' precision="day" relativeYears="[0..25]" noSelection="['':'']" type="date" name="shipDate-${qlineItem?.id}" value="${qlineItem.shipDate}"/></td>
            </tr>
        </g:each>
        </tbody>
    </table>
</fieldset>