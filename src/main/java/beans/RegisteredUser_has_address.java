package beans;

public class RegisteredUser_has_address {
    private int id_has_address;
	private int idUser;
	private String nameAddress;
	
	
	public int getId_has_address() {
		return id_has_address;
	}

	public void setId_has_address(int id_has_address) {
		this.id_has_address = id_has_address;
	}

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
