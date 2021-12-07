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
    <script src="js/create.js"></script>
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
                <form action="<%=request.getContextPath()%>/create.do?photo=<%=request.getAttribute("photo")%>"
                      method="post">
                    <div class="form-group">
                        <label for="category">Категория: </label>
                        <select class="form-control" id="category" name="category" title="Выберите категорию">
                            <option>Select</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="brand">Марка: </label>
                        <select class="form-control" id="brand" name="brand" title="Выберите марку"
                                onchange="setModelList(value)">
                            <option value="0">Select</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="model">Модель: </label>
                        <select class="form-control" id="model" name="model" title="Выберите модель">
                            <option>Select</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="body">Тип кузова: </label>
                        <select class="form-control" id="body" name="body" title="Выберите тип кузова">
                            <option>Select</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="description">Описание:</label>
                        <textarea id="description" name="description" class="form-control"
                                  title="Введите описание" rows="3"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="price">Цена: </label>
                        <input type="text" class="form-control" id="price" name="price"
                               title="Укажите цену" placeholder="Цена">
                    </div>
                    <button type="submit" class="btn btn-success" name="rowCell"
                            onclick="return validate();"> Подтвердить
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
