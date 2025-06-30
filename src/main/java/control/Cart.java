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
		
		int idCart=(Integer)request.getSession().getAttribute("id");
		RequestDispatcher dispatchToCart=request.getRequestDispatcher("/view/cart.jsp");
		
		Product_situatedin_cartDaoDataSource ds=new Product_situatedin_cartDaoDataSource();
		ArrayList<Product_situatedin_cartBean> products_situatedin_cart=new ArrayList<Product_situatedin_cartBean>();
		
		try {
			
			products_situatedin_cart=ds.doRetrieveByIdCart(idCart);
			request.setAttribute("products_situatedin_cart", products_situatedin_cart);
		}
		
		catch(SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/view/index.jsp").forward(request, response);
		}
		
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
