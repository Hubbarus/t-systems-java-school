<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="shortcut icon" href="/img/favicon.ico" type="img/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<header>
    <jsp:include page="../blocks/header.jsp"/>
</header>

<form:form method="post" modelAttribute="itemToEdit" action="/manage/editItem" onsubmit="return onSubmit();" id="itemForm" enctype="multipart/form-data">
    <form:input path="id" type="hidden"/>
    <table class="table">
        <tr>
<%--            Path to IMG--%>
            <td>
                <c:if test="${itemToEdit.itemName != null}">
                    <img src="${itemToEdit.pathToIMG}" height="300"><br>
                </c:if>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
                    </div>
                    <div class="custom-file">
                        <input name="file"
                               type="file"
                               class="custom-file-input"
                               id="file"
                               aria-describedby="inputGroupFileAddon01"/>
                        <label class="custom-file-label" for="file">Choose file</label>
                    </div>
                </div>
<%--                <label for="basic-url">Path to image in "img/" folder</label>--%>
<%--                <div class="input-group mb-3">--%>
<%--                    <div class="input-group-prepend">--%>
<%--                        <span class="input-group-text" id="basic-addon3">/img/</span>--%>
<%--                    </div>--%>
<%--                    <form:input path="pathToIMG"--%>
<%--                                placeholde="example.jpg"--%>
<%--                                type="text"--%>
<%--                                class="form-control"--%>
<%--                                id="basic-url"--%>
<%--                                aria-describedby="basic-addon3"--%>
<%--                                value="${itemToEdit.pathToIMG}" required="true"/>--%>
<%--                </div>--%>
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
                                <form:input value="${itemToEdit.itemName}"
                                            path="itemName"
                                            type="text"
                                            class="form-control"
                                            aria-label="Item name"
                                            aria-describedby="basic-addon1"
                                            placeholder="Item Name" required="true"/>
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
                                <form:select path="itemGroup"
                                             id="inputGroupSelect01"
                                             onchange="onSelectChange(event)"
                                             class="custom-select"
                                             required="true">
                                </form:select>
                            </div>
                        </td>
                    </tr>
<%--                    Item Price--%>
                    <tr>
                        <td>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Price</span>
                                </div>
                                <form:input value="${itemToEdit.price}"
                                            path="price"
                                            class="form-control"
                                            aria-label="Price"
                                            placeholder="Item price"
                                            required="true"/>
                                <div class="input-group-append">
                                    <span class="input-group-text">$</span>
                                </div>
                            </div>
                        </td>
                    </tr>
<%--                    Item Description--%>
                    <tr>
                        <td>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Description</span>
                                </div>
                                <form:textarea value="${itemToEdit.description} "
                                               path="description"
                                               class="form-control"
                                               aria-label="Description"
                                               placeholder="Description"
                                               required="true"/>
                            </div>
                        </td>
                    </tr>
<%--                    Weight--%>
                    <tr>
                        <td>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Weight</span>
                                </div>
                                <form:input value="${itemToEdit.weight}"
                                            path="weight"
                                            class="form-control"
                                            aria-label="Weight"
                                            placeholder="Weight"
                                            required="true"/>
                                <div class="input-group-append">
                                    <span class="input-group-text">kg</span>
                                </div>
                            </div>
                        </td>
                    </tr>
<%--                    Volume--%>
                    <tr>
                        <td>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Volume</span>
                                </div>
                                <form:input value="${itemToEdit.volume}"
                                            path="volume"
                                            class="form-control"
                                            aria-label="Volume"
                                            placeholder="Volume"
                                            required="true"/>
                                <div class="input-group-append">
                                    <span class="input-group-text">m³</span>
                                </div>
                            </div>
                        </td>
                    </tr>
<%--                    Stock--%>
                    <tr>
                        <td>
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">Stock</span>
                                </div>
                                <form:input value="${itemToEdit.stock}"
                                            path="stock"
                                            class="form-control"
                                            aria-label="Stock"
                                            placeholder="Quantity in stock"
                                            required="true"/>
                                <div class="input-group-append">
                                    <span class="input-group-text">pcs</span>
                                </div>
                            </div>
                        </td>
                    </tr>
<%--                    Button submit--%>
                    <tr>
                        <td>
                            <form:button type="submit" class="btn btn-outline-dark">Submit changes</form:button>
                        </td>
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

    <c:forEach items="${categories}" var="group">
        var group = {
            label : '${group}',
            value : '${group}',
        };
        groups.push(group);
    </c:forEach>

    function onSelectChange(event) {
        if (event.target.value === "add new") {
            var userInput = prompt('Type new group name: ');

            var newGroup = {
                label: userInput,
                value: userInput,
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

        if (selector.selected === 'Choose...') {
            var num = groups.length - 1;
            selector.options[num].selected = true;
        }
    }

    function setDefaultGroup() {
        var select = document.querySelector('#inputGroupSelect01').getElementsByTagName('option');

        selector.selected = null;

        for (let i = 0; i < select.length; i++) {
            if (select[i].value === '${itemToEdit.itemGroup}') {
                select[i].selected = true;
                break;
            }
        }

        if (selector.selected == null) {
            selector.selected = 'Choose...';
        }
    }

    function onSubmit() {
        var form = document.forms['itemForm'];
        var field = form.elements['inputGroupSelect01'];

        if (field.value === '#') {
            alert('Choose group, please');
            return false;
        } else {
            form.submit();
            return true;
        }
    }

    // Initial render
    render();
    setDefaultGroup();
</script>

<footer>
    <jsp:include page="../blocks/footer.jsp"/>
</footer>
</body>
</html>
