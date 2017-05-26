<h2>Optional Line Items</h2>
<fieldset class="form">
    <table>
        <thead>
        <tr>
            <g:sortableColumn property="lineItem.description" title="Line Item"/>
            <g:sortableColumn property="description" title="Description"/>
            <g:sortableColumn property="quantity" title="Quantity"/>
            <g:sortableColumn property="unitOfMeasure" title="UoM"/>
            <g:sortableColumn property="price" title="Unit Price"/>
            <g:sortableColumn property="getExtendedPrice" title="Extended Price"/>
            <g:sortableColumn property="leadTime" title="Lead Time (Weeks)" />
        </tr>
        </thead>
        <tbody>
        <g:each in="${quote?.optionLineItems}" var="optionLineItem" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${optionLineItem?.lineItem?.description}</td>
                <td>${optionLineItem?.description}</td>
                <td>${optionLineItem?.quantity}</td>
                <td>${optionLineItem?.unitOfMeasure}</td>
                <td>${optionLineItem?.price}</td>
                <td>${optionLineItem?.getExtendedPrice()}</td>
                <td>${quoteLineItem?.leadTime}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="nav" role="navigation">
        <ul>
            <li><g:link class="create" action="createOptionLineItem" params="[quoteId: quote?.id]">Create Optional Line Item</g:link></li>
        </ul>
    </div>
</fieldset>

