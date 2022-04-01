<jsp:include page="../includes/header.jsp"/>

<form action="/user/registerSubmit" method="post">
        email<input type="email" name="email" id="emailId"><br>
        First Name<input type="text" name="firstName" id="firstNameId"><br>
        LAst Name<input type="text" name="lastName" id="lastNameId"><br>
        Password<input type="password" name="password" id="passwordId"><br>
        Confirm Password<input type="password" name="passwordConfirm" id="passwordConfirmId">
        <br><br>
        <button type="submit">Submit</button>
    </form>
<jsp:include page="../includes/footer.jsp"/>