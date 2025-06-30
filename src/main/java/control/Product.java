package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import dao.ProductDaoDataSource;
import bean.ProductBean;
/**
 * Servlet implementation class Product
 */
@WebServlet("/Product")
public class Product extends HttpServlet {  
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id=Integer.parseInt(request.getParameter("idProdotto"));
		
		ProductDaoDataSource ds=new ProductDaoDataSource();
		ProductBean product=new ProductBean();
		try {product=ds.doRetrieveByKey(id);
		}
		
		catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		request.setAttribute("product", product);
		request.getRequestDispatcher("/view/product.jsp").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
