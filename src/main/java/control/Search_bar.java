package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.ProductDaoDataSource;
import bean.ProductBean;

/**
 * Servlet implementation class Filter_by
 */
@WebServlet("/Search_bar")
public class Search_bar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search_bar() {
        super();
       
    }

    private String escapeJson(String value) {
        return value == null ? "" : value.replace("\"", "\\\"");
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
			
		default: 
			
			String name=request.getParameter("name");
			if(name==null || name.equals("")) {
				
				errors+="Aggiungi nome prodotto<br>";
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("/guest/view/index.jsp").forward(request, response);
				return;
				
			}
			try {
				products=ds.doRetrievebyName(name);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				request.getRequestDispatcher("/error/500.html").forward(request, response);
				return;
			}
			break;
		}
			if(products.isEmpty()) {
				
				errors+="Input errato o prodotti non trovati<br>";
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("/guest/view/index.jsp").forward(request, response);
				return;
				
			}
			
			response.setContentType("application/json");
	        PrintWriter out = response.getWriter();
	        out.print("{\"products\":[");

	        for (int i = 0; i < products.size(); i++) {
	            ProductBean p = products.get(i);
	            out.print("{");
	            out.print("\"name\":\"" + escapeJson(p.getName()) + "\",");
	            out.print("\"idProduct\":" + p.getIdProduct() + ",");
	            out.print("\"price\":" + p.getPrice() + ",");
	            out.print("\"dateUpload\":\"" + p.getDateUpload() + "\",");
	            out.print("\"supplier\":\"" +  escapeJson(p.getSupplier()) + "\",");
	            out.print("\"imagePath\":\"" + escapeJson(p.getImagePath()) + "\"");
	            out.print("}");
	            if (i < products.size() - 1) {
	                out.print(",");
	            }
	        }

	        out.print("]}");
	        out.flush();
	    }

	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}  