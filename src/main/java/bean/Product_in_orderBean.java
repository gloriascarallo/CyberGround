package bean;

public class Product_in_orderBean {

	private int id_product_in_order;
	private int idOrder;
	private ProductBean product;
	private int quantity;
	private double price;
	
	public Product_in_orderBean() {
		idOrder=-1;
		product=new ProductBean();
		quantity=0;
		price=0;
	}

	public int getId_product_in_order() {
		return id_product_in_order;
	}

	public void setId_product_in_order(int id_product_in_order) {
		this.id_product_in_order = id_product_in_order;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public ProductBean getProduct() {
		return product;
	}

	public void setProduct(ProductBean product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getSubtotal() {
		
		return price*quantity;
	}

}
