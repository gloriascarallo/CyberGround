package control;
import java.util.ArrayList;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
		request.getRequestDispatcher("/500.html").forward(request, response);
		return;
	}
	
	RegisteredUserBean user=null;
	try {
		user=ds_user.doRetrieveByUsername(username);
		if(user!=null && user.getPassword().equals(hasPassword)) {
		request.getSession().setAttribute("isAdmin", Boolean.FALSE);
		request.getSession().setAttribute("isRegisteredUser", Boolean.TRUE);
		request.getSession().setAttribute("id", user.getId());
		
		// da rivedere (controllare id sessione e compararlo con quello dell'utente registrato estratto dal database)
		CartBean cart = (CartBean) request.getSession().getAttribute("cart");
        ArrayList<Product_situatedin_cartBean> dbProducts = ds_cart.doRetrieveByIdCart(user.getId());

        if (dbProducts == null || dbProducts.isEmpty()) {
            // Il carrello nel DB è vuoto, ma potremmo averne uno in sessione
            if (cart != null && cart.getProducts() != null && !cart.getProducts().isEmpty()) {
                // Salva i prodotti del carrello di sessione nel DB
                for (Product_situatedin_cartBean productInCart : cart.getProducts()) {
                    productInCart.setIdCart(user.getId()); 
                    ds_cart.doSave(productInCart);
                }
            } else {
                // Nessun carrello né in sessione né nel DB → crea carrello vuoto
                cart = new CartBean();
            }
        } else {
            // Il DB ha un carrello → usalo
            cart = new CartBean();
            cart.setIdCart(user.getId());
            cart.setProducts(dbProducts);
        }

        // Aggiorna il carrello in sessione
        request.getSession().setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath()+"/guest/view/index.jsp");
        return;
    }
} catch (SQLException e) {
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
