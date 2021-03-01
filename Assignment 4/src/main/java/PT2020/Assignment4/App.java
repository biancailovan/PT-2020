package PT2020.Assignment4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Restaurant;
import businessLayer.Observable;
import dataLayer.FileWrite;
import presentation.AddItem;
import presentation.AdministratorGUI;
import presentation.ChefGUI;
import presentation.Observer;
import presentation.TableFill;
import presentation.WaiterGUI;

/**
 * Hello world!
 *
 */
public class App 
{
	public static Restaurant restaurant = new Restaurant();
    public static void main( String[] args )
    {/*
    	BaseProduct bp1 = new BaseProduct("rosie", 5);
    	BaseProduct bp2 = new BaseProduct("castravete", 7);
    	
    	ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
    	//TableFill table=new TableFill();
    	CompositeProduct cp=new CompositeProduct("salata", menu);
    	System.out.println(bp1);
    	System.out.println(bp2);
    	System.out.println(cp);
    	//table.showMenu();
        //System.out.println( "Hello World!" );*/
    	AdministratorGUI adm=new AdministratorGUI();
    	adm.setRestaurant(restaurant);
    	//restaurant.addSomethingInMenu(bp1);
    	//restaurant.addSomethingInMenu(bp2);
    	//restaurant.addSomethingInMenu(bp2);
    	System.out.println(adm);
		adm.newMenuItem();
		//restaurant.addSomethingInMenu(adm.newMenuItem());
		adm.deleteMenuItem();
		adm.changeMenuItem();
		adm.viewAll();
		
		WaiterGUI waiter=new WaiterGUI();
		System.out.println(waiter);
		waiter.setRestaurant(restaurant);
		waiter.newOrder();
		waiter.newBill();
		waiter.viewAll();
		
		//ChefGUI chef=new ChefGUI();
		//FileWrite filewr=new FileWrite();
		//filewr.writer();
		
		int newOrder=0;
		ChefGUI chef= new ChefGUI();
		Restaurant r=new Restaurant();
		//r.addObserver(chef);
		r.notification(r.getOrder());
    }
}
