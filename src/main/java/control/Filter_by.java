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
       
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errors="";
		String action=request.getParameter("action");
		if(action==null || action.trim().equals("")) {
			
			errors+="Operazione non riuscita.<br>";
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/guest/view/index.jsp").forward(request, response);
			return;
		}
		ArrayList<ProductBean> products=new ArrayList<ProductBean>();
		ProductDaoDataSource ds=new ProductDaoDataSource();
		
		switch(action) {
		
		case "supplier": 
			
			String supplier=request.getParameter("supplierInput");
			if(supplier==null || supplier.equals("")) {
				
				errors+="Aggiungi nome fornitore<br>";
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("/guest/view/index.jsp").forward(request, response);
				return;
				
			}
			try {
				products=ds.doRetrievebySupplier(supplier);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				request.getRequestDispatcher("/error/500.html").forward(request, response);
				return;
			}
		    break;
		    
		case "price":
			
			String priceMinStr=request.getParameter("priceMin");
			double priceMin=0;
			String priceMaxStr=request.getParameter("priceMax");
			double priceMax=0;
			if (priceMinStr== null || priceMinStr.trim().isEmpty() || priceMaxStr== null || priceMaxStr.trim().isEmpty()) {
			    errors += "Inserisci prezzi validi.<br>";
			} else {
			    try {
			       priceMin = Double.parseDouble(priceMinStr);
			       priceMax=Double.parseDouble(priceMaxStr);
			        if (priceMin < 0 || priceMax<0) {
			            errors += "Il prezzo non può essere negativo.<br>";
			        }
			        if (priceMin > priceMax) {
                        errors+="Il prezzo minimo non può essere maggiore del massimo.<br>";
			        }
			    } catch (NumberFormatException e) {
			        errors += "Il prezzo deve essere un numero valido.<br>";
			    }
			}
			
			if(!errors.equals("")) {
				
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("/guest/view/index.jsp").forward(request, response);
				return;
				
			}
			
			try {
				products=ds.doRetrievebyPriceRange(priceMin, priceMax);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				request.getRequestDispatcher("/error/500.html").forward(request, response);
				return;
			}
			break;
			
		case "yearUpload": 
			
			String yearUploadStr=request.getParameter("yearUpload");
			int yearUpload=0;
			if (yearUploadStr== null || yearUploadStr.trim().isEmpty()) {
			    errors += "Inserisci anno valido.<br>";
			} else {
			    try {
			       yearUpload = Integer.parseInt(yearUploadStr);
			        if (yearUpload < 0) {
			            errors += "L'anno non può essere negativo.<br>";
			        }
			    } catch (NumberFormatException e) {
			        errors += "L'anno deve essere un numero valido.<br>";
			    }
			}
			
			if(!errors.equals("")) {
				
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("/guest/view/index.jsp").forward(request, response);
				return;
				
			}
			
			
			try {
				products=ds.doRetrievebyYearUpload(yearUpload);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				request.getRequestDispatcher("/error/500.html").forward(request, response);
				return;
			}
			
		break;
			
		default: 
			
			errors+="Input errato o prodotti non trovati<br>";
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/guest/view/index.jsp").forward(request, response);
			return;
			
			
		}
			if(products.isEmpty()) {
				
				errors+="Input errato o prodotti non trovati<br>";
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("/guest/view/index.jsp").forward(request, response);
				return;
				
			}
			request.setAttribute("products", products);
			request.getRequestDispatcher("/guest/view/index.jsp").forward(request, response);
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
