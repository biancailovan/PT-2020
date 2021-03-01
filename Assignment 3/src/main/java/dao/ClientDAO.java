package dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.DocumentException;

import bll.ClientBLL;
import connection.ConnectionFactory;
import model.Client;
import presentation.PDFClient;

/**
* ClientDAO Class, responsible for the interaction with the database for Client's table
*/
public class ClientDAO extends AbstractDAO<Client> {
	
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO client (name,address)"
			+ " VALUES (?,?)";
	private final static String findStatementString = "SELECT * FROM client where idclient = ?";
	private static final String deleteStatementString = "DELETE FROM client where name = ?";
			/*+ " VALUES (?)";*/
	private static final String reportStatementString = "SELECT * FROM client ";
	private static final String findByNameStatementString= "SELECT * FROM client where name = ?";
	
	/**
	 * Method findById helps us to find a client by id
	 * We need connection to our database
	 * The result of the findStatementString in the rs of type ResultSet
	 * We need to create a new object Client
	 * @param idclient represents the id of the client
	 * @return the client found in the database
	 * 
	 */
	public static Client findById(int idclient) {
		Client toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, idclient);
			rs = findStatement.executeQuery();
			rs.next();
			String name = rs.getString("name");
			String address = rs.getString("address");	
			toReturn = new Client(idclient, name, address);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	/**
	 * Method findByName helps us to find a client by its name
	 * We need connection to our database
	 * The result of the findByNameStatementString in the rs of type ResultSet
	 * We need to create a new object Client
	 * @param name represents the name of the client
	 * @return the client found in the database
	 * 
	 */
	public static Client findByName(String name) {
		Client toReturn = null;
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findByNameStatementString);
			findStatement.setString(1, name);
			rs = findStatement.executeQuery();
			rs.next();
			//String nameClient = rs.getString("name");
			//String address = rs.getString("address");		
			toReturn = new Client(rs.getInt(1), rs.getString(2), rs.getString(3));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"ClientDAO:findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	/**
     * Method insert executes the inseration of a client in database
     * We need connection to our database
     * We need an insertStatement
     * @param client the client to be inserted
     * @return insertedId
     */
	public static int insert(Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, client.getName());
			insertStatement.setString(2, client.getAddress());
			insertStatement.executeUpdate();
			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next() && rs.hashCode()!=0) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	/**
     * Method deleteClient deletes a client from database with a specific name
     * We need a connection to our database
     * We need a deleteStatement
     * @param nameClient name of client that will be deleted
     * @return deletedRows number of deleted rows
     */
	public static int deleteClient(String nameClient) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;
		int deletedRows=0;
		//nt deletedId=-1;
		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString/*, Statement.RETURN_GENERATED_KEYS*/);
			deleteStatement.setString(1, nameClient);
			//deletedRows=deleteStatement.executeUpdate();	
			//ResultSet rs = deleteStatement.getGeneratedKeys();
			deletedRows=deleteStatement.executeUpdate();
			//if (rs.next()) {
				//deletedId = rs.getInt(1);
			//}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		//return deletedId;
		return deletedRows;
	}
	static PDFClient pdfClient;
	ClientBLL clientBLL;
	
	/**
     * Method findAllClients finds all clients from database
     * We need a connection to our database
     * We need a reportStatement
     * @param toReturn ArrayList<Client>
     * @return ArrayList<Client>
     */
	public ArrayList<Client> findAllClients() {
		Connection dbConnection = ConnectionFactory.getConnection();
		ArrayList<Client> toReturn=new ArrayList<Client>();
		PreparedStatement reportStatement=null;
		ResultSet rs=null;
		int insertedId = -1;
		try {
			dbConnection = ConnectionFactory.getConnection();
			reportStatement = dbConnection.prepareStatement(reportStatementString);
			rs = reportStatement.executeQuery();
			while(rs.hashCode()!=0 && rs.next()) {
				int idclient=rs.getInt("idclient");
				String name=rs.getString("name");
				String address=rs.getString("address");
				toReturn.add(new Client(idclient, name, address));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "AbstractDAO: findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(reportStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
}


