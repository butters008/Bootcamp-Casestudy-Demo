<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="includes/header.jsp"/>
<h1>Hello</h1>
${userList}
<ul>
    <:forEach var="obj" items="${userList}">
        <li>${obj}</li>
    </:forEach>
</ul>


<jsp:include page="includes/header.jsp"/>