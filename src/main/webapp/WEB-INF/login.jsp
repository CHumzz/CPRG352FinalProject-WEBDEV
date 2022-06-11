<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME nVentory</title>
    </head>
    <body>
        <h1>Welcome to HOME nVentory </h1>
        <h2>Login</h2>
        <form action="login" method="post">
            email: <input type="text" name="email"><br>
            password: <input type="password" name="password"><br>
            <input type="submit" value="Sign in">
        </form>
        <a href="reset">Forgot your Password?</a> <a href="create">Create an account</a> 
    </body>
</html>
