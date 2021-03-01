package bll;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.itextpdf.text.DocumentException;

import bll.validators.ProductNameValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import dao.OrderItemDAO;
import dao.ProductDAO;
import model.Client;
import model.Product;
import presentation.PDFProduct;

/**
* ProductBLL Class implements the logic of operations from Product's table 
* 
*/
public class ProductBLL {
	
private static List<Validator<Product>> validators;
	
	/**
	 * This is the constructor that initializes ClientBLL Object
	 */
	public ProductBLL() {
		validators = new ArrayList<Validator<Product>>();
		//validators.add((Validator<Product>) new ProductNameValidator());
	}
	
	/**
	 * Method findProductById helps us to find a product by id
	 * @param id represents the id of the product
	 * 
	 * @return pr it will be returned the product
	 * 
	 */
	public Product findProductById(int id) {
		Product pr = ProductDAO.findById(id);
		if (pr == null) {
			throw new NoSuchElementException("The product with id =" + id + " was not found!");
		}
		return pr;
	}
	
	/**
	 * Method findProductByName helps us to find a product by name
	 * @param nameProduct represents the id of the product
	 * 
	 * @return pr it will be returned the product
	 * 
	 */
	public static Product findProductByName(String nameProduct) {
		Product pr = ProductDAO.findProductByName(nameProduct);
		if (pr == null) {
			throw new NoSuchElementException("The product with name =" + nameProduct + " was not found!");
		}
		return pr;
	}

	/**
	 * Method insertProduct helps us to insert a product in the database
	 * We want to be sure that the product is a valid one
	 * @param product, the product that we want to be inserted
	 * @return the product inserted in the database
	 * 
	 */
	public static int insertProduct(Product product) {
		for (Validator<Product> v : validators) {
			v.validate(product);
		}
		if(ProductDAO.findProductByName(product.getNameP())==null) {
			return ProductDAO.insert(product);
		}
		else
			return updatePlusProduct(product.getQuantity(), product.getNameP());
	}
	ProductDAO productDAO=new ProductDAO();
	
	/**
	 * Method updateProduct helps us to update a product from the database
	 * We want to be sure that our product is a valid one
	 * @param product, the product that we want to be updated
	 * @return the product updated in the database
	 */
	public Product updtProduct(Product product) throws IllegalArgumentException, IllegalAccessException {
		for (Validator<Product> v : validators) {
			v.validate(product);
		}
		return productDAO.updateObj(product);
	}
	OrderItemDAO orderitemDAO=new OrderItemDAO();
	
	/**
	 * Method deleteProduct helps us to delete a product from the database
	 * We want to be sure that our product exists
	 * @param product, the product that we want to be deleted
	 * 
	 */
	public void deleteProduct(String nameProduct) {
		Product pr=productDAO.findProductByName(nameProduct);
		if(pr==null) {
			throw new NoSuchElementException("The product with name =" + nameProduct + " was not found!");
		}
		if(orderitemDAO.findByProduct(pr.getIdproduct())!=null) {
			orderitemDAO.deleteObj(pr.getIdproduct(), "idproduct");
		}
		productDAO.deleteObj(pr.getIdproduct(),"idproduct");
	}
	
	/**
	 * Method updatePlusProduct helps us to update a product from the database
	 * @param name, name of our product
	 * @param quantity, to be added
	 */
	public static int updatePlusProduct(int quantity, String name) {
		int ok = ProductDAO.update1(quantity, name);
		if (ok == 0) {
			throw new NoSuchElementException("The Product was not found!");
		}
		return ok;
	}
	
	/**
	 * Method updateProduct helps us to update a product from the database
	 * @param name, name of our product
	 * @param quantity, in this case we want to substract
	 */
	public int updateProduct(int quantity, String name) {
		int ok = ProductDAO.update2(quantity, name);
		if (ok == 0) {
			throw new NoSuchElementException("The Product was not found!");
		}
		return ok;
	}
	
	/**
	 * Method findAll helps us to find all products from the database
	 */
	public ArrayList<Product> findAll(){
		return productDAO.findAllArrays2();
	}
	
	/**
	 * Method reportProduct of type ArrayList<Product> helps us
	 * to print in a PDF document all the products from our database
	 */
	public ArrayList<Product> reportProduct() throws FileNotFoundException, DocumentException {
		ArrayList<Product> list = ProductDAO.findAllProducts();
		PDFProduct prod = new PDFProduct(list);
		prod.generate();
		if (list == null) {
			throw new NoSuchElementException("The Product  was not found!");
		}
		return list;
	}	

}
