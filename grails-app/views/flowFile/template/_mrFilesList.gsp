
<h2>Files Uploaded</h2>
<fieldset class="form">
    <table>
        <thead>
        <tr>
            <g:sortableColumn property="name" title="File Name"/>
        </tr>
        </thead>
        <tbody>
        <g:each in="${files}" var="file" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td><g:link url="${file?.url}" target="_blank">${file?.name}</g:link></td>
            </tr>
        </g:each>
        </tbody>
    </table>
</fieldset>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="create" controller="flowFile" action="createFile" id="${materialRequest?.id}">Done</g:link></li>
    </ul>
</div>
