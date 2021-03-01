package presentation;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Restaurant;

public class DeleteItem {
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	private ArrayList<MenuItem> menuItems=new ArrayList<MenuItem>();
	private JButton button1=new JButton("Delete");
	static Restaurant restaurant= new Restaurant();
	JTextField textf0, textfNAME, textfPRICE, textf3, textf4;
	JTextField textfNAME1,textfNAME2, textfNAMEcomp, textfPRICEcomp;
	
	public DeleteItem() {
	this.restaurant=AdministratorGUI.restaurant;
	JFrame jframe=new JFrame("");
	jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	jframe.setSize(400, 400);
	JPanel content1=new JPanel();
	JLabel label1=new JLabel("Delete from Menu");
	content1.add(label1);
	
	JPanel content2=new JPanel();
	JLabel labelDEL=new JLabel("Base product");
	content2.add(labelDEL);
	JPanel content3=new JPanel();
	textfNAME=new JTextField();
	JLabel labelN=new JLabel("Enter the name: ");
	textfNAME.setPreferredSize(new Dimension(200, 25));
	textfNAME.setText(null);
	content3.add(labelN);
	content3.add(textfNAME);
	
	JPanel content4=new JPanel();
	JLabel labelDELE=new JLabel("Composite product");
	content4.add(labelDELE);
	JPanel content5=new JPanel();
	textfNAMEcomp=new JTextField();
	JLabel labelNA=new JLabel("Enter the name: ");
	textfNAMEcomp.setPreferredSize(new Dimension(200, 25));
	textfNAMEcomp.setText(null);
	content5.add(labelNA);
	content5.add(textfNAMEcomp);
	
	JPanel content6=new JPanel();
	content6.add(button1);
	JPanel panelfinal=new JPanel();
	 panelfinal.add(content1);
	 panelfinal.add(content2);
	 panelfinal.add(content3);
	 panelfinal.add(content4);
	 panelfinal.add(content5);
	 panelfinal.add(content6);
	 panelfinal.setLayout(new BoxLayout(panelfinal, BoxLayout.Y_AXIS));			
	 jframe.setContentPane(panelfinal);
	 jframe.setVisible(true);	
	}
	
	public void deleteBP() {
		this.restaurant=AdministratorGUI.restaurant;
		String baseproduct = "";
		ArrayList<MenuItem> menuitems= new ArrayList<MenuItem>();
		ArrayList<MenuItem> whatYouWantToRemove= new ArrayList<MenuItem>();
		restaurant.setMenuItems(menuitems);
		System.out.println("De aici stergem "+restaurant.getMenuItems());
		if(textfNAME.getText() != null){
			baseproduct=textfNAME.getText();
		}	
		System.out.println("numele produsului de sters: "+baseproduct);	
		if(baseproduct!=""){
			for(MenuItem m: menuitems){
				if(m instanceof BaseProduct){
						whatYouWantToRemove.add((BaseProduct)m);
						System.out.println(restaurant);
						textfNAME.setText(null);
						break;
					
				}
			}
			restaurant.deleteSomethingFromMenu(whatYouWantToRemove);
		}
	}
	
	public void deleteBaseorComposite(){
		this.restaurant=AdministratorGUI.restaurant;
		String baseproduct = "";
		String compositeProduct = "";
		ArrayList<MenuItem> menuitems= new ArrayList<MenuItem>();
		ArrayList<MenuItem> whatYouWantToRemove= new ArrayList<MenuItem>();
		restaurant.setMenuItems(menuitems);
		System.out.println("De aici stergem "+restaurant.getMenuItems());
		if(textfNAME.getText() != null && textfNAMEcomp.getText() == null){
			baseproduct=textfNAME.getText();
		}
		System.out.println("numele produsului de sters: "+baseproduct);
		
		if(textfNAMEcomp.getText() != null){
			compositeProduct=textfNAMEcomp.getText();
		}
		System.out.println(compositeProduct);
		try{		
			if(baseproduct != ""){
			for(MenuItem m:menuitems){
				if(m instanceof BaseProduct){
						if(baseproduct.equals(((BaseProduct) m).getNameP())){
							whatYouWantToRemove.add((BaseProduct)m);						
							break;
						}else{
							System.out.println("There is no such base product! ");
						}
				}		
			}
			System.out.println("DUPA CE AM STERS: "+whatYouWantToRemove);
			restaurant.deleteSomethingFromMenu(whatYouWantToRemove);
			
				for(MenuItem m: menuitems) {
					if(m instanceof CompositeProduct){
					if(compositeProduct.equals(((CompositeProduct) m).getNameP())){
						whatYouWantToRemove.add((CompositeProduct)m);					
						break;
					}else{
						System.out.println("There is no such composite product! ");
					}
					
					//System.out.println("DUPA CE AM STERS: "+whatYouWantToRemove);
					//restaurant.deleteSomethingFromMenu(whatYouWantToRemove);
				}
				}			
			//System.out.println("DUPA CE AM STERS: "+whatYouWantToRemove);
			textfNAME.setText(null);
			textfNAMEcomp.setText(null);
			restaurant.deleteSomethingFromMenu(whatYouWantToRemove);
			
			}		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void deleteButton(){
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//deleteBaseorComposite();
				deleteBP();
			}
		});
	}
}
