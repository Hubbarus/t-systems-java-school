<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="project.entity.enums.StatusEnum" %>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<div class="container align-content-center">
    <table class="table">
        <tr>
            <td>Order #</td>
            <td>Details</td>
            <td>Client</td>
            <td>Address</td>
            <td>Shipment</td>
            <td>Payment</td>
            <td>Payment Status</td>
            <td>Total</td>
            <td>Status</td>
            <td>Change Status</td>
        </tr>
        <c:forEach items="${orders}" var="order" begin="0" end="${orders.size()}">
        <tr>
            <td><c:out value="${order.id}"></c:out></td>
            <td>
                <table class="table table-bordered">
                    <tr>
                        <td>Item</td>
                        <td>Quantity</td>
                    </tr>
                    <c:forEach items="${order.items}" begin="0" end="${order.items.size()}" var="item">
                    <tr>
                        <td>
                            <c:out value="${item.item.itemName}, ${item.item.description}"></c:out>
                        </td>
                        <td>
                            <c:out value="${item.quantity}"></c:out>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </td>
            <td><c:out value="${order.client.firstName} ${order.client.lastName}"></c:out></td>
            <td><c:out value="${order.address}"></c:out></td>
            <td><c:out value="${order.shipmentMethod.value}"></c:out></td>
            <td><c:out value="${order.paymentMethod.value}"></c:out></td>
            <c:choose>
                <c:when test="${order.paymentStatus == true}">
                    <td class="alert-success">
                        <c:out value="Paid"></c:out>
                    </td>
                </c:when>
                <c:otherwise>
                    <td class="alert-warning">
                        <c:out value="Unpaid"></c:out>
                    </td>
                </c:otherwise>
            </c:choose>
            <td><c:out value="${order.subtotal}"></c:out></td>
            <td><c:out value="${order.status.toString()}"></c:out></td>
            <td>
                <form:form id="orderForm" name="orderForm" modelAttribute="thisOrder" action="/manage/orders/edit/" method="post">
                    <form:input path="id" type="hidden" value="${order.id}"></form:input>
                    <form:select path="status" onchange="x()">
                        <form:option value="${order.status}"></form:option>
                        <c:forEach items="${StatusEnum.values()}" var="statusType">
                            <form:option value="${statusType}"></form:option>
                        </c:forEach>
                    </form:select>
                </form:form>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>

<script type="text/javascript">
    function x() {
        document.getElementById('orderForm').action = '/manage/orders/edit/';
        document.getElementById('orderForm').submit();
    }
</script>

</body>
</html>
