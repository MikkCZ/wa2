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
    <h1>${actor.getFirstName()} ${actor.getLastName()}</h1>
    <a href="/actor-rating/actor/${actor.getId()}">Actor ratings (${actorRatings.size()})</a>
    <br>
    <table>
        <c:forEach items="${actorRatings}" var="actorRating">
            <tr>
                <td>${actorRating.getRatedBy().getEmail()} rated ${actor.getFirstName()} ${actor.getLastName()}:</td>
                <td>${actorRating.getComment()}</td>
            </tr>
        </c:forEach>
    </table>

    <br>

    <a href="/actor/theatres/${actor.getId()}">Theatres (${theatres.size()})</a>
    <br>
    <table>
        <c:forEach items="${theatres}" var="theatre">
            <tr>
                <td>${theatre.getName()}</td>
            </tr>
        </c:forEach>
    </table>

    <c:if test="${isLoggedIn == true}">
        <form method="post" action="/actor/${actor.getId()}">
            <input type="text" name="firstname" id="firstname" required="required">
            <br>
            <input type="text" name="lastname" id="lastname" required="required">
            <br>
            <input type="submit" value="Change">
        </form>
        <form method="post" action="/actor/delete/${actor.getId()}">
            <input type="submit" value="Delete">
        </form>
    </c:if>
</main>
<footer>
    <jsp:include page="../includes/foot.jsp" />
</footer>
</body>
</html>
