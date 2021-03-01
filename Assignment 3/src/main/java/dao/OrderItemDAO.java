package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import connection.ConnectionFactory;
import model.Order;
import model.OrderItem;

/**
* OrderItemDAO Class, responsible for the interaction with the database for OrderItem table
*/
public class OrderItemDAO extends AbstractDAO<OrderItem>{
	
	private final static String selectStatementString = "SELECT * FROM orderitem where idorder = ? and idproduct=?";
	private final static String selectByOrderStatementString = "SELECT * FROM orderitem where idorder = ?";
	private final static String selectByProductStatementString = "SELECT * FROM orderitem where idproduct = ?";
	private final static String selectByOrderAndProductStatementString = "SELECT * FROM orderitem where idorder=? and idproduct = ?";
	private final static String insertStatementString = "INSERT INTO orderitem (idclient, total)"+ "VALUES(?,?,?)";
	private final static String insertStatementSt = "INSERT INTO orderitem (idorder,idproduct, quantity)"+ "VALUES(?,?,?)";
	private final static String updateStatementString = "UPDATE INTO orderitem SET quantity=? WHERE idorder=? AND idproduct=?";
	private static final String reportStatementString = "SELECT * FROM orderitem ";
	
	/**
	 * Method findByProduct of type List<OrderItem> helps us to find an OrderItem by a product's id
	 * We need connection to our database
	 * The result of the selectByProductStatementString in the rs of type ResultSet
	 * @param idorder represents the id of the order
	 * @return ArrayList of OrderItems
	 * 
	 */
	public static List<OrderItem> findByProduct(int idorder) {
		Order toReturn = null;
		List<OrderItem> orders = new ArrayList<OrderItem>();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(selectByProductStatementString);
			findStatement.setLong(1, idorder);
			rs = findStatement.executeQuery();
			if(rs.next()) {
				orders.add(new OrderItem(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"OrderDAO:findByOrder " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return orders;
	}

	/**
	 * Method findByOrder of type List<OrderItem> helps us to find an Order by an order's id
	 * We need connection to our database
	 * The result of the selectByOrderStatementString in the rs of type ResultSet
	 * @param idorder represents the id of the order
	 * @return the list of order items found
	 * 
	 */
	public static List<OrderItem> findByOrder(int idorder) {
		Order toReturn = null;
		List<OrderItem> orders = new ArrayList<OrderItem>();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(selectByOrderStatementString);
			findStatement.setLong(1, idorder);
			rs = findStatement.executeQuery();
			if(rs.next()) {
			//toReturn = new Order(rs.getInt(1), rs.getInt(2),rs.getInt(3));
				orders.add(new OrderItem(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"OrderDAO:findByOrder " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return orders;
	}
	
	/**
	 * Method findByOrderAndProduct of type OrderItem helps us to find an OrderItem by an order's id and a product's id
	 * We need connection to our database
	 * The result of the selectByOrderAndProductStatementString in the rs of type ResultSet
	 * @param idorder represents the id of the order
	 * @param idproduct represents the id of the product
	 * @return the list of order items found
	 * 
	 */
	public static OrderItem findByOrderAndProduct(int idorder, int idproduct) {
		OrderItem toReturn = new OrderItem();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(selectByOrderAndProductStatementString);
			findStatement.setLong(1, idorder);
			findStatement.setLong(2, idproduct);
			rs = findStatement.executeQuery();
			if(rs.next()) {
			//toReturn = new Order(rs.getInt(1), rs.getInt(2),rs.getInt(3));
				return (new OrderItem(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"OrderDAO:findByOrder " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return null;
	}
	
	/**
     * Method insert executes the inseration of an order item in database
     * We need connection to our database
     * We need an insertStatement
     * @param ord the order item to be inserted
     * @return ord 
     */
	public OrderItem insert(OrderItem ord) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementSt, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, ord.getIdorder());
			insertStatement.setInt(2, ord.getIdproduct());
			insertStatement.setInt(3, ord.getQuantity());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next() && rs.hashCode()!=0) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderItemDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return ord;
	}
	
	/**
     * Method insert executes the update of an order item in database
     * We need connection to our database
     * We need an updateStatement
     * @param item the order item to be update
     * @return item
     */
	public static OrderItem update(OrderItem item) {		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement =null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(updateStatementString);
			findStatement.setLong(1, item.getQuantity());
			findStatement.setLong(2, item.getIdorder());
			findStatement.setLong(3, item.getIdproduct());
			rs = findStatement.executeQuery();
			/*if(rs.next()) {
			toReturn = new Order(rs.getInt(1), rs.getInt(2),rs.getInt(3));
				return (new OrderItem(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}*/
		} catch (SQLException e) {
			//LOGGER.log(Level.WARNING,"OrderItemDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return item;
	}
	
	/**
     * Method findAllItems finds all order items from database
     * We need a connection to our database
     * We need a reportStatement
     * @param toReturn ArrayList<OrderItem>
     * @return ArrayList<OrderItem>
     */
	public ArrayList<OrderItem> findAllItems() {
		Connection dbConnection = ConnectionFactory.getConnection();
		ArrayList<OrderItem> toReturn=new ArrayList<OrderItem>();
		PreparedStatement reportStatement=null;
		ResultSet rs=null;
		int insertedId = -1;
		try {
			dbConnection = ConnectionFactory.getConnection();
			reportStatement = dbConnection.prepareStatement(reportStatementString);
			rs = reportStatement.executeQuery();
			while(rs.hashCode()!=0 && rs.next()) {
				int idorder=rs.getInt("idorder");
				int idproduct=rs.getInt("idproduct");
				//String nameclient=rs.getString("nameclient");
				int quantity=rs.getInt("quantity");
				toReturn.add(new OrderItem(idorder,idproduct/*,nameclient*/, quantity));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO: findAllOrders " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(reportStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}


}
