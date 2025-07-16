package control_admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.Product_in_orderDaoDataSource;
import bean.OrderBean;
import bean.Product_in_orderBean;
import dao.OrderDaoDataSource;
/**
 * Servlet implementation class Filter_orders_byDates
 */
@WebServlet("/Filter_orders_byDates")
public class Filter_orders_byDates extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Filter_orders_byDates() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Date dateX = null;
		Date dateY = null;
		try {
		    dateX = Date.valueOf(request.getParameter("dateX"));
		    dateY = Date.valueOf(request.getParameter("dateY"));
		} catch (IllegalArgumentException e) {
		    request.setAttribute("errors", "Formato data non valido.");
		    request.getRequestDispatcher("/admin/view/user_profile.jsp").forward(request, response);
		    return;
		}
		int id=-1;
		try {
		    id = Integer.parseInt(request.getParameter("idCart"));
		} catch (NumberFormatException e) {
		    request.setAttribute("errors", "ID non valido.");
		    
		}
		
		OrderDaoDataSource ds=new OrderDaoDataSource();
		ArrayList<OrderBean> orders=new ArrayList<>();
		String errors="";
		
		try {
			orders=ds.doRetrieveByIdCartAndDatePurchaseRange(id, dateX, dateY);
		} catch (SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.html").forward(request, response);
			return;
		}
		
		if(orders.isEmpty()) {
			
			errors+="Ordini non trovati<br>";
			request.setAttribute("errors", errors);
		
		}
		
		Product_in_orderDaoDataSource ds_products=new Product_in_orderDaoDataSource();
		
		for(OrderBean order: orders) {
			
			ArrayList<Product_in_orderBean> products=new ArrayList<>();
			try {
				products=ds_products.doRetrieveByIdOrder(order.getIdOrder());
			} catch (SQLException e) {
				
				e.printStackTrace();
				request.getRequestDispatcher("/error/500.html").forward(request, response);
				return;
			}
			
			if(products.isEmpty()) {
				
				errors+="Errore nel caricamento ordini.<br>";
				request.setAttribute("errors", errors);
			
				
			}
			order.setProducts_in_order(products);
			
		}
		
			request.setAttribute("idCart", id);
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
