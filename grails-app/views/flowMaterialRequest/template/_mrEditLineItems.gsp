<br />
<h2>Scope of Supply <span class="required-indicator">*</span></h2>
    <table>
        <thead>
        <tr>
            <g:sortableColumn property="code" title="Line ID" />
            <g:sortableColumn property="wbs" title="WBS" />
            <g:sortableColumn property="name" title="Name" />
            <g:sortableColumn property="quantity" title="Qty" />
            <g:sortableColumn property="unitOfMeasure" title="UoM" />
        </tr>
        </thead>
        <tbody>
        <g:each in="${materialRequest?.lineItems}" var="mr" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${mr.code}</td>
                <td>${mr.wbs}</td>
                <td>${mr.name}</td>
                <td>${mr.quantity}</td>
                <td>${mr.unitOfMeasure}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="nav" role="navigation">
        <ul>
            <li>
                <g:actionSubmit value="Edit Line Items" action="clickEditLineItems" class="create" />
            </li>
        </ul>
    </div>

