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
		
		switch(action) {
		
		case "decrease":
			
			try {
				 
				product_incart.decreaseQuantity();
				if(product_incart.getQuantity()==0) {
					ds.doDelete(id); // database
					cart.removeProduct(product_incart);
				}
				else {
				ds.decreaseQuantity(id); // database
				}
			}
			
			catch(SQLException e) {
				
				e.printStackTrace();
			}
		break;
		
		case "increase":
			
			try {
				
				product_incart.increaseQuantity();
				ds.increaseQuantity(id); // database
				}
			
			
			catch(SQLException e) {
				
				e.printStackTrace();
			}
			
		break;
		
		case "remove": 
			
			cart.removeProduct(product_incart);
			
			try {
				
				ds.doDelete(id); // database
				
			}
          catch(SQLException e) {
				
				e.printStackTrace();
			}
			
		break;
		}
		response.sendRedirect(request.getContextPath()+"/control/Cart"); //da modificare
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
