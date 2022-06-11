package ca.sait.finalproject.servlets;

import ca.sait.finalproject.dataaccess.UserDB;
import ca.sait.finalproject.models.*;
import ca.sait.finalproject.services.*;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cole
 */
public class ManageUsersServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService service = new UserService();

        try {
            List<User> users = service.getAll();
    
            
            request.setAttribute("users", users);
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
          
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        User user = (User) session.getAttribute("user");
        String loggedIn = (String) session.getAttribute("email");
        UserService service = new UserService();
        int roleId;
        
   
    
        if (action != null && action.equals("add")) {
            try {
                String firstName = request.getParameter("first");
                String lastName = request.getParameter("last");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String role;
                roleId = checkRole(request.getParameter("role"));

                if (roleId == 1) {
                    role = "System Admin";
                } else if (roleId == 2) {
                    role = "Regular User";
                } else {
                    role = "Company Admin";
                }

                Role newRole = new Role(roleId, role);
                
                //If adding an identical email, reload page.
                if (user.getEmail() == email){
                    action = null;
                    //exit(-1);
                 }
                
                service.insert(email, true, firstName, lastName, password, newRole);
            } catch (Exception ex) {
                Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
            }

        } else if (action != null && action.equals("delete")) {

            try {
                String email = request.getParameter("email");
                
                //Currently logged in Admins cannot delete themselves
                if (loggedIn.equals(email)){
                    
                    action = "wait";
                    session.wait();
                 } else {
                service.delete(email);
                }
            } catch (Exception ex) {
                Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
            }
        } else if (action != null && action.equals("disable")) {

            try {
                String email = request.getParameter("email");
                //Currently logged in Admins cannot diable themselves
                if (loggedIn.equals(email)){
                    
                    action = "wait";
                    session.wait();
                } else {
                service.disable(email);
                }
            } catch (Exception ex) {
                Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
                
            }
        } else if (action != null && action.equals("edit")) {
            try {
                String firstName = request.getParameter("first");
                String lastName = request.getParameter("last");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String role;
                roleId = checkRole(request.getParameter("role"));

                
                if (roleId == 1) {
                    role = "System Admin";
                } else if (roleId == 2) {
                    role = "Regular User";
                } else {
                    role = "Company Admin";
                }
                
                Role oldRole = user.getRole();
                Role newRole = new Role(roleId, role);
                
                //Currently logged in Admins cannot demote themselves
                if (loggedIn.equals(email) ){
                    user.getEmail();
                    newRole = oldRole;
                    
                }
                
                boolean status = true;

                service.update(email, true, firstName, lastName, password, newRole);
            } catch (Exception ex) {
                Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
            }
        } else if (action != null && action.equals("enable")) {
            String email = request.getParameter("email");
            
            try {
                service.activate(email);
            } catch (Exception ex) {
                Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 

        UserService userService = new UserService();
        try {
            List<User> users = userService.getAll();

            request.setAttribute("users", users);
            this.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    private int checkRole(String role) {
        int roleId;

        switch (role) {
            case "1":
                roleId = 1;
                break;
            case "2":
                roleId = 2;
                break;
            default:
                roleId = 3;
                break;
        }
        return roleId;
    }

}
