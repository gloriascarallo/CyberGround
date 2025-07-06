package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.ProductDaoDataSource;
import bean.ProductBean;

/**
 * Servlet implementation class Filter_by
 */
@WebServlet("/Filter_by")
public class Filter_by extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Filter_by() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errors="";
		String action=request.getParameter("action");
		ArrayList<ProductBean> products=new ArrayList<ProductBean>();
		ProductDaoDataSource ds=new ProductDaoDataSource();
		
		switch(action) {
		
		case "supplier": 
			
			String supplier=request.getParameter("supplier");
			try {
				products=ds.doRetrievebySupplier(supplier);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		    break;
		    
		case "price":
			
			double price=Double.parseDouble(request.getParameter("price"));
			try {
				products=ds.doRetrievebyPrice(price);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			break;
			
		case "yearUpload": 
			
			int year=Integer.parseInt(request.getParameter("yearUpload"));
			try {
				products=ds.doRetrievebyYearUpload(year);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		break;
			
		default: 
			
			String name=request.getParameter("name");
			try {
				products=ds.doRetrievebyName(name);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			break;
		}
			if(products.isEmpty()) {
				
				errors+="Input errato o prodotti non trovati<br>";
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("/view/index.jsp");
				return;
				
			}
			request.setAttribute("products", products);
			request.getRequestDispatcher("/view/category.jsp");
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
