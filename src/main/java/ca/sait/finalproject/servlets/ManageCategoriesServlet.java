/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ca.sait.finalproject.servlets;

import ca.sait.finalproject.models.Category;
import ca.sait.finalproject.models.Role;
import ca.sait.finalproject.services.CategoryService;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ManageCategoriesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //    String action = request.getParameter("action");
        CategoryService service = new CategoryService();
        Category cat = (Category) session.getAttribute("category");

        try {

            // int id = cat.getId();
            List<Category> categories = service.getAll();
            request.setAttribute("categories", categories);

            this.getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManageCategoriesServlet.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        CategoryService service = new CategoryService();
        //Category cat = (Category) session.getAttribute("category");
        int catid;

        if (action != null && action.equals("add")) {
            try {
                String name = request.getParameter("cname");
               service.insert(name);
               

            } catch (Exception ex) {
                Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex);
            }

        } else if (action != null && action.equals("edit")) {
            try {

                catid = Integer.parseInt(request.getParameter("ename"));
                String rename = request.getParameter("newcat");
                
                service.update(catid, rename);

            } catch (Exception e) {

                e.printStackTrace();
                Logger.getLogger(ManageCategoriesServlet.class.getName()).log(Level.SEVERE, null, e);
            }

        }
        
            try {

                // int id = cat.getId();
                List<Category> categories = service.getAll();
                request.setAttribute("categories", categories);

                this.getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ManageCategoriesServlet.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }
    
/*
    private int checkCategory(String category) {
        CategoryService service = new CategoryService();
        int counter = 0;
        int catid;
        int id;
        while (true) {
            try {

                //  catid = service.get(counter);
                id
                        = counter++;

                Category categories = (Category) service.getAll();
                //    int id;

                id = categories.getId();

            } catch (Exception ex) {
                Logger.getLogger(ManageCategoriesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            //for(int i = 0; i <)
            switch (category) {
                case "1":
                    catid = 1;
                    break;
                case "2":
                    catid = 2;
                    break;
                default:
                    catid = 3;
                    break;
            }
            return catid;
        }
    }
*/

