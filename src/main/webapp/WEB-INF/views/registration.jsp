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

    <style>
        .text-err {
            color: red;
        }
    </style>
    <link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="blocks/header.jsp"/>
</header>

<div class="container mt-5 w-50 text-center">
<h2>Registration</h2>
</div>

<div class="container w-50 align-content-center">
<c:choose>
    <c:when test="${userNameError != null}">
        <div class="alert alert-danger text-center">
            <c:out value="${userNameError}"/><br>
            <a href="/client/registration/" class="btn btn-outline-success">Go back</a>
        </div>
    </c:when>

    <c:otherwise>
        <div class="table text-center align-content-center">
            <form:form method="POST" modelAttribute="clientForm" action="/client/registration/" onsubmit="return validate();">
                <h3 class="text-center">Client Information</h3>
<%--                First name--%>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">First Name</span>
                    </div>
                    <form:input path="firstName"
                                id = "fName"
                                type="text"
                                class="form-control"
                                placeholder="First Name"
                                aria-label="First Name"
                                aria-describedby="basic-addon1"
                                required="true"/>
                    <div class="input-group-append">
                        <span class="input-group-text"><span class="text-err" id="fNameErr"/></span>
                    </div>
                </div>
<%--                Last name--%>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon2">Last Name</span>
                    </div>
                    <form:input path="lastName"
                                id="lName"
                                type="text"
                                class="form-control"
                                placeholder="Last Name"
                                aria-label="Last Name"
                                aria-describedby="basic-addon2"
                                required="true"/>
                    <div class="input-group-append">
                        <span class="input-group-text"><span class="text-err" id="lNameErr"/></span>
                    </div>
                </div>
<%--                Date of birth--%>
                <fmt:formatDate value="${clientForm.birthDate}" var="date" pattern="yyyy-MM-dd"/>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon3">Birth date</span>
                    </div>
                    <form:input value="${date}"
                                path="birthDate"
                                type='date'
                                class="form-control"
                                aria-describedby="basic-addon3"
                                required="true"/>
                </div>
<%--                Email--%>
                <div class="input-group mb-3">
                    <form:input path="email"
                                type="email"
                                class="form-control"
                                placeholder="example@gmail.com"
                                aria-label="E-mail"
                                aria-describedby="basic-addon5"
                                required="true"/>
                    <div class="input-group-append">
                        <span class="input-group-text" id="basic-addon5">E-mail</span>
                    </div>
                </div>
<%--                Password--%>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon6">Password</span>
                    </div>
                    <form:password path="userPass"
                                   id="pw"
                                   class="form-control"
                                   placeholder="Password"
                                   aria-label="Password"
                                   aria-describedby="basic-addon6"
                                   required="true"/>
                    <div class="input-group-append">
                        <span class="input-group-text"><span class="text-err" id="pwErr"/></span>
                    </div>
                </div>
<%--                Repeat Password--%>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon7">Repeat Password</span>
                    </div>
                    <input type="password"
                           id="pwRep"
                           class="form-control"
                           placeholder="Password"
                           aria-label="Password"
                           aria-describedby="basic-addon6"
                           required="true"/>
                    <div class="input-group-append">
                        <span class="input-group-text"><span class="text-err" id="pwRepErr"/></span>
                    </div>
                </div>
<%--                Button--%>
                <button type="submit" class="btn btn-outline-success">Do register!</button>
            </form:form>
        </div>
    </c:otherwise>
</c:choose>
</div>

<footer>
    <jsp:include page="blocks/footer.jsp"/>
</footer>

<script type="text/javascript">
    function validate() {
        var fNameErr, lNameErr, pwErr;
        var hasErr = false;
        document.getElementById("fNameErr").innerText = "";
        document.getElementById("lNameErr").innerText = "";
        document.getElementById("pwErr").innerText = "";
        document.getElementById("pwRepErr").innerText = "";

        var fNameVal = document.getElementById("fName").value;
        if (fNameVal.length < 2) {
            fNameErr = "Too short name";
            document.getElementById("fNameErr").innerText = fNameErr;
            hasErr = true;
        }

        var lNameVal = document.getElementById("lName").value;
        if (lNameVal.length < 2) {
            lNameErr = "Too short Last name";
            document.getElementById("lNameErr").innerText = lNameErr;
            hasErr = true;
        }

        var pwVal = document.getElementById("pw").value;
        var pwRepVal = document.getElementById("pwRep").value;
        if (pwVal.length < 4) {
            pwErr = "Too short password";
            document.getElementById("pwErr").innerText = pwErr;
            hasErr = true;
        }
        if (pwVal != pwRepVal) {
            pwErr = "Password don't match!"
            document.getElementById("pwRepErr").innerText = pwErr;
            hasErr = true;
        }

        return !hasErr;
    }
</script>
</body>
</html>
