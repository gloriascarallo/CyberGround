package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CartBean;
import model.ProductBean;
import model.Product_situatedin_cartBean;
import dao.ProductDaoDataSource;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

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
	    
		String errors="";
		CartBean cart = (CartBean) request.getSession().getAttribute("cart");
	    if (cart == null) {
	    	errors = "Sessione scaduta o carrello mancante. Ricarica la pagina e riprova.";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/error/expiredSession.jsp").forward(request, response);
	        return;
	    }
	    int idCart = cart.getIdCart();
	    System.out.println(idCart);

	    String idProductStr = request.getParameter("productID");
	    if (idProductStr == null || idProductStr.trim().isEmpty()) {
	    	
	        errors = "Prodotto non trovato1.<br>";
	        System.out.println(errors);
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/Product").forward(request, response);
	        return;
	    }
	    int idProduct;
	    try {
	        idProduct = Integer.parseInt(idProductStr);
	    } catch (NumberFormatException e) {
	        errors = "ID prodotto non valido.<br>";
	        System.out.println(errors);
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/Product").forward(request, response);
	        return;
	    }
	    
	   

	        
	        ProductDaoDataSource ds_product=new ProductDaoDataSource();
	        ProductBean product=null;
	        
	        try {
	        product=ds_product.doRetrieveByKey(idProduct);
	        }
	        catch(SQLException e) {
	        	
	        	e.printStackTrace();
	        	request.getRequestDispatcher("/error/500.html").forward(request, response);
	        	return;
	        }
	        
	        if(product==null) {
	        	errors+="Prodotto non trovato.<br>";
	        	System.out.println(errors);
	        	request.setAttribute("errors", errors);
	        	request.getRequestDispatcher("/Product").forward(request, response);
	        	return;
	        	
	        }
	        
	       
				if(product.getQuantityAvailable()<=0) {
					
					errors+="La quantità disponibile di tale prodotto è minore rispetto alla quantità selezionata.<br>";
					System.out.println(errors);
					request.getSession().setAttribute("errorsQuantity", errors);
					response.sendRedirect(request.getContextPath() + "/Product?idProduct=" + idProduct + "&toRedirect=/guest/view/product.jsp");
					return;
					
					
				}
				
			
	     
		
		Product_situatedin_cartBean product_situatedin_cart=new Product_situatedin_cartBean();
		
		product_situatedin_cart.setIdCart(idCart);
		product_situatedin_cart.setQuantity(1);
		product_situatedin_cart.setDateAdded(Date.valueOf(LocalDate.now()));
		product_situatedin_cart.setProduct(product);
		cart.addProduct(product_situatedin_cart);
		
		Boolean isRegistered = (Boolean) request.getSession().getAttribute("isRegisteredUser");
	    boolean saveToDb = isRegistered != null && isRegistered;

	    if (saveToDb) {
	        Product_situatedin_cartDaoDataSource ds_cart = new Product_situatedin_cartDaoDataSource();
	        try {
	            ds_cart.doSave(product_situatedin_cart);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            request.getRequestDispatcher("/error/500.html").forward(request, response);
	            return;
	        }
	    }
		
		
		response.sendRedirect(request.getContextPath()+"/guest/view/after_adding_to_cart.jsp");
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
