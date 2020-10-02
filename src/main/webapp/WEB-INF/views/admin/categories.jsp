
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Categories Edit</title>

    <link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="../blocks/header.jsp"/>
</header>

<div class="container mt-3 text-center">
    <div class="container mt-1 h2">
        <c:out value="Manage categories"/>
    </div>
    <div class="container mt-2 text-center">
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle"
                    type="button"
                    id="dropdownMenuButton"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false">
                Choose category...
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <c:forEach begin="0" end="${categories.size()}" var="j" items="${categories}">
                    <a href="/manage/categories/${j}/" class="dropdown-item">
                        ${j}
                    </a>
                </c:forEach>
            </div>
            <div class="mr-5 ml-5 mt-4 mb-4">
                <a class="btn btn-outline-success" onclick="onAddClick()">Add new</a>
            </div>
        </div>
    </div>
    <div>
        <div class="container mt-3 mb-2">
            <div class="container mt-2 mb-2 h3">
                <c:out value="${cat}"/>
            </div>
            <table class="table mt-1 mb-1 text-center">
                <tr>
                <c:if test="${cat != null}">
                    <td class="text-center">
                        <a class="btn btn-outline-warning" onclick="onEditClick('${cat}')">Rename</a>
                    </td>
                    <td class="text-center">
                        <a class="btn btn-outline-danger" onclick="return onDeleteClick('${cat}');">Delete</a>
                    </td>
                </c:if>
                </tr>
            </table>
            </div>
    </div>
</div>

<footer>
    <jsp:include page="../blocks/footer.jsp"/>
</footer>

<script type="text/javascript">
    function onAddClick() {
        var input = prompt('Type new group name: ');
        window.location = '/manage/categoriesAdd?c=' + input;
    }
    
    function onEditClick(categ) {
        var input = prompt('Type new name of category: ');
        window.location = '/manage/categoriesEdit?new=' + input + '&old=' + categ;
    }
    
    function onDeleteClick(categ) {
        if (confirm('Are you sure you want to delete category?\n\n' +
            'NOTICE: All Items form this category will be moved to DEFAULT category ' +
            'and not gonna be shown in shop until you not set new category.' +
            '\nYou will be redirected to items edit page.')) {
            window.location = '/manage/categoriesDel?c=' + categ;
            return true;
        } else {
            return false;
        }
    }
</script>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

</body>
</html>
