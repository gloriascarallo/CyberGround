package bean;
import java.sql.Date;
import java.time.LocalDate;

public class Product_situatedin_cartBean {

	private int id_SituatedIn;
	private int idCart;
	private ProductBean product;
	private Date dateAdded;
	private int quantity;
	
	
	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void increaseQuantity() {
		
		quantity++;
		
	}
	
	public void decreaseQuantity() {
		
		quantity--;
	}


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




	public ProductBean getProduct() {
		return product;
	}
	
	public int getIdProduct() {
		
		return product.getIdProduct();
		
	}




	public void setProduct(ProductBean product) {
		this.product=product;
	}
	




	public Date getDateAdded() {
		return dateAdded;
	}




	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}


	public double getTotalPrice() {
		
		return product.getPrice()*quantity;
		
	}


	public Product_situatedin_cartBean() {
		id_SituatedIn=-1;
		idCart=-1;
		product=new ProductBean();
		dateAdded=Date.valueOf(LocalDate.now());
		quantity=1;
	}

}
