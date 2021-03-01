package presentation;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;

public class CreateNewOrder {
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	private ArrayList<MenuItem> menuItems=new ArrayList<MenuItem>();
	private ArrayList<MenuItem> list=new ArrayList<MenuItem>();
	private JButton button1=new JButton("Create new Order");
	static Restaurant restaurant= new Restaurant();
	JTextField textf0, textfNAME, textfPRICE, textf3, textf4;
	JTextField textfNAME1,textfNAME2, textfNAMEcomp, textfPRICEcomp;
	JTextField textfOrderid, textfTable;
	public CreateNewOrder() {
		this.restaurant=AddItem.restaurant;
		JFrame jframe=new JFrame("");
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.setSize(1000, 400);
		JPanel content1=new JPanel();
		JLabel label1=new JLabel("Add in Order");
		content1.add(label1);
		JPanel contentN=new JPanel();
		JLabel labelN= new JLabel("Name: ");
		contentN.add(labelN);
		textfNAME=new JTextField();
		textfNAME.setPreferredSize(new Dimension(200, 25));
		textfNAME.setText(null);
		contentN.add(textfNAME);
		JLabel labelP=new JLabel("Price: ");
		contentN.add(labelP);
		textfPRICE=new JTextField();
		textfPRICE.setPreferredSize(new Dimension(200, 25));
		textfPRICE.setText("0");
		contentN.add(textfPRICE);
		JPanel content2=new JPanel();
		JLabel labelOrd=new JLabel("ORDER id:");
		textfOrderid=new JTextField();
		textfOrderid.setPreferredSize(new Dimension(200, 25));
		textfOrderid.setText("0");
		content2.add(labelOrd);
		content2.add(textfOrderid);
		JLabel labelTAB=new JLabel("Table:");
		textfTable=new JTextField();
		textfTable.setPreferredSize(new Dimension(200, 25));
		textfTable.setText("0");
		content2.add(labelTAB);
		content2.add(textfTable);		
		JPanel contentfinal=new JPanel();
		contentfinal.add(button1);
		JPanel panelfinal=new JPanel();
		panelfinal.add(content1);
		panelfinal.add(contentN);
		panelfinal.add(content2);
		panelfinal.add(contentfinal);
		panelfinal.setLayout(new BoxLayout(panelfinal, BoxLayout.Y_AXIS));			
		jframe.setContentPane(panelfinal);	
		jframe.setVisible(true);		
		
	}
	
	public Order createOrder(ArrayList<MenuItem> arraylist){	
		Order order=null;
		Date date=new Date();
		int idorder=0, table=0;
		if(textfTable.getText() != "0" && textfOrderid.getText() != "0"){
			idorder=Integer.parseInt(textfOrderid.getText());
			table=Integer.parseInt(textfTable.getText());
		}
		if(table!=0 && idorder!=0){
			order=new Order(idorder, arraylist, date, table);	
			restaurant.addOrder(order);
		}
		textfOrderid.setText("0");
		textfTable.setText("0");
		return order;
	}
	
	public void addBaseProduct(ArrayList<MenuItem> list, MenuItem menu, String nameP, double price) {
		MenuItem menuNew=null;
		BaseProduct bp=new BaseProduct();
		menuNew=new BaseProduct(nameP, price);
		System.out.println(menuNew);
		list.add(menuNew);
	}
	
	public void addCompositeProduct(ArrayList<MenuItem> list, MenuItem menu, String nameP, double price) {
		MenuItem menuNew=null;
		CompositeProduct cp=new CompositeProduct();
		menuNew=new CompositeProduct(nameP, cp.getMenuItems());
		System.out.println(menuNew);
		list.add(menuNew);
	}
	
	public ArrayList<MenuItem> addInList(){
		ArrayList<MenuItem> menu=new ArrayList<MenuItem>();
		//restaurant.setMenuItems(menu);	
		menu=restaurant.getMenuItems();
		System.out.println(restaurant);
		System.out.println("Meniu: "+menu);
		ArrayList<String> s=new ArrayList<String>();
		String name="";
		double price=0;
		BaseProduct bp=null;
		CompositeProduct cp=null;
		if(textfNAME.getText() != null && textfPRICE.getText() != "0"){
			name=textfNAME.getText();
			price=Double.parseDouble(textfPRICE.getText());		
			for(MenuItem m: menu){
				if(m instanceof BaseProduct) {
					if(((BaseProduct)m).getNameP().equals(name))
						addBaseProduct(list,m,name, price);
				}
				else if(m instanceof CompositeProduct) {
					if(((CompositeProduct)m).getNameP().equals(name))
						addCompositeProduct(list,m,name,price);
				}
			}
		}
		
		textfNAME.setText(null);
		textfPRICE.setText("0");
		System.out.println("Client ordered: "+list);
		return list;
		
	}
	
	void createOrderButton(){
		button1.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				menuItems=addInList();
				System.out.println("Clients list: "+menuItems);
				Order ord=createOrder(menuItems);
				restaurant.addOrder(ord);
				System.out.println("You created an order.");				
			}
		});
	}

}
