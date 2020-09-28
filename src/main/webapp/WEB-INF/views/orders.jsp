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
    <jsp:include page="blocks/header.jsp"></jsp:include>
</header>
<div class="container align-content-center">
    <p class="text-dark text-md-center h2">${user.firstName}'s orders</p>

    <table class="table">
        <tr>
            <td>Order #</td>
            <td>Details</td>
            <td>Status</td>
        </tr>
        <c:forEach items="${orders}" var="order" begin="0" end="${orders.size()}">
        <tr>
            <td>
                <c:out value="${order.id}"></c:out>
            </td>
            <td>
                <table class="table table-bordered">
                    <tr>
                        <td>Name</td>
                        <td>Quantity</td>
                    </tr>
                        <c:forEach items="${order.items}" begin="0" end="${order.items.size()}" var="item">
                            <tr>
                                <td>
                                    <c:out value="${item.item.itemName}, ${item.item.description}}"></c:out>
                                </td>
                                <td>
                                    <c:out value="${item.quantity}"></c:out>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
                <td><c:out value="${order.status}"></c:out></td>
                </td>
        </tr>
        </c:forEach>
    </table>
    <a href="/client/userInfo/">Back to My Account</a>
</div>

<footer>
    <jsp:include page="blocks/footer.jsp"></jsp:include>
</footer>
</body>
</html>
