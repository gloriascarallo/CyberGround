package control;

import java.io.IOException;
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
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		Boolean isRegisteredUser = (Boolean) httpServletRequest.getSession().getAttribute("isRegisteredUser");
		Boolean isAdmin = (Boolean) httpServletRequest.getSession().getAttribute("isAdmin");
		String path = httpServletRequest.getServletPath();
		System.out.println(path);
		if (path.contains("/registeredUser/") && (isRegisteredUser==null || !isRegisteredUser)) {	
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
			return;
		} else if (path.contains("/admin/") && (isAdmin==null || !isAdmin)) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
			return;
		}

		chain.doFilter(request, response);
	}
}
