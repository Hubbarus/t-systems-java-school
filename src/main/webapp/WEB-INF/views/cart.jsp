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
</head>
<body>
<header>
    <jsp:include page="blocks/header.jsp"></jsp:include>
</header>
<c:choose>
    <c:when test="${items.list.size() == 0}">
        <span class="ui-state-error-text">
            No items in cart
        </span>
        <a href="/shop/" class="btn-dark">Go shopping</a>
    </c:when>
    <c:otherwise>
        <table class="table-bordered">
            <tr>
                <td>Item</td>
                <td>Quantity</td>
                <td>Price</td>
                <td></td>
            </tr>
            <c:forEach items="${items.list}" var="i" begin="0" end="${items.list.size()}">
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
                    <td>
                        <a href="/cart/removeItem?itemId=${i.item.id}" class="btn alert-danger">X</a>
                    </td>
            </tr>
            </c:forEach>
            <tr>
                <td>
                    <form:form action="/pay/" method="get" modelAttribute="items">
                        <c:forEach items="${items.list}" varStatus="vs">
                            <form:hidden path="list[${vs.index}].item.id"></form:hidden>
                            <form:hidden path="list[${vs.index}].item.itemName"></form:hidden>
                            <form:hidden path="list[${vs.index}].item.itemGroup"></form:hidden>
                            <form:hidden path="list[${vs.index}].item.description"></form:hidden>
                            <form:hidden path="list[${vs.index}].item.price"></form:hidden>
                            <form:hidden path="list[${vs.index}].item.weight"></form:hidden>
                            <form:hidden path="list[${vs.index}].item.volume"></form:hidden>
                            <form:hidden path="list[${vs.index}].item.stock"></form:hidden>
                            <form:hidden path="list[${vs.index}].quantity"></form:hidden>
                            <form:hidden path="subtotal" value="${total}"></form:hidden>
                        </c:forEach>
                        <button type="submit" class="btn-success">Go pay</button>
                    </form:form>
                </td>
                <td>Total:</td>
                <td>
                <c:out value="${total}"></c:out>
                </td>
            </tr>
        </table>
        <a href="/shop/" class="btn-success h5">Go back to shop</a>
        </c:otherwise>
</c:choose>

<footer>
    <jsp:include page="blocks/footer.jsp"></jsp:include>
</footer>
</body>
</html>
