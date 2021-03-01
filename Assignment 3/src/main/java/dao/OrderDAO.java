package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

import connection.ConnectionFactory;
import model.Client;
import model.Order;
import model.Product;

/**
* OrderDAO Class, responsible for the interaction with the database for Order's table
*/
public class OrderDAO extends AbstractDAO<Order>{
	
	private final static String selectStatementString = "SELECT * FROM order WHERE idclient=?";
	private final static String select2StatementString = "SELECT * FROM order WHERE nameclient=?";
	private final static String insertStatementString = "INSERT INTO order (idclient)" + "VALUES(?)";
	private static final String reportStatementString = "SELECT * FROM ordermanagement.order ";
	private static final String insertStatementSt = "INSERT INTO order (idclient,nameclient,total)"
			+ " VALUES (?,?,?)";
	
	/**
	 * Method findByIdClient of type Order helps us to find an Order by a client's id
	 * We need connection to our database
	 * The result of the selectStatementString in the rs of type ResultSet
	 * We need to create a new object Client
	 * @param idclient represents the id of the client
	 * @return the order found else null
	 * 
	 */
	public static Order findByIdClient(int idclient) {
		Order toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(selectStatementString);
			findStatement.setLong(1, idclient);
			rs = findStatement.executeQuery();
			if(rs.next()) {
			toReturn = new Order(rs.getInt(1), rs.getInt(2),rs.getString(3),rs.getInt(4));
			return toReturn;
			}
		} catch (SQLException e) {
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		//return toReturn;
		return null;
	}
	
	/**
	 * Method findByNameClient of type Order helps us to find an Order by a client's name
	 * We need connection to our database
	 * The result of the selectStatementString in the rs of type ResultSet
	 * We need to create a new object Client
	 * @param nameclient represents the name of the client
	 * @return the order found else null
	 * 
	 */
	public static Order findByNameClient(String nameclient) {
		Order toReturn = null;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(select2StatementString);
			findStatement.setString(1, nameclient);
			rs = findStatement.executeQuery();
			rs.next();

			int idorder = rs.getInt("idorder");
			int total = rs.getInt("total");
			toReturn = new Order(idorder, nameclient, total);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO:findByClientName " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	/**
     * Method insert executes the inseration of an order in database
     * We need connection to our database
     * We need an insertStatement
     * @param order the order to be inserted
     * @return order 
     */
	public static int insert(Order order) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementSt, Statement.RETURN_GENERATED_KEYS);
			//insertStatement.setInt(1, order.getIdorder());
			insertStatement.setInt(1, order.getIdclient());
			insertStatement.setString(2, order.getNameClient());
			insertStatement.setInt(3, order.getTotal());
			insertStatement.executeUpdate();
			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next() && rs.hashCode()!=0) {
				//insertedId = rs.getInt(1);
				//return new Order(rs.getInt(1), order.getIdclient(), order.getNameClient(),order.getTotal());
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			//LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		//return order;
		return insertedId;
	}
	
	/**
     * Method findAllOrders finds all orders from database
     * We need a connection to our database
     * We need a reportStatement
     * @param toReturn ArrayList<Order>
     * @return ArrayList<Order>
     */
	public ArrayList<Order> findAllOrders() {
		Connection dbConnection = ConnectionFactory.getConnection();
		ArrayList<Order> toReturn=new ArrayList<Order>();
		PreparedStatement reportStatement=null;
		ResultSet rs=null;
		int insertedId = -1;
		try {
			dbConnection = ConnectionFactory.getConnection();
			reportStatement = dbConnection.prepareStatement(reportStatementString);
			rs = reportStatement.executeQuery();
			while(rs.hashCode()!=0 && rs.next()) {
				int idorder=rs.getInt("idorder");
				int idclient=rs.getInt("idclient");
				String nameclient=rs.getString("nameclient");
				int total=rs.getInt("total");
				toReturn.add(new Order(idorder,idclient,nameclient, total));
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
