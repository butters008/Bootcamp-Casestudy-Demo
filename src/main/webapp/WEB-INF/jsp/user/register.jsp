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
    email<input type="email" name="email" id="emailId" value="${form.email}"><br>
    First Name<input type="text" name="firstName" id="firstNameId" value="${form.firstName}"><br>
    LAst Name<input type="text" name="lastName" id="lastNameId" value="${form.lastName}"><br>
    Password<input type="password" name="password" id="passwordId" value="${form.password}"><br>
    Confirm Password<input type="password" name="passwordConfirm" id="passwordConfirmId" value="${form.password}">
    <br><br>
    <button type="submit">Submit</button>
</form>

<table>
    <tr>
        <th>Error Key</th>
        <th>Error Value</th>
    </tr>
    <c:forEach items="${errors}" var="error">
        <tr>
            <td>${error.key}</td>
            <td>${error.value}</td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="../includes/footer.jsp"/>