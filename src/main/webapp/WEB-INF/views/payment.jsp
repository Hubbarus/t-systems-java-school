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
<table class="table">
    <form:form action="" modelAttribute="order" method="post">
<%--        <form:hidden path="items" value="${items.list}"></form:hidden>--%>
<%--        Hidden--%>
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
        <td>
            <button class="btn-dark" type="submit">Continue</button>
        </td>
    </tr>
    </form:form>
</table>

</body>
</html>
