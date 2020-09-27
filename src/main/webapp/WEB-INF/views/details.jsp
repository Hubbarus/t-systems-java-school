<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="project.entity.enums.ShipmentEnum" %>
<%@ page import="project.entity.enums.PaymentEnum" %>

<html>
<head>
    <title>Payment</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="blocks/header.jsp"></jsp:include>
</header>
<table class="table">
    <form:form action="" modelAttribute="order" method="post">
        <c:forEach items="${items.list}" varStatus="vs">
            <form:hidden path="items[${vs.index}].item.id"></form:hidden>
            <form:hidden path="items[${vs.index}].item.itemName"></form:hidden>
            <form:hidden path="items[${vs.index}].item.itemGroup"></form:hidden>
            <form:hidden path="items[${vs.index}].item.description"></form:hidden>
            <form:hidden path="items[${vs.index}].item.price"></form:hidden>
            <form:hidden path="items[${vs.index}].item.weight"></form:hidden>
            <form:hidden path="items[${vs.index}].item.volume"></form:hidden>
            <form:hidden path="items[${vs.index}].item.stock"></form:hidden>
            <form:hidden path="items[${vs.index}].quantity"></form:hidden>
        </c:forEach>
        <tr>
            <td class="text-black-50 h5">Choose address:</td>
            <td class="form-group">
                <select class="form-control" name="address" required="true">
                    <option value="" selected></option>
                    <c:forEach items="${order.client.addressList}" var="address">
                        <option value="${address}">
                                <c:out value="${address}"></c:out>
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <td class="text-black-50 h5">Choose payment:</td>
        <td class="form-group">
            <select class="form-control" name="paymentMethod" required="true">
                <option value=""></option>
                <c:forEach items="${PaymentEnum.values()}" var="payment">
                    <option>${payment}</option>
                </c:forEach>
            </select>
        </td>
        <tr>
        <td class="text-black-50 h5">Choose shipment:</td>
        <td class="form-group">
            <select class="form-control" name="shipmentMethod" required="true">
                <option value="" selected placeholder="Choose shipment"></option>
                <c:forEach items="${ShipmentEnum.values()}" var="shipment">
                    <option>${shipment}</option>
                </c:forEach>
            </select>
        </td>
        </tr>
        <tr>
            <td class="form-group">Order Details</td>
            <td>
                <table class="table">
                    <tr>
                        <td>Item</td>
                        <td>Price</td>
                        <td>Quantity</td>
                        <td>Total</td>
                    </tr>
                    <c:forEach items="${items.list}" var="item" begin="0" end="${items.list.size()}">
                        <tr>
                            <td>
                                <c:out value="${item.item.itemName} + ${item.item.description}"></c:out>
                            </td>
                            <td>
                                <c:out value="${item.item.price}"></c:out>
                            </td>
                            <td>
                                <c:out value="${item.quantity}"></c:out>
                            </td>
                            <td>
                                <c:set value="${item.quantity}" var="quan"></c:set>
                                <c:set value="${item.item.price}" var="price"></c:set>
                                <c:set value="${quan * price}" var="amount" scope="page"></c:set>
                                <c:out value="${amount}"></c:out>
                                <c:set value="${total + amount}" var="total"></c:set>
                            </td>
                        </tr>

                    </c:forEach>
                    <tr>
                        <td></td>
                        <td>Total: </td>
                        <td>${total}</td>
                        <td></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <a href="/cart/">Back to Cart</a>
            </td>
            <td>
                <button class="btn-dark" type="submit">Place Order</button>
            </td>
        </tr>
    </form:form>
</table>

<footer>
    <jsp:include page="blocks/footer.jsp"></jsp:include>
</footer>
</body>
</html>
