package start;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import bll.ClientBLL;
import bll.ProductBLL;
import model.Client;
import model.Product;

public class Start {
	
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException {

		Client client = new Client("Ion Popescu", "Bucuresti");

		ClientBLL clientBll = new ClientBLL();
		int id = clientBll.insertClient(client);
		if (id > 0) {
			clientBll.findClientById(id);
		}
			
		// Generate error
		try {
			clientBll.findClientById(1);
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}
		
		Product product = new Product("apple",1.5,20);

		ProductBLL productBll = new ProductBLL();
		int idP = productBll.insertProduct(product);
		if (idP > 0) {
			productBll.findProductById(idP);
		}
			
		// Generate error
		try {
			productBll.findProductById(1);
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}
		
			
		//clientBll.findClientById(1);
		//clientBll.deleteClient(client);
		/*if (id > 0) {
			clientBll2.findClientById(id);
		}
		
		// Generate error
		try {
			clientBll2.findClientById(1);
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}*/
		
		
	}

	
}
