package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import bean.RegisteredUser;
import bean.RegisteredUser_has_address;
import bean.RegisteredUser_has_method_payment;
import dao.RegisteredUserDaoDataSource;
import dao.RegisteredUser_has_addressDaoDataSource;
import dao.RegisteredUser_has_method_paymentDaoDataSource;
import jakarta.servlet.RequestDispatcher;
/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcherToRegistrationPage=request.getRequestDispatcher("registration.jsp");
		String errors="";
		String username=(String)request.getAttribute("username");
		
		RegisteredUserDaoDataSource ds1=new RegisteredUserDaoDataSource();
		
		
		try {
			if(ds1.doRetrieveByKey(username).getUsername().equals(username)) {
		
			errors+="Esiste già un utente con tale username.<br>";
			request.setAttribute("errors", errors);
			dispatcherToRegistrationPage.forward(request, response);
			}
		}
			catch(SQLException e) {
				
				e.printStackTrace();
				dispatcherToRegistrationPage.forward(request, response);
			}
		
		
		String name=(String)request.getAttribute("name");
		String lastName=(String)request.getAttribute("lastName");
		String telephone=(String)request.getAttribute("telephone");
		String email=(String)request.getAttribute("email");
		String password=(String)request.getAttribute("password");
		String[] addresses=(String[])request.getParameterValues("address");
		String[] pans=(String[])request.getParameterValues("methodPaymentPAN");
		String[] expirationDates=(String[])request.getParameterValues("methodPaymentScadenza");
		String[] cvcs=(String[])request.getParameterValues("methodPaymentCVC");
		
		RegisteredUser user=new RegisteredUser();
		
		user.setId((Integer)request.getSession().getAttribute("id"));
		user.setName(name);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setPassword(password);
		user.setTelephone(telephone);
		user.setEmail(email);
		
		ds1=new RegisteredUserDaoDataSource();
		
		try {ds1.doSave(user);
		}
		
		catch(SQLException e) {
			
			e.printStackTrace();
			dispatcherToRegistrationPage.forward(request, response);
		}
		
		RegisteredUser_has_addressDaoDataSource ds2=new RegisteredUser_has_addressDaoDataSource();
		RegisteredUser_has_address has_address=new RegisteredUser_has_address();
		
		for(String address: addresses) {
			
			has_address.setNameAddress(address);
			has_address.setUsernameRegisteredUser(username);
			
			try {
				
				ds2.doSave(has_address);
			}
			catch(SQLException e) {
				
				e.printStackTrace();
				dispatcherToRegistrationPage.forward(request, response);
			}
		}
		
		RegisteredUser_has_method_paymentDaoDataSource ds3=new RegisteredUser_has_method_paymentDaoDataSource();
		RegisteredUser_has_method_payment has_method_payment=new RegisteredUser_has_method_payment();
		
		for(String pan: pans) {
			for(String expirationDate: expirationDates) {
				for(String cvc: cvcs) {
					
					has_method_payment.setPan(pan);
					has_method_payment.setExpirationDate(expirationDate);
					has_method_payment.setCvc(cvc);
					
					try {
						
						ds3.doSave(has_method_payment);
						
					}
					catch(SQLException e) {
						
						e.printStackTrace();
						dispatcherToRegistrationPage.forward(request, response);
					}
				}
				
			}
			
		}
		
		response.sendRedirect("login.jsp");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
