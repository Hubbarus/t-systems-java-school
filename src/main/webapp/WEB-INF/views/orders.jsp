<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
    <link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

</head>
<body>
<header>
    <jsp:include page="blocks/header.jsp"/>
</header>
<div class="container align-content-center mt-1 w-75">
    <p class="text-dark text-md-center h2 mt-3 mb-2">${user.firstName}'s orders</p>

    <table class="table">
        <tr>
            <td>Order #</td>
            <td>Details</td>
            <td>Status</td>
        </tr>
        <c:forEach items="${orders}" var="order" begin="0" end="${orders.size()}">
        <tr>
            <td>
                <c:out value="${order.orderNo}"/>
            </td>
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
                <td><c:out value="${order.status}"/></td>
                </td>
        </tr>
        </c:forEach>
    </table>
    <a href="/client/userInfo/" class="btn btn-outline-success">Back to My Account</a>
</div>

<footer>
    <jsp:include page="blocks/footer.jsp"/>
</footer>
</body>
</html>
