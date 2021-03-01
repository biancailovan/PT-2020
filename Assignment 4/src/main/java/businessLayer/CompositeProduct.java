package businessLayer;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {

	@Override
	public double computePrice() {
		// TODO Auto-generated method stub
		double totalPrice=0;
		for(MenuItem m: menuItems) {
			if(m instanceof BaseProduct)
				totalPrice=totalPrice+m.computePrice();
		}
		return totalPrice;
	}
	
	/*returneaza pretul total*/
	public String computeTotalPrice() {
		double totalPrice=0;
		for(MenuItem m: menuItems) {
			if(m instanceof BaseProduct)
				totalPrice=totalPrice+((BaseProduct)m).computePrice();
		}
		return String.valueOf(totalPrice);
	}
	
	public void findAllBaseProducts() {
		for(MenuItem m: menuItems) {
			if(m instanceof BaseProduct) 
				System.out.println(((BaseProduct) m).getNameP());				
		}
	}
	
	private String name;
	private ArrayList<MenuItem> menuItems;
	/**
	 * @return the name of the composite product
	 */
	public String getNameP() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setNameP(String name) {
		this.name = name;
	}
	/**
	 * @return the menuItems
	 * 
	 */
	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}
	/**
	 * @param menuItems the menuItems to set
	 */
	public void setMenuItems(ArrayList<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
	
	public CompositeProduct(String name, ArrayList<MenuItem> menuItems) {
		super();
		this.name = name;
		this.menuItems = menuItems; /*lista de base products*/
	}

	public CompositeProduct() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CompositeProduct [name=" + name + ", menuItems=" + menuItems + "]";
	}
	
}
