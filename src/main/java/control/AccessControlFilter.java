package control;

import java.io.IOException;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AccessControlFilter
 */
@WebFilter(filterName = "/AccessControlFilter", urlPatterns = {"/registeredUser/*", "/admin/*"})
public class AccessControlFilter extends HttpFilter implements Filter {
    
	private static final long serialVersionUID = 1L;

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
	        throws IOException, ServletException {

	    HttpServletRequest httpRequest = (HttpServletRequest) request;
	    HttpServletResponse httpResponse = (HttpServletResponse) response;

	    Boolean isRegisteredUser = (Boolean) httpRequest.getSession().getAttribute("isRegisteredUser");
	    Boolean isAdmin = (Boolean) httpRequest.getSession().getAttribute("isAdmin");
	    String path = httpRequest.getServletPath();
	    DispatcherType dispatcherType = httpRequest.getDispatcherType();

	    System.out.println("Filtro path: " + path + ", dispatcher: " + dispatcherType);

	    
	    if (dispatcherType == DispatcherType.REQUEST) {
	        if (path.contains("/registeredUser/") && (isRegisteredUser == null || !isRegisteredUser)) {
	            httpResponse.sendRedirect(httpRequest.getContextPath() + "/error/accessDeniedRegisteredUser.jsp");
	            return;
	        } else if (path.contains("/admin/") && (isAdmin == null || !isAdmin)) {
	            httpResponse.sendRedirect(httpRequest.getContextPath() + "/error/accessDeniedAdmin.jsp");
	            return;
	        }
	    }
	    

	    // Altrimenti lascia passare (anche FORWARD, INCLUDE, ERROR, ecc.)
	    chain.doFilter(request, response);
	}
	
}