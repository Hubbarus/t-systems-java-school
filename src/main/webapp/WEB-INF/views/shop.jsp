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

    <link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="blocks/header.jsp"/>
</header>
<table class="table">
    <tr>
        <td>
<%--            Side menu with categories--%>
            <div class="container">
                <c:forEach begin="0" end="${categories.size()}" var="j" items="${categories}">
                    <c:if test="${!j.equalsIgnoreCase('default')}">
                        <a href="/shop/${j}" class="btn btn-outline-dark mt-2 mb-2">
                            <c:out value="${j}"/>
                        </a>
                        <br>
                    </c:if>
                </c:forEach>
            </div>
        </td>
<%--        Container with items--%>
        <td>
            <div class="container">
                <c:choose>
<%--                    For top 10 items--%>
                    <c:when test="${itemsCat == null}">
                        <jsp:include page="blocks/top10.jsp"/>
                    </c:when>
<%--                    For Items by Categories--%>
                    <c:when test="${items != null}">
                    <div class="row">
                        <c:forEach begin="0" end="${itemsCat.size()}" var="i" items="${itemsCat}">
                            <div class="col-md-4">
                                <div class="card mb-4 shadow-sm text-center">
                                    <a href="/shop/${i.itemGroup}/${i.id}">
                                        <img src="${i.pathToIMG}" class="img-fluid w-100">
                                    </a>
                                    <title>${i.itemName}</title>
                                    <text>${i.itemName}</text></img>
                                    <div class="card-body">
                                        <p class="card-text">${i.description}</p>
                                        <div class="d-flex justify-content-between align-items-center">
                                            <div class="btn-group">
                                                <a href="/shop/${i.itemGroup}/${i.id}" type="button" class="btn btn-sm btn-outline-secondary">View</a>
                                                <a href="/cart/add?itemId=${i.id}" type="button" class="btn btn-sm btn-outline-secondary">Buy in one click</a>
                                            </div>
                                            <small class="text-muted">stock: ${i.stock}pcs, ${i.price}$</small>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    </c:when>
                </c:choose>
            </div>
        </td>
    </tr>
</table>
<aside>

</aside>

<div>

</div>

<footer>
    <jsp:include page="blocks/footer.jsp"/>
</footer>
</body>
</html>
