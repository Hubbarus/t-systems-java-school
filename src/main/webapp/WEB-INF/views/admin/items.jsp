<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>

    <link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="../blocks/header.jsp"></jsp:include>
</header>
<div class="container-sm">
    <a href="/manage/editItem" class="btn btn-outline-dark" role="button" aria-pressed="true">Add new</a>
</div>
<table class="table">
    <tr>
        <td>#</td>
        <td>Name</td>
        <td>Group</td>
        <td>Description</td>
        <td>Weight</td>
        <td>Volume</td>
        <td>Price</td>
        <td>Quantity in stock</td>
        <td>Image</td>
        <td></td>
    </tr>
    <c:forEach items="${allItems}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.itemName}</td>
            <td>${item.itemGroup}</td>
            <td>${item.description}</td>
            <td>${item.weight}</td>
            <td>${item.volume}</td>
            <td>${item.price}</td>
            <td>${item.stock}</td>
            <td>
                <img src="${item.pathToIMG}" size="30">
            </td>
            <td>
                <form:form action="/manage/editItem" modelAttribute="itemToEdit" method="get">
                    <form:input path="id" type="hidden" value="${item.id}"/>
                    <form:input path="itemName" type="hidden" value="${item.itemName}"/>
                    <form:button type="submit" class="btn btn-outline-dark">Edit</form:button>
                </form:form>
            </td>
        </tr>
    </c:forEach>
</table>

<footer>
    <jsp:include page="../blocks/footer.jsp"/>
</footer>
</body>
</html>
