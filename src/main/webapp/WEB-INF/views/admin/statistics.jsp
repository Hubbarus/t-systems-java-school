<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Statistics</title>

    <link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="../blocks/header.jsp"/>
</header>

<table class="table align-middle">
    <tr>
<%--        Top Items--%>
        <td>
            <table class="table-bordered border-light">
                <tr>
                    <td>Item Name</td>
                    <td>Total Quantity</td>
                </tr>
                <c:forEach items="${topItems}" var="item" begin="0" end="${topItems.size()}">
                    <tr>
                        <td>
                            <c:out value="${item.item.itemName}, ${item.item.itemGroup}"/>
                        </td>
                        <td>
                            <c:out value="${item.quantity}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
<%--        Top Clients--%>
        <td>
            <table class="table-bordered border-light">
                <tr>
                    <td>Client Name</td>
                    <td>Total Quantity of Orders</td>
                </tr>
                <c:forEach items="${topClients}" var="client" begin="0" end="${topClients.size()}">
                    <tr>
                        <td>
                            <c:out value="${client.key.firstName} ${client.key.lastName}"/>
                        </td>
                        <td>
                            <c:out value="${client.value}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
<%--    Proceeds--%>
        <td>
            <c:if test="${err != null}">
                <div class="alert alert-danger">
                    <c:out value="${err}"/>
                </div>
            </c:if>

            <form:form method="post" modelAttribute="statDateForm" action="">
                <table class="table table-bordered border-light">
                    <tr>
                        <td>
                            <p>Enter From Date</p>
                            <form:input type="date" path="from" required="true"/>
                        </td>
                        <td>
                            <p>Enter From Date</p>
                            <form:input path="to" type="date" required="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button type="submit" class="btn-success">Submit</button>
                        </td>
                    </tr>
                </table>
            </form:form>

            <br>

            <c:if test="${statDateForm.orders.size() != 0}">
                <table class="table table-bordered border-light">
                    <tr>
                        <h2 class="alert alert-info">Total procceeds: ${statDateForm.proceeds}</h2>
                    </tr>
                    <tr>
                        <td>Date</td>
                        <td>Order #</td>
                        <td>Subtotal</td>
                    </tr>
                    <c:forEach var="order" items="${statDateForm.orders}">
                        <tr>
                            <td>
                                <c:out value="${order.date}"/>
                            </td>
                            <td>
                                <c:out value="${order.orderNo}"/>
                            </td>
                            <td>
                                <c:out value="${order.subtotal}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </td>
    </tr>
</table>

<footer>
    <jsp:include page="../blocks/footer.jsp"/>
</footer>
</body>
</html>
