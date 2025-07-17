package model;

import java.util.ArrayList;
public class CartBean {

	private int idCart;
	private ArrayList<Product_situatedin_cartBean> products;
	
	public CartBean() {
		idCart=-1;
		products=new ArrayList<Product_situatedin_cartBean>();
	}

	public int getIdCart() {
		return idCart;
	}

	public void setIdCart(int idCart) {
		this.idCart = idCart;
	}

	public ArrayList<Product_situatedin_cartBean> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product_situatedin_cartBean> products) {
		this.products = products;
	}
	
	public Product_situatedin_cartBean getProduct(int id) {
		
		for(Product_situatedin_cartBean product: products) {
			
			if(product.getIdProduct()==id)
			return product;
		}
		return null;
	}
	
	public Product_situatedin_cartBean getProductBySituatedInId(int idSituatedIn) {
	    for (Product_situatedin_cartBean product : products) {
	        if (product.getIdSituatedIn() == idSituatedIn) {
	            return product;
	        }
	    }
	    return null;
	}
	
	public void addProduct(Product_situatedin_cartBean product) {
		
		for(Product_situatedin_cartBean product_incart: products) {
			
			if(product_incart.getIdProduct()==product.getIdProduct()) {
				
				product_incart.setQuantity(product.getQuantity()+product_incart.getQuantity());
				return;
				
			}
		}
		
		products.add(product);
	}
	
	public void removeProduct(Product_situatedin_cartBean product) {
		
		products.remove(product);
	}
	
	public double getTotal() {
		
		double total=0;
		for(Product_situatedin_cartBean product_incart: products) {
			total+=product_incart.getTotalPrice();
			
		}
		return total;
		}
	}
	


