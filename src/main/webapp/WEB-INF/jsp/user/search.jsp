<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../includes/header.jsp"/>


<%--This is grabing the ${users} from the controller, from the method => response.addObject("users", users); <=
Now in the forEach loop we make a local var -> var="user" <- and use that inside the loop ${user.email} and
we are saying the user needs to grab the email for each element in the list that we are going through--%>
<div class="container">
    <h1>Search Page</h1>
    <form action="/user/searchUserName" method="get">
        <input type="text" name="searchName" value="${searchName}">
        <button type="submit">Submit</button>
    </form>
    <div class="row row-cols-auto">
        <table class="table">
            <tr>
                <th scope="col">Email</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td scope="row">${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                </tr>
            </c:forEach>
        </table>

    </div>
</div>


<jsp:include page="../includes/footer.jsp"/>