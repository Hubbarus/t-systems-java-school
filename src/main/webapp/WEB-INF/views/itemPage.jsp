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
    <link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <style>
        .td {
            max-width: 350px;
        }
    </style>
</head>
<body>
<header>
    <jsp:include page="blocks/header.jsp"/>
</header>
<div class="container mt-5 ml-5 mr-5 align-content-center">
    <c:if test="${cart.item.stock == 0}">
        <div class="alert alert-danger w-50 text-center align-content-center">
            <c:out value="Sorry, this item out of stock now!"/>
        </div>
    </c:if>
    <table class="table">
        <tr>
            <td class="align-content-center w-50">
                <img src="${cart.item.pathToIMG}" width="350">
            </td>
            <td class="align-content-md-center">
                <div>
                    <dl class="row">
                        <dt class="col-sm-3">Item name:</dt>
                        <dd class="col-sm-9">
                            <c:out value="${cart.item.itemName}"/>
                        </dd>
                    </dl>
                    <dl class="row">
                        <dt class="col-sm-3">Description:</dt>
                        <dd class="col-sm-9 td">
                            <c:out value="${cart.item.description}"/>
                        </dd>
                    </dl>
                    <dl class="row">
                        <dt class="col-sm-3">Price:</dt>
                        <dd class="col-sm-9">
                            <c:out value="${cart.item.price} $"/>
                        </dd>
                    </dl>
                    <dl class="row">
                        <dt class="col-sm-3">Weight:</dt>
                        <dd class="col-sm-9">
                            <c:out value="${cart.item.weight}"/>
                        </dd>
                    </dl>
                    <dl class="row">
                        <dt class="col-sm-3">Volume:</dt>
                        <dd class="col-sm-9">
                            <c:out value="${cart.item.volume}"/>
                        </dd>
                    </dl>
                    <dl class="row">
                        <dt class="col-sm-3">In Stock now:</dt>
                        <dd class="col-sm-9">
                            <c:out value="${cart.item.stock}"/>
                        </dd>
                    </dl>
                    <dl class="row">
                        <dt class="col-sm-3">Choose quantity</dt>
                        <dd class="col-sm-9">
                            <div class="btn-link">
                                <form:form method="post" modelAttribute="cart" name="itemForm" action="/cart/">
                                    <form:hidden path="id"/>
                                    <form:hidden path="item.id"/>
                                    <form:hidden path="item.itemName"/>
                                    <form:hidden path="item.itemGroup"/>
                                    <form:hidden path="item.description"/>
                                    <form:hidden path="item.price"/>
                                    <form:hidden path="item.weight"/>
                                    <form:hidden path="item.volume"/>
                                    <form:hidden path="item.stock"/>
                                    <div class="input-group mb-3">
                                        <form:input path="quantity"  min="1" max="${cart.item.stock}" type="number" class="form-control" aria-label="Quantity"/>
                                        <div class="input-group-append">
                                            <span class="input-group-text">pcs</span>
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-outline-success">Add to cart</button>
                                </form:form>
                            </div>
                        </dd>
                    </dl>
                </div>
            </td>
        </tr>
    </table>
</div>
<footer>
    <jsp:include page="blocks/footer.jsp"/>
</footer>
</body>
</html>
