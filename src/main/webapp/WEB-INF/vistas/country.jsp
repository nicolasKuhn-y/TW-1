<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

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

<main class="container mt-5 mb-auto">

    <form:form action="countries" method="GET">
        <div>
            <label class="d-block">Seleccione un pais:</label>


            <select name="code" id="code">

                <c:forEach items="${countries}" var="country">
                    <option value="<c:out value="${country.code}"/>">
                        <c:out value="${country.name}"/>
                    </option>
                </c:forEach>

            </select>
        </div>


        <button class="btn btn-primary mt-2">Consultar</button>
    </form:form>


    <section class="container">

        <c:if test="${not empty error}">
            <p class="alert alert-danger">${error}</p>
        </c:if>

        <div class="my-2 py-2">

            <c:if test="${not empty notFoundVaccinesRequired}">
                <p class="alert alert-danger">${notFoundVaccinesRequired}</p>
            </c:if>

            <c:if test="${not empty requiredVaccines}">
                <h2 class="mb-4">Las vacunas requeridas para entrar al pais son las siguientes:</h2>

                <div class="row row-cols-1 row-cols-md-3 g-4">


                    <c:forEach items="${requiredVaccines}" var="vaccine">

                        <div class="col">
                            <div class="card" style="width: 300px">
                                <img src="css/vacuna.svg" class="card-img-top p-4" alt="vacuna">
                                <div class="card-body">
                                    <h5 class="card-title"><c:out value="${vaccine.name}"/></h5>
                                    <p class="card-text">
                                        Tiene que aplicarse hasta <c:out value="${vaccine.totalInyections}"/> dosis y
                                        se recomienda un tiempo de descanso de aproximadamente
                                        <c:out value="${vaccine.totalInyections}"/>
                                        dias entre aplicaciones.
                                    </p>
                                </div>
                            </div>
                        </div>

                    </c:forEach>

                </div>
            </c:if>
        </div>


        <div class="my-2 py-2">

            <c:if test="${not empty notFoundVaccinesRecommended}">
                <p class="alert alert-danger">${notFoundVaccinesRecommended}</p>
            </c:if>

            <c:if test="${not empty recommendedVaccines}">
                <h2 class="mb-4">Las vacunas recomendadas para entrar al pais son las siguientes:</h2>

                <div class="row row-cols-1 row-cols-md-3 g-4">


                    <c:forEach items="${recommendedVaccines}" var="vaccine">
                        <div class="col">
                            <div class="card" style="width: 300px">
                                <img src="css/vacuna.svg" class="card-img-top p-4" alt="vacuna">
                                <div class="card-body">
                                    <h5 class="card-title"><c:out value="${vaccine.name}"/></h5>
                                    <p class="card-text">
                                        Tiene que aplicarse hasta <c:out value="${vaccine.totalInyections}"/> dosis y
                                        se recomienda un tiempo de descanso de aproximadamente
                                        <c:out value="${vaccine.totalInyections}"/>
                                        dias entre aplicaciones.
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>


                </div>
            </c:if>
        </div>


    </section>

</main>

<footer class=" text-center text-lg-start" style="background-color: #3fbbc0">

    <div class="text-center text-white p-3">
        <p>2022 Copyright: Taller web I </p>
    </div>

</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
<script src="js/localization.js"></script>
</body>
</html>
