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
<div class="container card">
<h2>Registration</h2>
</div>
<div class="container">
<c:choose>
    <c:when test="${userNameError != null}">
        <div class="alert alert-danger"><c:out value="${userNameError}"></c:out></div>
        <a href="/client/registration/" class="btn-success">Go back</a>
    </c:when>
    <c:otherwise>
        <div class="table">
            <form:form method="POST" modelAttribute="clientForm" action="/client/registration/">
                <h3 class="card-header">Client Information</h3>
                <p class="text-dark">Name: </p>
                <form:input path="firstName"></form:input>

                <p class="text-dark">Lastname: </p>
                <form:input path="lastName"></form:input>

                <p class="text-dark">Date of birth: </p>
                <fmt:formatDate value="${clientForm.birthDate}" var="date" pattern="yyyy-MM-dd"></fmt:formatDate>
                <form:input type="date" path="birthDate" value="${date}"></form:input>

                <p class="text-dark">E-mail: </p>
                <form:input type="email" path="email"></form:input>

                <p class="text-dark">Password: </p>
                <form:password path="userPass"></form:password>
                <button type="submit">Do register!</button>
            </form:form>
        </div>
    </c:otherwise>
</c:choose>
</div>
</body>
</html>
