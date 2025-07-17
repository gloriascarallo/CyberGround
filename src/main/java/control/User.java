package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.RegisteredUserBean;

import java.io.IOException;
import java.sql.SQLException;

import dao.RegisteredUserDaoDataSource;
/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errors="";
		Object idObj = request.getSession().getAttribute("id");
	    if (idObj == null) {
	    	errors = "Sessione scaduta o ID utente mancante. Ricarica la pagina e riprova.";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/error/expiredSession.jsp").forward(request, response);
	        return;
	        
	    }
	    int id=(Integer)idObj;
	    
		RequestDispatcher dispatchToUserPage=request.getRequestDispatcher("/registeredUser/view/user.jsp");
		RegisteredUserBean user=null;
		RegisteredUserDaoDataSource ds=new RegisteredUserDaoDataSource();
		try {
			
			user=ds.doRetrieveByKey(id);
			if (user == null) {
				errors+="Utente non trovato.<br>";
				request.setAttribute("errors", errors);
	            request.getRequestDispatcher("/UploadProducts").forward(request, response);
	            return;
			
			}
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.html").forward(request, response);
			return;
		}
		
		request.setAttribute("user", user);
		dispatchToUserPage.forward(request, response);
		return;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
