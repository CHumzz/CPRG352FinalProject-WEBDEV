package filters;

import ca.sait.finalproject.models.User;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;
import ca.sait.finalproject.models.*;


/**
 *
 * @author Cole
 */
public class AdminFilter implements Filter {
        
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        String email = (String)session.getAttribute("email");
        
        User user = (User) session.getAttribute("user");
      
        
        //Check if user is an admin or not
        
            if(user.getRole().getId() == 1){
                    chain.doFilter(request, response);
                    
            } else if( email == null){
                httpResponse.sendRedirect("login");
                return;
            }
        
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }
    
    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        
    }

}
