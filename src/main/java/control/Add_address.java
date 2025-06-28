package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import bean.RegisteredUser_has_addressBean;
import dao.RegisteredUser_has_addressDaoDataSource;
/**
 * Servlet implementation class Add_address
 */
@WebServlet("/Add_address")
public class Add_address extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_address() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=(String)request.getSession().getAttribute("username");
		String nameAddress=request.getParameter("indirizzo");
		String url=request.getRequestURI();
		
		RequestDispatcher dispatcherToAdd_addressPage=request.getRequestDispatcher("add_address.jsp");
		RegisteredUser_has_addressBean registereduser_has_address=new RegisteredUser_has_addressBean();
		RegisteredUser_has_addressDaoDataSource ds=new RegisteredUser_has_addressDaoDataSource();
		
		registereduser_has_address.setNameAddress(nameAddress);
		registereduser_has_address.setUsernameRegisteredUser(username);
		
		try {
			
			ds.doSave(registereduser_has_address);
			
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			dispatcherToAdd_addressPage.forward(request, response);
		}
		
		response.sendRedirect(url);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
