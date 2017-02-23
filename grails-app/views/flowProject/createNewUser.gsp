<html>
<head>
    <meta name="layout" content="${layoutRegister}"/>
    <s2ui:title messageCode='spring.security.ui.register.title'/>
</head>

<body>
<s2ui:formContainer type='register' focus='username' width='800px'>
    <g:form action="saveNewUser">
        <g:hiddenField name="projectId" value="${project?.id}"/>
        <g:if test='${emailSent}'>
            <br/>
            <g:message code='spring.security.ui.register.sent'/>
        </g:if>
        <g:else>
            <br/>
            <table>
                <tbody>
                <g:field type="text" name='email' size='40' labelCodeDefault='E-mail'/>
                <g:field type="password" name='password' size='40' labelCodeDefault='Password'/>
                <g:field type="password" name='password2' size='40' labelCodeDefault='Password (again)'/>
                </tbody>
            </table>
            <g:submitButton name="create" class="save" value="SAVE"/>
        </g:else>
    </g:form>
</s2ui:formContainer>
</body>
</html>
