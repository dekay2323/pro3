
<h2>Files Uploaded</h2>
<ul>
<g:each in="${files}" var="file">
    <li><g:link url="${file}" target="_blank">${file}</g:link></li>
</g:each>
</ul>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="create" action="createFile" id="${materialRequest?.id}">Upload File</g:link></li>
    </ul>
</div>