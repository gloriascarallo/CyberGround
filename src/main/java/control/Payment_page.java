package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.CartBean;
import bean.RegisteredUser_has_addressBean;
import bean.RegisteredUser_has_method_paymentBean;
import dao.RegisteredUser_has_addressDaoDataSource;
import dao.RegisteredUser_has_method_paymentDaoDataSource;
/**
 * Servlet implementation class Payment_page
 */
@WebServlet("/Payment_page")
public class Payment_page extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payment_page() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CartBean cart=(CartBean)request.getSession().getAttribute("cart");
		
		String errors="";
		Boolean isRegisteredUser=(Boolean)request.getSession().getAttribute("isRegisteredUser");
		if (Boolean.FALSE.equals(isRegisteredUser)) {
		    errors += "Non sei utente registrato<br>";
		    request.setAttribute("errors", errors);
		    request.getRequestDispatcher("/view/cart.jsp").forward(request, response);
		    return;
		}
		
		String username=(String)request.getSession().getAttribute("username");
		RegisteredUser_has_addressDaoDataSource ds_has_address=new RegisteredUser_has_addressDaoDataSource();
		RegisteredUser_has_method_paymentDaoDataSource ds_has_methods_payment=new RegisteredUser_has_method_paymentDaoDataSource();
		ArrayList<RegisteredUser_has_addressBean> user_addresses=new ArrayList<RegisteredUser_has_addressBean>();
		ArrayList<RegisteredUser_has_method_paymentBean> user_methods_payment=new ArrayList<RegisteredUser_has_method_paymentBean>();
		try {
		user_addresses=ds_has_address.doRetrieveByUsername(username);
		user_methods_payment=ds_has_methods_payment.doRetrieveByUsername(username);
		}
		catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		request.setAttribute("user_addresses", user_addresses);
		request.setAttribute("user_methods_payment", user_methods_payment);
		request.getRequestDispatcher("/view/payment_page.jsp").forward(request, response);
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
