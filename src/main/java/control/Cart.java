package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import dao.Product_situatedin_cartDaoDataSource;
import bean.CartBean;
import bean.Product_situatedin_cartBean;
import java.util.ArrayList;
/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errors="";
		RequestDispatcher dispatchToCart=request.getRequestDispatcher("/guest/view/cart.jsp");
		CartBean cart=(CartBean)request.getSession().getAttribute("cart");
		
		if(cart==null) {
			
			errors = "Sessione scaduta o carrello mancante. Ricarica la pagina e riprova.";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/error/expiredSession.jsp").forward(request, response);
	        return;
			
			
		}
		if (cart.getProducts()==null || cart.getProducts().isEmpty()) {
			errors+="Carrello vuoto o non inizializzato.<br>";
			request.setAttribute("errors", errors);
			dispatchToCart.forward(request, response);
			return;
		    
		}
		int idCart=cart.getIdCart();
		
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
