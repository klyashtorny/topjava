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

<h3><a href="index.html">Home</a></h3>
<h4>Список еды</h4>

<table>
    <thead>
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>

    </tr>
    </thead>
    <tbody style="color: green">
    <c:forEach items="${meals}" var="meal" >
        <tr <c:if test="${meal.exceed==true}"> style="color: red" </c:if>>
            <td>
                <javatime:format value="${meal.dateTime}" style="MS" />
            </td>
            <td  ><c:out value="${meal.description}"/> </td>
            <td><c:out value="${meal.calories}" /></td>

        </tr>

    </c:forEach>
    </tbody>
</table>

</body>

</html>
