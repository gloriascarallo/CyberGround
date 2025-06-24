package beans;

public class RegisteredUser_has_address {

	private int idUser;
	private String nameAddress;
	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getNameAddress() {
		return nameAddress;
	}

	public void setNameAddress(String nameAddress) {
		this.nameAddress = nameAddress;
	}

	public RegisteredUser_has_address() {
		idUser=-1;
		nameAddress="";
	}

}
