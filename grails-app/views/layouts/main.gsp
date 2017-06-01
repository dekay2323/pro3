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

    <g:layoutHead/>
</head>
<body>
    <g:if test="${!g.message(code: 'message.system.wide', default:'false').equals('false')}">
        <div class="alert alert-danger" role="alert">
            <g:message code="message.system.wide" />
        </div>
    </g:if>

   <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/#">
                    Pro3 <sec:ifLoggedIn>[<sec:username/>]</sec:ifLoggedIn> v${System.properties.getProperty("info.app.version")}
                </a>
            </div>
            <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
                <ul class="nav navbar-nav navbar-right">
                    <g:pageProperty name="page.nav" />
                </ul>
            </div>
        </div>
    </div>

    <g:layoutBody/>

    <div class="footer" role="contentinfo"></div>

    <div id="spinner" class="spinner" style="display:none;">
        Loading
    </div>

    <asset:javascript src="application.js"/>

</body>
</html>
