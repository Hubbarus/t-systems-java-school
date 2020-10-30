<%@ page import="project.dto.AddressDTO" %>
<%@ page import="java.util.Set" %>
<%@ page import="project.dto.ClientDTO" %>
<%@ page import="java.util.HashSet" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Information</title>

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

<div class="container w-50 align-content-center">
    <div class="text-center mt-3 mb-3">
        <h2>Edit Profile</h2>
    </div>
    <c:if test="${errorMsg != null}">
        <div class="alert alert-danger text-center" role="alert">
            <c:out value="${errorMsg}"/>
        </div>
    </c:if>

    <div class="container-sm text-center">
        <div class="container">
            <h2><b>Username :</b> ${client.email}</h2>
        </div>
        <table class="table">
            <form:form method="post" modelAttribute="client" action="" onsubmit="return validate();">
<%--                F name--%>
                <tr>
                    <td>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">First Name</span>
                            </div>
                            <form:input path="firstName"
                                        id="fName"
                                        type="text"
                                        class="form-control"
                                        value="${client.firstName}"
                                        placeholder="${client.firstName}"
                                        aria-label="First Name"
                                        aria-describedby="basic-addon1"
                                        required="true"/>
                            <div class="input-group-append">
                                <span class="input-group-text"><span class="text-err" id="fNameErr"/></span>
                            </div>
                        </div>

                    </td>
                </tr>
<%--                L name--%>
                <tr>
                    <td>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon2">Last Name</span>
                            </div>
                            <form:input path="lastName"
                                        id="lName"
                                        type="text"
                                        class="form-control"
                                        value="${client.lastName}"
                                        placeholder="${client.lastName}"
                                        aria-label="Last Name"
                                        aria-describedby="basic-addon2"
                                        required="true"/>
                            <div class="input-group-append">
                                <span class="input-group-text"><span class="text-err" id="lNameErr"/></span>
                            </div>
                        </div>
                    </td>
                </tr>
<%--                B date--%>
                <tr>
                    <td>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon3">Birth date</span>
                            </div>
                            <form:input value="${client.birthDate}"
                                        path="birthDate"
                                        type='date'
                                        class="form-control"
                                        aria-describedby="basic-addon3"
                                        required="true"/>
                        </div>
                    </td>
                </tr>
<%--                Pass--%>
                <tr>
                    <td>
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
                    </td>
                </tr>
<%--                Pass Repeat--%>
                <tr>
                    <td>
<%--                Pass Repeat--%>
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
                    </td>
                </tr>
<%--                Errors--%>
                <tr>
                    <td>
                        <c:if test="${errors.size() != 0}">
                            <c:forEach var="err" items="${errors}">
                                <div class="text alert-danger">
                                    <c:out value="${err.defaultMessage}"/><br>
                                </div>
                            </c:forEach>
                        </c:if>
                    </td>
                </tr>
<%--                Button--%>
                <tr>
                    <td>
                        <form:hidden path="id" value="${client.id}"/>
                        <form:hidden path="active" value="${client.active}"/>
                        <form:hidden path="role" value="${client.role}"/>
                        <form:hidden path="email" value="${client.email}"/>
                        <button class="btn btn-outline-success" type="submit" value="Confirm changes">Confirm changes</button>
                    </td>
                </tr>
            </form:form>

        </table>
    </div>
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
