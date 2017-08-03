<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
</head>
<body>

<div id="stats" class="content scaffold-list" role="main">
    <div class="container">
        <br />
        <table class="table-bordered">
            <thead>
            <tr>
                <th>Username</th>
                <th>Email</th>
                <th>Company Name</th>
                <th>Contact Name</th>
                <th>Phone Number</th>
                <th>Account</th>
                <th>Clients</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${userList}" var="user">
                <tr>
                    <td>${user?.username}</td>
                    <td>${user?.email}</td>
                    <td>${user?.companyName}</td>
                    <td>${user?.contactName}</td>
                    <td>${user?.phoneNumber}</td>
                    <td>${user?.account?.name}</td>
                    <td>${user?.account?.clients}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </fieldset>
    </div>
</body>
</html>