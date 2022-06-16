<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: rodrigo
  Date: 15/6/2022
  Time: 03:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="css/common.css">
</head>
<body>
<header class="py-2 header">
    <div class=" container-fluid d-flex align-items-center">
        <h1 class="h2">Viajes seguros</h1>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="${pageContext.request.contextPath}/countries" class="nav-link px-2 text-white">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/all-hospitals" class="nav-link px-2 text-white ">All</a></li>
            <li><a href="${pageContext.request.contextPath}/nearest-hospitals?lat=-34.70140180538249&long=-57.61267614077974" class="nav-link px-2 text-white ">nearest</a></li>
            <li><a href="#" class="nav-link px-2 text-white ">Vista 4</a></li>
            <li><a href="#" class="nav-link px-2 text-white ">Vista 5</a></li>
        </ul>
    </div>


</header>

<main class="container my-5">

     <h1>lista de vacunatorios</h1>

     <c:forEach items="${hospital}" var="hospital">
         <li>${hospital["name"]}</li>
     </c:forEach>


     <c:if test="${not empty error}">
         <h4><span>${error}</span></h4>
         <br>
     </c:if>
     ${msg-error}

    </main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>

</body>
</html>
