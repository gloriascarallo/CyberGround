package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.AdminDaoDataSource;
import dao.RegisteredUserDaoDataSource;
import dao.Product_situatedin_cartDaoDataSource;
import bean.CartBean;
import bean.Product_situatedin_cartBean;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        
    }

  

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String hasPassword=Security.toHash(password);
		String errors="";
		RequestDispatcher dispatcherToLoginPage=request.getRequestDispatcher("/view/login.jsp");
		
		if(username==null || username.trim().equals("")) {
			
			errors+="Inserisci l'username<br>";
		} 
		
		else {
			username=username.trim();
			
		}
		
if(password==null || password.trim().equals("")) {
			
			errors+="Inserisci la password<br>";
		} 
		
		else {
			password=password.trim();
			
		}

if(!errors.equals("")) {
	
	request.setAttribute("errors", errors);
	dispatcherToLoginPage.forward(request, response);
}

	RegisteredUserDaoDataSource ds_user=new RegisteredUserDaoDataSource();
	AdminDaoDataSource ds_admin=new AdminDaoDataSource();
	Product_situatedin_cartDaoDataSource ds_cart=new Product_situatedin_cartDaoDataSource();
	
	try {if(ds_admin.doRetrieveByUsername(username).getUsername().equals(username) && ds_admin.doRetrieveByUsername(username).getPassword().equals(hasPassword)) {
		request.getSession().setAttribute("isAdmin", Boolean.TRUE);
		request.getSession().setAttribute("isRegisteredUser", Boolean.FALSE);
		
		request.getSession().removeAttribute("cart");
		request.getSession().removeAttribute("id");
		
		request.getSession().setAttribute("id", ds_admin.doRetrieveByUsername(username).getId());
		// redirect
	}
		// da aggiungere confronto con tabella admin
		if(ds_user.doRetrieveByUsername(username).getUsername().equals(username) && ds_user.doRetrieveByUsername(username).getPassword().equals(hasPassword)) {
		request.getSession().setAttribute("isAdmin", Boolean.FALSE);
		request.getSession().setAttribute("isRegisteredUser", Boolean.TRUE);
		//request.getSession().setAttribute("id", ds.doRetrieveByKey(username).getId()); perche l'id nella sessione c'è dall'inizio
		
		CartBean cart=(CartBean)request.getSession().getAttribute("cart");
		ArrayList<Product_situatedin_cartBean> products_incart=cart.getProducts();
		for(Product_situatedin_cartBean product_incart: products_incart) {
			
			
			try {
				
				ds_cart.doSave(product_incart);
				
			}
			catch(SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		response.sendRedirect("/view/index.jsp");
		return;
	}
		else {
			errors+="Username e password non validi!<br>";
			request.setAttribute("errors", errors);
			dispatcherToLoginPage.forward(request, response);
			
		}
		
		
	}
	
	catch(SQLException e) {
		e.printStackTrace();
	    
	
	}
	
	
	response.sendRedirect("/view/index.jsp");
	return;
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
