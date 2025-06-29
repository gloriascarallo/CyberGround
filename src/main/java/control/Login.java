package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import dao.RegisteredUserDaoDataSource;

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
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String hasPassword=Security.toHash(password);
		String errors="";
		RequestDispatcher dispatcherToLoginPage=request.getRequestDispatcher("/view/login.jsp");
		
		if(username==null || username.trim().equals("")) {
			
			errors+="Inserisci l'username<br>";
		} 
		
		else {
			username=username.trim();
			
		}
		
if(password==null || password.trim().equals("")) {
			
			errors+="Inserisci la password<br>";
		} 
		
		else {
			password=password.trim();
			
		}

if(!errors.equals("")) {
	
	request.setAttribute("errors", errors);
	dispatcherToLoginPage.forward(request, response);
}

	RegisteredUserDaoDataSource ds=new RegisteredUserDaoDataSource();
	
	try {
		// da aggiungere confronto con tabella admin
		if(ds.doRetrieveByKey(username).getUsername().equals(username) && ds.doRetrieveByKey(username).getPassword().equals(hasPassword)) {
		request.getSession().setAttribute("isAdmin", Boolean.FALSE);
		request.getSession().setAttribute("isRegisteredUser", Boolean.TRUE);
		//request.getSession().setAttribute("id", ds.doRetrieveByKey(username).getId()); perche l'id nella sessione c'è dall'inizio
		request.getSession().setAttribute("username", username);
		response.sendRedirect("/view/index.jsp");
		
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
	
	response.sendRedirect("/view/index.jsp");
	return;
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
