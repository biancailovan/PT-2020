package businessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import presentation.AddItem;
import presentation.Observer;
import presentation.TableFill;

public class Restaurant implements Observable {
	
	private HashMap<Order, ArrayList<MenuItem>>clientsOrders=new HashMap<Order,ArrayList<MenuItem>>();
	private ArrayList<Order> clOrders=new ArrayList<Order>();
	private ArrayList<MenuItem> menuItems=new ArrayList<MenuItem>();
	private ArrayList<BaseProduct> bp=new ArrayList<BaseProduct>();
	private ArrayList<CompositeProduct> cp=new ArrayList<CompositeProduct>();
	
	public Restaurant(){
		
	}
	/**
	 * @return the clientsOrders
	 */
	public HashMap<Order, ArrayList<MenuItem>> getClientsOrders() {
		return clientsOrders;
	}
	/**
	 * @param clientsOrders the clientsOrders to set
	 */
	public void setClientsOrders(HashMap<Order, ArrayList<MenuItem>> clientsOrders) {
		this.clientsOrders = clientsOrders;
	}
	/**
	 * @return the clOrders
	 */
	public ArrayList<Order> getClOrders() {
		return clOrders;
	}
	/**
	 * @param clOrders the clOrders to set
	 */
	public void setClOrders(ArrayList<Order> clOrders) {
		this.clOrders = clOrders;
	}
	/**
	 * @return the menuItems
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
	
	/**
	 * @return the observers
	 */
	public ArrayList<Observer> getObservers() {
		return observers;
	}
	/**
	 * @param observers the observers to set
	 */
	public void setObservers(ArrayList<Observer> observers) {
		this.observers = observers;
	}
	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	public Restaurant(HashMap<Order, ArrayList<MenuItem>> clientsOrders, ArrayList<Order> clOrders,
			ArrayList<MenuItem> menuItems) {
		super();
		this.clientsOrders = clientsOrders;/*hash map ce contine comenzile clientilor*/
		this.clOrders = clOrders;/*comenzile clientilor*/
		this.menuItems = AddItem.restaurant.getMenuItems();

	}
	
	ArrayList<Observer> observers=new ArrayList<Observer>();

	public void addObserver(Object o) {
		// TODO Auto-generated method stub
		observers.add((Observer)o);
		
	}
	@Override
	public String toString() {
		return "Restaurant [clientsOrders=" + clientsOrders + ", clOrders=" + clOrders + ", menuItems=" + menuItems
				+ ", bp=" + bp + ", cp=" + cp + ", observers=" + observers
				+ ", order=" + order + "]";
	}

	Order order;
	/** Metoda pentru notoficare observer
	 */
	public void notifyObserver() {
		// TODO Auto-generated method stub
		for(Observer observer: observers)
			observer.update(order);
		
	}

	/** Metoda pentru stergere observer
	 */
	public void deleteObserver(Object o) {
		// TODO Auto-generated method stub
		observers.remove(o);
		
	}
	
	/** Metoda va returna true daca formatul este bun
	 */
	public boolean isWellFormed() {
		if(this.clientsOrders != null && this.menuItems != null)
			return true;
		else
			return false;
	}
	
	/** Metoda de adaugare in meniu a unui MenuItem
	 * @param menuItem ceea ce vrem sa adaugam
	 * fie ca este vorba despre un base product fie despre 
	 * un composite product
	 */
	public void addSomethingInMenu(MenuItem menuItem){
		assert isWellFormed();
		assert menuItem != null;
		int nrOfItems = menuItems.size();
			if(menuItem instanceof BaseProduct) {
				menuItems.add(menuItem);
			}
			else if(menuItem instanceof CompositeProduct) {
				menuItems.add(menuItem);
			}
			System.out.println("Adaugam in meniu "+menuItem);	
			
			assert nrOfItems == menuItems.size()+1;
	}
	
	/** Metoda de stergere din meniu a unui MenuItem
	 * @param whatWeWantToRemove ceea ce vrem sa stergem
	 * fie ca este vorba despre un base product fie despre 
	 * un composite product
	 * Trebuie sa ne asiguram ca numarul de elemente este
	 * mai mic decat era inainte
	 */
	public void deleteSomethingFromMenu(ArrayList<MenuItem> whatYouWantToRemove){
		assert isWellFormed();
		assert whatYouWantToRemove != null;
		int nrOfItems = menuItems.size();
		
			if(menuItems.containsAll(whatYouWantToRemove)) {
				menuItems.remove(whatYouWantToRemove);
			}
			
		assert nrOfItems == menuItems.size()-1;
	
	}
	
	/** Metoda de schimbare in meniu a unui MenuItem existent cu unul nou
	 * @param menuItem: array list-ul in care modificam
	 * @param changeBaseProduct: cu ce vom modifica itemul deja existent
	 * Trebuie sa ne asiguram ca numarul de elemente este
	 * acelasi ca inainte si elementul ce dorecte a fi schimbat va fi inserat pe aceesi pozitie
	 * @param k: pozitia unde modificam
	 */
	public void changeSomethingInMenu(int k, ArrayList<MenuItem> menuItems, MenuItem changeBaseProduct){
		assert isWellFormed();	
		assert changeBaseProduct != null;
		int nrOfItems = menuItems.size();
			if(changeBaseProduct instanceof BaseProduct){
				menuItems.set(k,changeBaseProduct);		
			}
			
		assert menuItems.size() == nrOfItems;
	}
	
	/** Metoda de adaugare a unui comenzi 
	 * @param order: comanda ce doreste a fi adaugata
	 * 
	 */
	public void addOrder(Order order){
		assert isWellFormed();
		assert order != null;
			clOrders.add(order);
			notifyObserver();/*pentru chef*/
			clientsOrders.put(order,order.getClientOrder());
			for (Map.Entry me : clientsOrders.entrySet()) {
				System.out.println("Key: "+me.getKey() + " & Value: " + me.getValue());
			}			
	}
	
	/** Metoda pentru primirea unei notificari
	 * @param orderr: comanda noua de care bucatarul va trebui instiintat
	 * 
	 */
	public void notification(Order orderr){
		this.order=orderr;
		notifyObserver();
	}
	

	
	

}
