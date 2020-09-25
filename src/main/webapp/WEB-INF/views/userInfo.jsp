<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: paulponomarev
  Date: 2020-09-22
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>My Account</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<div class="container align-content-center">
    <h1 class="card">Greetings, ${client.firstName}</h1>
    <br>
    <table class="table">
        <tr>
            <td class="text-black-50 h5">Name:</td>
            <td class="text-black-50 h5">${client.firstName}</td>
        </tr>
        <tr>
            <td class="text-black-50 h5">Lastname:</td>
            <td class="text-black-50 h5">${client.lastName}</td>
        </tr>
        <tr>
            <td class="text-black-50 h5">E-mail:</td>
            <td class="text-black-50 h5">${client.email}</td>
        </tr>
        <tr>
            <td class="text-black-50 h5">Date of Birth:</td>
            <td class="text-black-50 h5">${client.birthDate}</td>
        </tr>
        <tr>
            <td class="text-black-50 h5">Addresses: <br>
                <a href="/client/userInfo/manageAddress?action=add" class="btn-light">Add address</a>
            </td>
            <td class="text-black-50 h5">
                <table class="table-borderless align-content-center">
                <c:forEach var="i" items="${client.addressList}" begin="0" end="${client.addressList.size()}">
                    <tr>
                    <td>
                    <c:out value="${i.postcode}, ${i.country}, ${i.city}, ${i.street}, ${i.building}, ${i.apart}"></c:out>
                    </td>
                    <td>
                    <form:form action="/client/userInfo/manageAddress?action=manage/" modelAttribute="address" method="get">
                        <form:hidden path="id" value="${i.id}"></form:hidden>
                        <form:hidden path="country" value="${i.country}"></form:hidden>
                        <form:hidden path="city" value="${i.city}"></form:hidden>
                        <form:hidden path="postcode" value="${i.postcode}"></form:hidden>
                        <form:hidden path="street" value="${i.street}"></form:hidden>
                        <form:hidden path="building" value="${i.building}"></form:hidden>
                        <form:hidden path="apart" value="${i.apart}"></form:hidden>
                        <form:button class="btn btn-light">Edit</form:button>
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
                <a href="/client/userInfo/orders">See all orders</a>
            </td>
        </tr>
        <tr>
            <td class="text-black-50 h5">
            <a href="/client/userInfo/manage">Edit account</a>
            </td>
            <td></td>
        </tr>

    </table>
</div>
</body>
</html>
