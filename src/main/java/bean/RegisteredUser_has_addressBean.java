package bean;

public class RegisteredUser_has_addressBean {
    private int id_has_address;
	private int idRegisteredUser;
	private String nameAddress;
	
	
	public int getId_has_address() {
		return id_has_address;
	}

	public void setId_has_address(int id_has_address) {
		this.id_has_address = id_has_address;
	}

	public int getIdRegisteredUser() {
		return idRegisteredUser;
	}

	public void setIdRegisteredUser(int idRegisteredUser) {
		this.idRegisteredUser = idRegisteredUser;
	}

	public String getNameAddress() {
		return nameAddress;
	}

	public void setNameAddress(String nameAddress) {
		this.nameAddress = nameAddress;
	}

	public RegisteredUser_has_addressBean() {
		id_has_address=-1;
		idRegisteredUser=1;
		nameAddress="";
	}

}
