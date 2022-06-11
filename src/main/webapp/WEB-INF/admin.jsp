<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    </head>
    <body>


        <div class='container'> 
            <div class='row' align="center">
                <div class="col">
                    <h1> Welcome, ${user.firstName}</h1>
                    <h2>User Management System</h2>
                    <h6> <i>Currently signed in:</i> ${email}</h6>
                    <p> <b>Note:</b> You will <u>not</u> be able to make <i>critical changes</i> to <u>yourself</u>.<br> Request changes through another Admin</p><br>
                    <p> You can view and manage <a href="category"><i>Categories</i></a></p><br><br>
                    
                <table class="table">
                    <thead>
                        <tr>
                            <th>E-mail</th>
                            <th>First Name</th>
                            <th>Last Name</th>   
                            <th>Active</th> 
                            <th>Role</th>
                            <th>Password</th>
                            <th>Actions</th>  
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.email}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.active ? "Y" : "N"}</td>
                                <td>${user.role.name}</td>
                                <td>${user.password}</td>
                                <td>
                                    <form action ="admin" method="post">
                                        <input type="hidden" name="action" value="disable">
                                        <input type="hidden" name="email" value="${user.email}">
                                        <button type="submit">Disable</button>
                                    </form>
                                </td>
                                <td>
                                    <form action ="admin" method="post">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="email" value="${user.email}">
                                        <button type="submit">Delete</button>
                                    </form>
                                </td>
                                <td>
                                    <form action ="admin" method="post">
                                        <input type="hidden" name="action" value="enable">
                                        <input type="hidden" name="email" value="${user.email}">
                                        <button type="submit">Enable</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>

                </table>
                </div>
            </div>
                    <div class='row' align="center">
                        <p> You may modify the system below.<br> <b>Note:</b> New users can <u>not</u> be created if the email used to add them already exists in our system. 
                            If you would like to make any changes to existing users, type in their email address followed by the information you wish to change. All fields must be filled.<br> Email's are <u>non-changeable</u>.</p>
                    </div>
            <div class='row' align="center">
                <div class="col">
                <h2>Add user</h2>
                <p> Enter the information:</p>
                <form action="admin" method="post">
                    <input type="hidden" name="action" value="add">

                    <label for="email">E-mail: </label>
                    <input type="text" name="email" id="email">
                    <br>
                    <label for="first">First Name: </label>
                    <input type="text" name="first" id="first">
                    <br>
                    <label for="last">Last Name: </label>
                    <input type="text" name="last" id="last">
                    <br>
                    <label for="password">Password: </label>
                    <input type="text" name="password" id="password">
                    <br>
                    <label for="role">Role: </label>
                    <select name="role" id="role">
                        <option value="1">System Admin</option>
                        <option value="2">Regular User</option>
                        <option value="3">Company Admin</option>
                    </select>

                    <button type="submit">Add</button>
                </form>
                </div>
                
                <div class="col">
                <h2> Edit user </h2>
                <p> Edit accounts using account email:</p>
                <form action="admin" method="post">
                    <input type="hidden" name="action" value="edit">

                    <label for="email">E-mail: </label>
                    <input type="text" name="email" id="email">
                    <br>
                    <label for="first">First Name: </label>
                    <input type="text" name="first" id="first">
                    <br>
                    <label for="last">Last Name: </label>
                    <input type="text" name="last" id="last">
                    <br>
                    <label for="password">Password: </label>
                    <input type="text" name="password" id="password">
                    <br>
                    <label for="role">Role: </label>
                    <select name="role" id="role">
                        <option value="1">System Admin</option>
                        <option value="2">Regular User</option>
                        <option value="3">Company Admin</option>
                    </select>

                    <button type="submit">Edit</button>
                </form>
                </div>
                </div>
            
            
        </div><br><br>
                <div align="center">
                    <a href="login">Logout</a>
                </div>
            
        
    </body>
</html>
