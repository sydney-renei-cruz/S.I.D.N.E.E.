<%-- 
    Document   : Register
    Created on : Feb 25, 2016, 4:20:52 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Register</h1>
        <form action="Register" method="POST" enctype="multipart/form-data">
            User ID: <input type="text" name="userID" required><br>
            Username: <input type="text" name="username" required><br>
            Password: <input type="password" name="password" required><br>
            Image: <input type="file" name="image" required><br>
            <button type="submit"> Submit </button>
        </form>
    </body>
</html>
