<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <script src="https://use.fontawesome.com/8aa838ee1c.js"></script>

    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>

    <g:layoutHead/>
</head>
<body>
<g:if test="${!g.message(code: 'message.system.wide', default:'false').equals('false')}">
    <div class="alert alert-danger" role="alert">
        <g:message code="message.system.wide" />
    </div>
</g:if>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${createLink(uri: '/')}">Procurable</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Extra <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <sec:ifLoggedIn>
                            <li><g:link controller='logoff'>Logout</g:link></li>
                        </sec:ifLoggedIn>
                    </ul>
                </li>
                <g:render template="/template/contact" />
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<g:layoutBody/>

<div class="footer" role="contentinfo">
    <sec:username/> <span class="footer-right"><g:meta name="info.app.version"/></span>
</div>

<div id="spinner" class="spinner" style="display:none;">
    Loading
</div>
</body>
</html>
