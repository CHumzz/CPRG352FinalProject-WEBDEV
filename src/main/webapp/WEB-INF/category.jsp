<%-- 
    Document   : category
    Created on : 18-Apr-2022, 3:50:23 PM
    Author     : Cole
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categories</title>
    </head>
    <body>
        <div class='container'> 
            <div class='row' align="center">
                <div class="col">
                    <h1> Welcome, ${user.firstName}</h1>
                    <h2>Manage Categories</h2><br>

                    <table class="table">
                        <thead>
                            <tr>

                                <th>Category ID &nbsp;&nbsp;</th>
                                <th>Category Name</th>   
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="category" items="${categories}">
                                <tr>

                                    <td>&nbsp;${category.id}</td>

                                    <td>&nbsp;${category.name}</td>

                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                </div>
            </div>

            <div class='row' align="center">
                <div class="col">
                    <h2>Add Category</h2>
                    <form action="category" method="post">
                        <input type="hidden" name="action" value="add">

                        <label for="cname">Category Name: </label>
                        <input type="text" name="cname" id="cname">
                        <button type="submit">Add Category</button>
                    </form>

                </div><br><br>
                <h2>Edit Category </h2>
                <p> Select the Category number you would like to edit:</p>

                <form action="category" method="post">
                    <input type="hidden" name="action" value="edit">

                    <label for="ename"> Category Number: </label>
                    <input type="number" name="ename" id="ename"><br>

                    <label for="newcat">New Category Name: </label>
                    <input type="text" name="newcat" id="newcat"><br><br>
                    <button type="submit">Confirm Edits </button>
                </form>
                <br>

                <a href="admin">Manage Users</a><br>
                <a href="login">Logout</a>
                </body>
                </html>
