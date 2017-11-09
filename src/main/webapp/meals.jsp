<%--
  Created by IntelliJ IDEA.
  User: macin
  Date: 07.11.2017
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>

<head>
    <title>MealsWithExceed</title>


    <style>
        .normal {color: green;}
        .exceed {color: red;}

        table {
            border-collapse: collapse;
            width: 50%;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even){background-color: #f2f2f2}

        th {
            background-color: #4CAF50;
            color: white;
        }

    </style>

</head>

<body>
<section>
<h3><a href="index.html">Home</a></h3>
<h4>Список еды</h4>
<a  href="meals?action=add">Добавить запись еды в таблицу</a>
<hr/>
<table>

    <thead>
    <tr>
    <!--    <th>Индекс</th> -->
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th>Изменить</th>
        <th>Удалить</th>
    </tr>
    </thead>

    <tbody>

    <c:forEach items="${meals}" var="meal" >

       <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>

       <tr class="${meal.exceed ? 'exceed' : 'normal'}">
<!--
            <td > <c:out value="${meal.id}"/> </td>
 -->           <td>
                <javatime:format value="${meal.dateTime}" style="MS" />
            </td>
            <td><c:out value="${meal.description}"/> </td>
            <td><c:out value="${meal.calories}" /></td>
            <td><a href="meals?action=update&id=${meal.id}">Изменить</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Удалить</a></td>
        </tr>

    </c:forEach>
    </tbody>
</table>
</section>
</body>

</html>
