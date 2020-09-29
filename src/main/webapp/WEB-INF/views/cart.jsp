<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon">
</head>
<body>
<header>
    <jsp:include page="blocks/header.jsp"/>
</header>
<div class="container mt-5 w-75">
<c:choose>
    <c:when test="${items.list.size() == 0}">
        <div class="alert alert-info text-center">
            No items in cart
        </div>
        <br>
        <div class="container text-center">
            <a href="/shop/" class="btn btn-outline-success">Visit shop</a>
        </div>
    </c:when>
    <c:otherwise>
        <div class="container align-content-center">
        <table class="table">
            <tr>
                <td>Item</td>
                <td>Quantity</td>
                <td>Price</td>
                <td></td>
            </tr>
            <c:forEach items="${items.list}" var="i" begin="0" end="${items.list.size()}">
            <tr>
<%--    Name--%>
                <td class="text-center">
                    <c:out value="${i.item.itemName},"/>
                    <c:out value="${i.item.itemGroup}"/>
                </td>
<%--    Quantity--%>
                <td>
                    <c:out value="${i.quantity}"/>
                </td>
<%--    Total--%>
                <td>
                    <c:set value="${i.quantity}" var="quan"/>
                    <c:set value="${i.item.price}" var="price"/>
                    <c:set value="${quan * price}" var="amount" scope="page"/>
                    <c:out value="${amount}"/>
                    <c:set value="${total + amount}" var="total"/>
                </td>
<%--    Remove button--%>
                <td>
                    <div class="btn btn-outline-danger">
                        <a href="/cart/removeItem?itemId=${i.item.id}">X</a>
                    </div>
                </td>
            </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td>Total:</td>
                <td>
                <c:out value="${total}"/>
                </td>
                <td>
                    <form:form action="/pay/" method="get" modelAttribute="items">
                        <c:forEach items="${items.list}" varStatus="vs">
                            <form:hidden path="list[${vs.index}].item.id"/>
                            <form:hidden path="list[${vs.index}].item.itemName"/>
                            <form:hidden path="list[${vs.index}].item.itemGroup"/>
                            <form:hidden path="list[${vs.index}].item.description"/>
                            <form:hidden path="list[${vs.index}].item.price"/>
                            <form:hidden path="list[${vs.index}].item.weight"/>
                            <form:hidden path="list[${vs.index}].item.volume"/>
                            <form:hidden path="list[${vs.index}].item.stock"/>
                            <form:hidden path="list[${vs.index}].quantity"/>
                            <form:hidden path="subtotal" value="${total}"/>
                        </c:forEach>
                        <button type="submit" class="btn btn-outline-success">Go to payment page</button>
                    </form:form>
                </td>
            </tr>
        </table>
        </div>
        <br>
        <a href="/shop/" class="btn btn-outline-success">Back to shop</a>
        </c:otherwise>
</c:choose>
</div>

<footer>
    <jsp:include page="blocks/footer.jsp"/>
</footer>
</body>
</html>
