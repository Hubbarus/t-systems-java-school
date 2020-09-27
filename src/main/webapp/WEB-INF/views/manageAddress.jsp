<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit AddressBook</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="blocks/header.jsp"></jsp:include>
</header>
<div class="container-md">
            <table class="table">
                <form:form method="post" action="/client/userInfo/manageAddress" modelAttribute="address">
                    <tr>
                        <td class="text-black-50 h5">Country:</td>
                        <td class="form-group">
                            <form:input class="form-control" path="country" placeholder="${address.country}"></form:input>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-black-50 h5">City:</td>
                        <td class="form-group">
                            <form:input class="form-control" path="city" placeholder="${address.city}"></form:input>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-black-50 h5">Postcode:</td>
                        <td class="form-group">
                            <form:input class="form-control" path="postcode" placeholder="${address.postcode}"></form:input>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-black-50 h5">Street:</td>
                        <td class="form-group">
                            <form:input class="form-control" path="street" placeholder="${address.street}"></form:input>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-black-50 h5">Building:</td>
                        <td class="form-group">
                            <form:input class="form-control" path="building" placeholder="${address.building}"></form:input>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-black-50 h5">Apart:</td>
                        <td class="form-group">
                            <form:input class="form-control" path="apart" placeholder="${address.apart}"></form:input>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:hidden path="id" value="${address.id}"></form:hidden>
                            <button class="btn-success" type="submit" value="Confirm changes">Confirm changes</button>
                        </td>
                    </tr>
                </form:form>
            </table>
</div>

<footer>
    <jsp:include page="blocks/footer.jsp"></jsp:include>
</footer>
</body>
</html>
