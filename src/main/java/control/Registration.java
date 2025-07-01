package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import bean.RegisteredUserBean;
import bean.RegisteredUser_has_addressBean;
import bean.RegisteredUser_has_method_paymentBean;
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
		
		RequestDispatcher dispatcherToRegistrationPage=request.getRequestDispatcher("/view/registration.jsp");
		String errors="";
		String username=request.getParameter("username");
		String name=request.getParameter("name");
		String lastName=request.getParameter("lastName");
		String telephone=request.getParameter("telephone");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String[] addresses=(String[])request.getParameterValues("address");
		String[] pans=(String[])request.getParameterValues("methodPaymentPAN");
		String[] expirationDates=(String[])request.getParameterValues("methodPaymentScadenza");
		String[] cvcs=(String[])request.getParameterValues("methodPaymentCVC");
		
if(username==null || username.trim().equals("")) {
			
			errors+="Inserisci l'username<br>";
			
		} 
		
		else {
			username=username.trim();
			
		}

if(name==null || name.trim().equals("")) {
	
	errors+="Inserisci il nome<br>";
	
} 

else {
	name=name.trim();
	
}

if(lastName==null || lastName.trim().equals("")) {
	
	errors+="Inserisci il cognome<br>";
	
} 

else {
	lastName=lastName.trim();
	
}

if(telephone==null || telephone.trim().equals("")) {
	
	errors+="Inserisci il numero di telefono<br>";
	
} 

else {
	telephone=telephone.trim();
	
}

if(email==null || email.trim().equals("")) {
	
	errors+="Inserisci l'email<br>";
	
} 

else {
	email=email.trim();
	
}

if(password==null || password.trim().equals("")) {
	
	errors+="Inserisci la password<br>";
	
} 

else {
	password=password.trim();
	password=Security.toHash(password);
	
}

for(String address: addresses) {
	
	if(address==null || address.trim().equals("")) {
		
		errors+="Inserisci indirizzi<br>";
		break;
	} 

	else {
		address=address.trim();
		
	}
	
}

for(String pan: pans) {
	
	if(pan==null || pan.trim().equals("")) {
		
		errors+="Inserisci pan<br>";
		break;
	} 

	else {
		pan=pan.trim();
		
	}
	
}

for(String expirationDate: expirationDates) {
	
	if(expirationDate==null || expirationDate.trim().equals("")) {
		
		errors+="Inserisci scadenza<br>";
		break;
	} 

	else {
		expirationDate=expirationDate.trim();
		
	}
	
}

for(String cvc: cvcs) {
	
	if(cvc==null || cvc.trim().equals("")) {
		
		errors+="Inserisci cvc<br>";
		break;
	} 

	else {
		cvc=cvc.trim();
		
	}
	
}

if(!errors.equals("")) {
	
	request.setAttribute("errors", errors);
	dispatcherToRegistrationPage.forward(request, response);
}
		
		RegisteredUserDaoDataSource ds_user=new RegisteredUserDaoDataSource();
		
		
		try {
			if(ds_user.doRetrieveByKey(username).getUsername().equals(username)) {
		
			errors+="Esiste già un utente con tale username<br>";
			request.setAttribute("errors", errors);
			dispatcherToRegistrationPage.forward(request, response);
			}
		}
			catch(SQLException e) {
				
				e.printStackTrace();
				dispatcherToRegistrationPage.forward(request, response);
			}
		

		
		RegisteredUserBean user=new RegisteredUserBean();
		
		user.setId((Integer)request.getSession().getAttribute("id"));
		user.setUsername(username);
		user.setName(name);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setPassword(password);
		user.setTelephone(telephone);
		user.setEmail(email);
		
		ds_user=new RegisteredUserDaoDataSource();
		// salvarce anche user e cart nel database?
		try {ds_user.doSave(user);
		}
		
		catch(SQLException e) {
			
			e.printStackTrace();
			dispatcherToRegistrationPage.forward(request, response);
		}
		
		RegisteredUser_has_addressDaoDataSource ds2=new RegisteredUser_has_addressDaoDataSource();
		RegisteredUser_has_addressBean has_address=new RegisteredUser_has_addressBean();
		
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
		RegisteredUser_has_method_paymentBean has_method_payment=new RegisteredUser_has_method_paymentBean();
		
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
		
		response.sendRedirect("/view/login.jsp");
		return;
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
