<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${menu != null}">
<nav>
    <ul>
        <c:forEach items="${menu}" var="item">
            <li>
            <c:choose>
                <c:when test="${pageContext.request.servletPath == item.value}">
                    ${item.key}
                </c:when>
                <c:otherwise>
                    <a href="${item.value}">${item.key}</a>
                </c:otherwise>
            </c:choose>
            </li>
        </c:forEach>
    </ul>
</nav>
<nav>
    <ul>
        <li>
            <c:choose>
                <c:when test="${isLoggedIn == true}">
                    <a href="/login/logout">Logout</a>
                </c:when>
                <c:otherwise>
                    <a href="/login">Login</a>
                </c:otherwise>
            </c:choose>
        </li>
    </ul>
</nav>
</c:if>
<hr>
<br>
<br>