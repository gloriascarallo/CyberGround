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
import bean.Product_situatedin_cartBean;
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
	
		String errors="";
		Object idObj = request.getSession().getAttribute("id");
		if (idObj == null) {
			errors = "Sessione scaduta o ID utente mancante. Ricarica la pagina e riprova.";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/view/index.jsp").forward(request, response);
	        return;
		}
		int id = (Integer) idObj;
		
		CartBean cart=(CartBean)request.getSession().getAttribute("cart");
		if (cart == null) {
		    response.sendRedirect(request.getContextPath() + "/view/cart.jsp");
		    return;
		}
		
		if (cart.getProducts() == null || cart.getProducts().isEmpty()) {
		    errors+="Carrello vuoto.<br>";
		    request.setAttribute("errors", errors);
		    request.getRequestDispatcher("/view/cart.jsp").forward(request, response);
		    return;
		}
		
		for(Product_situatedin_cartBean product_incart: cart.getProducts()) {
			
			if(product_incart.getProduct().getQuantityAvailable()<product_incart.getQuantity()) {
				
				errors += "La quantità selezionata per il prodotto \"" + product_incart.getProduct().getName()
	                    + "\" (ID: " + product_incart.getProduct().getId()
	                    + ") supera la disponibilità (" + product_incart.getProduct().getQuantityAvailable() + ").<br>";
	        }
				
			}
			
	
		
		if(!errors.equals("")) {
			
			request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/view/cart.jsp").forward(request, response);
	        return;
			
		}
		
		RegisteredUser_has_addressDaoDataSource ds_has_address=new RegisteredUser_has_addressDaoDataSource();
		RegisteredUser_has_method_paymentDaoDataSource ds_has_methods_payment=new RegisteredUser_has_method_paymentDaoDataSource();
		ArrayList<RegisteredUser_has_addressBean> user_addresses=new ArrayList<RegisteredUser_has_addressBean>();
		ArrayList<RegisteredUser_has_method_paymentBean> user_methods_payment=new ArrayList<RegisteredUser_has_method_paymentBean>();
		
		try {
		user_addresses=ds_has_address.doRetrieveByIdRegisteredUser(id);
		user_methods_payment=ds_has_methods_payment.doRetrieveByIdRegisteredUser(id);
		if (user_addresses == null || user_addresses.isEmpty()) {
			errors+="Nessun indirizzo salvato.<br>";
		    
		}
		if (user_methods_payment == null || user_methods_payment.isEmpty()) {
			errors+="Nessun metodo di pagamento salvato.<br>";
		   
		}
		if(!errors.equals("")) {
			
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/view/cart.jsp").forward(request, response);
	        return;
			
		}
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/500.html").forward(request, response);
			return;
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
