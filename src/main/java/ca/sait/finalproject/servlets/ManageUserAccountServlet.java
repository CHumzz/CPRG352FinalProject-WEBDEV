/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ca.sait.finalproject.servlets;

import ca.sait.finalproject.dataaccess.UserDB;
import ca.sait.finalproject.models.*;
import ca.sait.finalproject.services.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cole
 */
public class ManageUserAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDB userDB = new UserDB();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        
        try {
            User user = (User) userDB.get(email);
            
            if (user.isActive()){
              //  wait(4000);
                getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                
            } else {
  
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ManageUserAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     //   getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDB userDB = new UserDB();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String action = request.getParameter("action");
        UserService service = new UserService();
        String message;
        int roleId;
        
        
            try {
            User user = (User) userDB.get(email);
            
            if (user.isActive()){
            if (action != null && action.equals("edit")) {
                try {
                    String firstName = request.getParameter("first");
                    String lastName = request.getParameter("last");
                    email = request.getParameter("email");
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
                    service.update(email, true, firstName, lastName, password, newRole);
                    message = "You have successfully changed your information! Please click the login button below.";
                    request.setAttribute("message", message);
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);

                    System.out.println(ex);
                }

            } else if (action != null && action.equals("disable")) {

                try {
                    email = request.getParameter("email");
                    service.disable(email);
                    message = "You have successfully disabled your account. Redirecting you to Login Page";
                    request.setAttribute("message", message);
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex);

                } finally {
                    //getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                   // response.sendRedirect("/WEB-INF/login.jsp");
                }
            }
            } else {
                  //these cause a white page
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }

            //getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            } catch (Exception ex) {
            Logger.getLogger(ManageUserAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        

    }
}
