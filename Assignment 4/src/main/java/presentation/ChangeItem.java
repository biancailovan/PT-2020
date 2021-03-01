package presentation;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Restaurant;

public class ChangeItem {
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	private ArrayList<MenuItem> menuItems=new ArrayList<MenuItem>();
	private JButton button1=new JButton("Change");
	private Restaurant restaurant= new Restaurant();
	JTextField textf0, textfNAME, textfPRICE, textf3, textf4;
	JTextField textfNAME1,textfNAME2, textfNAMEcomp, textfPRICEcomp;
	JTextField textfNAMEnew, textfPRICEnew;
	
	public ChangeItem() {
		//this.restaurant.setMenuItems(menuItems);
		this.restaurant=AddItem.restaurant;
		System.out.println("SHIT: "+restaurant);
		JFrame jframe=new JFrame("");
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.setSize(580, 265);
		JPanel content1=new JPanel();
		JLabel label1=new JLabel("Change in Menu");
		content1.add(label1);
		JLabel labelN= new JLabel("Name: ");
		JLabel labelNnew= new JLabel("New name: ");
		JLabel labelP=new JLabel("Price: ");
		JLabel labelPnew=new JLabel("New price: ");
		JPanel content2=new JPanel();
		textfNAME=new JTextField();
		textfNAME.setPreferredSize(new Dimension(200, 25));
		textfNAME.setText(null);
		textfNAMEnew=new JTextField();
		textfNAMEnew.setPreferredSize(new Dimension(200, 25));
		textfNAMEnew.setText(null);
		JPanel content3=new JPanel();
		textfPRICE=new JTextField();
		textfPRICE.setPreferredSize(new Dimension(50, 25));
		textfPRICE.setText("0");
		textfPRICEnew=new JTextField();
		textfPRICEnew.setPreferredSize(new Dimension(50, 25));
		textfPRICEnew.setText("0");
		JPanel contentfinal=new JPanel();
		contentfinal.add(labelN);
		contentfinal.add(textfNAME);
		contentfinal.add(labelNnew);
		contentfinal.add(textfNAMEnew);
		contentfinal.add(labelP);
		contentfinal.add(textfPRICE);
		contentfinal.add(labelPnew);
		contentfinal.add(textfPRICEnew);
		contentfinal.add(button1);
		
		JPanel panelfinal=new JPanel();
		panelfinal.add(content1);
		panelfinal.add(content2);
		panelfinal.add(content3);
		panelfinal.add(contentfinal);
		panelfinal.setLayout(new BoxLayout(panelfinal, BoxLayout.Y_AXIS));			
		jframe.setContentPane(panelfinal);
	    jframe.setVisible(true);	

	}
	
	public void changeButton(){
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				changeBaseProduct();
				System.out.println("Your change has been approved");				
			}
		});
	}
	
	public void changeBaseProduct() {
		ArrayList<MenuItem> menuitems= new ArrayList<MenuItem>();
		MenuItem changeBase=null, changeComposite=null;
		String name="", newName="";
		double price=0, newPrice=0;
		
		if(textfNAMEnew.getText() != null && textfNAME.getText() != null && textfPRICEnew.getText() != "0" && textfPRICE.getText() != "0"){
			name = textfNAME.getText();
			newName=textfNAMEnew.getText();
			price=Double.parseDouble(textfPRICE.getText());	
			newPrice=Double.parseDouble(textfPRICEnew.getText());	
			BaseProduct bs=new BaseProduct(name, price);
			changeBase=new BaseProduct(newName,newPrice);
			System.out.println("You've changed : "+bs+" with: "+changeBase);		
		}
		
		menuitems=restaurant.getMenuItems();
		System.out.println("ce am preluat: "+menuitems);
		
		int pos = 0;
		for(MenuItem m: menuitems){
			if(m instanceof BaseProduct){
				if (((BaseProduct)m).getNameP().equals(name)){
				{
					System.out.println("am gasit!!!!!!!!!!!!!!!!!");
					restaurant.changeSomethingInMenu(pos, menuitems, changeBase);
					break;
				}
				}
			}	
			pos++;
		}
		
		textfNAME.setText(null);
		textfNAMEnew.setText(null);
		textfPRICE.setText("0");
		textfPRICEnew.setText("0");
	}

}
