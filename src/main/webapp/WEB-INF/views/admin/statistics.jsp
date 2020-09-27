<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Statistics</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="../blocks/header.jsp"></jsp:include>
</header>

<table class="table align-middle">
    <tr>
        <td>
            <table class="table-bordered border-light">
                <tr>
                    <td>Item Name</td>
                    <td>Total Quantity</td>
                </tr>
                <c:forEach items="${topItems}" var="item" begin="0" end="${topItems.size()}">
                    <tr>
                        <td>
                            <c:out value="${item.item.itemName}, ${item.item.itemGroup}"></c:out>
                        </td>
                        <td>
                            <c:out value="${item.quantity}"></c:out>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <td>
            <table class="table-bordered border-light">
                <tr>
                    <td>Client Name</td>
                    <td>Total Quantity of Orders</td>
                </tr>
                <c:forEach items="${topClients}" var="client" begin="0" end="${topClients.size()}">
                    <tr>
                        <td>
                            <c:out value="${client.key.firstName} ${client.key.lastName}"></c:out>
                        </td>
                        <td>
                            <c:out value="${client.value}"></c:out>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <td>
            Form by Dates
        </td>
    </tr>
</table>

<footer>
    <jsp:include page="../blocks/footer.jsp"></jsp:include>
</footer>
</body>
</html>
