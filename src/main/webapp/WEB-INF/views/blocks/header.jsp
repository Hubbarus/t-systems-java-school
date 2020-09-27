<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style>
    .background {
        background-color: rgba(144, 173, 15, 0.77);
    }
</style>
<nav class="site-header sticky-top py-1 background">
    <div class="container d-flex flex-column flex-md-row justify-content-between">
        <p class="text-white text-md-center h4 text">Hello,
            <sec:authorize access="isAuthenticated()">${user.firstName}</sec:authorize>
            <sec:authorize access="!isAuthenticated()">Guest</sec:authorize>!</p>

        <a href="/shop/" class="text-dark text-md-center h4">View Shop</a>

        <sec:authorize access="!isAuthenticated()">
            <a href="/client/registration/" class="text-dark text-md-center h4">Registration</a>
            <a href="/login" class="text-dark text-md-center h4">Sigh In</a>
        </sec:authorize>

        <sec:authorize access="hasRole('ADMIN') || hasRole('USER')">
            <sec:authorize access="hasRole('USER')">
                <a href="/client/userInfo/" class="text-dark text-md-center h4">My account</a>
            </sec:authorize>
            <sec:authorize access="hasRole('ADMIN')">
                <a href="/manage/" class="text-dark text-md-center h4">Manage</a>
            </sec:authorize>
                <a href="/logout" class="text-dark text-md-center h4">Log Out</a>
        </sec:authorize>
        <div>
            <a href="/cart/" class="text-dark text-md-center h4">Cart
            <img src="/img/cart_icon.png" width="20" height="20" type="image/png"></a>
        </div>
    </div>
</nav>