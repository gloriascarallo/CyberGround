package control;

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
/**
 * Servlet implementation class UpdateCart
 */
@WebServlet("/UpdateCart")
public class UpdateCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		String idStr = request.getParameter("product_incartID");
		int id;
		try {
		    id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write("{\"result\":\"ERROR\", \"message\":\"ID prodotto non valido\"}");
		    return;
		}
		CartBean cart = (CartBean) request.getSession().getAttribute("cart");
		if (cart == null) {
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write("{\"result\":\"ERROR\", \"message\":\"Carrello non trovato\"}");
		    return;
		}
		Product_situatedin_cartDaoDataSource ds=new Product_situatedin_cartDaoDataSource();
		Product_situatedin_cartBean product_incart=cart.getProduct(id);
		if (product_incart == null) {
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write("{\"result\":\"ERROR\", \"message\":\"Prodotto non trovato nel carrello\"}");
		    return;
		}
		
		Boolean isRegistered = (Boolean) request.getSession().getAttribute("isRegisteredUser");
		boolean saveToDb = isRegistered != null && isRegistered;
		
		switch(action) {
		
		case "decrease":
			
				int quantity = product_incart.getQuantity();

				if (quantity == 1) {
				    cart.removeProduct(product_incart);
				    if(saveToDb) {
			try {
			ds.doDelete(id);
		}
	catch(SQLException e) {
					    	
	e.printStackTrace();
	request.getRequestDispatcher("/500.html").forward(request, response);
	return;
}
				    }
				    
				}
				else {
				product_incart.decreaseQuantity();
				if(saveToDb) {
				try {
					ds.decreaseQuantity(id);
				} catch (SQLException e) {
					
					e.printStackTrace();
					request.getRequestDispatcher("/500.html").forward(request, response);
					return;
				}
			}
		}

			
		break;
		
		case "increase":
			
				
				product_incart.increaseQuantity();
				if(saveToDb) {
					try {
				ds.increaseQuantity(id); 
					}
					catch(SQLException e) {
						
						e.printStackTrace();
						request.getRequestDispatcher("/500.html").forward(request, response);
						return;
				}
			}
			
			
		break;
		
		case "remove": 
			
			cart.removeProduct(product_incart);
			
			if(saveToDb) {
			try {
				
				ds.doDelete(id);
				
			}
          catch(SQLException e) {
				
				e.printStackTrace();
				request.getRequestDispatcher("/500.html").forward(request, response);
				return;
			}
		}	
		break;
		
		default:
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"result\":\"ERROR\", \"message\":\"Azione non valida\"}");
            return;
    
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String jsonResponse = "{"
		    + "\"result\":\"OK\","
		    + "\"quantity\":" + product_incart.getQuantity() + ","
		    + "\"productTotal\":" + product_incart.getTotalPrice() + ","
		    + "\"cartTotal\":" + cart.getTotal()
		    + "}";

		response.getWriter().write(jsonResponse);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
