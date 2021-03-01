package businessLayer;

/*Interfata are metodele ce trebuie implementate 
 * fie de administrator
 * fie de waiter*/
public interface IRestaurantProcessing {
	
	/*Administrator
	 * new menu
	 * delete menu
	 * edit menu
	 * */
	
	/** ADMINISTRATOR
	 * Adauga un menu item in meniul restaurantului
	 * @param : menuItem MenuItem-ul pe care il vom adauga 
	 * @pre : this.menuItems != null && menuItem!=null
	 * 	Sa existe un obiect tip MenuItem
	 * 
	 * @post : Adaugarea menu item-ului in Restaurant, nrOfItems == menuItems.size()+1;
	 * */
	public void newMenuItem();
	
	/** 
	 * Schimba un menu item existent in meniu cu unul nou
	 * @pre : this.menuItems != null && menuItem!=null && newMenuItem!=null
	 * @param : menuItem MenuItem-ul existent, menuItemNew MenuItem-ul pe care vrem sa il adaugam in locul sau
	 * Trebuie sa existe produsul pe care vreau sa il schimb
	 * 
	 * @post : Schimbarea menu item-ului din meniu, menuItems.size() == nrOfItems;
	 * */
	public void changeInMenu();
	/** 
	 * @pre : this.menuItems != null && menuItem!=null && containsMenuItem(menuItem)
	 * @param : menuItem-ul existent
	 * Trebuie sa existe produsul pe care vreau sa il sterg
	 * @post : Stergerea menu item-ului din meniu, nrOfItems == menuItems.size()-1;
	 * */
	public void deleteMenuItem();
	
	/*Waiter
	 * new order
	 * generate Bill
	 * */
	/** WAITER
	 * @pre : this.clientsOrders != null && this.menuItems != null && orders!=null
	 * 	Sa existe un obiect tip Order
	 * 
	 * @post : Creearea unei noi comenzi
	 * */
	public void newOrder();
	/** WAITER
	 * @pre : this.clientsOrders != null && this.menuItems != null && orders!=null
	 * 	Sa existe un obiect tip Order
	 * 
	 * @post : Creearea unei facturi
	 * */
	public void newBill(); /*generates a bill*/

}
