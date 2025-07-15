package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.RegisteredUser_has_addressBean;
import dao.RegisteredUser_has_addressDaoDataSource;

/**
 * Servlet implementation class RegisteredUserAddresses
 */
@WebServlet("/RegisteredUserAddresses")
public class RegisteredUserAddresses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisteredUserAddresses() {
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
	    
		ArrayList<RegisteredUser_has_addressBean> user_addresses;
		RegisteredUser_has_addressDaoDataSource ds_has_address=new RegisteredUser_has_addressDaoDataSource();
		try {
		user_addresses=ds_has_address.doRetrieveByIdRegisteredUser(id);
		
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.html").forward(request, response);
			return;
		}
		
		if(user_addresses==null || user_addresses.isEmpty()) {
			
			errors+="Indirizzi non trovati.<br>";
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/registeredUser/view/registeredUser_addresses.jsp").forward(request, response);
			return;
			
		}
		
		request.setAttribute("user_addresses", user_addresses);
		request.getRequestDispatcher("/registeredUser/view/registeredUser_addresses.jsp").forward(request, response);
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
