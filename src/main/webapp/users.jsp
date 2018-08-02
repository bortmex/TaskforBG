<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 02.08.2018
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
    <!-- Latest compiled and minified CSS -->
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <!-- Website CSS style -->
    <link href="css/style.css" rel="stylesheet">

    <!-- Website Font style -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
</head>
<script>
    function relocate_home() {
        location.href = "index.html";
    }
</script>
<body>
<br>
<br>
<br>
<br>

<div class="container">
    <div class="btn-toolbar">
        <input type="button" class="btn btn-info" value="Back start page" onclick=" relocate_home()">
    </div>
    <div class="well">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Birthday</th>
                <th>Email</th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${users}" var="user">
                <jsp:useBean id="user" scope="page" type="com.javaproject.model.User"/>
                <tr>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.birthday}</td>
                    <td>${user.email}</td>
                    <td> <a href="users?action=delete&id=${user.id}" role="button" data-toggle="modal"><i class="icon-remove"></i></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
