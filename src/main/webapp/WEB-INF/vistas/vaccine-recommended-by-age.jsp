<%--
  Created by IntelliJ IDEA.
  User: rodrigo
  Date: 30/6/2022
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>vaccines recommended for age</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
    />

    <link rel="stylesheet" href="css/common.css">
</head>
<body>
<header class="py-2 header">
    <div class=" container-fluid d-flex align-items-center">
        <h1 class="h2">Viajes seguros</h1>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="${pageContext.request.contextPath}/countries" class="nav-link px-2 text-white">Home</a></li>
            <li>
                <a href="${pageContext.request.contextPath}/nearest-hospitals?lat=-34.70140180538249&long=-57.61267614077974"
                   class="nav-link px-2 text-white ">
                    Hospitales cercanos</a>
            </li>
            <li><a href="${pageContext.request.contextPath}/reserves" class="nav-link px-2 text-white">Mis reservas</a></li>
            <li><a href="${pageContext.request.contextPath}/vaccine-recommended-by-age" class="nav-link px-2 text-white">factor edad</a></li>
            <li><a href="#" class="nav-link px-2 text-white">Vista 5</a></li>
        </ul>
    </div>

</header>

<main class="container my-5">
<form:form action="vaccine-recommended-by-age" method="GET">
    <div>
        <label for="anio" style=" display: block;font: 1rem 'Fira Sans', sans-serif;">Ingrese su año de nacimiento:</label>

        <input type="number" name="anio" id="anio">


    </div>

    <button class="btn btn-primary mt-2">Consultar</button>
</form:form>


<p>Por su edad se le recomienda aplicarse las siguientes vacunas: </p>
<c:forEach items="${vaccine}" var="vaccine">
    <li>
        <c:out value="${vaccine.name}"/>
    </li>
</c:forEach>
</main>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
</body>
</html>
