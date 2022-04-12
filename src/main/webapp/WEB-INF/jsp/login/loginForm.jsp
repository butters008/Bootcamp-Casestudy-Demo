<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../includes/header.jsp"/>
<%--
This page will come up whenever the the user is not logged in
--%>
<div class="container">
    <form class="form-group" action="/login/loginSubmit" method="post">
        Username: <input class="form-control" type="text" name="username"><br>
        Password: <input class="form-control" type="text" name="password">

        <br>
        <br>
        <button class="btn btn-primary" type="submit">Login</button>
    </form>
</div>
<jsp:include page="../includes/footer.jsp"/>

