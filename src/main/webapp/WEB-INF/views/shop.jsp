<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Shop</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="blocks/header.jsp"></jsp:include>
</header>
<c:if test="${categories == null}">
    <c:forEach begin="0" end="${items.size()}" var="i" items="${items}">
        <a href="/shop/${i.itemGroup}/${i.id}"><br>
                <c:out value="${i.itemName}"/>
        </a>
    </c:forEach>
</c:if>
<c:forEach begin="0" end="${categories.size()}" var="j" items="${categories}">
    <a href="/shop/${j}">
        <c:out value="${j}"/><br>
    </a>
</c:forEach>

<footer>
    <jsp:include page="blocks/footer.jsp"></jsp:include>
</footer>
</body>
</html>
