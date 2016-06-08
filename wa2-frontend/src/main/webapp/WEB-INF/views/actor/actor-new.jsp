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
    <form method="post" action="/actor/new">
        <input type="text" name="firstname" id="firstname" required="required">
        <br>
        <input type="text" name="lastname" id="lastname" required="required">
        <br>
        <input type="submit" value="Create">
    </form>
</main>
<footer>
    <jsp:include page="../includes/foot.jsp" />
</footer>
</body>
</html>
