<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Your cart</h1>
<c:choose>
    <c:when test="${resultMap == null or resultMap.size() == 0}">You haven't bought anything yet</c:when>
    <c:otherwise>
        <table border="1" cellpadding="10px" width="100%" style="border-collapse: collapse">
            <c:forEach items="${resultMap.keySet()}" var="c">
                <tr>
                    <td>${c.getCoffeeType()}</td>
                    <td>${c.getDescription()}</td>
                    <td>${c.getPrice()}</td>
                    <td>${resultMap.get(c)}</td>
                    <td><a href="/sp/delete?id=${c.getCoffeeId()}">remove</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="6">
                    Total: <%= request.getSession().getAttribute("totalPrice") %>
                </td>
            </tr>
        </table>
    </c:otherwise>

</c:choose>

<a href="/" >back to shopping</a>

</body>
</html>
