<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="project.entity.enums.StatusEnum" %>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon">
</head>
<body>
<header>
    <jsp:include page="../blocks/header.jsp"/>
</header>
<div class="container align-content-center mt-5">
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
            <form:form id="orderForm" name="orderForm" modelAttribute="thisOrder" action="/manage/orders/edit/" method="post">
                <tr>
                    <td><c:out value="${order.orderNo}"/></td>
                    <td>
                        <table class="table table-bordered">
                            <tr>
                                <td>Item</td>
                                <td>Quantity</td>
                            </tr>
                            <c:forEach items="${order.items}" begin="0" end="${order.items.size()}" var="item">
                            <tr>
                                <td>
                                    <c:out value="${item.item.itemName}"/>
                                </td>
                                <td>
                                    <c:out value="${item.quantity}"/>
                                </td>
                            </tr>
                            </c:forEach>
                        </table>
                    </td>
                    <td><c:out value="${order.client.firstName} ${order.client.lastName}"/></td>
                    <td><c:out value="${order.address.postcode}, ${order.address.country}, ${order.address.city}, ${order.address.street}, ${order.address.building}, ${order.address.apart}"/></td>
                    <td><c:out value="${order.shipmentMethod.value}"/></td>
                    <td><c:out value="${order.paymentMethod.value}"/></td>
                    <c:choose>
                        <c:when test="${order.paymentStatus == true}">
                        <td class="alert-success">
                            <c:out value="Paid"/>
                        </td>
                        </c:when>
                        <c:otherwise>
                            <td class="alert-warning">
                                <c:out value="Unpaid"/>
                            </td>
                        </c:otherwise>
                    </c:choose>
                    <td><c:out value="${order.subtotal}"/></td>
                    <td><c:out value="${order.status.toString()}"/></td>
                    <td>
                        <form:input path="id" type="hidden" name="id"/>
                        <form:select path="status" onchange="submitOrderForm(${order.id})">
                            <form:option value="${order.status}"/>
                            <c:forEach items="${StatusEnum.values()}" var="statusType">
                                <form:option value="${statusType}"/>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
            </form:form>
        </c:forEach>
    </table>
</div>

<script type="text/javascript">
    function submitOrderForm(id) {
        var form = document.forms['orderForm'];
        var idTag = form.elements["id"];
        idTag.value = id;
        form.action = '/manage/orders/edit/';
        form.submit();
    }
</script>

<footer>
    <jsp:include page="../blocks/footer.jsp"/>
</footer>
</body>
</html>
