package bll;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.itextpdf.text.DocumentException;

import bll.validators.OrderTotalValidator;
import bll.validators.Validator;
import dao.OrderDAO;
import dao.OrderItemDAO;
import model.Client;
import model.Order;
import presentation.PDFOrder;

/**
* OrderBLL Class implements the logic of operations from Order's table 
* 
*/
public class OrderBLL {
	
private static List<Validator<Order>> validators;
OrderDAO orderDAO=new OrderDAO();
	
	/**
	 * This is the constructor that initializes ClientBLL Object
	 */
	public OrderBLL() {
		validators = new ArrayList<Validator<Order>>();
		//validators.add((Validator<Order>) new OrderTotalValidator());
	}
	
	/**
	 * Method insert helps us to find an order by a client's name
	 */
	public Order findOrderByName(String name) {
		Order ok = OrderDAO.findByNameClient(name);
		if (ok == null) {
			throw new NoSuchElementException("The Order with name " + name + " was not found!");
		}
		return ok;
	}
	
	/**
	 * Method insert helps us to insert an order in the database
	 * We want to be sure that our order is a valid one
	 * @param order, the order that we want to be inserted
	 * @return the order inserted in the database
	 * 
	 */
	public static int insertOrder(Order order) {
		for (Validator<Order> v : validators) {
			v.validate(order);
		}
		return OrderDAO.insert(order);
	}
	
	/**
	 * Method insert helps us to insert an order in the database
	 * We want to be sure that our order is a valid one
	 * @param order, the order that we want to be inserted
	 * @return the order inserted in the database
	 * 
	 */
	public Order insert(Order order) {
		for (Validator<Order> v : validators) {
			v.validate(order);
		}
		if(OrderDAO.findByIdClient(order.getIdclient())!=null) {
			order=OrderDAO.findByIdClient(order.getIdclient());
			return order;
		}
		else {
			//order=OrderDAO.insert(order);
			return order;
		}	
	}
	
	
	/**
	 * Method update helps us to insert an order in the database
	 * We want to be sure that the order is a valid one
	 * @param ord, the ord that we want to be updated
	 * @return the order updated in the database
	 * 
	 */
	public Order update(Order ord) throws IllegalArgumentException, IllegalAccessException {
		for (Validator<Order> v : validators) {
			v.validate(ord);
		}
		if(ord!=null) {
		return orderDAO.updateObj(ord);
		}
		else
			return null;
	}

}
