package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import dao.Product_situatedin_cartDaoDataSource;
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
		
		String errors="";
		CartBean cart=(CartBean)request.getSession().getAttribute("cart");
		if (cart == null) {
			errors = "Sessione scaduta o carrello mancante. Ricarica la pagina e riprova.";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/error/expiredSession.jsp").forward(request, response);
	        return;
		}
		int idCart=cart.getIdCart();
		if (cart.getProducts() == null || cart.getProducts().isEmpty()) {
		    errors="Carrello vuoto.<br>";
		    request.setAttribute("errors", errors);
		    request.getRequestDispatcher("/guest/view/cart.jsp");
		    return;
		}
		
		OrderBean order=new OrderBean();
		order.setIdCart(idCart);
		order.setDatePurchase(Date.valueOf(LocalDate.now()));
		Random rand=new Random();
		int randomDayShipping=rand.nextInt(30)+1;
		int randomDayDelivery=randomDayShipping+rand.nextInt(30)+1;
		order.setDateShipping(Date.valueOf(LocalDate.now().plusDays(randomDayShipping)));
		order.setDateDelivery(Date.valueOf(LocalDate.now().plusDays(randomDayDelivery)));
		OrderDaoDataSource ds_order=new OrderDaoDataSource();
		
		try {
			ds_order.doSave(order);
			
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.html").forward(request, response);
			return;
		}
		
		ArrayList<Product_in_orderBean> products_in_order=new ArrayList<Product_in_orderBean>();
		Product_situatedin_cartDaoDataSource ds_cart=new Product_situatedin_cartDaoDataSource();
		Product_in_orderDaoDataSource ds=new Product_in_orderDaoDataSource();
		
		for(Product_situatedin_cartBean product_incart: cart.getProducts()) {
			Product_in_orderBean product_in_order=new Product_in_orderBean();
			product_in_order.setIdOrder(order.getIdOrder());
			product_in_order.setProduct(product_incart.getProduct());
			product_in_order.setPrice(product_incart.getProduct().getPrice());
			product_in_order.setQuantity(product_incart.getQuantity());
			
			try {
				
				ds.doSave(product_in_order);
				
			}
			
			catch(SQLException e) {
				
				e.printStackTrace();
				request.getRequestDispatcher("/error/500.html").forward(request, response);
				return;
			}
			products_in_order.add(product_in_order);
			
		}
		
		ArrayList<Product_situatedin_cartBean> productsToRemove = new ArrayList<>(cart.getProducts());

		for (Product_situatedin_cartBean product_incart : productsToRemove) {
		   
		    cart.removeProduct(product_incart);
		    try {
				ds_cart.doDelete(product_incart.getId_SituatedIn());
			} catch (SQLException e) {
				
				e.printStackTrace();
				request.getRequestDispatcher("/error/500.html").forward(request, response);
				return;
			}
		}
		
		
		order.setProducts_in_order(products_in_order); // non penso sia necessario
		
	
		response.sendRedirect(request.getContextPath()+"/registeredUser/view/success_payment.jsp");
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
