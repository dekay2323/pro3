<div class="nav" role="navigation">
    <ul>
        <li><a href="${createLink(uri: '/')}">
            <i class="fa fa-home fa-lg" aria-hidden="true"></i> Home</a>
        </li>
        <li><g:link controller="listProject" action="index">Project List</g:link></li>
        <li><g:link controller="listRfq" action="index">Rfq List</g:link></li>
        <li><g:link controller="listAllQuotes" action="index">Quotes List</g:link></li>
        <li>
            <sec:ifLoggedIn>
                <g:link controller='logoff'><i class="fa fa-sign-out fa-lg" aria-hidden="true"></i> Logout</g:link>
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
                <g:link controller='login' action='auth'>Login</g:link>
            </sec:ifNotLoggedIn>
        </li>


    </ul>
</div>