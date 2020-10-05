<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager Page</title>
    <link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="../blocks/header.jsp"/>
</header>

<div class="container text-center align-content-center mt-5 mb-3 w-75">
    <div class="container mt-1 mb-2 text-center h2">
        <c:out value="Manager page"/>
    </div>
    <div class="container mt-2 text-center align-content-center">
        <table class="table align-content-center text-center">
            <tr>
                <td class="text-center align-content-md-center ml-2 mr-2">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <img src="/img/admin_order_icon.png" class="card-img-top" width="75"/>
                            <p class="card-text">Here you can see all orders and manage its status.</p><br>
                            <a href="/manage/orders?page=1" class="btn btn-outline-success">See all orders</a>
                        </div>
                    </div>
                </td>
                <td class="text-center align-content-md-center ml-2 mr-2">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <img src="/img/admin_stat_icon.png" class="card-img-top" width="75"/>
                            <p class="card-text">Take a look at proceeds and top 10 items in shop. Also you can figure out who is buying a lot.</p>
                            <a href="/manage/statistics" class="btn btn-outline-success">Statistics</a>
                        </div>
                    </div>
                </td>
                <td class="text-center align-content-md-center ml-2 mr-2">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <img src="/img/admin_item_icon.png" class="card-img-top" width="75"/>
                            <p class="card-text">Edit all items and add new - all in one page!</p><br>
                            <a href="/manage/items?page=1" class="btn btn-outline-success">Manage Items</a>
                        </div>
                    </div>
                </td>
                <td class="text-center align-content-md-center ml-2 mr-2">
                    <div class="card" style="width: 18rem;">
                        <div class="card-body">
                            <img src="/img/admin_categ_item.jpg" class="card-img-top" width="75"/>
                            <p class="card-text">Add, Delete and Edit categories of items</p><br>
                            <a href="/manage/categories" class="btn btn-outline-success">Manage Categories</a>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>

<footer>
    <jsp:include page="../blocks/footer.jsp"/>
</footer>
</body>
</html>
