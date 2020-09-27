<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Main</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

    <style>
        .ul-li-item {
            color: #924d07;
            font-size: 14px;
            font-weight: 400;
            line-height: 24px;
            margin-left: 20px;
        }

    </style>

</head>
<body>
<div class="container-md">
    <h2>Greetings,
        <sec:authorize access="isAuthenticated()">
            ${user.firstName}
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            ${user}
        </sec:authorize>
    !</h2><br>

    <c:if test="${param.get('success').equals('yes')}">
        <div class="alert alert-success">
        <c:out value="Order has been placed successfully"></c:out>
        </div>
    </c:if>

<ul>
    <li class="ul-li-item">
    <div class="container align-content-center">
        <a href="/shop/" class="text-dark text-md-center h4">View Shop</a>
    </div>
    </li>

    <li class="ul-li-item">
    <div class="container align-content-center">
        <a href="/cart/" class="text-dark text-md-center h4">Cart</a>
    </div>
    </li>

    <sec:authorize access="!isAuthenticated()">
    <li class="ul-li-item">
        <div class="container align-content-center">
            <a href="/client/registration/" class="text-dark text-md-center h4">Registration</a>
        </div>
    </li>

    <li class="ul-li-item">
        <div class="container align-content-center">
            <a href="/login" class="text-dark text-md-center h4">Log In</a><br>
        </div>
    </li>
    </sec:authorize>


    <sec:authorize access="hasRole('ADMIN') || hasRole('USER')">
        <sec:authorize access="hasRole('USER')">
        <li class="ul-li-item">
            <div class="container align-content-center">
                <a href="/client/userInfo/" class="text-dark text-md-center h4">My account</a>
            </div>
        </li>
        </sec:authorize>
        <sec:authorize access="hasRole('ADMIN')">
            <li class="ul-li-item">
                <div class="container align-content-center">
                    <a href="/manage/" class="text-dark text-md-center h4">Manage</a>
                </div>
            </li>
        </sec:authorize>
    <li class="ul-li-item">
        <div class="container align-content-center">
            <a href="/logout" class="text-dark text-md-center h4">Log Out</a>
        </div>
    </li>

    </sec:authorize>
</ul>
</div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>
