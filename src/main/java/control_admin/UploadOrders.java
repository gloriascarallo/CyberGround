package control_admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.OrderBean;
import bean.Product_in_orderBean;
import dao.OrderDaoDataSource;
import dao.Product_in_orderDaoDataSource;

/**
 * Servlet implementation class UploadOrders
 */
@WebServlet("/UploadOrders")
public class UploadOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errors="";
		
		String idCartStr = request.getParameter("idCart");
	    if (idCartStr == null || idCartStr.trim().isEmpty()) {
	        errors = "Carrello non trovato.<br>";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/admin/view/user_profile.jsp").forward(request, response);
	        return;
	    }

	    int idCart;
	    try {
//	        idCart = Integer.parseInt(idCartStr);
	    	idCart = 0;
	    } catch (NumberFormatException e) {
	        errors = "ID carrello non valido.<br>";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/admin/view/user_profile.jsp").forward(request, response);
	        return;
	    }
	    
	    ArrayList<OrderBean> orders=new ArrayList<OrderBean>();
		OrderDaoDataSource ds_order=new OrderDaoDataSource();
		
		try {
			orders=ds_order.doRetrieveByIdCart(idCart);
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.html").forward(request, response);
			return;
			
		}
		
		if(orders.isEmpty()) {
			
			errors+="Ordini non trovati.<br>";
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/admin/view/user_profile.jsp").forward(request, response);
			return;
			
		}
		
		Product_in_orderDaoDataSource ds_product=new Product_in_orderDaoDataSource();
		
		for(OrderBean order: orders) {
			
			ArrayList<Product_in_orderBean> products_in_order=new ArrayList<Product_in_orderBean>();
			try {

				products_in_order=ds_product.doRetrieveByIdOrder(order.getIdOrder());
				if(products_in_order.isEmpty()) {
					
					errors+="Prodotti nell'ordine non trovati.<br>";
					request.setAttribute("errors", errors);
					request.getRequestDispatcher("/admin/view/user_profile.jsp").forward(request, response);
					return;
					
				}
				
				order.setProducts_in_order(products_in_order);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				request.getRequestDispatcher("/error/500.html").forward(request, response);
				return;
			}
			
			
		}
		
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/admin/view/user_orders.jsp").forward(request, response);
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
