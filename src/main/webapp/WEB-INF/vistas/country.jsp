<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <title>Title</title>
</head>
<body>

<main class="container">

    <form:form action="show-vaccines" method="GET">
        <label>Seleccione un pais:</label>

        <select name="code" id="code">
            <option value="AR">Argentina</option>
            <option value="IN">India</option>
            <option value="JP">Japon</option>
            <option value="US">Estados Unidos</option>
            <option value="SG">Singapore</option>
        </select>

        <button class="btn btn-default">Consultar</button>
    </form:form>


        <c:forEach items="${vaccines}" var="vaccine">
            <h1>Vacuna: <c:out value="${vaccine.name}"/></h1>
        </c:forEach>


    <c:if test="${not empty notFoundVaccines}">
        <p>No hay vacunas requeridas para entrar al pais seleccionado.</p>
    </c:if>


</main>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
