<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit AddressBook</title>
    <link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="blocks/header.jsp"/>
</header>
<div class="container mt-3 mb-3">
    <c:if test="${address.country != null}">
        <c:set var="greet" value="Edit address" scope="page"/>
    </c:if>
    <c:if test="${address.country == null}">
        <c:set var="greet" value="Add new address" scope="page"/>
    </c:if>
    <div class="container text-center">
        <h2><c:out value="${greet}"/></h2>
    </div>
    <div class="container align-content-center">
        <table class="table text-center align-content-center mt-3 mb-3">
            <form:form method="post" action="/client/userInfo/manageAddress" modelAttribute="address">
<%--                Country--%>
                <tr>
                    <td>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1">Country: </span>
                            </div>
                            <form:input path="country" placeholder="${address.country}"
                                        type="text" class="form-control"
                                        aria-label="Имя пользователя"
                                        aria-describedby="basic-addon1"/>
                        </div>
                    </td>
                </tr>
<%--                City--%>
                <tr>
                    <td>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon2">City: </span>
                            </div>
                            <form:input path="city" placeholder="${address.city}"
                                        type="text" class="form-control"
                                        aria-label="Имя пользователя"
                                        aria-describedby="basic-addon2"/>
                        </div>
                    </td>
                </tr>
<%--                Postcode--%>
                <tr>
                    <td>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon3">Postcode: </span>
                            </div>
                            <form:input  path="postcode" placeholder="${address.postcode}"
                                         type="text"
                                         class="form-control"
                                         aria-label="Имя пользователя"
                                         aria-describedby="basic-addon3"/>
                        </div>
                    </td>
                </tr>
<%--                Street--%>
                <tr>
                    <td>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon4">Street: </span>
                            </div>
                            <form:input type="text" path="street" placeholder="${address.street}"
                                   class="form-control"
                                   aria-label="Имя пользователя"
                                   aria-describedby="basic-addon4"/>
                        </div>
                    </td>
                </tr>
<%--                Building--%>
                <tr>
                    <td>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon5">Building: </span>
                            </div>
                            <form:input path="building" placeholder="${address.building}"
                                        type="text"
                                        class="form-control"
                                        aria-label="Имя пользователя"
                                        aria-describedby="basic-addon5"/>
                        </div>
                    </td>

                </tr>
<%--                Apart--%>
                <tr>
                    <td>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon6">Apart: </span>
                            </div>
                            <form:input path="apart" placeholder="${address.apart}"
                                        type="text" class="form-control"
                                        aria-label="Имя пользователя"
                                        aria-describedby="basic-addon6"/>
                        </div>
                    </td>
                </tr>
<%--                Button--%>
                <tr>
                    <td>
                        <form:hidden path="id" value="${address.id}"/>
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
</body>
</html>
