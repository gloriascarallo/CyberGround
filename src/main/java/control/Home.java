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
import java.util.ArrayList;

import dao.Product_situatedin_cartDaoDataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.CartBean;
import bean.Product_situatedin_cartBean;

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
final String SECRET_KEY = "qwerTY-SECRET-KEY-2025";
		
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
			
			Cookie[] cookies = request.getCookies();
			
			String guestIdStr = null;
			int guestId=-1;
			CartBean cart=new CartBean();

			if (cookies != null) {
			    for (Cookie c : cookies) {
			        if ("guestId".equals(c.getName())) {
			            guestIdStr = c.getValue(); // es. "42|abc123hash"                         System.out.println(guestIdStr);
		                String[] parts = guestIdStr.split("\\|");

		                if (parts.length == 2) {
		                    try {
		                    	int parsedGuestId = Integer.parseInt(parts[0]);
		                        String expectedHash = Security.hmacSHA256(String.valueOf(parsedGuestId), SECRET_KEY);
		                        if (expectedHash.equals(parts[1])) {
		                        
		                        	guestId=parsedGuestId;
		                        	    cart.setIdCart(guestId);
		                        	    Product_situatedin_cartDaoDataSource ds_cart=new Product_situatedin_cartDaoDataSource();
		                        	    ArrayList<Product_situatedin_cartBean> products=new ArrayList<>();
		                        	    products=ds_cart.doRetrieveByIdCart(guestId);
		                        	    cart.setProducts(products);
		                            break; 
		                        } else {
		                            System.out.println("Hash mismatch - possibile manipolazione!");
		                        }
		                    } catch (NumberFormatException e) {
		                        System.out.println("guestId non è un intero valido.");
		                    } catch (SQLException e) {
								
								e.printStackTrace();
								request.getRequestDispatcher("/error/500.html").forward(request, response);
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
		        
		String value = guestId + "|" + Security.hmacSHA256(String.valueOf(guestId), SECRET_KEY);
		System.out.println("Creato id: " + value);
		Cookie guestCookie = new Cookie("guestId", value);
	    guestCookie.setMaxAge(60 * 60 * 24 * 30); 
	    guestCookie.setPath(request.getContextPath()); 
	    guestCookie.setHttpOnly(true);
	    response.addCookie(guestCookie);
	    cart.setIdCart(guestId);
	    
		}
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
