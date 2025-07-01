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
import bean.Product_in_orderBean;
import bean.OrderBean;
import dao.OrderDaoDataSource;
import dao.Product_in_orderDaoDataSource;
/**
 * Servlet implementation class Payment
 */
@WebServlet("/Payment")
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CartBean cart=(CartBean)request.getSession().getAttribute("cart");
		OrderBean order=new OrderBean();
		int idOrder=order.getIdOrder();
		order.setIdCart(cart.getIdCart());
		ArrayList<Product_in_orderBean> products_in_order=new ArrayList<Product_in_orderBean>();
		
		for(Product_situatedin_cartBean product_incart: cart.getProducts()) {
			
		
			cart.removeProduct(product_incart);
			
			Product_in_orderDaoDataSource ds=new Product_in_orderDaoDataSource();
			Product_in_orderBean product_in_order=new Product_in_orderBean();
			product_in_order.setIdOrder(idOrder);
			product_in_order.setProduct(product_in_order.getProduct());
			product_in_order.setPrice(product_incart.getProduct().getPrice());
			product_in_order.setQuantity(product_incart.getQuantity());
			
			try {
				
				ds.doSave(product_in_order);
				
			}
			
			catch(SQLException e) {
				
				e.printStackTrace();
				
			}
			products_in_order.add(product_in_order);
			
		}
		
		OrderDaoDataSource ds_order=new OrderDaoDataSource();
		order.setProducts_in_order(products_in_order);
		
		try {
			ds_order.doSave(order);
			
		}
		catch(SQLException e) {
			
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/view/success_payment");
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
