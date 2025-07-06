package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import bean.CartBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = null;

		
			try {
				Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");

				ds = (DataSource) envCtx.lookup("jdbc/storage");

			} catch (NamingException e) {
				System.out.println("Error:" + e.getMessage());
			}
		
	
		if(request.getSession().getAttribute("id")==null) {
			
			 try (Connection con = ds.getConnection()) {
			        PreparedStatement ps = con.prepareStatement(
			            "INSERT INTO USER () VALUES ()", 
			            Statement.RETURN_GENERATED_KEYS
			        );
			    
			        ps.executeUpdate();

			        ResultSet rs = ps.getGeneratedKeys();
			        if (rs.next()) {
			            int clientId = rs.getInt(1);
			            request.getSession().setAttribute("id", clientId);
			            PreparedStatement psCart = con.prepareStatement(
				                "INSERT INTO CART (IDCART) VALUES (?)"
				            );
				            psCart.setInt(1, clientId);
				            psCart.executeUpdate();
				            psCart.close();

				            CartBean cart = new CartBean();
				            cart.setIdCart(clientId);
				            request.getSession().setAttribute("cart", cart);
				        }
				        ps.close();
			        }
			     catch (SQLException e) {
			        e.printStackTrace(); 
			    }
		}
			
		request.getSession().setAttribute("isAdmin", Boolean.FALSE);
		request.getSession().setAttribute("isRegisteredUser", Boolean.FALSE);
		response.sendRedirect(request.getContextPath()+"/view/index.jsp");
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
