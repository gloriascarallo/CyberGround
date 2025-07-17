package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProductBean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.ProductDaoDataSource;

/**
 * Servlet implementation class Discounts
 */
@WebServlet("/Discounts")
public class Discounts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Discounts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String discountStr=request.getParameter("discountPercentage");
		Double discountPercentage=0.0;
		String errors="";
		
		if (discountStr != null && !discountStr.trim().isEmpty()) {
		    try {
		        discountPercentage = Double.parseDouble(discountStr);
		    } catch (NumberFormatException e) {
		        errors += "Il valore dello sconto non è valido.<br>";
		        request.setAttribute("errors", errors);
				request.getRequestDispatcher("/guest/view/index.jsp").forward(request, response);
				return;
		    }
		}
		
		ProductDaoDataSource ds=new ProductDaoDataSource();
		ArrayList<ProductBean> products=new ArrayList<>();
		
		try {
			products=ds.doRetrieveDiscountedProducts(discountPercentage);
		} catch (SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.html").forward(request, response);
			return;
		}
		
		if(products.isEmpty()) {
			
			errors+="Prodotti scontati non trovati.<br>";
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/guest/view/index.jsp").forward(request, response);
			return;
			
		}
		
		request.setAttribute("productsDiscounted", products);
		request.getRequestDispatcher("/guest/view/discounts.jsp").forward(request, response);
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
