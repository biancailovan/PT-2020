package bll.validators;

/**
* Validator interface
* the method validate is implemented by other classes from this package.
* 
*/
public interface Validator<T> {
	
	public void validate(T t);

}
