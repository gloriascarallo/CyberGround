package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.ProductDaoDataSource;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import bean.CartBean;
import bean.ProductBean;
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
	    
		String errors="";
		CartBean cart = (CartBean) request.getSession().getAttribute("cart");
	    if (cart == null) {
	    	errors = "Sessione scaduta o carrello mancante. Ricarica la pagina e riprova.";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/expiredSession.html").forward(request, response);
	        return;
	    }
	    int idCart = cart.getIdCart();

	    String idProductStr = request.getParameter("idProduct");
	    if (idProductStr == null || idProductStr.trim().isEmpty()) {
	        errors = "Prodotto non trovato.<br>";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/guest/view/product.jsp").forward(request, response);
	        return;
	    }
	    int idProduct;
	    try {
	        idProduct = Integer.parseInt(idProductStr);
	    } catch (NumberFormatException e) {
	        errors = "ID prodotto non valido.<br>";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/guest/view/product.jsp").forward(request, response);
	        return;
	    }
	    
	    String quantityStr = request.getParameter("quantity");
	    int quantity = 1; 
	    if (quantityStr == null || quantityStr.trim().isEmpty()) {
	        errors = "Inserisci la quantità.<br>";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/guest/view/product.jsp").forward(request, response);
	        return;
	    } else {
	        try {
	            quantity = Integer.parseInt(quantityStr);
	            if (quantity <= 0) {
	                errors = "La quantità disponibile non può essere minore di 1.<br>";
	                request.setAttribute("errors", errors);
	                request.getRequestDispatcher("/guest/view/product.jsp").forward(request, response);
	                return;
	            }
	        } catch (NumberFormatException e) {
	            errors = "La quantità disponibile deve essere un numero valido.<br>";
	            request.setAttribute("errors", errors);
	            request.getRequestDispatcher("/guest/view/product.jsp").forward(request, response);
	            return;
	        }
	    }

	        
	        ProductDaoDataSource ds_product=new ProductDaoDataSource();
	        ProductBean product=null;
	        
	        try {
	        product=ds_product.doRetrieveByKey(idProduct);
	        }
	        catch(SQLException e) {
	        	
	        	e.printStackTrace();
	        	request.getRequestDispatcher("/500.html").forward(request, response);
	        	return;
	        }
	        
	        if(product==null) {
	        	errors+="Prodotto non trovato.<br>";
	        	request.setAttribute("errors", errors);
	        	request.getRequestDispatcher("/guest/view/product.jsp").forward(request, response);
	        	return;
	        	
	        }
	        
	       
				if(product.getQuantityAvailable()<quantity) {
					
					errors+="La quantità disponibile di tale prodotto è minore rispetto alla quantità selezionata.<br>";
					request.setAttribute("errors", errors);
					request.getRequestDispatcher("/guest/view/product.jsp").forward(request, response);
					return;
					
					
				}
				
			try {
				ds_product.decreaseQuantityAvailable(idProduct, quantity);
			} catch (SQLException e) {
				
				e.printStackTrace();
				request.getRequestDispatcher("/500.html").forward(request, response);
	        	return;
			}
	     
		
		Product_situatedin_cartBean product_situatedin_cart=new Product_situatedin_cartBean();
		
		product_situatedin_cart.setIdCart(idCart);
		product_situatedin_cart.setQuantity(quantity);
		product_situatedin_cart.setDateAdded(Date.valueOf(LocalDate.now()));
		
		
		Product_situatedin_cartDaoDataSource ds_cart=new Product_situatedin_cartDaoDataSource();
		
		
		try {
			product_situatedin_cart.setProduct(product);
			cart.addProduct(product_situatedin_cart);
			ds_cart.doSave(product_situatedin_cart); 
		}
        
		catch(SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/500.html").forward(request, response);
        	return;
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
