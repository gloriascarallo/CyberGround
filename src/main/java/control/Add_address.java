  package control;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import dao.AddressDaoDataSource;
import bean.AddressBean;
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
		
		request.getRequestDispatcher("/registeredUser/view/add_address.jsp").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errors="";
		RequestDispatcher dispatcherToAdd_address=request.getRequestDispatcher("/registeredUser/view/add_address.jsp");
		Object idObj=request.getSession().getAttribute("id");
		if (idObj == null) {
			errors = "Sessione scaduta o ID utente mancante. Ricarica la pagina e riprova.";
	        request.setAttribute("errors", errors);
	        request.getRequestDispatcher("/expiredSession.html").forward(request, response);
	        return;
	    }
	    int id = (Integer) idObj;
		String nameAddress=request.getParameter("address");
		
		if(nameAddress==null || nameAddress.trim().equals("")) {
			errors+="Inserire indirizzo.<br>";
			request.setAttribute("errors", errors);
			dispatcherToAdd_address.forward(request, response);
			return;
			
			
		}
		nameAddress=nameAddress.trim();
		AddressDaoDataSource ds_address=new AddressDaoDataSource();
		
		try {
		    AddressBean address = ds_address.doRetrieveByKey(nameAddress);
		    
		    
		    if (address == null) {
		        AddressBean newAddress = new AddressBean();
		        newAddress.setName(nameAddress);
		        ds_address.doSave(newAddress);
		    }
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/500.html").forward(request, response);
			return;
		}
		
		RegisteredUser_has_addressBean registereduser_has_address=new RegisteredUser_has_addressBean();
		RegisteredUser_has_addressDaoDataSource ds_has_address=new RegisteredUser_has_addressDaoDataSource();
		
		registereduser_has_address.setNameAddress(nameAddress);
		registereduser_has_address.setIdRegisteredUser(id);
		
		try {
			if(ds_has_address.existsByUserAndAddress(id, nameAddress)) {
				
				errors += "Questo indirizzo è già associato al tuo profilo.<br>";
			    request.setAttribute("errors", errors);
			    dispatcherToAdd_address.forward(request, response);
			    return;
			}
			ds_has_address.doSave(registereduser_has_address);
			
		}
		catch(SQLException e) {
			
			e.printStackTrace();
			request.getRequestDispatcher("/500.html").forward(request, response);
			return;
		}
		
		request.setAttribute("message", "Indirizzo aggiunto con successo!<br>");
		dispatcherToAdd_address.forward(request, response);
		return;
	}

}
