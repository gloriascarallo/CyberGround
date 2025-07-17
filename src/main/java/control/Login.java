package control;
import java.util.ArrayList;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AdminBean;
import model.CartBean;
import model.Product_situatedin_cartBean;
import model.RegisteredUserBean;

import java.io.IOException;
import java.sql.SQLException;
import dao.AdminDaoDataSource;
import dao.RegisteredUserDaoDataSource;
import dao.Product_situatedin_cartDaoDataSource;

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
		
		request.getRequestDispatcher("/guest/view/login.jsp").forward(request, response);
		return;
		
		}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String hasPassword=Security.toHash(password);
		System.out.println(hasPassword);
		String errors="";
		RequestDispatcher dispatcherToLoginPage=request.getRequestDispatcher("/guest/view/login.jsp");
		
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
			
	    request.getSession().invalidate();
        HttpSession newSession = request.getSession(true);
		newSession.setAttribute("isAdmin", Boolean.TRUE);
		newSession.setAttribute("isRegisteredUser", Boolean.FALSE);
		newSession.setAttribute("id", admin.getId());
		response.sendRedirect(request.getContextPath()+"/admin/view/dashboard.jsp");
		return;
	}
	}
	
	catch(SQLException e) {
		e.printStackTrace();
		request.getRequestDispatcher("/error/500.html").forward(request, response);
		return;
	}
	
	RegisteredUserBean user=null;
	try {
		user=ds_user.doRetrieveByUsername(username);
		if(user!=null && user.getPassword().equals(hasPassword)) {
		
			 // Salva carrello della vecchia sessione prima di invalidare
		        CartBean oldCart = (CartBean) request.getSession().getAttribute("cart");

		        // Invalida la vecchia sessione e creane una nuova
		        request.getSession().invalidate();
		        HttpSession newSession = request.getSession(true);

		        newSession.setAttribute("isAdmin", Boolean.FALSE);
		        newSession.setAttribute("isRegisteredUser", Boolean.TRUE);
		        newSession.setAttribute("id", user.getId());

		        CartBean cart = new CartBean();
		        cart.setIdCart(user.getId());

		        ArrayList<Product_situatedin_cartBean> dbProducts = ds_cart.doRetrieveByIdCart(user.getId());

		        if (dbProducts == null || dbProducts.isEmpty()) {
		            // se il DB è vuoto, usa carrello della vecchia sessione se disponibile
		            if (oldCart != null && oldCart.getProducts() != null && !oldCart.getProducts().isEmpty()) {
		                for (Product_situatedin_cartBean product : oldCart.getProducts()) {
		                    product.setIdCart(user.getId());
		                    ds_cart.doSave(product); // salva nel DB associandolo all'utente
		                }
		                cart.setProducts(oldCart.getProducts());
		            } else {
		                cart.setProducts(new ArrayList<>()); // carrello vuoto
		            }
		        } else {
		            cart.setProducts(dbProducts); // carrello dal DB
		        }

		        // Imposta il carrello nella nuova sessione
		        newSession.setAttribute("cart", cart);

		        System.out.println("Carrello utente loggato ID: " + cart.getIdCart());
		        response.sendRedirect(request.getContextPath() + "/UploadProducts");
		        return;
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		    request.getRequestDispatcher("/error/500.html").forward(request, response);
		    return;
		}
		

	
	
			errors+="Username e password non validi!<br>";
			request.setAttribute("errors", errors);
			dispatcherToLoginPage.forward(request, response);
			return;
			
	}

}
