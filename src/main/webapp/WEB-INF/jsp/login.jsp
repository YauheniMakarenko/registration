<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/loginAndRegist.css">
</head>

<body>
<div>
    <form method="POST" action="/login" class="modal">
        <h2>Login</h2>
        <div class="someDiv">
            <p><input name="username" type="text" placeholder="Username" autofocus="true"/><br></p>
            <p><input name="password" type="password" placeholder="Password"/><br></p>
            <button type="submit" class="button">Log In</button>
            <h4><a href="/registration">Create new Account</a></h4>
        </div>
    </form>
</div>
</body>
</html>