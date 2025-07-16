package bean;
import java.sql.Date;
import java.time.LocalDate;

public class ProductBean {

	private int idProduct;
	private String name;
	private double price;
	private Double discountPercentage;
	private Date dateExpirationDiscount;
	private String description;
	private Date dateUpload;
	private String supplier;
	private String categoryName;
	private String imagePath;
	private int quantityAvailable;



	public int getIdProduct() {
		return idProduct;
	}


	public void setIdProduct(int id) {
		this.idProduct = id;
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
	
	public double getDiscountedPrice() {
		
		double prezzo = this.price;

	    if (this.discountPercentage != null && this.dateExpirationDiscount != null) {
	        LocalDate today = LocalDate.now();
	        if (!this.dateExpirationDiscount.toLocalDate().isBefore(today)) {
	            double sconto = this.discountPercentage.doubleValue();
	            prezzo = prezzo - (prezzo * sconto / 100.0);
	        }
	    }

	    return prezzo;
		
	}

	public Double getDiscountPercentage() {
		return discountPercentage;
	}


	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}


	public Date getDateExpirationDiscount() {
		return dateExpirationDiscount;
	}


	public void setDateExpirationDiscount(Date dateExpirationDiscount) {
		this.dateExpirationDiscount = dateExpirationDiscount;
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
	
	public void decreaseQuantityAvailable(int quantity) {
		
		quantityAvailable=quantityAvailable-quantity;
	}
	

	public ProductBean() {
		idProduct=-1;
		name="";
		price=0;
		discountPercentage=null;
		dateExpirationDiscount=null;
		description="";
		dateUpload=Date.valueOf(LocalDate.now());
		supplier="";
		categoryName="";
		imagePath="";
		quantityAvailable=1;
	}

}
