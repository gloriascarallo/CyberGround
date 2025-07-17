package model;

public class RegisteredUser_has_method_paymentBean {

	private int id_has_method_payment;
	private int idRegisteredUser;
	private String pan;
	private String expirationDate;
	private String cvc;
	
	
	public int getId_has_method_payment() {
		return id_has_method_payment;
	}

	public void setId_has_method_payment(int id_has_method_payment) {
		this.id_has_method_payment = id_has_method_payment;
	}

	public int getIdRegisteredUser() {
		return idRegisteredUser;
	}

	public void setIdRegisteredUser(int idRegisteredUser) {
		this.idRegisteredUser = idRegisteredUser;
	}
	
	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public RegisteredUser_has_method_paymentBean() {
		id_has_method_payment=-1;
		idRegisteredUser=-1;
		pan="";
		expirationDate="";
		cvc="";
	}

}
