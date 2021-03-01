package presentation;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import PT2020.Assignment4.App;
import businessLayer.Restaurant;
import businessLayer.MenuItem;

public class AdministratorGUI {
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	static Restaurant restaurant= new Restaurant();
	private JButton addInMenu=new JButton("Add in menu");
	private JButton deleteFromMenu=new JButton("Delete from menu");
	private JButton changeInMenu=new JButton("Change in menu");
	public JButton viewAll=new JButton("View all");
	private JTable tableFill=new JTable();
	TableFill table=new TableFill();
	
	public AdministratorGUI() {
		//this.restaurant.setMenuItems(menuItems);		
		//this.setRestaurant(restaurant);
		JFrame frame = new JFrame("Restaurant management system");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(450, 600);		
		JPanel panel1=new JPanel();
		JLabel label1= new JLabel("Administrator user");
		label1.setPreferredSize(new Dimension(120, 100));
		panel1.add(label1);		
		JPanel panelADD=new JPanel();
		addInMenu.setPreferredSize(new Dimension(300, 50));
		panelADD.add(addInMenu);		
		JPanel panelCH=new JPanel();
		changeInMenu.setPreferredSize(new Dimension(300, 50));
		panelCH.add(changeInMenu);		
		JPanel panelDEL=new JPanel();
		deleteFromMenu.setPreferredSize(new Dimension(300, 50));
		panelDEL.add(deleteFromMenu);		
		JPanel panelVIEW=new JPanel();
		viewAll.setPreferredSize(new Dimension(200, 50));
		panelVIEW.add(viewAll);	
		JPanel panelFinal=new JPanel();
		panelFinal.add(panel1);
		panelFinal.add(panelADD);
		panelFinal.add(panelCH);
		panelFinal.add(panelDEL);
		panelFinal.add(panelVIEW);
		panelFinal.setLayout(new BoxLayout(panelFinal, BoxLayout.Y_AXIS));	
		frame.setContentPane(panelFinal);
		frame.setVisible(true);
		
	}
	
	public void newMenuItem(){
		addInMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Add in menu");
				AddItem add= new AddItem();
				add.addButton();
			}
		});
	}
	
	public void deleteMenuItem(){
		//this.restaurant=DeleteItem.restaurant;
		deleteFromMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Delete from menu");
				DeleteItem delete=new DeleteItem();
				delete.deleteButton();
			}
		});
	}
	
	public void changeMenuItem(){
		changeInMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Change in menu");
				ChangeItem change=new ChangeItem();
				change.changeButton();
			}
		});
	}
	
	public void showMenu() {
		TableFill table=new TableFill();
		String[][] meniu=new String[100][100];
		ArrayList<MenuItem> list=new ArrayList<MenuItem>();
		
		list=restaurant.getMenuItems();
		restaurant.setMenuItems(list);
		System.out.println("Lista de item :"+list);
		meniu = table.getMenu(list);
	
		/*System.out.println(meniu[0][0]);
		System.out.println(meniu[0][1]);
		System.out.println(meniu[0][2]);*/
		table.menuTable(meniu);
	}
	
	@Override
	public String toString() {
		return "AdministratorGUI [restaurant=" + restaurant + "]";
	}

	public void viewAll(){
		viewAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("View all");
				//TableFill table=new TableFill(restaurant);
				showMenu();			
			}
		});
	}
		
}
