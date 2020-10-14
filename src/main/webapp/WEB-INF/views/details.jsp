<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="project.entity.enums.ShipmentEnum" %>
<%@ page import="project.entity.enums.PaymentEnum" %>

<html>
<head>
    <title>Payment</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon">
</head>
<body>
<header>
    <jsp:include page="blocks/header.jsp"/>
</header>
<div class="container w-50 mt-5 mb-3">
    <div class="header mt-3 mb-2">
        <c:out value="Order details"/>
    </div>
    <table class="table mb-2">
        <form:form action="" modelAttribute="order" method="post" id="detailsForm" onsubmit="return onSubmit();">
<%--            Init--%>
            <c:forEach items="${items.list}" varStatus="vs">
                <form:hidden path="items[${vs.index}].item.id"/>
                <form:hidden path="items[${vs.index}].item.itemName"/>
                <form:hidden path="items[${vs.index}].item.itemGroup"/>
                <form:hidden path="items[${vs.index}].item.description"/>
                <form:hidden path="items[${vs.index}].item.price"/>
                <form:hidden path="items[${vs.index}].item.weight"/>
                <form:hidden path="items[${vs.index}].item.volume"/>
                <form:hidden path="items[${vs.index}].item.stock"/>
                <form:hidden path="items[${vs.index}].quantity"/>
            </c:forEach>
<%--            Address--%>
            <tr>
                <td>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="addressSelector">Delivery address:</label>
                        </div>
                        <form:select path="address" onchange="onChangeAddressAction(event)" class="form-control" required="true" id="addressSelector"/>
                    </div>
                </td>
            </tr>
<%--            Payment--%>
            <tr>
                <td>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="paymentSelector">Payment</label>
                        </div>
                        <form:select path="paymentMethod" class="custom-select" id="paymentSelector" required="true">
                            <option selected>Choose...</option>
                            <c:forEach items="${PaymentEnum.values()}" var="payment">
                                <option value="${payment}"><c:out value="${payment.toString()}"/></option>
                            </c:forEach>
                        </form:select>
                    </div>
                </td>
            </tr>
<%--            Shipment--%>
            <tr>
                <td>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="shipmentSelector">Shipment</label>
                        </div>
                        <form:select path="shipmentMethod" class="custom-select" id="shipmentSelector" required="true">
                            <option selected>Choose...</option>
                            <c:forEach items="${ShipmentEnum.values()}" var="shipment">
                                <option value="${shipment}">
                                    <c:out value="${shipment.toString()}"/>
                                </option>
                            </c:forEach>
                        </form:select>
                    </div>
                </td>
            </tr>
<%--            Order details--%>
            <tr>
                <td class="text-center align-content-center">
                    <div class="container mt-1 mb-1"><strong><c:out value="Items in order"/></strong></div>
                    <table class="table table-bordered mt-1 mb-1 border-info">
                        <tr>
                            <td>Item</td>
                            <td>Price</td>
                            <td>Quantity</td>
                            <td>Total</td>
                        </tr>
                        <c:forEach items="${items.list}" var="item" begin="0" end="${items.list.size()}">
                            <tr>
                                <td>
                                    <c:out value="${item.item.itemName}"/>
                                </td>
                                <td>
                                    <c:out value="${item.item.price}"/>
                                </td>
                                <td class="align-content-center">
                                    <c:out value="${item.quantity}"/>
                                </td>
                                <td>
                                    <c:set value="${item.quantity}" var="quan"/>
                                    <c:set value="${item.item.price}" var="price"/>
                                    <c:set value="${quan * price}" var="amount" scope="page"/>
                                    <c:out value="${amount}"/>
                                    <c:set value="${total + amount}" var="total"/>
                                </td>
                            </tr>

                        </c:forEach>
                        <tr>
                            <td></td>
                            <td></td>
                            <td>Total: </td>
                            <td><strong>${total}</strong></td>
                        </tr>
                    </table>
                </td>
            </tr>
<%--            Button--%>
            <tr>
                <td class="align-content-md-end">
                    <button class="btn btn-outline-danger" type="submit">Place Order</button>
                </td>
            </tr>
        </form:form>
    </table>
    <a href="/cart/" class="btn btn-outline-success">Back to Cart</a>
</div>
<footer>
    <jsp:include page="blocks/footer.jsp"/>
</footer>

<script type="text/javascript">
    var addressSelector = document.getElementById('addressSelector');
    var addressOptions = [
        {
            label: 'Choose...',
            value: '#',
        },
        {
            label: 'Add new',
            value: 'add new',
        },
    ];

    <c:forEach items="${order.client.addressList}" var="address">
        var address = {
            label: '${address.postcode}, ${address.country}, ${address.city}, ${address.street}, ${address.building}, ${address.apart}',
            value: '${address}',
        };

        addressOptions.push(address);
    </c:forEach>

    function onSubmit() {
        var form = document.forms['detailsForm'];
        var addressTag = form.elements["addressSelector"];
        var payTag = form.elements["paymentSelector"];
        var shipTag = form.elements["shipmentSelector"];

        if (addressTag.value === 'Choose...' || payTag.value === 'Choose...' || shipTag.value === 'Choose...') {
            alert('Please check input!');
            return false;
        } else {
            form.method = 'post';
            form.action = '';
            form.submit();
            return true;
        }
    }

    function render() {
        addressSelector.innerHTML = '';

        for (var i = 0; i < addressOptions.length; i += 1) {
            var currentOption = addressOptions[i];

            var optionElement = document.createElement('option');
            optionElement.value = currentOption.value;
            optionElement.innerHTML = currentOption.label;

            addressSelector.append(optionElement);
        }

        if (addressSelector.selected == null) {
            addressSelector.selected = 'Choose...';
        }
    }

    function onChangeAddressAction(event) {
        if (event.target.value === "add new") {
            location.href = "/client/userInfo/manageAddress?action=add";
        }
    }

    render();
</script>
</body>
</html>
