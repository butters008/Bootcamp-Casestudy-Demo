<%--
    We need the to have the this tag inside EACH page (not inside the header) This will allow us
    to use functions and statments within the .jsp page.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../includes/header.jsp"/>

<%--
    This is a standard jsp if statment.  We can redo the check as ${form.id == null}
--%>
<c:if test="${empty form.id}">
    <h1>Sign Up</h1>
</c:if>
<c:if test="${not empty form.id}">
    <h1>Edit</h1>
</c:if>

<form action="/user/registerSubmit" method="post">
    <input type="hidden" name="id" value="${form.id}">

    <%--    email<input type="text" name="email" id="emailId" value="${form.email}"><br>--%>
    email<input type="text" name="email" id="emailId"><br>
    <c:forEach items='${bindingResult.getFieldErrors("email")}' var="error">
        <div style="color: red;">${error.getDefaultMessage()}</div>
    </c:forEach>

    First Name<input type="text" name="firstName" id="firstNameId" value="${form.firstName}"><br>
    <c:forEach items='${bindingResult.getFieldErrors("firstName")}' var="error">
        <div style="color: red;">${error.getDefaultMessage()}</div>
    </c:forEach>

    Last Name<input type="text" name="lastName" id="lastNameId" value="${form.lastName}"><br>
    <c:forEach items='${bindingResult.getFieldErrors("lastName")}' var="error">
        <div style="color: red;">${error.getDefaultMessage()}</div>
    </c:forEach>

    Password<input type="password" name="password" id="passwordId" value="${form.password}"><br>
    <c:forEach items='${bindingResult.getFieldErrors("password")}' var="error">
        <div style="color: red;">${error.getDefaultMessage()}</div>
    </c:forEach>

    Confirm Password<input type="password" name="passwordConfirm" id="passwordConfirmId" value="${form.password}">
    <c:forEach items='${bindingResult.getFieldErrors("passwordConfirm")}' var="error">
        <div style="color: red;">${error.getDefaultMessage()}</div>
    </c:forEach>

    <br><br>
    Check Box<input type="checkbox" name="checkbox">
    <br><br>
    <button type="submit">Submit</button>
</form>

<%--<c:if test="${not empty formErrors}">--%>
<p>This is a test</p>

<%--</c:if>--%>
<jsp:include page="../includes/footer.jsp"/>