package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Product_situatedin_cartBean;
import dao.Product_situatedin_cartDaoDataSource;
/**
 * Servlet implementation class Filter_cart_by
 */
@WebServlet("/Filter_cart_by")
public class Filter_cart_by extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Filter_cart_by() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		double priceMin=Double.parseDouble(request.getParameter("priceMin"));
		double priceMax=Double.parseDouble(request.getParameter("priceMax"));
		Date dateAdded=Date.valueOf(request.getParameter("dateAdded"));
		int idCart=Integer.parseInt(request.getParameter("idCart"));
		Product_situatedin_cartDaoDataSource ds=new Product_situatedin_cartDaoDataSource();
		ArrayList<Product_situatedin_cartBean>products=new ArrayList<Product_situatedin_cartBean>();
		String errors="";
		
		
		// aggiungere action
		try {
			products=ds.doRetrieveByDateAdded(idCart, dateAdded);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			
			products=ds.doRetrieveByIdCartAndPriceRange(idCart, priceMin, priceMax);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(products.isEmpty()) {
			
			errors+="Non ci sono prodotti nel carrello che rispettano tali parametri<br>";
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/admin/").forward(request, response);; // da vedere
			return;
		}
		
		
		request.setAttribute("products_incart", products);
		request.getRequestDispatcher("/admin/").forward(request, response); // da vedere
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
