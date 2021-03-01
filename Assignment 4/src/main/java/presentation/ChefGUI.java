package presentation;

import javax.swing.JOptionPane;

import businessLayer.Observable;
import businessLayer.Order;
import businessLayer.Restaurant;

public class ChefGUI {
	
	private Order order=new Order();
	private Restaurant restaurant=new Restaurant();
	
	public void update(Observable obs, Object obj) {
		// TODO Auto-generated method stub
		System.out.println("You have an order! ");

	}

	public static void infoBox(String string, String string2) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, string, "CHEF: " + string2, JOptionPane.INFORMATION_MESSAGE);		
	}
	

}
