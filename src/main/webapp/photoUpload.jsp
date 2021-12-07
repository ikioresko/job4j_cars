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
        function validate() {
            var x = Boolean(true);
            if ($('#photo').val() === "") {
                alert($('#photo').attr('title'));
                x = false;
            }
            return x;
        }
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
                Новое объявление
            </div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/upload.do" method="post"
                      enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="photo">Загрузить фото: </label>
                        <br>
                        <input type="file" id="photo" name="photo" title="Загрузите фото">
                    </div>
                    <button type="submit" class="btn btn-success" name="rowCell" onclick="return validate();">
                        Загрузить
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
