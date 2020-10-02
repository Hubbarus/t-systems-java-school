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
<div class="container align-content-center mt-5 w-100 text-center">
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
            <td></td>
        </tr>
        <c:forEach items="${orders}" var="order" begin="0" end="${orders.size()}">
            <form:form id="orderForm" name="orderForm" modelAttribute="thisOrder" action="/manage/orders/edit/" method="post">
                <tr>
<%--                    Order #--%>
                    <td><c:out value="${order.orderNo}"/></td>
<%--                    Order Details--%>
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
<%--                    Client--%>
                    <td><c:out value="${order.client.firstName} ${order.client.lastName}"/></td>
<%--                    Address--%>
                    <td><c:out value="${order.address.postcode}, ${order.address.country}, ${order.address.city}, ${order.address.street}, ${order.address.building}, ${order.address.apart}"/></td>
<%--                    Shipment--%>
                    <td><c:out value="${order.shipmentMethod.value}"/></td>
<%--                    Payment Method--%>
                    <td><c:out value="${order.paymentMethod.value}"/></td>
<%--                    Payment Status--%>
                    <c:choose>
                        <c:when test="${order.paymentStatus == true}">
                            <td class="alert-success text-center">
                                <div class="form-check">
                                    <form:checkbox path="paymentStatus"
                                                class="form-check-input position-static"
                                                id="blankCheckbox"
                                                value="${order.paymentMethod}"
                                                aria-label="..." checked="true"/>
                                </div>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td class="alert-warning text-center">
                                <div class="form-check">
                                    <form:checkbox path="paymentStatus"
                                                class="form-check-input position-static"
                                                id="blankCheckbox"
                                                value="${order.paymentStatus}"
                                                aria-label="..."/>
                                </div>
                            </td>
                        </c:otherwise>
                    </c:choose>
<%--                    Subtotal--%>
                    <td><c:out value="${order.subtotal}"/></td>
<%--                    Status--%>
                    <td>
                        <form:input path="id" type="hidden" name="id" value="${order.id}"/>
                        <div class="input-group mb-3">
                            <form:select path="status" class="btn btn-secondary btn-sm dropdown-toggle">
                                <option selected>${order.status}</option>
                                <c:forEach items="${StatusEnum.values()}" var="statusType">
                                    <option value="${statusType}">
                                        <c:out value="${statusType.toString()}"/>
                                    </option>
                                </c:forEach>
                            </form:select>
                        </div>
                    </td>
<%--                    Submit button--%>
                    <td>
                        <button type="submit" class="btn btn-outline-secondary">Submit</button>
                    </td>
                </tr>
            </form:form>
        </c:forEach>
    </table>
    <%--        Navigation--%>
    <div class="container-fluid mt-2 mb-2 text-center align-content-center">
        <jsp:include page="../blocks/orderPaging.jsp"/>
    </div>
</div>

<footer>
    <jsp:include page="../blocks/footer.jsp"/>
</footer>
</body>
</html>
