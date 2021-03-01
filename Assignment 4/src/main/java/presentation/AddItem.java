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


public class AddItem {
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = AdministratorGUI.restaurant;
	}
	
	private ArrayList<MenuItem> menuItems=new ArrayList<MenuItem>();
	private JButton button1=new JButton("Add");
	public static Restaurant restaurant= new Restaurant();
	JTextField textf0, textfNAME, textfPRICE, textf3, textf4;
	JTextField textfNAME1,textfNAME2, textfNAMEcomp, textfPRICEcomp;
	
	public AddItem() {
		//this.restaurant.setMenuItems(menuItems);
		JFrame jframe=new JFrame("");
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.setSize(1000, 400);
		JPanel content1=new JPanel();
		JLabel label1=new JLabel("Add in Menu");
		content1.add(label1);
		JLabel labelN= new JLabel("Name: ");
		JLabel labelP=new JLabel("Price: ");
		
		JLabel labelP1= new JLabel("Base Product no. 1:");
		JLabel labelP2= new JLabel("Base Product no. 2:");
		JLabel labelComp= new JLabel("Composite Product:");
		JLabel labelTP=new JLabel("Price: ");
		
		JPanel content2=new JPanel();
		JLabel label2=new JLabel("Add base product");
		content2.add(label2);
		JPanel content3=new JPanel();
		textfNAME= new JTextField();
		textfNAME.setPreferredSize(new Dimension(200, 25));
		textfNAME.setText(null);
		textfPRICE=new JTextField();
		textfPRICE.setPreferredSize(new Dimension(100, 25));
		textfPRICE.setText("0");
		content3.add(labelN);
		content3.add(textfNAME);
		content3.add(labelP);
		content3.add(textfPRICE);
		
		
		JPanel content4=new JPanel();
		JLabel label4=new JLabel("Add composite product");
		content4.add(label4);
		JPanel content5=new JPanel();
		/*base product 1 and 2*/
		textfNAME1= new JTextField();
		textfNAME1.setPreferredSize(new Dimension(200, 25));
		textfNAME1.setText(null);
		textfNAME2= new JTextField();
		textfNAME2.setPreferredSize(new Dimension(200, 25));
		textfNAME2.setText(null);
		textfNAMEcomp= new JTextField();
		textfNAMEcomp.setPreferredSize(new Dimension(200, 25));
		textfNAMEcomp.setText(null);
		
		content5.add(labelP1);
		content5.add(textfNAME1);
		content5.add(labelP2);
		content5.add(textfNAME2);
		content5.add(labelComp);
		content5.add(textfNAMEcomp);
		
		JPanel content6=new JPanel();
		textfPRICEcomp=new JTextField();
		textfPRICEcomp.setPreferredSize(new Dimension(100,25));
		textfPRICEcomp.setText("0");
		content6.add(labelTP);
		content6.add(textfPRICEcomp);
		
		JPanel content7=new JPanel();
		content7.add(button1);
		
		JPanel panel= new JPanel();
		 panel.add(content1);
		 panel.add(content2);
		 panel.add(content3);
		 panel.add(content4);
		 panel.add(content5);
		 panel.add(content6);
		 panel.add(content7);
		 panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));			
		 jframe.setContentPane(panel);
		 jframe.setVisible(true);	
		
	}
	
	public void addInMenuBaseProduct(){	
		this.restaurant=AdministratorGUI.restaurant;
		String name="";
		double price=0;
		try{	
			if(textfNAME.getText()!=null){
				name=textfNAME.getText();
				price=Double.parseDouble(textfPRICE.getText());
			}	
			if(price!=0 && name!=""){
				BaseProduct baseP=new BaseProduct(name, price);
				//System.out.println(baseP);		
				MenuItem item=(MenuItem) baseP;
				restaurant.addSomethingInMenu(item);
				textfNAME.setText(null);
				textfPRICE.setText("0");
				System.out.println("BASE: "+restaurant);			
			}
		}
		catch(NumberFormatException e){
			e.printStackTrace();
		}
	}
	
	public void addInMenuCompositeProduct(){	
		String baseP1 = "", baseP2 = "", name = "";
		double price=0;	
		if(textfNAME1.getText()!=null && textfNAME2.getText()!=null && textfNAMEcomp.getText()!=null){
			baseP1=textfNAME1.getText();
			baseP2=textfNAME2.getText();
			name=textfNAMEcomp.getText();
			price=Double.parseDouble(textfPRICEcomp.getText());
		}	
		try{	
			if(baseP1 != "" && baseP2 != "" && name != "" && price != 0){
				BaseProduct baseproduct1=new BaseProduct(baseP1, price);
				BaseProduct baseproduct2=new BaseProduct(baseP2, price);			
				CompositeProduct comp=new CompositeProduct();
				ArrayList<MenuItem> menuItems=new ArrayList<MenuItem>();
				comp.setMenuItems(menuItems);
				menuItems.add(baseproduct1);
				menuItems.add(baseproduct2);
				comp.setNameP(name);
				restaurant.addSomethingInMenu(comp);
				System.out.println(restaurant);
				textfNAME1.setText(null);
				textfNAME2.setText(null);
				textfNAMEcomp.setText("0");
				textfPRICEcomp.setText("0");								
			}
		}
		catch(NumberFormatException e){
			e.printStackTrace();
		}	
	}
	
	public void addButton(){
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				addInMenuBaseProduct();
				addInMenuCompositeProduct();	
			}
		});
	}
		

}

