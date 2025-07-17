package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CartBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
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
			if (ds == null) {
			    throw new ServletException("Datasource non trovato.");
			}
			
			int guestId=-1;
			CartBean cart=new CartBean();

			try (Connection con = ds.getConnection()) {
		        PreparedStatement ps = con.prepareStatement(
		            "INSERT INTO USER () VALUES ()", 
		            Statement.RETURN_GENERATED_KEYS
		        );
		        ps.executeUpdate();

		        ResultSet rs = ps.getGeneratedKeys();
		        if (rs.next()) {
		            guestId = rs.getInt(1);
		        }  else {
		        	throw new SQLException("guestId nel cookie non valido (utente non esiste più)");
		    }
		        rs.close();
		        ps.close();

		        PreparedStatement psCart = con.prepareStatement(
		                "INSERT INTO CART (IDCART) VALUES (?)"
		            );
		            psCart.setInt(1, guestId);
		            psCart.executeUpdate();
		            psCart.close();

		        } catch (SQLException e) {
		            e.printStackTrace();
		            request.getRequestDispatcher("/error/500.html").forward(request, response);
		            return;
		        }
		        
		
	    cart.setIdCart(guestId);
	    
		
		request.getSession().setAttribute("cart", cart);
		request.getSession().setAttribute("id", guestId);
		request.getSession().setAttribute("isAdmin", Boolean.FALSE);
		request.getSession().setAttribute("isRegisteredUser", Boolean.FALSE);
		response.sendRedirect(request.getContextPath()+"/guest/view/login.jsp");
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
