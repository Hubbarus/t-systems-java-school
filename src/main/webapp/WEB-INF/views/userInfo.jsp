<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <a href="/client/userInfo/manageAddress?action=add">Add address</a>
            </td>
            <td class="text-black-50 h5">
                <c:forEach var="i" items="${client.addressList}" begin="0" end="${client.addressList.size()}">
                    <c:out value="${i}"></c:out>
                    <a href="/client/userInfo/manageAddress?action=manage&addressId=${i.id}">Manage</a><br>
                </c:forEach>
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
