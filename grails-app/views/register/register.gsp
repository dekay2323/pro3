<html>
<head>
	<meta name="layout" content="register"/>
	<s2ui:title messageCode='spring.security.ui.register.title'/>
</head>
<body>
<s2ui:formContainer type='register' focus='username' width='800px'>
	<s2ui:form beanName='registerCommand'>
		<g:if test='${emailSent}'>
		<br/>
		<g:message code='spring.security.ui.register.sent'/>
		</g:if>
		<g:else>
		<br/>

			<g:hasErrors bean="${registerCommand}">
				<ul class="errors" role="alert">
					<g:eachError bean="${registerCommand}" var="error">
						<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
								error="${error}"/></li>
					</g:eachError>
				</ul>
			</g:hasErrors>


			<table>
			<tbody>
			<tr class="prop">
				<td valign="top" class="name">
					<label for="account">Company</label>
					
				</td>
				<td valign="top" class="value ">
					<g:textField name="account" size="40" id="account" value="${account}" />
				</td>
			</tr>
			
			
%{--
			<td valign="top" class="value errors">
				<input type="text" name="username" value="" size="40" id="username">
				<span class="s2ui_error">Property [username] of class [class com.pro3.user.RegisterController$RegisterCommand] cannot be null</span>
			</td>
--}%
			
			<s2ui:textFieldRow name='username' size='40' labelCodeDefault='Username'/>
			<s2ui:textFieldRow name='email' size='40' labelCodeDefault='E-mail'/>
			<s2ui:passwordFieldRow name='password' size='40' labelCodeDefault='Password'/>
			<s2ui:passwordFieldRow name='password2' size='40' labelCodeDefault='Password (again)'/>
			</tbody>
		</table>
		<s2ui:submitButton elementId='submit' messageCode='spring.security.ui.register.submit'/>
		</g:else>
	</s2ui:form>
</s2ui:formContainer>
</body>
</html>
