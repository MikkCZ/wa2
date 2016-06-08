<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="includes/head.jsp" />
</head>
<body>
<header>
    <jsp:include page="includes/menu.jsp" />
</header>
<main>
    <form method="post" action="/login">
        <input type="email" name="email" id="email" required="required">
        <br>
        <input type="password" name="password" id="password" required="required">
        <br>
        <input type="submit" value="Login">
    </form>
</main>
<footer>
    <jsp:include page="includes/foot.jsp" />
</footer>
</body>
</html>
