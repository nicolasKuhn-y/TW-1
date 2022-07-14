<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="css/common.css">

</head>
<body>
<main class="container mt-5" style="max-width: 500px">

    <form:form class="mt-3" action="validar-login" method="POST" modelAttribute="user">

        <div class="form-outline mb-4">
            <label class="form-label" for="email">Email</label>
            <form:input path="email" id="email" type="email" class="form-control"/>
        </div>


        <div class="form-outline mb-4">
            <label class="form-label" for="password">Contrasena</label>
            <form:input path="password" type="password" id="password" class="form-control"/>
        </div>

        <button type="submit" class="btn btn-block text-white mb-4 w-100" style="background-color: #3fbbc0">
            Loguearse
        </button>

        <c:if test="${not empty error}">
            <p class="alert alert-danger">${error}</p>
        </c:if>

    </form:form>

</main>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
</body>
</html>
