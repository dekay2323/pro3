<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                                data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">Pro3 <sec:ifLoggedIn>[<sec:username/>]</sec:ifLoggedIn></a>
                    </div>

                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="${createLink(uri: '/')}">Home<span
                                    class="sr-only">(current)</span></a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                   aria-expanded="false">Actions <span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><g:link controller="listProject" action="index">Project List</g:link></li>
                                    <li><g:link controller="listRfq" action="index">Rfq List</g:link></li>
                                    <li class="divider"></li>
                                    <li><g:link controller="listAllQuotes" action="index">Quotes List</g:link></li>
                                </ul>
                            </li>
                        </ul>

                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <sec:ifLoggedIn><g:link controller='logoff'>Logout</g:link></sec:ifLoggedIn>
                                <sec:ifNotLoggedIn><g:link controller='login'
                                                           action='auth'>Login</g:link></sec:ifNotLoggedIn>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </div>
</div>
