package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.ClientNameValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

/**
* ClientBLL Class implements the logic of operations from Client's table 
* 
*/
public class ClientBLL {
	
	private List<Validator<Client>> validators;
	ClientDAO clientDAO;
	
	/**
     * This is the constructor that initializes ClientBLL Object
     */
	public ClientBLL() {
		validators = new ArrayList<Validator<Client>>();
		//validators.add((Validator<Client>) new ClientNameValidator());
		clientDAO=new ClientDAO();
		
	}
	
	/**
	 * Method findClientById helps us to find a client by id
	 * @param id represents the id of the client
	 * 
	 * @return cl it will be returned the client 
	 * 
	 */
	public Client findClientById(int id) {
		Client cl = ClientDAO.findById(id);
		if (cl == null) {
			throw new NoSuchElementException("The client with id =" + id + " was not found!");
		}
		return cl;
	}
	
	/**
	 * Method findClientByName helps us to find a client by name
	 * @param id represents the id of the client
	 * 
	 * @return cl it will be returned the client 
	 * 
	 */
	public static Client findClientByName(String name) {
		Client cl = ClientDAO.findByName(name);
		if (cl == null) {
			throw new NoSuchElementException("The client with name =" + name + " was not found!");
		}
		return cl;
	}

	/**
	 * Method insertClient helps us to insert a client in the database
	 * We want to be sure that the client is a valid one
	 * @param client, the client that we want to be inserted
	 * @return the client inserted in the database
	 * 
	 */
	public int insertClient(Client client) {
		for (Validator<Client> v : validators) {
			v.validate(client);
		}
		if(client==null) {
			return ClientDAO.insert(client);
		}
		else return -1;		
	}
	
	/**
	 * Method findAll helps us to find all clients from the database
	 * @return ArrayList<Client>, arraylist of all the clients
	 */
	public ArrayList<Client> findAll(){
		return clientDAO.findAllArrays();
	}
	
	/**
	 * Method makeInsertOfTheClient helps us to insert a client in the database
	 * We search our client by name, with the method findByName
	 * @param client, the client that we want to be inserted
	 * @return the client inserted in the database
	 * 
	 */
	public static int makeInsertOfTheClient(String name, String address) {
		Client client=ClientDAO.findByName(name);
		if(client==null) {
			return ClientDAO.insert(client);
		}
		else
			return -1;
	}
	
	/**
	 * Method deleteClient helps us to delete a client from the database
	 * We want to be sure that our client exists
	 * @param client, the client that we want to be deleted
	 * 
	 */
	public void deleteClient(String nameClient) {
		Client client = clientDAO.findByName(nameClient);/* pentru a verifica daca clientul exista in DB*/
		if (client == null) {
			throw new NoSuchElementException("The client with name = " + nameClient + " was not found!");
		}
		clientDAO.deleteObj(client.getIdclient(), "idclient");
	}

	/**
	 * Method updateClient helps us to update a client from the database
	 * We want to be sure that our client is a valid one
	 * @param client, the client that we want to be updated
	 * @return the client updated in the database
	 */
	public Client update(Client cl) throws IllegalArgumentException, IllegalAccessException {
		for(Validator<Client> v: validators) {
			v.validate(cl);
		}
		return clientDAO.updateObj(cl);
	}
	
}
