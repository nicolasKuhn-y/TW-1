<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <li><a href="#" class="nav-link px-2 text-white ">Vista 2</a></li>
            <li><a href="#" class="nav-link px-2 text-white ">Vista 3</a></li>
            <li><a href="#" class="nav-link px-2 text-white ">Vista 4</a></li>
            <li><a href="#" class="nav-link px-2 text-white ">Vista 5</a></li>
        </ul>
    </div>


</header>

<main class="container my-5">

    <form:form action="show-vaccines" method="GET">
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

        <div class="row row-cols-1 row-cols-md-3 g-4">

            <c:forEach items="${vaccines}" var="vaccine">

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

    </section>


    <c:if test="${not empty notFoundVaccines}">
        <p class="mt-2">No hay vacunas requeridas para entrar al pais seleccionado.</p>
    </c:if>


</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>--%>
<%--<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>--%>
<%--<script src="js/bootstrap.min.js" type="text/javascript"></script>--%>
</body>
</html>
