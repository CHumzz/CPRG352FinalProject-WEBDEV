<%-- 
    Document   : reset
    Created on : 8-Apr-2022, 1:29:54 PM
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
        <h1>Forgot Password</h1>
        <form action="reset" method="post">
            <p> Please enter your email address</p><br>
            <label for="email">Email Address: &nbsp;</label>
            <input type="email" name="email" id="email">
            
            <input type="submit" value="Submit">
        </form>
      <p> ${message} </p>
      <a href="login">Login</a>
        
    </body>
</html>
