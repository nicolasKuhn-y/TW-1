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

<main class="container my-4 py-4 px-2 shadow" style="background-color: #FFFBFA;">

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

        <form action="${pageContext.request.contextPath}/reserves/${hospital.id}" method="POST"
              style="max-width: 400px">

            <div class="form-row">
                <div class="form-group col-md-12 my-2">
                    <label for="date">Fecha</label>
                    <input id="date" type="date" name="date" class="form-control"/>
                </div>
                <div class="form-group col-md-12 my-2">
                    <label for="time">Hora</label>
                    <input id="time" type="time" name="time" class="form-control"/>
                </div>
            </div>
            <button type="submit" class="btn btn-primary w-100 my-2">Agendar Turno</button>
        </form>

    </section>


    <section>


        <div class="row d-flex justify-content-center">
            <div class="col-md-11 col-lg-9 col-xl-7">


                <div class="my-5 py-5 text-dark">
                    <div class="card">
                        <div class="card-body p-4">
                            <div class="d-flex flex-start w-100">
                                <img class="rounded-circle shadow-1-strong me-3"
                                     src="${pageContext.request.contextPath}/images/avatar.jpg" alt="avatar"
                                     width="65"
                                     height="65"/>

                                <form action="${pageContext.request.contextPath}/comments/${hospital.id}" class="w-100">
                                    <div class="form-outline">
                                        <label class="mb-2" for="textArea">Dejar un comentario:</label>
                                        <textarea class="form-control" id="textArea" name="description"
                                                  rows="4"></textarea>
                                    </div>

                                    <div class="d-flex justify-content-between mt-3">
                                        <button type="submit" class="btn text-white bold"
                                                style="background-color: #3fbbc0; width: 100px">
                                            Enviar
                                        </button>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>

                <c:if test="${not empty comments}">
                    <c:forEach items="${comments}" var="comment">

                        <div class="d-flex flex-start mb-4">
                            <img class="rounded-circle shadow-1-strong me-3"
                                 src="${pageContext.request.contextPath}/images/avatar.jpg" alt="avatar" width="65"
                                 height="65"/>
                            <div class="card w-100">
                                <div class="card-body p-4">
                                    <div class="">
                                        <h5>${comment.author.name}</h5>
                                        <p class="small">Publicado hace ${comment.dayOfCreation} dias</p>
                                        <p>
                                                ${comment.description}
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </c:forEach>
                </c:if>

            </div>
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
<script src="${pageContext.request.contextPath}/js/localization.js"></script>
</body>
</html>
