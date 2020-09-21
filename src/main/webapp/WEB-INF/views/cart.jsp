<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: paulponomarev
  Date: 2020-09-20
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Cart</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<c:choose>
    <c:when test="${items.size() == 0}">
        <span class="ui-state-error-text">
            No items in cart
        </span>
        <a href="/shop" class="btn-dark">Go shopping</a>
    </c:when>
    <c:otherwise>
        <table class="table-bordered">
            <tr>
                <td>Item</td>
                <td>Quantity</td>
                <td>Price</td>
            </tr>
            <c:forEach items="${items}" var="i" begin="0" end="${items.size()}">
            <tr>
                    <td>
                        <c:out value="${i.item.itemName},"></c:out>
                        <c:out value="${i.item.itemGroup},"> </c:out>
                        <c:out value="${i.item.description}"></c:out>
                    </td>
                    <td>
                        <c:out value="${i.quantity}"></c:out>
                    </td>
                    <td>
                        <c:set value="${i.quantity}" var="quan"></c:set>
                        <c:set value="${i.item.price}" var="price"></c:set>
                        <c:set value="${quan * price}" var="amount" scope="page"></c:set>
                        <c:out value="${amount}"></c:out>
                        <c:set value="${total + amount}" var="total"></c:set>

                    </td>
                </c:forEach>
            </tr>
            <tr>
                <td></td>
                <td>Total:</td>
                <td>
                <c:out value="${total}"></c:out>
                </td>
            </tr>
        </table>
    </c:otherwise>
</c:choose>
<a href="/shop/pay" class="btn-success">Go to payment page</a>
<a href="/shop" class="btn-success">Go back to shop</a>

</body>
</html>
