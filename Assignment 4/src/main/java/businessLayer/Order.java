package businessLayer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Order{
	
	public int idorder;
	private ArrayList<MenuItem> clientOrder;
	private Date date;
	private int table;
	private int newOrder=0;
	public Order(int idorder, ArrayList<MenuItem> clientOrder, Date date, int table) {
		super();
		this.idorder = idorder;
		this.clientOrder = clientOrder;
		this.date = date;
		this.table=table;
		
	}
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public int getNewOrder() {
		return newOrder;
	}
	public void setNewOrder(int newOrder) {
		this.newOrder=newOrder;
	}
	/**
	 * @return the table
	 */
	public int getTable() {
		return table;
	}
	/**
	 * @param table the table to set
	 */
	public void setTable(int table) {
		this.table = table;
	}
	/**
	 * @return the idorder
	 */
	public int getIdorder() {
		return idorder;
	}
	/**
	 * @param idorder the idorder to set
	 */
	public void setIdorder(int idorder) {
		this.idorder = idorder;
	}
	/**
	 * @return the clientOrder
	 */
	public ArrayList<MenuItem> getClientOrder() {
		return clientOrder;
	}
	/**
	 * @param clientOrder the clientOrder to set
	 */
	public void setClientOrder(ArrayList<MenuItem> clientOrder) {
		this.clientOrder = clientOrder;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/*returnez id-ul comenzii*/
	public int hashCode() {
		return idorder;
	}
	
	/*pentru a afla pretul comenzii*/
	public double findOrderPrice() {
		double totalPrice=0;
		for(MenuItem m: clientOrder) {
			if(m instanceof CompositeProduct)
				totalPrice=totalPrice+((CompositeProduct)m).computePrice();
			if(m instanceof BaseProduct)
				totalPrice=totalPrice+((BaseProduct)m).getPrice();	
		}
		if(totalPrice>0) {
			return totalPrice;
		}
		else
			return 0;
	}//String.valueOf(totalPrice)
	
	@Override
	public String toString() {
		SimpleDateFormat format=new SimpleDateFormat("dd.MM.yyyy ',' hh:mm");
		return "Order [idorder=" + idorder + ", clientOrder=" + clientOrder + ", date=" + format.format(date)+", table=" + table + "]";
	}

}
