package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import dao.RegisteredUserDaoDataSource;
import bean.RegisteredUserBean;
/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errors="";
	    
	Boolean isRegisteredUser=(Boolean)request.getSession().getAttribute("isRegisteredUser");
	if (Boolean.FALSE.equals(isRegisteredUser)) {
	    errors += "Non sei utente<br>";
	    request.setAttribute("errors", errors);
	    request.getRequestDispatcher("/view/index.jsp").forward(request, response);
	    return;
	}
	
	
		String username=(String)request.getSession().getAttribute("username");
		RequestDispatcher dispatchToIndexPage=request.getRequestDispatcher("/view/index.jsp");
		RequestDispatcher dispatchToUserPage=request.getRequestDispatcher("/view/user.jsp");
		RegisteredUserBean user=new RegisteredUserBean();
		RegisteredUserDaoDataSource ds=new RegisteredUserDaoDataSource();
		try {
			
			user=ds.doRetrieveByKey(username);
			
			
			
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			dispatchToIndexPage.forward(request, response);
		}
		
		request.setAttribute("user", user);
		dispatchToUserPage.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
