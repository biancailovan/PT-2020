package bll;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.itextpdf.text.DocumentException;

import dao.OrderItemDAO;
import dao.ProductDAO;
import model.Client;
import model.OrderItem;
import model.Product;

/**
* OrderItemBLL Class implements the logic of operations from OrderItem's table 
* 
*/
public class OrderItemBLL {
	private OrderItemDAO orderitemDAO;
	
	/**
	 * This is the constructor that initializes ClientBLL Object
	 */
	public OrderItemBLL() {
		orderitemDAO=new OrderItemDAO();
	}

	/**
	 * Method insert helps us to insert an order in the database
	 * @param newQuantity new Quantity(old quantity + the quantity of the order item)
	 * @param ordItem, the order that we want to be inserted
	 * @return the orderitem inserted in the database or the new object updated
	 * 
	 */
	public OrderItem insert(OrderItem ordItem) {
		// TODO Auto-generated method stub
		if (orderitemDAO.findByOrderAndProduct(ordItem.getIdorder(), ordItem.getIdproduct()) != null) {
			OrderItem ordered = orderitemDAO.findByOrderAndProduct(ordItem.getIdorder(), ordItem.getIdproduct());
			int newQuantity = ordered.getQuantity() + ordItem.getQuantity();
			ordered.setQuantity(newQuantity);
			return orderitemDAO.update(ordered);
		} 
		else
			return orderitemDAO.insert(ordItem);
	}

	/**
	 * Method findByOrder helps us to find all order items with the same id
	 * @param ordItem, our order item
	 * @return an array list with the objects founded in the database
	 * 
	 */
	public ArrayList<OrderItem> findByOrder(OrderItem ordItem) {
		// TODO Auto-generated method stub
		return (ArrayList<OrderItem>) orderitemDAO.findByOrder(ordItem.getIdorder());
		//return null;
	}
	
	
}
