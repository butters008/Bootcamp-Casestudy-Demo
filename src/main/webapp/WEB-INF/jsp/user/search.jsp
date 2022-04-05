<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../includes/header.jsp"/>

<h1>Search Page</h1>
<%--This is grabing the ${users} from the controller, from the method => response.addObject("users", users); <=
Now in the forEach loop we make a local var -> var="user" <- and use that inside the loop ${user.email} and
we are saying the user needs to grab the email for each element in the list that we are going through--%>
<table>
    <tr>
        <th>Email</th>
        <th>First Name</th>
        <th>Last Name</th>
    </tr>
<c:forEach items="${users}" var="user">
    <tr>
        <td>${user.email}</td>
        <td>${user.firstName}</td>
        <td>${user.firstName}</td>
    </tr>
</c:forEach>
</table>

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