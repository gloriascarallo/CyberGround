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
		int id=Integer.parseInt(request.getParameter("product_incartID"));
		CartBean cart=(CartBean)request.getSession().getAttribute("cart");
		Product_situatedin_cartDaoDataSource ds=new Product_situatedin_cartDaoDataSource();
		Product_situatedin_cartBean product_incart=cart.getProduct(id);
		if (product_incart == null) {
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write("{\"result\":\"ERROR\", \"message\":\"Prodotto non trovato nel carrello\"}");
		    return;
		}
		
		switch(action) {
		
		case "decrease":
			
			try {
				int quantity = product_incart.getQuantity();

				if (quantity == 1) {
				    cart.removeProduct(product_incart);
				    ds.doDelete(id);
				}
				else {
				product_incart.decreaseQuantity();
				ds.decreaseQuantity(id);
				}
			}
			catch(SQLException e) {
				
				e.printStackTrace();
				request.getRequestDispatcher("/500.html").forward(request, response);
				return;
			}
		break;
		
		case "increase":
			
			try {
				
				product_incart.increaseQuantity();
				ds.increaseQuantity(id); // database
				}
			
			
			catch(SQLException e) {
				
				e.printStackTrace();
				request.getRequestDispatcher("/500.html").forward(request, response);
				return;
			}
			
		break;
		
		case "remove": 
			
			cart.removeProduct(product_incart);
			
			try {
				
				ds.doDelete(id);
				
			}
          catch(SQLException e) {
				
				e.printStackTrace();
				request.getRequestDispatcher("/500.html").forward(request, response);
				return;
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
