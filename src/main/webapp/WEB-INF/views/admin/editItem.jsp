<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="../blocks/header.jsp"></jsp:include>
</header>

<form:form method="post" modelAttribute="itemToEdit" action="manage/editItem">
    <form:input path="id" type="hidden"/>
    <table class="table">
        <tr>
<%--            Path to IMG--%>
            <td>
                <c:if test="${itemToEdit.itemName != null}">
                    <img src="${itemToEdit.pathToIMG}">
                </c:if>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
                    </div>
                    <div class="custom-file">
                        <form:input path="pathToIMG" type="file" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01" required="false"/>
                        <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
                    </div>
                </div>
            </td>
            <td>
                <table class="table">
<%--                    Item Name--%>
                    <tr>
                        <td>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1">Item Name</span>
                                </div>
                                <form:input value="${itemToEdit.itemName}" path="itemName" type="text" class="form-control" placeholder="Item Name" aria-label="Имя пользователя" aria-describedby="basic-addon1"/>
                            </div>
                        </td>
                    </tr>
<%--                    Item Group--%>
                    <tr>
                        <td>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="inputGroupSelect01">Group</label>
                                </div>
                                <form:select path="itemGroup" id="inputGroupSelect01" onchange="onSelectChange(event)" class="custom-select">
                                </form:select>
                            </div>
                        </td>
                    </tr>
<%--                    --%>
                    <tr>

                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form:form>

<script type="text/javascript">
    var selector = document.getElementById('inputGroupSelect01');
    var groups = [
        {
            label: 'Choose...',
            value: '#',
        },
        {
            label: 'Add new',
            value: 'add new',
        },
    ];

    <c:forEach items="${itemGroups}" var="group">
        var group = {
            label : '${group}',
            value : '${group}',
        };
        groups.push(group);
    </c:forEach>

    function onSelectChange(event) {
        if (event.target.value === "add new") {
            var userInput = prompt('Type new group name: ')

            var newGroup = {
                label: userInput,
                value: userInput.toLowerCase(),
            };

            groups.push(newGroup);
            render();
        }
    }

    function render() {
        selector.innerHTML = '';

        for (var i = 0; i < groups.length; i += 1) {
            var currentGroup = groups[i];

            var optionElement = document.createElement('option');
            optionElement.value = currentGroup.value;
            optionElement.innerHTML = currentGroup.label;

            selector.append(optionElement);
        }
    }

    // Initial render
    render();
</script>

<footer>
    <jsp:include page="../blocks/footer.jsp"/>
</footer>
</body>
</html>
