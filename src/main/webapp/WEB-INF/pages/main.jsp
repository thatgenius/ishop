<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

<c:set value="0" var="colourAlter"/>
<c:url value="/sp/addToCart" var="addToCart"/>
<c:url value="/sp/cart" var="cart"/>
<c:url value="/login" var="signin"/>
<c:url value="/j_spring_security_logout" var="signout"/>
<c:url value="admin/" var="admin"/>



<table width="100%" border="0" bgcolor="#daa520">
<tr>

    <td align="right" colspan="2" style="padding-right:10px;">
        <security:authorize access="hasRole('ROLE_ADMIN')">
            Hello <security:authentication property="principal.username"/>!
            <a href="${signout}">Sign Out</a>
            <a href="${admin}">admin</a>
        </security:authorize>
        <security:authorize access="hasRole('ROLE_USER')">
            Hello <security:authentication property="principal.username"/>!
            <a href="${signout}">Sign Out</a>
        </security:authorize>
        <security:authorize access="!(hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')) ">
            Hello guest!
            <a href="${signin}">Sign In</a>
        </security:authorize>
    </td>
</tr>
    <tr>

        <td align="right" width="190px" style="padding-right:10px;">
            <div style="text-align:right;">
                <span Id="result">
                    <div style="display:inline;" Id="1">${itemAmount != null ? itemAmount : "0"} items</div>
                    <div style="display:inline;" Id="2">$${totalPrice != null ? totalPrice : "0"}</div>
                </span>
                <div style="display:inline;" Id="3"><a href="${cart}">Cart</a></div>
            </div>
        </td>
    </tr>
</table>
<br/><br/>


<%--coffees table--%>
<table width="100%">
    <tr>
        <td width="8%"></td>
        <td width="84%">

            <table border="0" width="100%">
                <c:forEach items="${resultList}" var="coffee">
                    <tr bgcolor="${((colourAlter=colourAlter+1) % 2 == 0) ? "#FFDEAD" : "#FFDEAD"}">
                        <td>
                                ${coffee.coffeeType}<br/>
                                    <c:if test="${coffee.additives.size() != 0}">
                                        <div>Additives:
                                            <c:forEach items="${coffee.additives}" var="additive" >
                                                ${additive.additiveName}<br/>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                price: ${coffee.price}<br/>
                                <a href="" class="${coffee.coffeeId}" id="addToCart">add to cart</a><br/>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </td>
        <td width="8%"></td>
        </tr>
    </table>
<%--ajax request to add coffee to cart--%>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
    function addToCart(id) {
        $.ajax({
            data: {id: id},
            url: '/sp/addToCart',
            type: 'POST',
            success: function (data) {
                $('#result').html(data);

            }
        });
        return false;
    }
    $("a#addToCart").click(function () {
        var a = this.getAttribute("class");
        return addToCart(this.getAttribute("class"));
    });
</script>


</body>
</html>





