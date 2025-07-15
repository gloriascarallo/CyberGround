package control_admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import bean.RegisteredUserBean;
import dao.RegisteredUserDaoDataSource;

/**
 * Servlet implementation class UploadUser
 */
@WebServlet("/UploadUser")
public class UploadUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errors="";
		String idStr = request.getParameter("id");

	    if (idStr == null || idStr.trim().isEmpty()) {
	        errors = "Utente non trovato.<br>";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/admin/view/users.jsp").forward(request, response);
	        return;
	    }

	    int id;
	    try {
	        id = Integer.parseInt(idStr);
	    } catch (NumberFormatException e) {
	        errors = "ID utente non valido.<br>";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/admin/view/users.jsp").forward(request, response);
	        return;
	    }
	    
		RequestDispatcher dispatchToUserPage=request.getRequestDispatcher("/admin/view/user_profile.jsp");
		RegisteredUserBean user=null;
		RegisteredUserDaoDataSource ds=new RegisteredUserDaoDataSource();
		try {
			
			user=ds.doRetrieveByKey(id);
			if (user == null) {
				errors="Utente non trovato.<br>";
				request.setAttribute("errors", errors);
	            request.getRequestDispatcher("/admin/view/users.jsp").forward(request, response);
	            return;
			
			}
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.html").forward(request, response);
			return;
		}
		
		request.setAttribute("user", user);
		dispatchToUserPage.forward(request, response);
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
