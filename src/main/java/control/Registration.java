package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AddressBean;
import model.Method_paymentBean;
import model.RegisteredUserBean;
import model.RegisteredUser_has_addressBean;
import model.RegisteredUser_has_method_paymentBean;

import java.io.IOException;
import java.sql.SQLException;
import dao.AddressDaoDataSource;
import dao.Method_paymentDaoDataSource;
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
		
		request.getRequestDispatcher("/guest/view/registration.jsp").forward(request, response);
		return;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcherToRegistrationPage=request.getRequestDispatcher("/guest/view/registration.jsp");
		String errors="";
		Object idObj = request.getSession().getAttribute("id");
	    if (idObj == null) {
	    	errors = "Sessione scaduta o ID utente mancante. Ricarica la pagina e riprova.";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/error/expiredSession.jsp").forward(request, response);
	        return;
	    }
	    
	    int id=(Integer)idObj;
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
			
			errors+="Inserisci l'username.<br>";
			
		} 
		
		else {
			username=username.trim();
			
		}

if(name==null || name.trim().equals("")) {
	
	errors+="Inserisci il nome.<br>";
	
} 

else {
	name=name.trim();
	
}

if(lastName==null || lastName.trim().equals("")) {
	
	errors+="Inserisci il cognome.<br>";
	
} 

else {
	lastName=lastName.trim();
	
}

if(telephone==null || telephone.trim().equals("")) {
	
	errors+="Inserisci il numero di telefono.<br>";
	
} 

else {
	telephone=telephone.trim();
	
}

if(email==null || email.trim().equals("")) {
	
	errors+="Inserisci l'email.<br>";
	
} 

else {
	email=email.trim();
	
}

if(password==null || password.trim().equals("")) {
	
	errors+="Inserisci la password.<br>";
	
} 

else {
	password=password.trim();
	password=Security.toHash(password);
	
}

for(String address: addresses) {
	
	if(address==null || address.trim().equals("")) {
		
		errors+="Inserisci indirizzi.<br>";
		break;
	} 

	else {
		address=address.trim();
		
	}
	
}

for(String pan: pans) {
	
	if(pan==null || pan.trim().equals("")) {
		
		errors+="Inserisci pan.<br>";
		break;
	} 

	else {
		pan=pan.trim();
		
	}
	
}

for(String expirationDate: expirationDates) {
	
	if(expirationDate==null || expirationDate.trim().equals("")) {
		
		errors+="Inserisci scadenza.<br>";
		break;
	} 

	else {
		expirationDate=expirationDate.trim();
		
	}
	
}

for(String cvc: cvcs) {
	
	if(cvc==null || cvc.trim().equals("")) {
		
		errors+="Inserisci cvc.<br>";
		break;
	} 

	else {
		cvc=cvc.trim();
		
	}
	
}

if(!errors.equals("")) {
	
	request.setAttribute("errors", errors);
	dispatcherToRegistrationPage.forward(request, response);
	return;
}
		
		RegisteredUserDaoDataSource ds_user=new RegisteredUserDaoDataSource();
		
		
		try {
			if(ds_user.doRetrieveByUsername(username)!=null) {
		
			errors+="Esiste già un utente con tale username.<br>";
			request.setAttribute("errors", errors);
			dispatcherToRegistrationPage.forward(request, response);
			return;
			}
		}
			catch(SQLException e) {
				
				e.printStackTrace();
				request.getRequestDispatcher("/error/500.html").forward(request, response);
				return;
			}
		

		
		RegisteredUserBean user=new RegisteredUserBean();
		
		user.setId(id);
		user.setUsername(username);
		user.setName(name);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setTelephone(telephone);
		user.setEmail(email);

		try {ds_user.doSave(user);
		}
		
		catch(SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.html").forward(request, response);
			return;
		}
		
		RegisteredUser_has_addressDaoDataSource ds_hasaddress=new RegisteredUser_has_addressDaoDataSource();
		AddressDaoDataSource ds_address=new AddressDaoDataSource();
		RegisteredUser_has_addressBean has_address=null;
		AddressBean address=null;
	
		for(String addressName: addresses) {
			AddressBean existingAddress;
			try {
				existingAddress = ds_address.doRetrieveByKey(addressName);
			} catch (SQLException e) {
				
				e.printStackTrace();
				request.getRequestDispatcher("/error/500.html").forward(request, response);
	            return;
			}

		    if (existingAddress == null) {
		        // Indirizzo non esiste, allora lo salvo
		        address = new AddressBean();
		        address.setName(addressName);
		        try {
		            ds_address.doSave(address);
		            
		            
		        } catch(SQLException e) {
		            e.printStackTrace();
		            request.getRequestDispatcher("/error/500.html").forward(request, response);
		            return;
		        }
		    }

		    // Crea la relazione utente-indirizzo
		    has_address = new RegisteredUser_has_addressBean();
		    has_address.setNameAddress(addressName);
		    has_address.setIdRegisteredUser(id);

		    try {
		        ds_hasaddress.doSave(has_address);
		    } catch(SQLException e) {
		        e.printStackTrace();
		        request.getRequestDispatcher("/error/500.html").forward(request, response);
		        return;
		    }
			
		}
		
		RegisteredUser_has_method_paymentDaoDataSource ds_has_method_payment = new RegisteredUser_has_method_paymentDaoDataSource();
		Method_paymentDaoDataSource ds_method_payment = new Method_paymentDaoDataSource();
		RegisteredUser_has_method_paymentBean has_method_payment = null;
		Method_paymentBean method_payment = null;

		for (int i = 0; i < pans.length; i++) {
		    Method_paymentBean existingMethodPayment;
		    try {
		        existingMethodPayment = ds_method_payment.doRetrieveByKey(pans[i]);
		    } catch (SQLException e) {
		        e.printStackTrace();
		        request.getRequestDispatcher("/error/500.html").forward(request, response);
		        return;
		    }

		    if (existingMethodPayment == null) {
		        // Se il metodo di pagamento non esiste, lo salvo
		        method_payment = new Method_paymentBean();
		        method_payment.setPan(pans[i]);
		        method_payment.setExpirationDate(expirationDates[i]);
		        method_payment.setCvc(cvcs[i]);
		        try {
		            ds_method_payment.doSave(method_payment);
		        } catch (SQLException e) {
		            e.printStackTrace();
		            request.getRequestDispatcher("/error/500.html").forward(request, response);
		            return;
		        }
		    }

		    // Crea la relazione utente - metodo di pagamento
		    has_method_payment = new RegisteredUser_has_method_paymentBean();
		    has_method_payment.setIdRegisteredUser(id);
		    has_method_payment.setPan(pans[i]);
		    has_method_payment.setExpirationDate(expirationDates[i]);
		    has_method_payment.setCvc(cvcs[i]);

		    try {
		        ds_has_method_payment.doSave(has_method_payment);
		    } catch (SQLException e) {
		        e.printStackTrace();
		        request.getRequestDispatcher("/error/500.html").forward(request, response);
		        return;
		    }
		}
			
		// Cancella il cookie guestId
		Cookie guestCookie = new Cookie("guestId", "");
		guestCookie.setMaxAge(0);
		guestCookie.setPath("/"); 
		response.addCookie(guestCookie);

		request.setAttribute("message", "Registrazione avvenuta con successo!<br>");
		request.getRequestDispatcher("/guest/view/login.jsp").forward(request, response);
		return;
		
	}

	}
