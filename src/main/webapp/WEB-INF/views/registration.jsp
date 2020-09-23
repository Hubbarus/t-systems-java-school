<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Registration</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<h2>Registration</h2>
<c:choose>
    <c:when test="${userNameError != null}">
        <c:out value="${userNameError}"></c:out>
        <a href="/client/registration/" class="btn-success">Go back</a>
    </c:when>
    <c:otherwise>
        <div class="table">
            <form:form method="POST" modelAttribute="clientForm" action="/client/registration/">
                <h3 class="card-header">Client Information</h3>
                <p class="text-dark">Name: </p>
                <form:input path="client.firstName"></form:input>

                <p class="text-dark">Lastname: </p>
                <form:input path="client.lastName"></form:input>

                <p class="text-dark">Date of birth: </p>
                <fmt:formatDate value="${clientForm.client.birthDate}" var="date" pattern="yyyy-MM-dd"></fmt:formatDate>
                <form:input type="date" path="client.birthDate" value="${date}"></form:input>

                <p class="text-dark">E-mail: </p>
                <form:input type="email" path="client.email"></form:input>

                <p class="text-dark">Password: </p>
                <form:password path="client.userPass"></form:password>

                <h3 class="card-header">Address</h3>
                <p class="text-dark">Country: </p>
                <form:input path="address.country"></form:input>

                <p class="text-dark">City: </p>
                <form:input path="address.city"></form:input>

                <p class="text-dark">Postcode: </p>
<%--                <input type="number">--%>
                <form:input path="address.postcode"></form:input>

                <p class="text-dark">Street: </p>
                <form:input path="address.street"></form:input>

                <p class="text-dark">Building #: </p>
<%--                <input type="number">--%>
                <form:input type="number" path="address.building"></form:input>

                <p class="text-dark">Apart #: </p>
<%--                <input type="number">--%>
                <form:input type="number" path="address.apart"></form:input>
                <button type="submit">Do register!</button>
            </form:form>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
