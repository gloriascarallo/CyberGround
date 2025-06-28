package bean;
import java.sql.Date;

public class OrderBean {



    private int id_Order;
	private int id;
    private Date datePurchase;
    private Date dateDelivery;
    private Date dateShipping;
    private int idCart;
	
	
    public int getId_Order() {
		return id_Order;
	}



	public void setId_Order(int id_Order) {
		this.id_Order = id_Order;
	}
	
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



	public int getIdCart() {
		return idCart;
	}



	public void setIdCart(int idCart) {
		this.idCart = idCart;
	}



	public OrderBean() {
		id=-1;
		datePurchase=new Date(0);
		dateDelivery=new Date(0);
		dateShipping=new Date(0);
		idCart=-1;
	}

}
