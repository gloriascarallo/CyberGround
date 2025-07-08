package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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
	public static final String SECRET_KEY = "qwerTY-SECRET-KEY-2025";
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
		
			Cookie[] cookies = request.getCookies();
			String guestIdStr = null;
			int guestId=-1;

			if (cookies != null) {
			    for (Cookie c : cookies) {
			        if ("guestId".equals(c.getName())) {
			            guestIdStr = c.getValue(); // es. "42|abc123hash"
		                String[] parts = guestIdStr.split("\\|");

		                if (parts.length == 2) {
		                    try {
		                    	int parsedGuestId = Integer.parseInt(parts[0]);
		                        String expectedHash = Security.hmacSHA256(String.valueOf(parsedGuestId), SECRET_KEY);
		                        if (expectedHash.equals(parts[1])) {
		                        	guestId=parsedGuestId;
		                            break; 
		                        } else {
		                            System.out.println("Hash mismatch - possibile manipolazione!");
		                        }
		                    } catch (NumberFormatException e) {
		                        System.out.println("guestId non è un intero valido.");
		                    }
			          
			        }
			    }
			  }
			}
			
		if(guestId==-1) {
			
			 try (Connection con = ds.getConnection()) {
			        PreparedStatement ps = con.prepareStatement(
			            "INSERT INTO USER () VALUES ()", 
			            Statement.RETURN_GENERATED_KEYS
			        );
			    
			        ps.executeUpdate();

			        ResultSet rs = ps.getGeneratedKeys();
			        if (rs.next()) {
			            guestId = rs.getInt(1);
			            request.getSession().setAttribute("id", guestId);
			            PreparedStatement psCart = con.prepareStatement(
				                "INSERT INTO CART (IDCART) VALUES (?)"
				            );
				            psCart.setInt(1, guestId);
				            psCart.executeUpdate();
				            psCart.close();

				            CartBean cart = new CartBean();
				            cart.setIdCart(guestId);
				            request.getSession().setAttribute("cart", cart);
				        }
				        ps.close();
			        }
			     catch (SQLException e) {
			        e.printStackTrace(); 
			        request.getRequestDispatcher("/500.html").forward(request, response);
			        return;
			    }
		}
		String value = guestId + "|" + Security.hmacSHA256(String.valueOf(guestId), SECRET_KEY);
		Cookie guestCookie = new Cookie("guestId", value);
	    guestCookie.setMaxAge(60 * 60 * 24 * 30); 
	    guestCookie.setPath("/");
	    guestCookie.setHttpOnly(true);
	    response.addCookie(guestCookie);
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
