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

import businessLayer.Restaurant;
import businessLayer.MenuItem;
import businessLayer.Order;

public class WaiterGUI extends JFrame {
	
	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = WaiterGUI.restaurant;
	}
	
	static Restaurant restaurant=new Restaurant();
	private ArrayList<MenuItem> list1=new ArrayList<MenuItem>();
	private ArrayList<MenuItem> list2=new ArrayList<MenuItem>();
	private JTable tableFill=new JTable();
	TableFill table=new TableFill(restaurant);
	private JButton computeBill=new JButton("Compute Bill");
	private JButton createNewOrder=new JButton("Create new order");
	private JButton viewAll=new JButton("View All");
	//Order order=new Order();
	
	public WaiterGUI() {
		this.restaurant=CreateNewOrder.restaurant;
		JFrame frame = new JFrame("Restaurant management system");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(450, 600);
		
		JPanel panel1=new JPanel();
		JLabel label1= new JLabel("Waiter user");
		label1.setPreferredSize(new Dimension(120, 100));
		panel1.add(label1);
		
		JPanel panelADD=new JPanel();
		createNewOrder.setPreferredSize(new Dimension(300, 50));
		panelADD.add(createNewOrder);
		
		JPanel panelBILL=new JPanel();
		computeBill.setPreferredSize(new Dimension(300, 50));
		panelBILL.add(computeBill);
		
		JPanel panelVIEW=new JPanel();
		viewAll.setPreferredSize(new Dimension(200, 50));
		panelVIEW.add(viewAll);
		
		JPanel panelFinal=new JPanel();
		panelFinal.add(panel1);
		panelFinal.add(panelADD);
		panelFinal.add(panelBILL);
		panelFinal.add(panelVIEW);
		panelFinal.setLayout(new BoxLayout(panelFinal, BoxLayout.Y_AXIS));
		frame.setContentPane(panelFinal);
		frame.setVisible(true);
		
	}
	private int newOrder=0;
	public void newOrder(){
		createNewOrder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Create Order");
				//afisareMeniu();
				CreateNewOrder order=new CreateNewOrder();
				order.createOrderButton();
				//order.addInListButton();
				newOrder=1;			
			}
		});
	}
	
	public void newBill(){
		computeBill.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Compute Bill");
				ComputeBill compute= new ComputeBill();
				compute.billButton();				
			}
		});
	}
	
	public void viewAll(){
		viewAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("View all");
				//TableFill table=new TableFill(restaurant);
				//table.showOrders();		
				showOrd();
			}
		});
	}
	
	public void showOrd() {
		this.restaurant=CreateNewOrder.restaurant;
		TableFill table=new TableFill();
		String[][] meniu=new String[100][100];
		ArrayList<Order> list=new ArrayList<Order>();
		list=restaurant.getClOrders();
		System.out.println(restaurant);
		System.out.println("Lista de afisat in tabel"+list);
		meniu = table.getOrders(list);
	
		/*System.out.println(meniu[0][0]);
		System.out.println(meniu[0][1]);
		System.out.println(meniu[0][2]);*/
		table.orderTable(meniu);
	}
	
}
