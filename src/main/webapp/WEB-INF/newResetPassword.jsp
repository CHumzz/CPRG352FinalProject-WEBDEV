<%-- 
    Document   : newResetPassword
    Created on : 10-Apr-2022, 1:23:46 PM
    Author     : Cole
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <p> If your email matches on file, the password will be updated.</p><br>
        <p> Enter email and your new password below. </p>
        
        <form action="reset" method="post">
            <input type="hidden" name="uuid" id="uuid" value="${uuid}">
            <label for="email">Enter your email: </label>
            <input type="email" name="email" id="email" required><br>
            <label for="password">New Password: </label>
            <input type="password" name="password" id="password" required>
            <button type="submit">Change Password</button>     
        </form>
            <p>${message}</p>
            <a href="login">Login</a>
    </body>
</html>
