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
    <form method="post" action="/async/new">
        <input type="submit" value="New unicorn">
    </form>
</main>
<footer>
    <jsp:include page="../includes/foot.jsp" />
</footer>
</body>
</html>
