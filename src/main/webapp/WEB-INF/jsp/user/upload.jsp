<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../includes/header.jsp"/>
<div class="container">
    <h1>File Upload</h1>

    <form action="/user/uploadSubmit" method="post" enctype="multipart/form-data">

        Subject Name: <input type="text" name="subject"><br>
        File Upload: <input type="file" name="file"><br>
        <button type="submit">Upload</button>

    </form>


</div>
<jsp:include page="../includes/footer.jsp"/>