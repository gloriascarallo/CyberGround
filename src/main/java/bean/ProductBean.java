package bean;
import java.sql.Date;

public class ProductBean {

	private int id;
	private String name;
	private double price;
	private String description;
	private Date dateUpload;
	private String supplier;
	private String categoryName;
	private String imagePath;
	private int quantityAvailable;



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

	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public int getQuantityAvailable() {
		return quantityAvailable;
	}


	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
	

	public ProductBean() {
		id=-1;
		name="";
		price=0;
		description="";
		dateUpload=new Date(0);
		supplier="";
		categoryName="";
		imagePath="";
		quantityAvailable=1;
	}

}
