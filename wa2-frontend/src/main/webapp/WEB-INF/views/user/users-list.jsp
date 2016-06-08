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
        <c:forEach items="${users}" var="user">
            <tr>
                <td><a href="/user/${user.getId()}">${user.getId()}</a></td>
                <td>${user.getEmail()}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="/user/new"><button>New</button></a>
</main>
<footer>
    <jsp:include page="../includes/foot.jsp" />
</footer>
</body>
</html>