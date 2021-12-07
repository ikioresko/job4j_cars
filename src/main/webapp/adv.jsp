<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <title>Cars adv</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <style>
        .block {
            width: 200px;
            display: inline-block;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link"
                   href="<%=request.getContextPath()%>/index.jsp">Главная</a>
            </li>
            <c:if test="${user == null}">
                <li class="nav-item">
                    <a class="nav-link"
                       href="<%=request.getContextPath()%>/login.jsp">Войти</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"
                       href="<%=request.getContextPath()%>/reg.jsp">Регистрация</a>
                </li>
            </c:if>
            <c:if test="${user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/logout.do">
                        <c:out value="${user.username}"/> | Выйти</a>
                </li>
            </c:if>
        </ul>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <div class="block"> Номер объявления: <c:out value="${advert.id}"/></div>
                <c:set var="advertUserId"><c:out value="${advert.author.id}"/></c:set>
                <c:if test="${user.id == advertUserId}">
                    <div class="block"><a href='<c:url value="/sold.do?id=${advert.id}"/>'>
                        Снять с продажи</a>
                    </div>
                    <div class="block"><a href='<c:url value="/update.do?id=${advert.id}"/>'>
                        Редактировать</a>
                    </div>
                </c:if>
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Фото</th>
                        <th scope="col">Характеристики</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr>
                        <td class="block">
                            <img src="<c:url value="/download?name="/><c:out value="${advert.photoPath}"/>"
                                 width="340px" height="250px" alt="photo"/>
                        </td>
                        <td>
                            <table class="table table-borderless">
                                <tr>
                                    <td>
                                        Марка: <c:out value="${advert.brand.name}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Модель: <c:out value="${advert.model.name}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Кузов: <c:out value="${advert.body.name}"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Цена: <c:out value="${advert.price}"/> руб</th>
                    </tr>
                    </thead>
                    <thead>
                    <tr>
                        <th scope="col">Описание</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><c:out value="${advert.description}"/></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>