<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>

<header class="py-2 header">
    <div class=" container-fluid d-flex align-items-center">
        <h1 class="h2">Viajes seguros</h1>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="${pageContext.request.contextPath}/countries" class="nav-link px-2 text-white">Home</a></li>
            <li>
                <a id="coordinates" href="${pageContext.request.contextPath}/nearest-hospitals"
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

<main class="container my-4 py-4">

    <c:if test="${not empty error}">
        <p class="alert alert-danger">${error}</p>
    </c:if>

    <section style="max-width: 1000px">
        <div>
            <h1>
                Hospital <c:out value="${hospital.name}"/>
            </h1>

            <img src="${pageContext.request.contextPath}${hospital.imageUrl}" width="500" alt=""/>
        </div>

        <div class="my-4" style="max-width: 650px">
            <p>
                <c:out value="${hospital.description}"/>
            </p>

            <p>
                Turnos disponibles para sacar:
                <c:out value="${hospital.appointmentsAmount}"/>
            </p>
        </div>

        <form action="${pageContext.request.contextPath}/reserves/${hospital.id}" method="POST">
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="date">Fecha</label>
                    <input id="date" type="date" name="date" class="form-control"/>
                </div>
                <div class="form-group col-md-2">
                    <label for="time">Hora</label>
                    <input id="time" type="time" name="time" class="form-control"/>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Agendar Turno</button>
        </form>

    </section>
</main>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
<script src="js/localization.js"></script>

</body>
</html>
