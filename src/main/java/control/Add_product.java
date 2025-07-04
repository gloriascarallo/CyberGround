package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import dao.ProductDaoDataSource;
import bean.ProductBean;

/**
 * Servlet implementation class Add_product
 */
@WebServlet("/Add_product")
public class Add_product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_product() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		double price=0;
		String priceStr = request.getParameter("price");
		String description=request.getParameter("description");
		String supplier=request.getParameter("supplier");
		String category=request.getParameter("category");
		int quantityAvailable=1;
		String quantityAvailableStr=request.getParameter(name);
		String errors="";
		
if(name==null || name.trim().equals("")) {
			
			errors+="Inserisci l'username<br>";
		} 

if (priceStr== null || priceStr.trim().isEmpty()) {
    errors += "Inserisci il prezzo.<br>";
} else {
    try {
       price = Double.parseDouble(priceStr);
        if (price < 0) {
            errors += "Il prezzo non può essere negativo.<br>";
        }
    } catch (NumberFormatException e) {
        errors += "Il prezzo deve essere un numero valido.<br>";
    }
}

if(description==null || description.trim().equals("")) {
	
	errors+="Inserisci la descrizione<br>";
} 	


if(supplier==null || supplier.trim().equals("")) {
	
	errors+="Inserisci il fornitore<br>";
} 
		
	
	

if(category==null || category.trim().equals("")) {
	
	errors+="Inserisci la descrizione<br>";
} 

if (quantityAvailableStr== null || quantityAvailableStr.trim().isEmpty()) {
    errors += "Inserisci il prezzo.<br>";
} else {
    try {
        quantityAvailable = Integer.parseInt(quantityAvailableStr);
        if (quantityAvailable <= 0) {
            errors += "La quantità disponibile non può essere minore di 1.<br>";
        }
    } catch (NumberFormatException e) {
        errors += "La quntità disponibile deve essere un numero valido.<br>";
    }
}
if(!errors.equals("")) {
	
	request.setAttribute("errors", errors);
	request.getRequestDispatcher("/admin/view/add_product.jsp").forward(request, response);
	return;
}

ProductBean product=new ProductBean();
product.setName(name);
product.setPrice(price);
product.setDescription(description);
product.setCategoryName(category);
product.setQuantityAvailable(quantityAvailable);
product.setDateUpload(Date.valueOf(LocalDate.now()));
product.setSupplier(supplier);

ProductDaoDataSource ds=new ProductDaoDataSource();

try {
	ds.doSave(product);
} catch (SQLException e) {
	
	e.printStackTrace();
}

response.sendRedirect(request.getContextPath() + "/admin/"); // da finire
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
