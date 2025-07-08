package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.RegisteredUser_has_method_paymentBean;
import dao.RegisteredUser_has_method_paymentDaoDataSource;

/**
 * Servlet implementation class RegisteredUserMethodsPayment
 */
@WebServlet("/RegisteredUserMethodsPayment")
public class RegisteredUserMethods_Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisteredUserMethods_Payment() {
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
	        request.getRequestDispatcher("/view/index.jsp").forward(request, response);
	        return;
	    }
	    int id=(Integer)idObj;
	    
		RegisteredUser_has_method_paymentDaoDataSource ds_has_methods_payment=new RegisteredUser_has_method_paymentDaoDataSource();
		ArrayList<RegisteredUser_has_method_paymentBean> user_methods_payment;
		try {
		user_methods_payment=ds_has_methods_payment.doRetrieveByIdRegisteredUser(id);
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/500.html").forward(request, response);
			return;
		}
		
		if(user_methods_payment==null || user_methods_payment.isEmpty()) {
			
			errors+="Metodi di pagamento non trovati.<br>";
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/view/registeredUser_methods_payment.jsp").forward(request, response);
			return;
			
		}
		
	
		request.setAttribute("user_methods_payment", user_methods_payment);
		request.getRequestDispatcher("/view/registeredUser_methods_payment.jsp").forward(request, response);
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
