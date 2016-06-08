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
    <c:choose>
        <c:when test="${result == null}">
            <h1>No unicorn here yet. Please click <a href="">here</a> to refresh.</h1>
        </c:when>
        <c:otherwise>
            <h1>Here is the truth: ${result}</h1>
        </c:otherwise>
    </c:choose>
</main>
<footer>
    <jsp:include page="../includes/foot.jsp" />
</footer>

<script>
    var done = false;

    function doPoll() {
        if(!done) {
            var xhr = new XMLHttpRequest();
            xhr.addEventListener("readystatechange", function() {
                if(xhr.status == 200) {
                    document.querySelector("h1").innerHTML = JSON.parse(xhr.responseText)["result"];
                    done = true;
                }
            });
            xhr.open("GET", "/asyncrest/${resultId}", true);
            xhr.send();
        }
    }

    var interval = setInterval(doPoll, 5000);
</script>

</body>
</html>
