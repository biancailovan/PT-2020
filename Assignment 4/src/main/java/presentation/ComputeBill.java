package presentation;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;
import dataLayer.FileWrite;

public class ComputeBill {
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	static Restaurant restaurant=new Restaurant();
	
	JButton button1=new JButton("Compute Price");
	JTextField textfNAME;
	public ComputeBill() {
		this.restaurant=CreateNewOrder.restaurant;
		JFrame jframe=new JFrame("");
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.setSize(1000, 400);
		JPanel content1=new JPanel();
		JLabel label1=new JLabel("Compute order price");
		content1.add(label1);
		JPanel content2=new JPanel();
		textfNAME=new JTextField();
		JLabel labelORD=new JLabel("Order id:");
		textfNAME.setPreferredSize(new Dimension(200, 25));
		content2.add(label1);
		content2.add(textfNAME);			
		JPanel content=new JPanel();
		content.add(button1);
		JPanel panelfinal=new JPanel();
		panelfinal.add(content1);
		panelfinal.add(content2);
		panelfinal.add(content);
		panelfinal.setLayout(new BoxLayout(panelfinal, BoxLayout.Y_AXIS));
		jframe.setContentPane(panelfinal);
		jframe.setVisible(true);
	}
	
	public void getBill(){
		int order=0;
		Order ord=null;
		double price=0;
		if(textfNAME.getText()!=null)
			order=Integer.parseInt(textfNAME.getText());
		ArrayList<Order> orders=new ArrayList<Order>();
		orders=this.restaurant.getClOrders();
		System.out.println(orders);
		HashMap<Order,ArrayList<MenuItem>> menu=new HashMap<Order,ArrayList<MenuItem>>();		
		for(Order o: orders){
			if(o.findOrderPrice() == order){
				double s=o.findOrderPrice();
				String string=String.valueOf(s);
				ord=o;
				FileWrite fileWr=new FileWrite();
				string+="\nTotal Price: "+ord.findOrderPrice();
				System.out.println("TOTAL PRICE: "+string);
				fileWr.writer(string);
				//textf.setText(string);
			}
		}
		 for (Map.Entry men :menu.entrySet()) {
			 Order o=(Order) men.getKey();
				if(ord.hashCode() == o.hashCode())
					System.out.println("\nKey: "+men.getKey() + " & Value: " + men.getValue());	
		 }	
	}
	
	public void billButton(){
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				getBill();			
			}
		});
	}
	

}
