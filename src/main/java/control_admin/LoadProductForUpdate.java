package control_admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import dao.ProductDaoDataSource;
import bean.ProductBean;

@WebServlet("/LoadProductForUpdate")
public class LoadProductForUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoadProductForUpdate() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String errors="";
        String idStr = request.getParameter("idProduct");
        int id;
        if (idStr == null || idStr.isEmpty()) {
        	errors+="Inserisci un id.<br>";
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/admin/view/products.jsp").forward(request, response);
            return;
        } else {
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/admin/view/products.jsp");
            return;
        }

        ProductDaoDataSource dao = new ProductDaoDataSource();
        try {
            ProductBean product = dao.doRetrieveByKey(id);
            if (product == null) {
                errors+="Prodotto non trovato.<br>";
			      request.setAttribute("errors", errors);
                request.getRequestDispatcher("/admin/view/products.jsp").forward(request, response);
                return;
            }

            // Metti il prodotto come attributo nella request
            request.setAttribute("product", product);
            // Forward alla JSP di update prodotto, così la form può precompilarsi
            request.getRequestDispatcher("/admin/view/update_product.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error/500.html");
        }
    }
}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
