package control_admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product_situatedin_cartBean;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

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
		
		String errors="";
		
		int idCart = 0;
	    try {
	        idCart = Integer.parseInt(request.getParameter("idCart"));
	    } catch (NumberFormatException e) {
	        errors += "ID carrello non valido.<br>";
	    }

	    request.setAttribute("idCart", idCart);
	    String action=request.getParameter("action");
	    if (action == null || action.trim().equals("")) {
	        errors += "Nessuna azione selezionata.<br>";
	    }
	    
	    if(!errors.equals("")) {
	    	request.setAttribute("errors", errors);
	    	request.getRequestDispatcher("/admin/view/user_cart.jsp").forward(request, response);
		    return;
		}

		Product_situatedin_cartDaoDataSource ds=new Product_situatedin_cartDaoDataSource();
		ArrayList<Product_situatedin_cartBean>products=new ArrayList<Product_situatedin_cartBean>();
		
		
		
		switch(action) {
		
		case "date":
			
			Date dateAdded=null;
			String dateAddedParam = request.getParameter("dateAdded");
			
			if (dateAddedParam == null || dateAddedParam.trim().isEmpty()) {
			    errors += "La data è obbligatoria.<br>";
			} else {
			    try {
			        dateAdded = Date.valueOf(dateAddedParam.trim());
			    } catch (IllegalArgumentException e) {
			        errors += "La data inserita non è valida. Usa il formato AAAA-MM-GG.<br>";
			    }
			}
			
			if(!errors.equals("")) {
			    request.setAttribute("errors", errors);
			    request.getRequestDispatcher("/admin/view/user_cart.jsp").forward(request, response);
			    return;
			    
			}
			
		try {
			products=ds.doRetrieveByDateAdded(idCart, dateAdded);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.html").forward(request, response);
			return;
		}
		break;
		
		case "price":
			
			String priceMinParam = request.getParameter("priceMin");
			String priceMaxParam = request.getParameter("priceMax");

			double priceMin = 0;
			double priceMax = 0;
			

			if (priceMinParam == null || priceMinParam.trim().equals("")) {
			    errors += "Il prezzo minimo è obbligatorio.<br>";
			} else {
			    try {
			        priceMin = Double.parseDouble(priceMinParam.trim());
			        if (priceMin < 0) {
			            errors += "Il prezzo minimo non può essere negativo.<br>";
			        }
			    } catch (NumberFormatException e) {
			        errors += "Il prezzo minimo non è un numero valido.<br>";
			    }
			}

			if (priceMaxParam == null || priceMaxParam.trim().equals("")) {
			    errors += "Il prezzo massimo è obbligatorio.<br>";
			} else {
			    try {
			        priceMax = Double.parseDouble(priceMaxParam.trim());
			        if (priceMax < 0) {
			            errors += "Il prezzo massimo non può essere negativo.<br>";
			        }
			    } catch (NumberFormatException e) {
			        errors += "Il prezzo massimo non è un numero valido.<br>";
			    }
			}

			if (errors.equals("") && priceMin > priceMax) {
			    errors += "Il prezzo minimo non può essere maggiore del prezzo massimo.<br>";
			}
			
			if(!errors.equals("")) {
				request.setAttribute("errors", errors);
			    request.getRequestDispatcher("/admin/view/user_cart.jsp").forward(request, response);
			    return;
			}
			
		try {
			
			products=ds.doRetrieveByIdCartAndPriceRange(idCart, priceMin, priceMax);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.html").forward(request, response);
			return;
		}
		break;
		
		default: 
			
			errors+="Nessuna azione selezionata.<br>";
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/admin/view/user_cart.jsp").forward(request, response);
			return;
		}
		
		if(products.isEmpty()) {
			
			errors+="Non ci sono prodotti nel carrello che rispettano tali parametri<br>";
			
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/admin/view/user_cart.jsp").forward(request, response);
			return;
		}
		
		
		request.setAttribute("products_situatedin_cart", products);
	request.getRequestDispatcher("/admin/view/user_cart.jsp").forward(request, response);
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
