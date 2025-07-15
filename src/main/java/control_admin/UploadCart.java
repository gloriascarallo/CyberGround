package control_admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.Product_situatedin_cartBean;
import dao.Product_situatedin_cartDaoDataSource;

/**
 * Servlet implementation class UploadCart
 */
@WebServlet("/UploadCart")
public class UploadCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errors="";
		RequestDispatcher dispatchToCart=request.getRequestDispatcher("/admin/view/user_cart.jsp");
		
		String idParam = request.getParameter("idCart");
	    if (idParam == null) {
	        errors = "Carrello non trovato.<br>";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/UploadUser").forward(request, response);
	        return;
	    }

	    int idCart;
	    try {
        idCart = Integer.parseInt(idParam);
	    	
	    } catch (NumberFormatException e) {
	        errors = "ID carrello non valido.<br>";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/UploadUser").forward(request, response);
	        return;
	    }
		
		Product_situatedin_cartDaoDataSource ds=new Product_situatedin_cartDaoDataSource();
		ArrayList<Product_situatedin_cartBean> products_situatedin_cart=new ArrayList<Product_situatedin_cartBean>();
		
		
		try {
			
			products_situatedin_cart=ds.doRetrieveByIdCart(idCart);
			
		}
		
		catch(SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.html").forward(request, response);
			return;
		}
		
		if(products_situatedin_cart.isEmpty()) {
			
			errors+="Prodotti nel carrello non trovati.<br>";
			request.setAttribute("errors", errors);
			dispatchToCart.forward(request, response);
			return;
			
		}
		request.setAttribute("idCart", idCart);
		request.setAttribute("products_situatedin_cart", products_situatedin_cart);
		dispatchToCart.forward(request, response);
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
