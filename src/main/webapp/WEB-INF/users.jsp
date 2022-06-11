<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    </head>
    <body>


        <div class='container'> 
            <div class='row' align="center">
                <div class="col">
                    <h1> Welcome, ${user.firstName}</h1>
                    <br>
                    <div class="col">
                        <h2> Manage your information: </h2>
                        <form action="user" method="post">
                            <input type="hidden" name="action" value="edit">

                            <label for="email">E-mail</label>
                            <input type="text" name="email" id="email" value="${user.email}">
                            <br>
                            <label for="first">First Name</label>
                            <input type="text" name="first" id="first" value="${user.firstName}">
                            <br>
                            <label for="last">Last Name</label>
                            <input type="text" name="last" id="last" value="${user.lastName}">
                            <br>
                            <label for="password">Password</label>
                            <input type="text" name="password" id="password">
                            <br><br>
                            <p> Confirming Changes will redirect you to log in page.</p>
                            <button type="submit">Confirm Edits</button><br><br><!-- comment -->

                        </form>
                    </div><br><br><br>
                    <div class="row" >
                        <p> To disable your account, please click this button below. You will be redirected to log in page<br><br>
                            <b>Note:</b> Once disabled, you will not be able to sign-in. Contact support to reactivate your account.<br> 
                            </p>
                                <form action ="user" method="post">
                                <input type="hidden" name="action" value="disable">
                                <input type="hidden" name="email" value="${user.email}">
                                <button type="submit">Disable</button>
                                </form>
                    </div>
                        <br><br><br><p> ${message}</p>
                        <a href="inventory">My Items</a>  <br>  
                    <a href="login">Logout</a>
                    
                    <!--need inventory information here-->
                </div>
            </div>
        </div>


    </body>
</html>
