<h2>Optional Line Items</h2>
<fieldset class="form">
    <table>
        <thead>
        <tr>
            <td>Description</td>
            <td>Unit Price</td>
            <td>Ship Date</td>
        </tr>
        </thead>
        <tbody>
        <g:each in="${quote?.optionLineItems}" var="optionLineItem" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${optionLineItem?.description}</td>
                <td>${optionLineItem?.price}</td>
                <td>${optionLineItem?.shipDate}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</fieldset>
<div class="nav" role="navigation">
    <ul>
        <li><g:link class="create" action="createOptionLineItem" params="[quoteId: quote?.id]">Create Optional Line Item</g:link></li>
    </ul>
</div>
