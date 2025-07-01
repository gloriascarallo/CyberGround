package control;

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
		
		Boolean isRegisteredUser=(Boolean)request.getSession().getAttribute("isRegisteredUser");
		if (Boolean.FALSE.equals(isRegisteredUser)) {
		    errors += "Non sei utente registrato<br>";
		    request.setAttribute("errors", errors);
		    request.getRequestDispatcher("/view/refund.jsp").forward(request, response);
		    return;
		}
		
		int id=(Integer)(request.getAttribute("id"));
		OrderDaoDataSource ds_order=new OrderDaoDataSource();
		OrderBean order=null;
		
		try {
			
			order=ds_order.doRetrieveByKey(id);
			
			if(order==null) {
				
				errors+="Ordine non trovato<br>";
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("/view/refund.jsp").forward(request, response);
				return;
				
			}
			
				Product_in_orderDaoDataSource ds_products=new Product_in_orderDaoDataSource();
				ArrayList<Product_in_orderBean> products=ds_products.doRetrieveByIdOrder(order.getIdOrder());
				order.setProducts_in_order(products);			
				request.setAttribute("order", order);
				request.getRequestDispatcher("/view/order_refund.jsp").forward(request, response);
				return;
						
						
					
				
			}
			
		catch(SQLException e) {
			
			e.printStackTrace();
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
