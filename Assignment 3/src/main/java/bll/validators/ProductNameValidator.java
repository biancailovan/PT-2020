package bll.validators;

import model.Product;

/**
* ProductNameValidator Class implements the Validator interface
* the class validates the name of the product
* 
*/
public class ProductNameValidator implements Validator<Product> {

	private static final int MIN_LENGTH_NAME=3;
	private static final int MAX_LENGTH_NAME=10;
	
	public void validate(Product p) {
		
		if(p.getNameP().length()<MIN_LENGTH_NAME || p.getNameP().length()>MAX_LENGTH_NAME) {
			throw new IllegalArgumentException("The Product Name limit is not respected!");
		}
	}
}
