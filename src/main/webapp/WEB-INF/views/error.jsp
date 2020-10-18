<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

</head>
<body>
<header>
    <jsp:include page="blocks/header.jsp"/>
</header>
<div class="container text-center w-50">
    <h2>App threw exception: ${exception}</h2>
    <h3>with message: </h3>
    <h3>${msg}</h3>
    <div class="container text-center mt-3">
        <a href="/shop/" class="btn btn-outline-success">Visit shop</a>
    </div>
</div>
<footer>
    <jsp:include page="blocks/footer.jsp"/>
</footer>
</body>
</html>
