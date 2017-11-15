<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Users</h2>
    <a href="users?action=create">Добавить пользователя</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Имя</th>
            <th>Почта</th>
            <th>Пароль</th>
            <th>Норма Калорий</th>
            <th>Активный</th>
            <th>Зарегестрирован</th>
            <th>Удалить</th>
            <th>Сохранить</th>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" scope="page" type="ru.javawebinar.topjava.model.User"/>
            <tr>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.caloriesPerDay}</td>
                <td>${user.enabled}</td>
                <td>${user.registered.toLocaleString()}</td>

                <td><a href="users?action=update&id=${user.id}">Изменить</a></td>
                <td><a href="users?action=delete&id=${user.id}">Удалить</a></td>
            </tr>
        </c:forEach>
    </table>
</section>



</body>
</html>