package control;
import java.util.ArrayList;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import dao.AdminDaoDataSource;
import dao.RegisteredUserDaoDataSource;
import dao.Product_situatedin_cartDaoDataSource;
import bean.AdminBean;
import bean.CartBean;
import bean.Product_situatedin_cartBean;
import bean.RegisteredUserBean;

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
		
		request.getRequestDispatcher("/view/login.jsp").forward(request, response);
		return;
		
		}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String hasPassword=Security.toHash(password);
		String errors="";
		RequestDispatcher dispatcherToLoginPage=request.getRequestDispatcher("/view/login.jsp");
		
		if(username==null || username.trim().equals("")) {
			
			errors+="Inserisci l'username.<br>";
		} 
		
		else {
			username=username.trim();
			
		}
		
if(password==null || password.trim().equals("")) {
			
			errors+="Inserisci la password.<br>";
		} 
		
		else {
			password=password.trim();
			
		}

if(!errors.equals("")) {
	
	request.setAttribute("errors", errors);
	dispatcherToLoginPage.forward(request, response);
	return;
}

	RegisteredUserDaoDataSource ds_user=new RegisteredUserDaoDataSource();
	AdminDaoDataSource ds_admin=new AdminDaoDataSource();
	Product_situatedin_cartDaoDataSource ds_cart=new Product_situatedin_cartDaoDataSource();
	AdminBean admin=null;
	
	try {
		admin=ds_admin.doRetrieveByUsername(username);
		if(admin!=null && admin.getPassword().equals(hasPassword)) {
		request.getSession().setAttribute("isAdmin", Boolean.TRUE);
		request.getSession().setAttribute("isRegisteredUser", Boolean.FALSE);
		
		request.getSession().removeAttribute("cart");
		request.getSession().removeAttribute("id");
		
		request.getSession().setAttribute("id", admin.getId());
		response.sendRedirect(request.getContextPath()+"/admin/view/dashboard.jsp");
		return;
	}
	}
	
	catch(SQLException e) {
		e.printStackTrace();
		request.getRequestDispatcher("/500.html").forward(request, response);
		return;
	}
	
	RegisteredUserBean user=null;
	try {
		user=ds_user.doRetrieveByUsername(username);
		if(user!=null && user.getPassword().equals(hasPassword)) {
		request.getSession().setAttribute("isAdmin", Boolean.FALSE);
		request.getSession().setAttribute("isRegisteredUser", Boolean.TRUE);
		
		
		CartBean cart=(CartBean)request.getSession().getAttribute("cart");
		if(cart!=null) {
		ArrayList<Product_situatedin_cartBean> products_incart=cart.getProducts();
		for(Product_situatedin_cartBean product_incart: products_incart) {
			
				
				ds_cart.doSave(product_incart);
				
			}
		}
		response.sendRedirect(request.getContextPath()+"/view/index.jsp");
		return;
	}
	}
			catch(SQLException e) {
				
				e.printStackTrace();
				request.getRequestDispatcher("/500.html").forward(request, response);
				return;
			}
	
			errors+="Username e password non validi!<br>";
			request.setAttribute("errors", errors);
			dispatcherToLoginPage.forward(request, response);
			return;
			
	}

}
