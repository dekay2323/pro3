<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">Home</a></li>
        <li><g:link class="list" controller="listProject" action="index">Material Request</g:link></li>
        <li><g:link class="list" controller="listRfq" action="index">Request for Quote</g:link></li>
        <li><g:link class="list" controller="listPurchaseOrder" action="index">Purchase Order</g:link></li>
        <li><g:link controller="listAllQuotes" action="index">Act as Vendor</g:link></li>
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