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
		
		ArrayList<OrderBean> orders=new ArrayList<OrderBean>();
		CartBean cart=(CartBean)request.getSession().getAttribute("cart");
		int idCart=cart.getIdCart();
		OrderDaoDataSource ds_order=new OrderDaoDataSource();
		
		try {
			orders=ds_order.doRetrieveByIdCart(idCart);
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		for(OrderBean order: orders) {
			
			Product_in_orderDaoDataSource ds_product=new Product_in_orderDaoDataSource();
			try {
				ArrayList<Product_in_orderBean> products_in_order=new ArrayList<Product_in_orderBean>();
				products_in_order=ds_product.doRetrieveByIdOrder(order.getIdOrder());
				order.setProducts_in_order(products_in_order);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
		}
		
		
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/view/orders.jsp");
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
