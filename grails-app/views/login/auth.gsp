<html>
<head>
	<title>Pro3</title>
	<link href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/cerulean/bootstrap.min.css" rel="stylesheet" integrity="sha384-zF4BRsG/fLiTGfR9QL82DrilZxrwgY/+du4p/c7J72zZj+FLYq4zY00RylP9ZjiT" crossorigin="anonymous">
</head>

<body>
<br />
<br />
<div class="container">
	<div class="row">
		<div class="col-sm-4"></div>			
		<div class="col-sm-4">
			<div class="well bs-component">
				<form class="form-horizontal" action="${postUrl ?: '/login/authenticate'}" method="POST" id="loginForm" autocomplete="off">
				<fieldset>
					<legend>Login</legend>
					<div class="form-group">
						<div class="col-sm-12">
							<input type="text" class="form-control" id="username" placeholder="Username" name="${usernameParameter ?: 'username'}" >
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<input type="text" class="form-control" id="password" placeholder="Password" name="${passwordParameter ?: 'password'}" >
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="submit" class="btn btn-primary">Submit</button>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<input type="checkbox" class="chk" name="${rememberMeParameter ?: 'remember-me'}" id="remember_me" <g:if test='${hasCookie}'>checked="checked"</g:if>/>
							<label for="remember_me" class="control-label">Remember Me</label>
						</div>
					</div>
				</fieldset>
			</form>
			</div>
		</div>
		<div class="col-sm-4"></div>
	</div>

	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<div class="form-group">
				<div class="col-sm-12">
					<g:link class="btn btn-success" controller="register" action="register">Register New Account</g:link>
				</div>
			</div>
		</div>
		<div class="col-sm-4"></div>
	</div>
</div>