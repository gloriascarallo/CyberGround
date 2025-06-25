package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import beans.RegisteredUserDaoDataSource;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=(String)request.getAttribute("username");
		String password=(String)request.getAttribute("password");
		String errors="";
		RequestDispatcher dispatcherToLoginPage=request.getRequestDispatcher("login.jsp");
		username=username.trim();
		password=password.trim();
		
	RegisteredUserDaoDataSource ds=new RegisteredUserDaoDataSource();
	
	try {
		// da aggiungere confronto con tabella admin
		if(ds.doRetrieveByKey(username).getUsername().equals(username) && ds.doRetrieveByKey(username).getPassword().equals(password)) {
		request.getSession().setAttribute("isAdmin", Boolean.FALSE);
		request.getSession().setAttribute("isRegisteredUser", Boolean.TRUE);
		response.sendRedirect("index.jsp");
		
	}
		else {
			errors+="Username e password non validi!<br>";
			request.setAttribute("errors", errors);
			dispatcherToLoginPage.forward(request, response);
			
		}
		
		
	}
	
	catch(SQLException e) {
		e.printStackTrace();
	    dispatcherToLoginPage.forward(request, response);
	
	}
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
