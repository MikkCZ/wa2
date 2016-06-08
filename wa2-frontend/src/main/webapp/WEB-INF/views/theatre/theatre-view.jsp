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
    <h1>${theatre.getName()}</h1>
    <c:if test="${isLoggedIn == true}">
        <form method="post" action="/theatre/${theatre.getId()}">
            <input type="text" name="name" id="name" required="required">
            <br>
            <input type="submit" value="Change">
        </form>
        <form method="post" action="/theatre/delete/${theatre.getId()}">
            <input type="submit" value="Delete">
        </form>
    </c:if>
    <a href="/theatre-rating/theatre/${theatre.getId()}">Theatre ratings (${theatreRatings})</a>
</main>
<footer>
    <jsp:include page="../includes/foot.jsp" />s
</footer>
</body>
</html>
