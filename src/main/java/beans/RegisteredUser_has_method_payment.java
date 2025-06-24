package beans;

public class RegisteredUser_has_method_payment {

	private int idUser;
	private String pan;
	private String expirationDate;
	private String cvc;
	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
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

	public RegisteredUser_has_method_payment() {
		idUser=-1;
		pan="";
		expirationDate="";
		cvc="";
	}

}
