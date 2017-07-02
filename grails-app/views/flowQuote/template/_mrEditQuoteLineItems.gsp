<h2>Scope of Supply</h2>
<fieldset class="form">
    <table>
        <thead>
        <tr>
            <g:sortableColumn property="lineItem.code" title="Line ID"/>
            <g:sortableColumn property="lineItem.wbs" title="WBS"/>
            <g:sortableColumn property="lineItem.description" title="Description"/>
            <g:sortableColumn property="lineItem.quantity" title="Qty"/>
            <g:sortableColumn property="lineItem.unitOfMeasure" title="UoM"/>
            <g:sortableColumn property="price" title="Unit Price"/>
            <g:sortableColumn property="extendedPrice" title="Extended Price"/>
            <g:sortableColumn property="leadTime" title="Lead Time (weeks)" />
            <g:sortableColumn property="leadTimeType" title="Status" />
            <g:sortableColumn property="checkOff" title="Check Off"/>
        </tr>
        </thead>
        <tbody>
        <g:each in="${quote?.quoteLineItems}" var="quoteLineItem" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${quoteLineItem?.lineItem?.code}</td>
                <td>${quoteLineItem?.lineItem?.wbs}</td>
                <td>${quoteLineItem?.lineItem?.name}</td>
                <td>${quoteLineItem?.lineItem?.quantity}</td>
                <td>${quoteLineItem?.lineItem?.unitOfMeasure}</td>
                <td>
                    <pro3:field type="text" name="price-${quoteLineItem?.id}" value="${quoteLineItem?.price}" readonly="${readonly}"/>
                </td>
                <td>${quoteLineItem?.extendedPrice}</td>
                <td>
                    <pro3:field type="text" name="leadTime-${quoteLineItem?.id}" value="${quoteLineItem?.leadTime}" readonly="${readonly}"/>
                </td>
                <td>
                    <pro3:select id="leadTimeType" name='leadTimeType-${quoteLineItem?.id}' value="${quoteLineItem?.leadTimeType?.id}"
                                 noSelection="${['':'']}"
                                 from='${com.pro3.domain.list.LeadTimeType.list()}'
                                 optionKey="id"
                                 optionValue="name"
                                 readonly="${readonly}" />
                </td>
                <td>
                    <pro3:checkBox name="checkOff-${quoteLineItem?.id}" value="${quoteLineItem?.checkOff}" readonly="${readonly}" />
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</fieldset>
