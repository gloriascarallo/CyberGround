package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ProductBean;
import dao.ProductDaoDataSource;

/**
 * Servlet implementation class Category
 */
@WebServlet("/Category")
public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Category() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String errors = "";  

		String category = request.getParameter("category");

		if (category == null || category.trim().equals("")) {  
		    errors = "Categoria non trovata.<br>";  
		    request.setAttribute("errors", errors);  
		    request.getRequestDispatcher("/guest/view/index.jsp").forward(request, response);  
		    return;  
		}  

		ProductDaoDataSource ds = new ProductDaoDataSource();  
		ArrayList<ProductBean> products = new ArrayList<>();  

		try {  
		    products = ds.doRetrievebyCategoryName(category);  
		} catch (SQLException e) {  
		    e.printStackTrace();  
		    request.getRequestDispatcher("/error/500.html").forward(request, response);  
		    return;  
		}  

		if (products.isEmpty()) {  
		    errors = "Nessun prodotto trovato.<br>";  
		    request.setAttribute("errors", errors);  
		    request.getRequestDispatcher("/guest/view/category.jsp").forward(request, response);  
		    return;  
		}  

		request.setAttribute("category", category);
		request.setAttribute("products", products);  
		request.setAttribute("nowTimestamp", System.currentTimeMillis());
		request.getRequestDispatcher("/guest/view/category.jsp").forward(request, response);  
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
