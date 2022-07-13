<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mis reservas</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
    />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.0/main.min.css">

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
            <li><a href="${pageContext.request.contextPath}/reserves" class="nav-link px-2 text-white">Mis reservas</a>
            </li>
            <li><a href="${pageContext.request.contextPath}/vaccine-recommended-by-age"
                   class="nav-link px-2 text-white">factor edad</a></li>
            <li><a href="#" class="nav-link px-2 text-white">Vista 5</a></li>
        </ul>
    </div>

</header>

<main class="container my-5">

    <c:if test="${empty reserves}">
        <p class="alert alert-danger">Usted no tiene reservas registradas hasta hoy.</p>
    </c:if>


    <c:if test="${not empty reserves}">

        <div>
            <h1 class="h3">Bienvenido ${userName}!</h1>
            <p class="mb-1">Estas son sus reservas hasta la fecha:</p>
        </div>


        <ul class="list-group " style="max-width: 800px">
            <c:forEach items="${reserves}" var="reserve">
                <li class="list-group-item my-4 shadow-sm rounded">
                    <div class="p-2">
                        <p class="mb-2">
                            Su turno esta agendado para el dia: <c:out value="${reserve.dateFormatted}"/>
                        </p>

                        <p class="mb-2">
                            El turno se realizara en el hospital <c:out value="${reserve.hospital.name}"/>
                            con domicilio en <c:out value="${reserve.hospital.address}"/>.
                        </p>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </c:if>


    <section class="mt-5">
        <div id='calendar'></div>
    </section>

</main>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.0/main.min.js"></script>
<script src="js/calendar.js">
</script>
</body>
</html>
