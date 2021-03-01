package model;

/**
 * OrderItem Class provides specific information for an order item: 
 * id order, id product, quantity
 */
public class OrderItem {
	
	private int idorder;
	private int idproduct;
	private int quantity;
	
	/**
     * Constructor of OrderItem that initializes an OrderItem object
     * @param idorder order id
     * @param idproduct product id
     * @param quantity quantity
     */
	public OrderItem(int idorder, int idproduct, int quantity) {
		super();
		this.idorder = idorder;
		this.idproduct = idproduct;
		this.quantity = quantity;
	}
	
	public OrderItem() {
		
	}

	public int getIdorder() {
		return idorder;
	}

	
	public void setIdorder(int idorder) {
		this.idorder = idorder;
	}

	public int getIdproduct() {
		return idproduct;
	}

	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderItem [idorder=" + idorder + ", idproduct=" + idproduct + ", quantity=" + quantity + "]";
	}
	
}
