/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ca.sait.finalproject.servlets;

import ca.sait.finalproject.models.Role;
import ca.sait.finalproject.models.User;
import ca.sait.finalproject.services.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cole
 */
public class CreateAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/create.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        UserService service = new UserService();
        String message;
        int roleId;

        if (action != null && action.equals("add")) {
            try {
                String firstName = request.getParameter("first");
                String lastName = request.getParameter("last");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String role;
                roleId = 2;

                if (roleId == 1) {
                    role = "System Admin";
                } else if (roleId == 2) {
                    role = "Regular User";
                } else {
                    role = "Company Admin";
                }

                Role newRole = new Role(roleId, role);
                service.insert(email, true, firstName, lastName, password, newRole);
                message = "You have successfully signed-up! Please click the login button below.";
                request.setAttribute("message", message);
                getServletContext().getRequestDispatcher("/WEB-INF/create.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                
                System.out.println(ex);
            }

                
            }


    }

}

