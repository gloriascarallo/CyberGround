package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.ProductDaoDataSource;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import bean.CartBean;
import bean.Product_situatedin_cartBean;
import dao.Product_situatedin_cartDaoDataSource;
/**
 * Servlet implementation class Adding_to_cart
 */
@WebServlet("/Adding_to_cart")
public class Adding_to_cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Adding_to_cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		CartBean cart=(CartBean)request.getSession().getAttribute("cart");
		int idProdotto=Integer.parseInt(request.getParameter("idProdotto"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		
		RequestDispatcher dispatcherToProduct=request.getRequestDispatcher("/view/product.jsp");
		
		Product_situatedin_cartBean product_situatedin_cart=new Product_situatedin_cartBean();
		
		product_situatedin_cart.setIdCart(cart.getIdCart());
		product_situatedin_cart.setQuantity(quantity);
		product_situatedin_cart.setDateAdded(new Date(System.currentTimeMillis()));
		
		ProductDaoDataSource ds_product=new ProductDaoDataSource();
		Product_situatedin_cartDaoDataSource ds_cart=new Product_situatedin_cartDaoDataSource();
		
		
		try {
			product_situatedin_cart.setProduct(ds_product.doRetrieveByKey(idProdotto));
			cart.addProduct(product_situatedin_cart);
			ds_cart.doSave(product_situatedin_cart); // non so se è necessario
		}
        
		catch(SQLException e) {
			
			e.printStackTrace();
			dispatcherToProduct.forward(request, response);
		}
		
		response.sendRedirect(request.getContextPath()+"/view/after_adding_to_cart.jsp");
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
