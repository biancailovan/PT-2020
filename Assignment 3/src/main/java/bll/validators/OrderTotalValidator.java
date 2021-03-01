package bll.validators;

import model.Client;
import model.Order;

/**
* OrderTotalValidator Class implements the Validator interface
* the class validates the total to be sure it is not a negative number
* 
*/
public class OrderTotalValidator implements Validator<Order> {
	
public void validate(Order o) {
		
		if(o.getTotal()<0) {
			throw new IllegalArgumentException("The total is a negative value!");
		}
     }

}
