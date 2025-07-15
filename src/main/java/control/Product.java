package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import dao.ProductDaoDataSource;
import bean.ProductBean;
/**
 * Servlet implementation class Product
 */
@WebServlet("/Product")
public class Product extends HttpServlet {  
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errors="";
		String toRedirect=request.getParameter("toRedirect");
		
		if (toRedirect == null || toRedirect.trim().isEmpty()) {
	        toRedirect = "/error/500.html";
	    }
		
		List<String> allowedRedirects = Arrays.asList("/guest/view/category.jsp", "/guest/view/index.jsp", "/guest/view/cart.jsp", "/guest/view/discounts.jsp", "/registeredUser/view/orders.jsp");
		if (!allowedRedirects.contains(toRedirect)) {
		    toRedirect = "/error/500.html";
		}
		
		int id;
		try {
		    id = Integer.parseInt(request.getParameter("idProdotto"));
		} catch (NumberFormatException | NullPointerException e) {
		    errors += "ID prodotto non valido.<br>";
		    request.setAttribute("errors", errors);
		    request.getRequestDispatcher(toRedirect).forward(request, response);
		    return;
		}
		
		ProductDaoDataSource ds=new ProductDaoDataSource();
		ProductBean product=null;
		try {product=ds.doRetrieveByKey(id);
		}
		
		catch(SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.html").forward(request, response);
			return;
		}
		
		if(product==null) {
			errors+="Prodotto non trovato.<br>";
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(toRedirect).forward(request, response);
			return;
			
			
		}
		
		
		request.setAttribute("product", product);
		request.getRequestDispatcher("/guest/view/product.jsp").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
