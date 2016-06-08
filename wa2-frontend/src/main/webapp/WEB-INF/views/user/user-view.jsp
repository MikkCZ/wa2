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
    <h1>${user.getEmail()}</h1>
    <c:if test="${isLoggedIn == true}">
        <form method="post" action="/user/${user.getId()}">
            <input type="email" name="email" id="email" required="required">
            <br>
            <input type="password" name="password" id="password">
            <br>
            <input type="submit" value="Change">
        </form>
        <form method="post" action="/user/delete/${user.getId()}">
            <input type="submit" value="Delete">
        </form>
    </c:if>
    <a href="/actor-rating/user/${user.getId()}">Actor ratings (${actorRatings})</a>
    <br>
    <a href="/play-rating/user/${user.getId()}">Play ratings (${playRatings})</a>
    <br>
    <a href="/theatre-rating/user/${user.getId()}">Theatre ratings (${theatreRatings})</a>
</main>
<footer>
    <jsp:include page="../includes/foot.jsp" />
</footer>
</body>
</html>
