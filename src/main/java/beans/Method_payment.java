package beans;

public class Method_payment {

	private String pan;
	private String expirationDate;
	private String cvc;
	
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

	public Method_payment() {
		pan="";
		expirationDate="";
		cvc="";
	}

}
