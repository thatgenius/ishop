<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>

<a href="/">Go home</a>
<a href="/admin/">Go back to list</a><br/><br/>

  <f:form modelAttribute="coffeeCreateForm" action="createNewCoffee" method="POST">
    coffee type:  <f:select path="coffeeType" items="${coffeeTypeList}"/> <br/>
    description: <f:input path="description"/> <br/>
    price: <f:input path="price" /> <br/>
    additive type:  <f:select path="additiveType" items="${additiveTypeList}"/> <br/>
    <%--additive type:  <f:select path="additiveType" items="${additiveTypeList}"/> <br/>--%>
    <input type="submit" value="create new coffee"/>
  </f:form>

</body>
</html>
