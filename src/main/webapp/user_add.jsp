<%--
  Created by IntelliJ IDEA.
  User: alexa
  Date: 02.08.2018
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление User</title>
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
    <jsp:useBean id="user" type="com.javaproject.model.User" scope="request"/>
    <div class="row main">
        <div class="main-login main-center">
            <h5 style="text-align: center">Add User</h5>
            <form method="post" action="users">
                <div class="form-group">
                    <label for="name" class="cols-sm-2 control-label">Your Name</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" value="${user.name}" name="name" id="name"  placeholder="Enter your Name"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="surname" class="cols-sm-2 control-label">Your Surname</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" value="${user.surname}" name="surname" id="surname"  placeholder="Enter your Surname"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="dateBirthday" class="cols-sm-2 control-label">Your Date Birthday</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-text">Data</span>
                            <input type="date" class="form-control" value="${user.birthday}" name="dateBirthday" id="dateBirthday"  placeholder="Enter your Date Birthday"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="cols-sm-2 control-label">Your Email</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" value="${user.email}" name="email" id="email"  placeholder="Enter your Email"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="cols-sm-2 control-label">Password</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                            <input type="password" class="form-control" value="${user.password}" name="password" id="password"  placeholder="Enter your Password"/>
                        </div>
                    </div>
                </div>
                <div class="form-group ">
                    <button class="btn btn-primary btn-lg btn-block login-button" type="submit">Save</button>
                    <input class="btn btn-primary btn-lg btn-block login-button" type="button" value="Back start page" onclick=" relocate_home()">
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>
