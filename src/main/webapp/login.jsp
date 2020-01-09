<%--
  Created by IntelliJ IDEA.
  User: osint
  Date: 12/13/19
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<%@ include file="header.jsp" %>
<br/>
<div class="container">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8 shadow p-3 mb-5 bg-secondary rounded">
            <p class="text-center text-white font-weight-bolder">
                Login
            </p>
        </div>
        <div class="col-sm-2"></div>
    </div>
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <form class="form-group" method="post" action="/login">
                Enter email<input class="ml-4 mb-1" type="text"  name="email"/><br/>
                Enter password<input class="ml-2" name="password" type="password"/><br/>
                <button type="submit" class="btn btn-secondary" value="Login">Login</button>
            </form>
            <a href="javascript: window.history.go(-1)">Back</a><br/>
        </div>
        <div class="col-sm-2"></div>
    </div>
</div>
<%@ include file="footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
