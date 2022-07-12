<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: rodrigo
  Date: 30/6/2022
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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

<c:forEach items="${vaccine}" var="vaccine">


        <li>
            <c:out value="${vaccine.name}"/>
        </li>


</c:forEach>

<c:if test="${not empty error}">
    <p class="">${error}</p>
</c:if>
</body>
</html>
