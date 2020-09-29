<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <c:forEach items="${topTenItems}" var="cart">

        <div class="col-md-4">
            <div class="card mb-4 shadow-sm text-center">
                <a href="/shop/${cart.item.itemGroup}/${cart.item.id}">
                    <img src="${cart.item.pathToIMG}" class="img-fluid w-100">
                </a>
                <title><strong>${cart.item.itemName}</strong></title>
                <rect width="100%" height="100%" fill="#55595c"></rect>
                <text>${cart.item.itemName}</text></img>
                <div class="card-body">
                    <p class="card-text">${cart.item.description}</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="btn-group">
                            <a href="/shop/${cart.item.itemGroup}/${cart.item.id}" type="button" class="btn btn-sm btn-outline-secondary">View</a>
                            <button type="button" class="btn btn-sm btn-outline-secondary">Add to Cart</button>
                        </div>
                        <small class="text-muted">stock: ${cart.item.stock}pcs, ${cart.item.price}$</small>
                    </div>
                </div>
            </div>
        </div>

    </c:forEach>
</div>