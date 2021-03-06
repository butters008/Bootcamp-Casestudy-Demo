<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <title>Document</title>
</head>
<body>
<nav class="navbar navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="/index">Index</a> |
        <a class="navbar-brand" href="/ajax">Ajax</a> |
        <a class="navbar-brand" href="/user/register">Register</a> |
        <sec:authorize access="hasAuthority('ADMIN')">
            <a class="navbar-brand" href="/user/search">Search</a> |
            <a class="navbar-brand" href="/user/upload">File Upload</a> |
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <a class="navbar-brand" href="/login/login">login</a> |
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <a class="navbar-brand" href="/login/logout">logout</a>
            | <sec:authentication property="principal.username"/>
        </sec:authorize>
    </div>

</nav>
<hr>