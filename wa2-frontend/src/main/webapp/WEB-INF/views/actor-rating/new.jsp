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
    <form method="post" action="/actor-rating/new">
        <select name="actorid" id="actorid" required="required">
            <c:forEach items="${actors}" var="actor">
                <option value="${actor.getId()}">${actor.getFirstName()} ${actor.getLastName()}</option>
            </c:forEach>
        </select>
        <br>
        <input type="text" name="comment" id="comment">
        <br>
        <input type="submit" value="Rate">
    </form>
</main>
<footer>
    <jsp:include page="../includes/foot.jsp" />
</footer>
</body>
</html>