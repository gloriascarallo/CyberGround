package beans;
import java.util.Date;

public class Order {

	private int id;
    private Date datePurchase;
    private Date dateDelivery;
    private Date dateShipping;
    private int idCart;
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Date getDatePurchase() {
		return datePurchase;
	}



	public void setDatePurchase(Date datePurchase) {
		this.datePurchase = datePurchase;
	}



	public Date getDateDelivery() {
		return dateDelivery;
	}



	public void setDateDelivery(Date dateDelivery) {
		this.dateDelivery = dateDelivery;
	}



	public Date getDateShipping() {
		return dateShipping;
	}



	public void setDateShipping(Date dateShipping) {
		this.dateShipping = dateShipping;
	}



	public int getidCart() {
		return idCart;
	}



	public void setidCart(int idCart) {
		this.idCart = idCart;
	}



	public Order() {
		id=-1;
		datePurchase=new Date();
		dateDelivery=new Date();
		dateShipping=new Date();
		idCart=-1;
	}

}
