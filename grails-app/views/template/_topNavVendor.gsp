<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">Home</a></li>
        <li>
            <sec:ifLoggedIn>
                <g:link class="home" controller='logoff'>Logout</g:link>
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
                <g:link class="home" controller='login' action='auth'>Login</g:link>
            </sec:ifNotLoggedIn>
        </li>


    </ul>
</div>