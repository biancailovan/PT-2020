package model;

/**
* Client Class provides specific information for a client: hid id, his name, his address
*/

public class Client {
	
	private int idclient;
	private String name;
	private String address;
	
	 /**
     * This is the constructor that initializes Client Object
     * @param clientid initial client's id
     * @param name initial name
     * @param address initial address
     */
	public Client(int idclient, String name, String address) {
		super();
		this.idclient = idclient;
		this.name = name;
		this.address = address;
	}
	
	public Client(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public Client() {
		// TODO Auto-generated constructor stub
	}
	
	/**
     * get id of the client
     * @return idclient
     */
	public int getIdclient() {
		return idclient;
	}

	/**
     * set id of the client
     */
	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	/**
     * get name of the client
     * @return name
     */
	public String getName() {
		return name;
	}

	/**
     * set name of the client
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * get address of the client
     * @return address
     */
	public String getAddress() {
		return address;
	}

	/**
     * set address of the client
     */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
     * toString method
     * @return necessary data for a client
     */
	@Override
	public String toString() {
		return "Client [idclient=" + idclient + ", name=" + name + ", address=" + address + "]";
	}	

}
