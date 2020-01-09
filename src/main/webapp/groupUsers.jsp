<%--
  Created by IntelliJ IDEA.
  User: osint
  Date: 12/13/19
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Szkoła programowania</title>
</head>
<body>
<%@ include file="header.jsp" %><br/>
<div class="container">
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8 shadow p-3 mb-5 bg-secondary rounded">
            <p class="text-center text-white font-weight-bolder">
                ${group.name} group users
            </p>
        </div>
        <div class="col-sm-2"></div>
    </div>
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <c:if test="${not empty groupUsers}">
                <table class="table table-sm table-striped table-bordered">
                    <thead class="thead-dark">
                    <th>User Name</th>
                    <th>Actions</th>
                    </tr>
                    </thead>
                    <c:forEach items="${groupUsers}" var="user">
                        <tr>
                            <td>${user.userName}</td>
                            <td><a href="/userdetails?userId=${user.id}">Details</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <br/><a href="javascript: window.history.go(-1)">Back</a>
        </div>
        <div class="col-sm-2"></div>
    </div>
</div>
<%@ include file="footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>

