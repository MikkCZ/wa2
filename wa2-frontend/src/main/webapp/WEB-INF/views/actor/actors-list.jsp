<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../includes/head.jsp" />
</head>
<body>
<header>
    <jsp:include page="../includes/menu.jsp" />
</header>
<main>
    <table>
        <c:forEach items="${actors}" var="actor">
            <tr>
                <td><a href="/actor/${actor.getId()}">${actor.getId()}</a></td>
                <td>${actor.getFirstName()} ${actor.getLastName()}</td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${isLoggedIn == true}">
        <a href="/actor/new"><button>New</button></a>
    </c:if>
</main>
<footer>
    <jsp:include page="../includes/foot.jsp" />
</footer>
</body>
</html>