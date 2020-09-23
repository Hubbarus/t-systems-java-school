<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${cart.item.itemName}</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<div class="tab">
    <div class="alert-info">
        <p class="badge">Item name: </p>
            <c:out value="${cart.item.itemName}"></c:out><br>
        <p class="badge">Description: </p>
            <c:out value="${cart.item.description}"></c:out><br>
        <p class="badge">Price: </p>
            <c:out value="${cart.item.price}"></c:out><br>
        <p class="badge">Weight: </p>
            <c:out value="${cart.item.weight}"></c:out><br>
        <p class="badge">Volume : </p>
            <c:out value="${cart.item.volume}"></c:out><br>
        <p class="badge">In Stock now: </p>
            <c:out value="${cart.item.stock}"></c:out><br>
    </div>
</div>
<div class="btn-link">
    <form:form method="post" modelAttribute="cart" name="itemForm" action="/cart/">
        <form:hidden path="id"></form:hidden>
        <form:hidden path="item.itemName"></form:hidden>
        <form:hidden path="item.itemGroup"></form:hidden>
        <form:hidden path="item.description"></form:hidden>
        <form:hidden path="item.price"></form:hidden>
        <form:hidden path="item.weight"></form:hidden>
        <form:hidden path="item.volume"></form:hidden>
        <form:hidden path="item.stock"></form:hidden>
        <form:input type="number" path="quantity" min="1" max="${cart.item.stock}"></form:input>
        <input type="submit" value="Add to cart"/>
    </form:form>
</div>
</body>
</html>
