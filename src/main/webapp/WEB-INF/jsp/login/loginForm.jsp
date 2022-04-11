<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../includes/header.jsp"/>

<form action="/login/loginSubmit" method="post">
    Username: <input type="text" name="username"><br>
    Password: <input type="text" name="password">
    
    <br>
    <br>
    <button type="submit">Login</button>
</form>

<jsp:include page="../includes/footer.jsp"/>

