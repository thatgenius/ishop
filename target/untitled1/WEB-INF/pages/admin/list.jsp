<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<a href="/">Go home</a>
<a href="/admin/create">Create new coffee</a>

<table border="1" cellpadding="10px" width="100%" style="border-collapse: collapse">
    <c:forEach items="${resultList}" var="c">
        <tr>
            <td>${c.getCoffeeId()}</td>
            <td>${c.getCoffeeType()}</td>
            <td>${c.getDescription()}</td>
            <td>${c.getPrice()}</td>
            <td><a href="/admin/delete?id=${c.getCoffeeId()}">delete</a></td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
