<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form:form method="POST" modelAttribute="userForm">
    <h2>Регистрация</h2>
    <div>
        <form:input type="text" path="firstName" placeholder="Username"
                    autofocus="true"></form:input>
    </div>
    <div>
        <form:input type="text" path="lastName" placeholder="Username"></form:input>
    </div>
    <div>
        <form:input type="text" path="username" placeholder="Username"></form:input>
    </div>
    <div>
        <form:input type="password" path="userPass" placeholder="Password"></form:input>
    </div>
    <div>
        <form:input type="date" path="birthDate" placeholder="Username"></form:input>
    </div>
    <div>
        <form:input type="email" path="email" placeholder="Username"></form:input>
    </div>
    <form:form method="POST" modelAttribute="addressForm">
        <h4>Enter Address</h4>
        <div>
            <form:input type="text" path="country" placeholder="Username"></form:input>
        </div>
        <div>
            <form:input type="text" path="city" placeholder="Username"></form:input>
        </div>
        <div>
            <form:input type="text" path="postcode" placeholder="Username"></form:input>
        </div>
        <div>
            <form:input type="text" path="street" placeholder="Username"></form:input>
        </div>
        <div>
            <form:input type="number" path="building" placeholder="Username"></form:input>
        </div>
        <div>
            <form:input type="number" path="apart" placeholder="Username"></form:input>
        </div>
    </form:form>
    <button type="submit">Зарегистрироваться</button>
</form:form>
</body>
</html>
