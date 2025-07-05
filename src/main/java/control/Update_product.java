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
 * Servlet implementation class Update_product
 */
@WebServlet("/Update_product")
public class Update_product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update_product() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// imagePath modificato?
		int id=Integer.parseInt(request.getParameter("idProduct"));
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
	request.getRequestDispatcher("/admin/.jsp").forward(request, response); // da finire
	return;
}

ProductBean product=new ProductBean();
ProductDaoDataSource ds=new ProductDaoDataSource();
boolean modified=false;
try {
	product=ds.doRetrieveByKey(id);
} catch (SQLException e) {
	
	e.printStackTrace();
}

if(!product.getName().equals(name)) {
	
	product.setName(name);
	modified=true;
	
}

if(product.getPrice()!=(price)) {
	
	product.setPrice(price);
	modified=true;
	
}

if(!product.getCategoryName().equals(category)) {
	
	product.setCategoryName(category);
	modified=true;
	
}

if(product.getQuantityAvailable()!=(quantityAvailable)) {
	
	product.setQuantityAvailable(quantityAvailable);
	modified=true;
	
}

if(!product.getDescription().equals(description)) {
	
	product.setDescription(description);
	modified=true;
	
}

if(!product.getSupplier().equals(supplier)) {
	
	product.setSupplier(supplier);
	modified=true;
	
}


if(modified) {
	
	try {
		ds.doUpdate(product);
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
}

response.sendRedirect("/admin/"); // da finire
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
