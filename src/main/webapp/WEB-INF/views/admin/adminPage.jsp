<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="../blocks/header.jsp"/>
</header>
<div class="container">
    <table class="table">
        <tr>
            <td>
                <a href="/manage/orders">See all orders</a>
            </td>
        </tr>
        <tr>
            <td>
                <a href="/manage/statistics">Statistics</a>
            </td>
        </tr>
        <tr>
            <td>
                <a href="/manage/items">Manage Items</a>
            </td>
        </tr>
    </table>
</div>

<footer>
    <jsp:include page="../blocks/footer.jsp"/>
</footer>
</body>
</html>
