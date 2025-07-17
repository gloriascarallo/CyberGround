package control_admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.RegisteredUserDaoDataSource;
import model.RegisteredUserBean;
/**
 * Servlet implementation class UploadUsers
 */
@WebServlet("/UploadUsers")
public class UploadUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<RegisteredUserBean> users=new ArrayList<>();
		RegisteredUserDaoDataSource ds=new RegisteredUserDaoDataSource();
		
		try {
			users=(ArrayList<RegisteredUserBean>) ds.doRetrieveAll(null);
		} catch (SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.html").forward(request, response);
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
