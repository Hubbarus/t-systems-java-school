<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav aria-label="Page navigation">
    <ul class="pagination justify-content-center">
        <c:if test="${currentPage != 1}">
            <li class="page-item">
                <a class="page-link" href="/manage/orders?page=${currentPage - 1}" tabindex="-1" aria-disabled="true">Previous</a>
            </li>
        </c:if>
        <c:forEach begin="1" end="${numOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="page-item disabled"><p class="page-link">${i}</p></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="/manage/orders?page=${i}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage lt numOfPages}">
            <li class="page-item">
                <a class="page-link" href="/manage/orders?page=${currentPage + 1}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>