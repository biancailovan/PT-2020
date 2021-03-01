package model;

/**
* Order Class provides specific information for an order: order's id, 
* client's id(the one who made the order), and total price(of order)
*/
public class Order  {
	
	private int idorder;
	private int idclient;
	private String nameClient;
	private int total;
	
	/**
     * Constructor of Order Class that initializes an Order object
     * @param idorder Order id
     * @param idclient the id of the client that makes the order
     * @param nameClient the name of the client that makes the order
     * @param total Total Price of an Order
     */
	public Order(int idorder, int idclient,String nameClient, int total) {
		super();
		this.idorder = idorder;
		this.idclient = idclient;
		this.nameClient=nameClient;
		this.total = total;
	}
	
	public Order(int idclient,String nameClient, int total) {
		super();
		this.idclient = idclient;
		this.nameClient=nameClient;
		this.total = total;
	}
	
	public Order() {
		
	}

	public Order(int idorder, int idclient, int total) {
		// TODO Auto-generated constructor stub
		this.idclient=idclient;
		this.idorder=idorder;
		this.total=total;
	}

	/**
     * gets the order id
     * @return idorder
     */
	public int getIdorder() {
		return idorder;
	}

	/**
     * sets the order id
     */
	public void setIdorder(int idorder) {
		this.idorder = idorder;
	}
	
	/**
     * gets the name of the client
     * @return nameClient
     */
	public String getNameClient() {
		return nameClient;
	}

	/**
     * sets the name of the client
     */
	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	/**
     * gets the id of the client that makes an order
     * @return idclient
     */
	public int getIdclient() {
		return idclient;
	}

	/**
     * sets the id of the client
     */
	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	/**
     * gets the total price of an order
     * @return total
     */
	public int getTotal() {
		return total;
	}

	/**
     * sets the total price for an order
     */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
     * toString method
     * @return all the necessary data to show for an order
     */
	@Override
	public String toString() {
		return "Order [idorder=" + idorder + ", idclient=" + idclient + ", total=" + total + "]";
	}	

}
