<%-- 
    Document   : inventory
    Created on : 17-Apr-2022, 2:42:07 PM
    Author     : Cole
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Items Page</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>
    <body>
        <br>
        <div class='container'> 
            <div class='row' align="center">
                <div class="col">
                    <h1> Welcome, ${user.firstName}</h1>
                    <h2>Inventory System</h2><br>
                    
                <table class="table">
                    <thead>
                        <tr>
                            
                            <th>Category</th>
                            <th>Item Name</th>   
                            <th>Price $</th> 
                            <th>Owner</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${items}">
                            <tr>
                                
                                <td>${item.category.name}</td>
                                <td>${item.name}</td>       
                                <td>${item.price}</td>
                                <td>${item.owner}</td>
                                <td>
                                <form action ="inventory" method="post">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="owner" value="${item.owner}">
                                        <button type="submit">Delete</button>
                                </form>
                                </td>
                                 </tr>
                        </c:forEach>
                    </tbody>

                </table>
                </div>
            </div>
                    <br><p align="center"> ${message}</p>   
            <div class='row' align="center">
                <div class="col">
                <h2>Add Item</h2>
                <form action="inventory" method="post">
                    <input type="hidden" name="action" value="add">

                    <label for="categ">Category:</label>
                    <select name="categ" id="categ">
                        <c:forEach var="title" items="${categories}">
                            <option value="${title.name}">${title.name}</option> 
                        </c:forEach>
                    </select>
                    <br>
                    <label for="name">Item Name:</label>
                    <input type="text" name="name" id="name">
                    <br>
                    <label for="cost">Price:&nbsp;&nbsp;&nbsp;</label>
                    <input type="number" name="cost" id="cost">
                    <br><br>

                    <button type="submit">Add Item</button>
                </form>
                </div>
                
                <div class="col">
                <h2> Edit Item </h2>
                <form action="inventory" method="post">
                    <input type="hidden" name="action" value="edit">

                    <label for="iname">Item Name:</label>
                    <select name="iname" id="iname">
                        <c:forEach var="item" items="${items}">
                            <option value="${item.name}">${item.name}</option> 
                        </c:forEach>
                    </select>&nbsp;&nbsp;
                    <label for="name">New Item Name:</label>
                    <input type="text" name="name" id="name">
                    <br>
                    <label for="categ">Category:</label>
                    <select name="categ" id="categ">
                        <c:forEach var="title" items="${categories}">
                            <option value="${title.name}">${title.name}</option> 
                        </c:forEach>
                    </select>&nbsp;&nbsp;
                    <label for="newcateg">New Category: </label>
                    <select name="newcateg" id="newcateg">
                        <c:forEach var="title" items="${categories}">
                            <option value="${title.name}">${title.name}</option> 
                        </c:forEach>
                    </select>&nbsp;&nbsp;
                    <br>
                    <label for="price">New Price:</label>
                    <input type="number" name="price" id="price">
                    <br><br>
                    <button type="submit">Confirm Edits</button>
                </form>
                </div>
            </div> <br>   
                                 
        </div>
                    <div align="center">
                    <a href="user">My Account</a><br>
                    <a href="login">Logout</a>
                    </div>          
    </body>
</html>
