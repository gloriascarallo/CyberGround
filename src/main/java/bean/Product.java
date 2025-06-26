package bean;
import java.sql.Date;

public class Product {

	private int id;
	private String name;
	private double price;
	private String description;
	private Date dateUpload;
	private String supplier;
	private String categoryName;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
		
		
	}
	
	public Date getDateUpload() {
		return dateUpload;
	}


	public void setDateUpload(Date dateUpload) {
		this.dateUpload = dateUpload;
	}


	public String getSupplier() {
		return supplier;
	}


	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public Product() {
		id=-1;
		name="";
		price=-1;
		description="";
		dateUpload=new Date(0);
		supplier="";
		categoryName="";
	}

}
