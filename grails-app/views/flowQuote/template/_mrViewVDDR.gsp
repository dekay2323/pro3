<h2>Vendor Drawings and Document Requirements</h2>
<fieldset class="form">
    <table>
        <thead>
        <tr>
            <td>Code</td>
            <td>Document Number</td>
            <td>Document Description</td>
            <td>With Bids #Copies</td>
            <td>For Review #Copies</td>
            <td>Schedule</td>
            <td>Certified Final #Copies</td>
            <td>Schedule</td>
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
                <td></td>
            </tr>
        </g:each>
        </tbody>
    </table>
</fieldset>