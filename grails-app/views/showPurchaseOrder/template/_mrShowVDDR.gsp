<h2>Vendor Drawings and Document Requirements</h2>
<fieldset class="form">
    <table>
        <thead>
        <tr>
            <g:sortableColumn property="code" title="Code"/>
            <g:sortableColumn property="number" title="Document Number"/>
            <g:sortableColumn property="description" title="Document Description"/>
            <g:sortableColumn property="copies" title="With Bids #Copies"/>
            <g:sortableColumn property="copiesForReview" title="For Review #Copies"/>
            <g:sortableColumn property="p1" title="Schedule"/>
            <g:sortableColumn property="copiesFinal" title="Certified Final #Copies"/>
        </tr>
        </thead>
        <tbody>
        <g:each in="${materialRequest?.vddrs}" var="vddr" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${vddr.code}</td>
                <td>${vddr.number}</td>
                <td>${vddr.description}</td>
                <td>${vddr.copies}</td>
                <td>${vddr.copiesForReview}</td>
                <td></td>
                <td>${vddr.copiesFinal}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</fieldset>