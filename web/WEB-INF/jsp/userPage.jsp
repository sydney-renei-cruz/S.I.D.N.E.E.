<%-- 
    Document   : userPage
    Created on : 02 2, 16, 8:24:58 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="navbar.jsp" %>
        <link type="text/css" rel="stylesheet" href="css/main.css"/>
    </head>
    <body>
        <div class="container page-header">
            <center>
                <div class="img-con">
                    <img src="img/syd.jpg" class="img-circle" style=""> 
                </div>
            </center>
        </div>
        <div class="container">
            <center>
                <h1 class="userName">Sydney Cruz</h1>
                <h2 class="userID" style="font-size: 1.5em;">1111</h2>
                <div style="font-size: 2em">
                    <a class="btn-lg btn btn-info" href="registerUser.jsp" style="color: #fff !important;"> Register Another User </a>
                    <a class="btn-lg btn btn-warning" href="#" style="color: #fff !important;"> Log Out </a>
                </div>
            </center> 
        </div>
    </body>
</html>
