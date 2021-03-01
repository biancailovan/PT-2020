package bll.validators;

import model.Client;

/**
* ClientNameValidator Class implements the Validator interface
* the class validates the client's name
* 
*/
public class ClientNameValidator implements Validator<Client> {
	
	private static final int MIN_LENGTH_NAME=3;
	private static final int MAX_LENGTH_NAME=45;
	
	public void validate(Client c) {
		
		if(c.getName().length()<MIN_LENGTH_NAME || c.getName().length()>MAX_LENGTH_NAME) {
			throw new IllegalArgumentException("The Client Name limit is not respected!");
		}
	}

}
