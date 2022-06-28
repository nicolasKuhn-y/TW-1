<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <a href="${pageContext.request.contextPath}/nearest-hospitals?lat=-34.70140180538249&long=-57.61267614077974"
                   class="nav-link px-2 text-white ">Hospitales cercanos</a></li>
            <li><a href="#" class="nav-link px-2 text-white ">Vista 3</a></li>
            <li><a href="#" class="nav-link px-2 text-white ">Vista 4</a></li>
            <li><a href="#" class="nav-link px-2 text-white ">Vista 5</a></li>
        </ul>
    </div>
</header>

<main class="container">


    <div class="row m-5">

        <c:if test="${not empty error}">
            <p class="alert alert-danger">${error}</p>
        </c:if>


        <c:forEach items="${hospitals}" var="hospital">
            <div class="col">
                <div class="card mx-2 animate__fadeInUp">
                    <img src="${pageContext.request.contextPath}${hospital.imageUrl}" height="180" class="card-img-top"
                         alt="foto hospital ${hospital.name}">

                    <div class="card-body">
                        <h5 class="card-title">${hospital.name}</h5>
                        <p class="card-text">
                            Email de contacto: ${hospital.email}

                        </p>

                        <p class="card-text">
                            Direccion: ${hospital.address}
                        </p>

                        <p class="card-text">${hospital.description}</p>

                        <a href="${pageContext.request.contextPath}/hospitals/${hospital.id}" class="btn btn-primary">Ver
                            detalle</a>
                    </div>

                </div>
            </div>
        </c:forEach>


    </div>


</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
</body>
</html>
