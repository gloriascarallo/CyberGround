package control_admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import dao.ProductDaoDataSource;
/**
 * Servlet implementation class Remove_product
 */
@WebServlet("/Remove_product")
public class Remove_product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Remove_product() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errors="";
		String idStr=request.getParameter("idProduct");
		int id;
		if (idStr== null || idStr.trim().isEmpty()) {
			errors+="Inserisci un id.<br>";
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/admin/view/products.jsp").forward(request, response);
			return;
		} else {
		ProductDaoDataSource ds=new ProductDaoDataSource();
		try {
			id=Integer.parseInt(idStr);
			ds.doDelete(id);
		} catch (SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.html").forward(request, response);
			return;
		}
		
		response.sendRedirect(request.getContextPath()+"/admin/view/products.jsp");
		return;
	}
}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
