package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import bean.RegisteredUser_has_method_paymentBean;
import dao.RegisteredUser_has_method_paymentDaoDataSource;

/**
 * Servlet implementation class Add_payment_method
 */
@WebServlet("/Add_payment_method")
public class Add_payment_method extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_payment_method() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id=(Integer)request.getSession().getAttribute("id");
		String pan=request.getParameter("PAN");
		String expirationDate=request.getParameter("Scadenza");
		String cvc=request.getParameter("CVC");
		String url=request.getRequestURI();
		
		RequestDispatcher dispatcherToAdd_payment_methodPage=request.getRequestDispatcher("/view/add_payment_method.jsp");
		RegisteredUser_has_method_paymentBean registereduser_has_method_payment=new RegisteredUser_has_method_paymentBean();
		RegisteredUser_has_method_paymentDaoDataSource ds=new RegisteredUser_has_method_paymentDaoDataSource();
		
		
		registereduser_has_method_payment.setIdRegisteredUser(id);
		registereduser_has_method_payment.setPan(pan);
		registereduser_has_method_payment.setExpirationDate(expirationDate);
		registereduser_has_method_payment.setCvc(cvc);
		
		try {
			
			ds.doSave(registereduser_has_method_payment);
			
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			dispatcherToAdd_payment_methodPage.forward(request, response);
		}
		
		response.sendRedirect(url);
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
