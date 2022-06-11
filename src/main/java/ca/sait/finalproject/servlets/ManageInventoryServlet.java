/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ca.sait.finalproject.servlets;

import ca.sait.finalproject.dataaccess.CategoryDB;
import ca.sait.finalproject.dataaccess.UserDB;
import ca.sait.finalproject.models.Category;
import ca.sait.finalproject.models.Item;
import ca.sait.finalproject.services.CategoryService;
import ca.sait.finalproject.services.InventoryService;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.console;
import java.util.List;
import java.util.logging.ConsoleHandler;
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
public class ManageInventoryServlet extends HttpServlet {

 
    
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDB userDB = new UserDB();
        CategoryDB categoryDB = new CategoryDB();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String message = "";
        InventoryService service = new InventoryService();
        CategoryService s = new CategoryService();
        
        try {
             List<Item> items = service.getAll(email);
             List<Category> categories = s.getAll();

             request.setAttribute("categories", categories);
            request.setAttribute("items", items);
            if( items.isEmpty()){
                message = "You don't currently have any Items. Add some now!";
                request.setAttribute("message", message);
            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
        } catch (Exception ex) {
            message = "Your in the catch block";
            ex.printStackTrace();
            Logger.getLogger(ManageInventoryServlet.class.getName()).log(Level.SEVERE, message, ex);

        }


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);

    }


}
