package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.RegisteredUserBean;
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
		String username=request.getParameter("username");
		RegisteredUserDaoDataSource ds=new RegisteredUserDaoDataSource();
		RegisteredUserBean user=new RegisteredUserBean();
		try {
			
			user=ds.doRetrieveByUsername(username);
			
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		request.setAttribute("user", user);
		request.getRequestDispatcher("/admin/").forward(request, response); // da finire
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
