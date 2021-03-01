package bll.validators;

import model.Client;

/**
* ClientAddressValidator Class implements the Validator interface
* the class validates the client's address
* 
*/

public class ClientAddressValidator implements Validator<Client> {
	
	private static final int MIN_LENGTH_NAME=3;
	private static final int MAX_LENGTH_NAME=45;
	
	public void validate(Client c) {
		
		if(c.getAddress().length()<MIN_LENGTH_NAME || c.getAddress().length()>MAX_LENGTH_NAME) {
			throw new IllegalArgumentException("The Client's address limit is not respected!");
		}
     }

}
