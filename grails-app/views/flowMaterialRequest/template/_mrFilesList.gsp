<h2>Files Uploaded</h2>
<fieldset class="form">
    <table>
        <thead>
        <tr>
            <g:sortableColumn property="file" title="File"/>
        </tr>
        </thead>
        <tbody>
        <g:each in="${files}" var="file" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td><g:link url="${file?.url}" target="_blank">${file?.filename}</g:link></td>
            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="nav" role="navigation">
        <ul>
            <li><g:link class="create" controller="flowFile" action="createFile" id="${materialRequest?.id}">Upload File</g:link></li>
        </ul>
    </div>
</fieldset>