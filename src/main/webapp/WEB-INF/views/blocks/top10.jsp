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
                <text class="mt-2">${cart.item.itemName}</text></img>
                <div class="card-body">
                    <p class="card-text">${cart.item.description}</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="btn-group">
                            <a href="/shop/${cart.item.itemGroup}/${cart.item.id}" type="button" class="btn btn-sm btn-outline-secondary">View</a>
                            <c:choose >
                                <c:when test="${cart.item.stock == 0}">
                                    <button class="btn btn-sm btn-outline-secondary" disabled>Buy in one click</button>
                                </c:when>
                                <c:otherwise>
                                    <a href="/cart/add?itemId=${cart.item.id}" type="button" class="btn btn-sm btn-outline-secondary">Buy in one click</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <small class="text-muted">stock: ${cart.item.stock}pcs, ${cart.item.price}$</small>
                    </div>
                </div>
            </div>
        </div>

    </c:forEach>
</div>