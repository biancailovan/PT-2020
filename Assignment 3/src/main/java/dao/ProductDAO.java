package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Client;
import model.Product;

/**
* ProductDAO Class responsible for the interaction with the database for Product table
*/
public class ProductDAO extends AbstractDAO<Product> {
	
	protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO product (nameP,price, quantity)"
			+ " VALUES (?,?,?)";
	private static final String deleteStatementString = "DELETE FROM product where nameP = ? ";
	private static final String deleteByIdStatementString = "DELETE FROM product where idproduct = ? ";
	private final static String findStatementString = "SELECT * FROM product where idproduct = ?";
	private final static String findProdNameStatementString = "SELECT * FROM product where nameP = ?";
	private static final String reportStatementString = "SELECT * FROM product ";
	private final static String updateStatementString = "UPDATE Product SET quantity=quantity-? where nameP=?";
	private final static String updatePStatementString = "UPDATE Product SET quantity=quantity+? where nameP=?";

	/**
	 * Method findById helps us to find a product by id
	 * We need connection to our database
	 * The result of the findStatementString in the rs of type ResultSet
	 * We need to create a new object Product
	 * @param idproduct represents the id of the product
	 * @return the product found in the database
	 * 
	 */
	public static Product findById(int idproduct) {
		Product toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, idproduct);
			rs = findStatement.executeQuery();
			rs.next();
			String nameP = rs.getString("nameP");
			double price = rs.getDouble(2);
			int quantity = rs.getInt(3);	
			toReturn = new Product(idproduct, nameP, price, quantity);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ProductDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	/**
     * Method insert executes the inseration of a product in database
     * We need connection to our database
     * We need an insertStatement
     * @param product the product to be inserted
     * @return insertedId
     */
	public static int insert(Product product) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, product.getNameP());
			insertStatement.setDouble(2, product.getPrice());
			insertStatement.setInt(3, product.getQuantity());
			insertStatement.executeUpdate();
			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.hashCode()!=0 && rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	/**
     * Method delete deletes a client from database with a specific name
     * We need a connection to our database
     * We need a deleteStatement
     * @param nameP name of product that will be deleted
     * @return deletedRows number of deleted rows
     */
	public static int delete(String nameP) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;
		int deletedRows=0;
		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
			deleteStatement.setString(1, nameP);
			deletedRows=deleteStatement.executeUpdate();		
			//ResultSet rs = deleteStatement.getGeneratedKeys();
			/*if (rs.next()) {
				deletedId = rs.getInt(1);
			}*/
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		return deletedRows;
	}
	
	/**
     * Method delete deletes a product from database with a specific id
     * We need a connection to our database
     * We need a deleteByIdStatement
     * @param idproduct id of product that will be deleted
     * @return deletedRows number of deleted rows
     */
	public static int deleteProductById(int idproduct) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;
		int deletedRows=0;
		int deletedId=-1;
		try {
			deleteStatement = dbConnection.prepareStatement(deleteByIdStatementString, Statement.RETURN_GENERATED_KEYS);
			deleteStatement.setInt(1, idproduct);
			deletedRows=deleteStatement.executeUpdate();	
			ResultSet rs = deleteStatement.getGeneratedKeys();
			if (rs.next()) {
				deletedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		return deletedRows;
	}
	
	/**
     * Method updates a product from database 
     * We need a connection to our database
     * We need an update statement
     * @param nameP name of the product to be modified
     * @param quantity modif quantity (+)
     * @return 1 if succeed
     */
	/*De fiecare data cand in fisier apare comanda de inserare a unui produs cu o anumita cantitate,
	 * se va adauga la cantitatea deja existenta cantitatea adaugata*/
	public static int update1(int quantity, String nameP) {/*plus*/
		int ok = 0;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		try {
			findStatement = dbConnection.prepareStatement(updatePStatementString);
			findStatement.setInt(1, quantity);
			findStatement.setString(2, nameP);
			findStatement.executeUpdate();
			ok = 1;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:updateMinus " + e.getMessage());
		} finally {
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return ok;
	}
	
	/**
     * Method updates a product from database 
     * We need a connection to our database
     * We need an update statement
     * @param nameP name of the product to be modified
     * @param quantity modif quantity (-)
     * @return 1 if succeed
     */
	/*De fiecare data cand apare o comanda, trebuie sa scadem din cantitatea existenta in baza de date
	 * cantitatea ceruta de client*/
	public static int update2(int quantity, String name) {/*minus*/
		int i = 0;
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		try {
			findStatement = dbConnection.prepareStatement(updateStatementString);
			findStatement.setInt(1, quantity);
			findStatement.setString(2, name);
			findStatement.executeUpdate();
			i = 1;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:updateMinus " + e.getMessage());
		} finally {
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return i;
	}
	
	/**
	 * Method findProductByName helps us to find a product by name
	 * We need connection to our database
	 * The result of the findProdByNameStatementString in the rs of type ResultSet
	 * @param name represents the name of the product
	 * @return the product found in the database
	 * 
	 */
	public static Product findProductByName(String name) {
		Product toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findProdNameStatementString);
			findStatement.setString(1, name);
			rs = findStatement.executeQuery();
			rs.next();	
			int idproduct = rs.getInt("idproduct");
			double price = rs.getDouble("price");
			int quantity = rs.getInt("quantity");
			toReturn=new Product(idproduct, name, price, quantity);
		} catch (SQLException e) {
			//LOGGER.log(Level.WARNING,"ProductDAO:findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	
	
	/**
     * Method findAllProducts finds all products from database
     * We need a connection to our database
     * We need a reportStatement
     * @param toReturn ArrayList<Product>
     * @return ArrayList<Product>
     */
	public static ArrayList<Product> findAllProducts() {
		Connection dbConnection = ConnectionFactory.getConnection();
		ArrayList<Product> toReturn=new ArrayList<Product>();
		PreparedStatement reportStatement=null;
		ResultSet rs=null;
		//int insertedId = -1;
		try {
			dbConnection = ConnectionFactory.getConnection();
			reportStatement = dbConnection.prepareStatement(reportStatementString);
			rs = reportStatement.executeQuery();
			while(rs.hashCode()!=0 && rs.next()) {
				int idproduct=rs.getInt("idproduct");
				String nameP=rs.getString("nameP");
				double price=rs.getDouble("price");
				int quantity=rs.getInt("quantity");
				toReturn.add(new Product(idproduct, nameP,price, quantity));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO: findAllProducts " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(reportStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
}
