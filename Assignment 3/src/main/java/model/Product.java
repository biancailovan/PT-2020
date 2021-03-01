package model;

/**
 * Product Class provides specific information for a product: 
 * id of the product, name of the product, price of the product and quantity
 */
public class Product {
	
	private int idproduct;
	private String nameP;
	private double price;
	private int quantity;
	
	/**
     * Constructor of Product that initializes a product
     * @param idproduct product id
     * @param nameP product name
     * @param price price of the product
     * @param quantity wanted quantity
     */
	public Product(int idproduct, String nameP, double price, int quantity) {
		super();
		this.idproduct = idproduct;
		this.nameP = nameP;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Product(String nameP, double price, int quantity) {
		super();
		this.nameP = nameP;
		this.price = price;
		this.quantity = quantity;
	}
	
	public Product() {
		
	}

	/**
	 * @return the idproduct
	 */
	public int getIdproduct() {
		return idproduct;
	}

	/**
	 * @param idproduct the idproduct to set
	 */
	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}

	/**
	 * @return the nameP
	 */
	public String getNameP() {
		return nameP;
	}

	/**
	 * @param nameP the nameP to set
	 */
	public void setNameP(String nameP) {
		this.nameP = nameP;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [idproduct=" + idproduct + ", nameP=" + nameP + ", price=" + price + ", quantity=" + quantity
				+ "]";
	}	

}
