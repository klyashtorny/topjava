<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }
        .exceeded {
            color: red;
        }
        .centercol {
            position: relative; /* Относительное позиционирование */
            width: 100px;
            padding: 3px; /* Поля вокруг текста */
            margin: 5px 20px 0 330px; /* Отступы вокруг блока */
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Еда AuthorizedUser.id():</h2>

    <form method="post" action="meals?action=filter">
        <table border="0" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>От даты</th>
                <th><input type="date" value="${sd}" name="startDate"></th>
                <th>От времени</th>
                <td><input type="time" value="${st}" name="startTime"></td>
            </tr>
            </thead>

            <th>До даты</th>
            <th><input type="date" value="${ed}" name="endDate"></th>
            <th>До времени</th>
            <td><input type="time" value="${et}" name="endTime"></td>

        </table>
        <button class="centercol" type="submit">Filter</button>
    </form>

    <a href="meals?action=create">Add Meal</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>