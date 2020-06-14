<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#checkBoxAll').click(function () {
                if ($(this).is(":checked"))
                    $('.chkCheckBoxId').prop('checked', true);
                else
                    $('.chkCheckBoxId').prop('checked', false);
            });
        });
    </script>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/admin.css">
</head>

<body>
<div>
    <form action="${pageContext.request.contextPath}/admin" method="post" class="formdb">
        <button type="submit" name="action" value="delete" class="btn"><i class="fa fa-trash"></i></button>
        <button type="submit" name="action" value="block" class="btn"><i class="fa fa-lock"></i></button>
        <button type="submit" name="action" value="unblock" class="btn"><i class="fa fa-unlock"></i></button>

        <sec:authorize access="isAuthenticated()">
            <button type="button" onclick="location.href='/logout'" class="btn btn-primary pull-right">Logout</button>
        </sec:authorize>

        <table>
            <thead>
            <td><input type="checkbox" id="checkBoxAll"> <Br></td>
            <th>ID</th>
            <th>UserName</th>
            <th>Email</th>
            <th>Registration Date</th>
            <th>lastlogin</th>
            <th>status</th>
            <th>Roles</th>
            </thead>
            <c:forEach items="${allUsers}" var="user">
                <tr>
                    <td><input type="checkbox" class="chkCheckBoxId" value="${user.id}" name="username"> <Br></td>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.registrationdate}</td>
                    <td>${user.lastlogin}</td>
                    <td>${user.status}</td>
                    <td>
                        <c:forEach items="${user.roles}" var="role">${role.name}; </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>

</div>
</body>
</html>