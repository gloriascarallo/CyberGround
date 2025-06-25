package beans;
import java.sql.Date;

public class Product_situatedin_cart {

	private int id_SituatedIn;
	private int idCart;
	private int idProduct;
	private Date dateAdded;
	
	
	public int getId_SituatedIn() {
		return id_SituatedIn;
	}



	public void setId_SituatedIn(int id_SituatedIn) {
		this.id_SituatedIn = id_SituatedIn;
	}
	
	
	public int getIdCart() {
		return idCart;
	}




	public void setIdCart(int idCart) {
		this.idCart = idCart;
	}




	public int getIdProduct() {
		return idProduct;
	}




	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}




	public Date getDateAdded() {
		return dateAdded;
	}




	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}




	public Product_situatedin_cart() {
		idCart=-1;
		idProduct=-1;
		dateAdded=new Date(0);
	}

}
