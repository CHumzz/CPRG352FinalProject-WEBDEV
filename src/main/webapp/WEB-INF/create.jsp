<%-- 
    Document   : create
    Created on : 15-Apr-2022, 3:35:55 PM
    Author     : Cole
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <h1 align="center">Hello! Create your account below</h1>
        
        <div class='row' align="center">
                <div class="col">
                <h2>Your Information:</h2>
                <form action="create" method="post">
                    <input type="hidden" name="action" value="add">

                    <label for="email">E-mail</label>
                    <input type="text" name="email" id="email">
                    <br>
                    <label for="first">First Name</label>
                    <input type="text" name="first" id="first">
                    <br>
                    <label for="last">Last Name</label>
                    <input type="text" name="last" id="last">
                    <br>
                    <label for="password">Password</label>
                    <input type="text" name="password" id="password">
                    <br>
                    <button type="submit">Create</button>
                </form>
                <p> ${message}</p>
                <a href="login">Login</a>
                </div>
    </body>
</html>
