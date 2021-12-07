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
    <script>
        function change(x) {
            localStorage.setItem('num', x);
            window.location.reload();
        }

        $(document).ready(function () {
            if (localStorage.getItem('num') === null) {
                localStorage.setItem('num', "1");
            }
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/cars/index.do',
                data: {"filter": localStorage.getItem('num')},
                dataType: 'json',
                success: function (data) {
                    let adv = "";
                    for (let advert of data) {
                        console.log(advert)
                        console.log(advert.id)
                        let id = advert.id;
                        let status = "В продаже";
                        if (advert.sold === true) {
                            status = "Продано";
                        }
                        adv += "<tr><td>" + id + "</td>"
                            + "<td><img src='<c:url value='/download?name='/>"
                            + advert.photoPath + "' width='130px' height='100px'/></td>"
                            + "<td>" + advert.brand + " " + advert.model + "</td>"
                            + "<td><a href=<c:url value='/adv.do?" + id + "'/>>"
                            + advert.description + "</a></td>"
                            + "<td>" + status + "</td>"
                            + "<td>" + advert.price + "</td>"
                            + "<td>" + advert.created + "</td>"
                            + "</tr>";
                    }
                    $('#tb').html(adv);
                }
            })
        });
    </script>
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
                <div class="row">
                    <label for="filter">Отобразить: </label>
                    <select class="form-control" id="filter" name="filter" onchange="change(value)"
                            title="Фильтр ">
                        <option value="1">Все объявления</option>
                        <option value="2">За последние 24ч</option>
                    </select>
                </div>
            </div>
            <div class="card-body">
                <table class="table" id="table" name="table">
                    <thead>
                    <tr>
                        <th scope="col">Номер</th>
                        <th scope="col">Фото</th>
                        <th scope="col">Марка</th>
                        <th scope="col">Описание</th>
                        <th scope="col">Статус</th>
                        <th scope="col">Цена</th>
                        <th scope="col">Дата</th>
                    </tr>
                    </thead>
                    <tbody id="tb">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <br>
    <c:if test="${user != null}">
        <div class="row">
            <form action="photoUpload.jsp" method="get">
                <button type="submit" class="btn btn-success">Добавить объявление</button>
            </form>
        </div>
        <br>
    </c:if>
</div>
</body>
</html>