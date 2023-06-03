<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${param.title}</title>


<c:url value="/resources/css/bootstrap.min.css" var="bootCss"></c:url>
<c:url value="/resources/js/bootstrap.bundle.min.js" var="bootJs"></c:url>

<link rel="stylesheet" href="${bootCss}" />
<scipt scr="${bootJs}"></scipt>
</head>
<body>

</body>
</html>