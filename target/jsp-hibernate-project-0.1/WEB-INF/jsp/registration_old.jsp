
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Create User Page</title>
    <link href="/resources/styles2.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please input New User info!
    </div>



<%--    <c:url value="/create" var="var"/>--%>
<%--    <form action="${var}" method="POST">--%>

<%--<form:form method="POST" modelAttribute="userForm" class="form-signin">--%>
    <form method="post" modelAttribute="userForm" action="/registration">

    <label for="name">User name
        <input class="input-field" type="text" required="required" id="name" name="name">
    </label>

    <label for="username">Username
        <input class="input-field" type="text" required="required" id="username" name="username">
    </label>
    <label for="password">Password
        <input class="input-field" type="password" required="required" id="password" name="password">
    </label>
    <select name="role" id="role">User rights (user or admin)>
        <option value="user" selected>User credentials</option>
        <option value="admin">Admin credentials</option>
    </select>
    <hr>
    <input type="submit" value="Create New User">

    </form>


    <a href="<c:url value='/logout' />">Logout</a>


    <div class="form-style-2-heading">
        Already registered! Это я не додумал.


    </div>


</div>
</body>
</html>
