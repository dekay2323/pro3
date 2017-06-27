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

<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="http://www.procurableapp.com">Procurable</a>
        </div>

        <sec:ifLoggedIn>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><g:link controller="listProject" action="index">Project</g:link></li>
                    <li><g:link controller="listRfq" action="index">Request for Quote</g:link></li>
                    <li><g:link controller="listPurchaseOrder" action="index">Purchase Order</g:link></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Extra <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><g:link controller="listAllQuotes" action="index">Act as Vendor</g:link></li>
                            <li><g:link controller="showOverview" action="index">Overview</g:link></li>
                            <li><g:link controller='logoff'>Logout</g:link></li>
                        </ul>
                    </li>
                    <li><g:link controller="contact" view="index">Contact</g:link></li>
                </ul>
            </div>
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li><g:link controller="contact" view="index">Contact</g:link></li>
                </ul>
            </div>
        </sec:ifNotLoggedIn>
    </div>
</nav>

<g:layoutBody/>

<div class="footer" role="contentinfo">
    <sec:username/> <span class="footer-right"><g:meta name="info.app.version"/></span>
</div>

<div id="spinner" class="spinner" style="display:none;">
    Loading
</div>

<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
            (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
        m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-100280276-4', 'auto');
    ga('send', 'pageview');

</script>
</body>
</html>
