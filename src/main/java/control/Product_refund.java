package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import bean.Product_in_orderBean;
import dao.Product_in_orderDaoDataSource;
/**
 * Servlet implementation class Product_refund
 */
@WebServlet("/Product_refund")
public class Product_refund extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product_refund() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idStr = request.getParameter("idProduct_in_order");
	    int id;
	    String errors = "";

	    try {
	        if (idStr == null || idStr.trim().isEmpty()) {
	            throw new NumberFormatException("Parametro mancante");
	        }
	        id = Integer.parseInt(idStr.trim());
	    } catch (NumberFormatException e) {
	        errors += "ID prodotto non valido.<br>";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/registeredUser/view/refund.jsp").forward(request, response);
	        return;
	    }
	    
		Product_in_orderDaoDataSource ds=new Product_in_orderDaoDataSource();
		Product_in_orderBean product=null;
		
		try {
			product=ds.doRetrieveByKey(id);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(product==null) {
			errors += "Prodotto non trovato nell'ordine.<br>";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/registeredUser/view/refund.jsp").forward(request, response);
	        return;
			
		}
		
		request.setAttribute("product", product);
		request.getRequestDispatcher("/registeredUser/view/product_refund.jsp").forward(request, response);;
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
