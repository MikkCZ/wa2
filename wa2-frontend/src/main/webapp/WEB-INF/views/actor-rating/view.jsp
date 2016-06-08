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
        <tr>
            <td>${rating.getRatedBy().getEmail()} rated ${rating.getActor().getFirstName()} ${rating.getActor().getLastName()}:</td>
            <td>${rating.getComment()}</td>
        </tr>
    </table>
    <c:if test="${isLoggedIn == true}">
        <form method="post" action="/actor-rating/${rating.getId()}">
            <input type="text" name="comment" id="comment" value="${rating.getComment()}">
            <br>
            <input type="submit" value="Update">
        </form>
    </c:if>
</main>
<footer>
    <jsp:include page="../includes/foot.jsp" />
</footer>
</body>
</html>