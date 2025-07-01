package bean;
import java.sql.Date;
import java.util.ArrayList;

public class OrderBean {



    private int idOrder;
    private Date datePurchase;
    private Date dateDelivery;
    private Date dateShipping;
    private int idCart;
    private ArrayList<Product_in_orderBean> products_in_order;
	
	
    public int getIdOrder() {
		return idOrder;
	}



	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}



	public ArrayList<Product_in_orderBean> getProducts_in_order() {
		return products_in_order;
	}



	public void setProducts_in_order(ArrayList<Product_in_orderBean> products_in_order) {
		this.products_in_order = products_in_order;
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


public double getTotalOrder() {
	
	double total=0;
	for(Product_in_orderBean product_in_order: products_in_order) {
		total+=product_in_order.getSubtotal();
		
	}
	return total;
}

	public OrderBean() {
		
		datePurchase=new Date(0);
		dateDelivery=new Date(0);
		dateShipping=new Date(0);
		idCart=-1;
		products_in_order=new ArrayList<Product_in_orderBean>();
	}

}
