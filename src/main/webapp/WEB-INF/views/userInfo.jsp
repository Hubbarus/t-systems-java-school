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

    <title>My Account</title>

    <link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>

<header>
    <jsp:include page="blocks/header.jsp"/>
</header>



<div class="container align-content-center mt-3 mb-3 w-50">
    <c:if test="${successMsg != null}">
        <div class="alert alert-success text-center mt-3 mb-3" role="alert">
            <c:out value="${successMsg}"/>
        </div>
    </c:if>

    <h1 class="mt-1 mb-1">Greetings, ${client.firstName}!</h1>
    <br>
    <table class="table">
        <tr>
            <td class="text-black-50 h5">Name:</td>
            <td class="text-black-50 h5">${client.firstName}</td>
        </tr>
        <tr>
            <td class="text-black-50 h5">Last Name:</td>
            <td class="text-black-50 h5">${client.lastName}</td>
        </tr>
        <tr>
            <td class="text-black-50 h5">E-mail:</td>
            <td class="text-black-50 h5">${client.email}</td>
        </tr>
        <tr>
            <td class="text-black-50 h5">Password</td>
            <td class="text-black-50 h5">********</td>
        </tr>
        <tr>
            <td class="text-black-50 h5">Date of Birth:</td>
            <td class="text-black-50 h5">${client.birthDate}</td>
        </tr>
        <tr>
            <td class="text-black-50 h5">Addresses:
                <br>
                <br>
                <a href="/client/userInfo/addAddress" class="btn btn-outline-secondary">Add address</a>
            </td>
            <td class="text-black-50 h5">
                <table class="table-borderless align-content-center text-center">
                <c:forEach var="i" items="${client.addressList}" begin="0" end="${client.addressList.size()}">
                    <tr>
                    <td class="text-black-50 h5">
                    <c:out value="${i.postcode}, ${i.country}, ${i.city}, ${i.street}, ${i.building}, ${i.apart}"/>
                    </td>
                    <td class="text-black-50 h5">
                    <form:form action="/client/userInfo/editAddress" modelAttribute="address" method="get">
                        <input name="action" id="action" value="edit" type="hidden">
                        <form:hidden path="id" value="${i.id}"/>
                        <form:hidden path="country" value="${i.country}"/>
                        <form:hidden path="city" value="${i.city}"/>
                        <form:hidden path="postcode" value="${i.postcode}"/>
                        <form:hidden path="street" value="${i.street}"/>
                        <form:hidden path="building" value="${i.building}"/>
                        <form:hidden path="apart" value="${i.apart}"/>
                        <form:button class="btn btn-outline-secondary">Edit</form:button>
                    </form:form>
                    </td>
                </c:forEach>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td class="text-black-50 h5">Orders:</td>
            <td class="text-black-50 h5">
                <a href="/client/userInfo/orders" class="btn btn-outline-success">See all orders</a>
            </td>
        </tr>
        <tr>
            <td class="text-black-50 h5">
            <a href="/client/userInfo/manage" class="btn btn-outline-secondary">Edit account</a>
            </td>
            <td></td>
        </tr>

    </table>
</div>

<footer>
    <jsp:include page="blocks/footer.jsp"/>
</footer>
</body>
</html>
