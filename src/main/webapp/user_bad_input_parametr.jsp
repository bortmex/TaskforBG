<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 02.08.2018
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Не корректные данные</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <!-- Website CSS style -->
    <link href="css/style.css" rel="stylesheet">

    <!-- Website Font style -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>

    <script>
        function relocate_home() {
            location.href = "index.html";
        }
    </script>
</head>
<body>
<br>
<br>
<br>
<br>
<div class="container">
    <div class="row main">
        <div class="main-login main-center">
            <h5 style="text-align: center">The entered data is incorrect</h5>

            <div class="form-group" style="text-align: center">
                <a href="users?action=create" type="button" id="button1" class="btn btn-primary btn-lg btn-block login-button">Try to enter the data again</a>
                <a href="index.html" type="button" id="button" class="btn btn-primary btn-lg btn-block login-button">Back
                    start page</a>
            </div>
        </div>

    </div>
</div>
</body>
</html>
