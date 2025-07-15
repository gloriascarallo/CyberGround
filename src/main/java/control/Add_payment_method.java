package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import dao.Method_paymentDaoDataSource;
import bean.Method_paymentBean;
import bean.RegisteredUser_has_method_paymentBean;
import dao.RegisteredUser_has_method_paymentDaoDataSource;

/**
 * Servlet implementation class Add_payment_method
 */
@WebServlet("/Add_payment_method")
public class Add_payment_method extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_payment_method() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/registeredUser/view/add_payment_method.jsp").forward(request, response);
		return;
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errors="";
		RequestDispatcher dispatcherToAdd_payment_methodPage=request.getRequestDispatcher("/registeredUser/view/add_payment_method.jsp");
		Object idObj=request.getSession().getAttribute("id");
		if (idObj == null) {
			errors = "Sessione scaduta o ID utente mancante. Ricarica la pagina e riprova.";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/error/expiredSession.jsp").forward(request, response);
	        return;
	    }
	    int id = (Integer) idObj;
	    String redirectAfter = request.getParameter("redirectAfter");
		String pan=request.getParameter("PAN");
		String expirationDate=request.getParameter("Scadenza");
		String cvc=request.getParameter("CVC");
		
		if(pan==null || pan.trim().equals("")) {
			errors+="Inserire pan.<br>";
		
			
		}
		
		else {
			
			pan=pan.trim();
			
		}
		
		if(expirationDate==null || expirationDate.trim().equals("")) {
			errors+="Inserire scadenza.<br>";
		
			
		}
		
		else {
			
			expirationDate=expirationDate.trim();
			
		}
		
		if(cvc==null || cvc.trim().equals("")) {
			errors+="Inserire cvc.<br>";
		
			
		}
		
		else {
			
			cvc=cvc.trim();
			
		}
		
		
		if(!errors.equals("")) {
			
			request.setAttribute("errors", errors);
			dispatcherToAdd_payment_methodPage.forward(request, response);
			return;
			
		}
		
		Method_paymentDaoDataSource ds_method_payment=new Method_paymentDaoDataSource();
		
		try {
			
			Method_paymentBean method_payment=ds_method_payment.doRetrieveByKey(pan);
			
			 if (method_payment == null) {
			        Method_paymentBean newMethod_payment = new Method_paymentBean();
			        newMethod_payment.setPan(pan);
			        newMethod_payment.setExpirationDate(expirationDate);
			        newMethod_payment.setCvc(cvc);
			        ds_method_payment.doSave(newMethod_payment);
			    }
			}
			catch(SQLException e) {
				
				e.printStackTrace();
				request.getRequestDispatcher("/error/500.html").forward(request, response);
				return;
			}
			
			
		
		RegisteredUser_has_method_paymentBean registereduser_has_method_payment=new RegisteredUser_has_method_paymentBean();
		RegisteredUser_has_method_paymentDaoDataSource ds_has_method_payment=new RegisteredUser_has_method_paymentDaoDataSource();
		
		
		registereduser_has_method_payment.setIdRegisteredUser(id);
		registereduser_has_method_payment.setPan(pan);
		registereduser_has_method_payment.setExpirationDate(expirationDate);
		registereduser_has_method_payment.setCvc(cvc);
		
		try {
				if(ds_has_method_payment.existsByUserAndPanAndExpirationDateAndCvc(id, pan, expirationDate, cvc)) {
				
				errors += "Questo metodo di pagamento è già associato al tuo profilo.<br>";
			    request.setAttribute("errors", errors);
			    dispatcherToAdd_payment_methodPage.forward(request, response);
			    return;
			}
			ds_has_method_payment.doSave(registereduser_has_method_payment);
			
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/error/500.html").forward(request, response);
			return;
		}
		
		request.getSession().setAttribute("message", "Metodo di pagamento aggiunto con successo!<br>");
		//dispatcherToAdd_payment_methodPage.forward(request, response);
		if ("/registeredUser/view/payment_page.jsp".equals(redirectAfter)) {
		    response.sendRedirect(request.getContextPath() + "/Payment_page");
		} else if ("/registeredUser/view/user.jsp".equals(redirectAfter)) {
		    response.sendRedirect(request.getContextPath() + "/registeredUserMethods_Payment");
		}
		return;
	}

}
