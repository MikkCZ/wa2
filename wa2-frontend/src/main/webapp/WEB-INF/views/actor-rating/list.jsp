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
        <c:forEach items="${actorRatings}" var="actorRating">
            <tr>
                <td><a href="/actor-rating/${actorRating.getId()}">${actorRating.getId()}</a></td>
                <td>${actorRating.getRatedBy().getEmail()} rated ${actorRating.getActor().getFirstName()} ${actorRating.getActor().getLastName()}:</td>
                <td>${actorRating.getComment()}</td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${isLoggedIn == true}">
        <a href="/actor-rating/new"><button>New</button></a>
    </c:if>
</main>
<footer>
    <jsp:include page="../includes/foot.jsp" />
</footer>
</body>
</html>