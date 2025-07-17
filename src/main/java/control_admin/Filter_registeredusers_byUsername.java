package control_admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.RegisteredUserBean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.RegisteredUserDaoDataSource;
/**
 * Servlet implementation class Filter_registeredusers_byUsername
 */
@WebServlet("/Filter_registeredusers_byUsername")
public class Filter_registeredusers_byUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Filter_registeredusers_byUsername() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errors="";
		String username=request.getParameter("username");
		if(username==null || username.trim().equals("")) {
			
			errors+="Inserire username.<br>";
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/admin/view/users.jsp").forward(request, response);
			return;
			
		}
		RegisteredUserDaoDataSource ds=new RegisteredUserDaoDataSource();
		ArrayList<RegisteredUserBean> users=null;
		try {
			
			users=ds.doRetrieveByUsername(username);
			
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.html").forward(request, response);
			return;
			
		}
		
		if(users==null || users.isEmpty()) {
			
			errors+="Utenti non trovati.<br>";
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/admin/view/users.jsp").forward(request, response);
			return;
		}
		
		
		request.setAttribute("users", users);
		request.getRequestDispatcher("/admin/view/users.jsp").forward(request, response); 
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
