package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import bean.Product_situatedin_cart;
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
	    
		int idCart=(Integer)request.getSession().getAttribute("id");
		int idProdotto=Integer.parseInt(request.getParameter("idProdotto"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		RequestDispatcher dispatcherToProduct=request.getRequestDispatcher("product.jsp");
		Product_situatedin_cart product_situatedin_cart=new Product_situatedin_cart();
		product_situatedin_cart.setIdCart(idCart);
		product_situatedin_cart.setIdProduct(idProdotto);
		product_situatedin_cart.setQuantity(quantity);
		product_situatedin_cart.setDateAdded(new Date(System.currentTimeMillis()));
		
		
		Product_situatedin_cartDaoDataSource ds=new Product_situatedin_cartDaoDataSource();
		
		try {
			
			ds.doSave(product_situatedin_cart);
		}
        
		catch(SQLException e) {
			
			e.printStackTrace();
			dispatcherToProduct.forward(request, response);
		}
		
		response.sendRedirect("after_adding_to_cart.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
