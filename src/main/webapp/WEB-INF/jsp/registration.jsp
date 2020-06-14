<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/loginAndRegist.css">
</head>

<body>
<div>
    <form:form method="POST" modelAttribute="userForm" class="modal">
        <h2>Registration</h2>
        <div>
            <form:input type="text" path="username" placeholder="Username"
                           autofocus="true"></form:input>
            <div class="error_message">
                <form:errors path="username"></form:errors>${usernameError}</div>


            <p><form:input type="text" path="email" placeholder="Email"
                           autofocus="true"></form:input></p>

            <form:input type="password" path="password" placeholder="Password"></form:input>
            <div class="error_message"><form:errors path="password"></form:errors></div>
            <p></p>
            <form:input type="password" path="passwordConfirm"
                           placeholder="Confirm your password"></form:input>
            <div class="error_message">${passwordError}</div>

        </div>
        <p>
            <button type="submit" class="button">Registrate</button>
        </p>
        <h3><a href="/">Home</a></h3>
    </form:form>

</div>
</body>
</html>