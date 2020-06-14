<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>Главная</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/loginAndRegist.css">
</head>
<body>
<div>
    <form class="modal">
        <sec:authorize access="!isAuthenticated()">
            <button type="button" onclick="location.href='/login'" class="button">Login</button>
            <p>
            <button type="button" onclick="location.href='/registration'" class="button">Registration</button>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <h3>${pageContext.request.userPrincipal.name}</h3>
            <button type="button" onclick="document.location='/logout'" class="button">Logout</button>
        </sec:authorize>
    </form>
</div>
</body>
</html>