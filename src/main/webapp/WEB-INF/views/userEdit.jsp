<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Information</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<c:if test="${errorMsg != null}">
    <div class="alert alert-danger align-content-center" role="alert">
        <c:out value="${errorMsg}"></c:out>
    </div>
</c:if>

<div class="container-sm">
    <table class="table">
        <form:form method="post" modelAttribute="client" action="">
            <tr>
                <td class="text-black-50 h5">Name:</td>
                <td class="form-group">
                    <form:input class="form-control" path="firstName" placeholder="${client.firstName}"></form:input>
                </td>
            </tr>
            <tr>
                <td class="text-black-50 h5">Lastname:</td>
                <td class="text-black-50 h5">
                    <form:input class="form-control" path="lastName" value="${client.lastName}" placeholder="${client.lastName}"></form:input>
                </td>
            </tr>
            <tr>
                <td class="text-black-50 h5">Date of Birth:</td>
                <td class="text-black-50 h5">
                    <form:input class="form-control" type="date" path="birthDate"></form:input></td>
            </tr>
            <tr>
                <td class="text-black-50 h5">E-mail/Login:</td>
                <td class="text-black-50 h5">
                    <form:input class="form-control" required="true" path="email" type="email" value="${client.email}" placeholder="${client.email}"></form:input>
                </td>
            </tr>
            <tr>
                <td class="text-black-50 h5">Password:</td>
                <td class="text-black-50 h5">
                    <form:password class="form-control" required="true" path="userPass"></form:password>
                </td>
            </tr>
            <tr>
                <td>
                    <form:hidden path="active" value="${client.active}"></form:hidden>
                    <form:hidden path="role" value="${client.role}"></form:hidden>
                    <button class="btn-success" type="submit" value="Confirm changes">Confirm changes</button>
                </td>
            </tr>
        </form:form>

    </table>
</div>
</body>
</html>
