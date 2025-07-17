package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderBean;
import model.Product_in_orderBean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.OrderDaoDataSource;
import dao.Product_in_orderDaoDataSource;
/**
 * Servlet implementation class Refund
 */
@WebServlet("/Refund")
public class Refund extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Refund() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errors="";
		String idStr = request.getParameter("id");
	    int idOrder;
	    try {
	        if (idStr == null || idStr.trim().isEmpty()) {
	            throw new NumberFormatException("ID ordine mancante");
	        }
	        idOrder = Integer.parseInt(idStr.trim());
	    } catch (NumberFormatException e) {
	        errors += "ID ordine non valido.<br>";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/registeredUser/view/refund.jsp").forward(request, response);
	        return;
	    }
	    
	    Object idObj=request.getSession().getAttribute("id");
		if (idObj == null) {
			errors = "Sessione scaduta o ID utente mancante. Ricarica la pagina e riprova.";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/expiredSession.html").forward(request, response);
	        return;
	    }
	    int id = (Integer) idObj;
	   
	    
		OrderDaoDataSource ds_order=new OrderDaoDataSource();
		OrderBean order=null;
		
		try {
			
			order=ds_order.doRetrieveByIdOrderAndIdCart(idOrder, id);
			
			if(order==null) {
				
				errors+="Ordine non trovato<br>";
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("/registeredUser/view/refund.jsp").forward(request, response);
				return;
				
			}
			
				Product_in_orderDaoDataSource ds_products=new Product_in_orderDaoDataSource();
				ArrayList<Product_in_orderBean> products = ds_products.doRetrieveByIdOrder(order.getIdOrder());
				if (products == null) {
				    products = new ArrayList<>();
				}
				order.setProducts_in_order(products);
							
				request.setAttribute("order", order);
				request.getRequestDispatcher("/registeredUser/view/order_refund.jsp").forward(request, response);
				return;
						
						
					
				
			}
			
		catch(SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.html").forward(request, response);
			return;
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
