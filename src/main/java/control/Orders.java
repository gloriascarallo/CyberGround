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
import bean.OrderBean;
import bean.Product_in_orderBean;
import dao.Product_in_orderDaoDataSource;
import dao.OrderDaoDataSource;
/**
 * Servlet implementation class Orders
 */
@WebServlet("/Orders")
public class Orders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Orders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errors="";
		ArrayList<OrderBean> orders=new ArrayList<OrderBean>();
		CartBean cart=(CartBean)request.getSession().getAttribute("cart");
		if (cart == null) {
		    response.sendRedirect(request.getContextPath() + "/view/cart.jsp");
		    return;
		}
		int idCart=cart.getIdCart();
		
		OrderDaoDataSource ds_order=new OrderDaoDataSource();
		
		try {
			orders=ds_order.doRetrieveByIdCart(idCart);
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/500.html").forward(request, response);
			return;
			
		}
		
		if(orders.isEmpty()) {
			
			errors+="Ordini non trovati.";
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/view/user.jsp").forward(request, response);
			return;
			
		}
		
		for(OrderBean order: orders) {
			
			Product_in_orderDaoDataSource ds_product=new Product_in_orderDaoDataSource();
			ArrayList<Product_in_orderBean> products_in_order=new ArrayList<Product_in_orderBean>();
			try {

				products_in_order=ds_product.doRetrieveByIdOrder(order.getIdOrder());
				if(products_in_order.isEmpty()) {
					
					errors+="Prodotti nell'ordine non trovati.";
					request.setAttribute("errors", errors);
					request.getRequestDispatcher("/view/user.jsp").forward(request, response);
					return;
					
				}
				
				order.setProducts_in_order(products_in_order);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				request.getRequestDispatcher("/500.html").forward(request, response);
				return;
			}
			
			
		}
		
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/view/orders.jsp").forward(request, response);
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
