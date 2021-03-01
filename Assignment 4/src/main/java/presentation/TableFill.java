package presentation;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import businessLayer.BaseProduct;
import businessLayer.Restaurant;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Order;

public class TableFill extends JFrame implements Serializable{
	Restaurant restaurant=new Restaurant();
	//TableFill table=new TableFill();
	private JTable table=new JTable();
	
	public JTable getTable() {
		return this.table;
	}
	public TableFill(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public TableFill() {

	}
	
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant=restaurant;
	}
	JFrame menuitems;
	JTable menuItems;
	JFrame orders;
	JTable orderS;
	
	public void orderTable(String[][] order){
		orders=new JFrame();
		orders.setTitle("Table for orders");
		String[] columnNames = {"Order ID", "Date", "Client Order", "Table "};		
		orderS = new JTable(order, columnNames);
		orders.setBounds(30, 40, 300, 300);
		JScrollPane scrollPane = new JScrollPane(orderS);
		orders.add(scrollPane);
		orders.setSize(500, 200);
		orders.setVisible(true);
	}
	
	public void menuTable(String[][] menu){
		menuitems= new JFrame();
		menuitems.setTitle("Tabel items");
		String[] columnNames= {"Tip item","Name ","Price"};
		menuItems= new JTable(menu,columnNames);
		menuItems.setBounds(30, 40, 300, 300);
		JScrollPane scrollPane= new JScrollPane(menuItems);
		menuitems.add(scrollPane);
		menuitems.setSize(700, 300);
		menuitems.setVisible(true);
	}
	
	public String[][] getOrders(ArrayList<Order> orders){
		String[][] mat = new String[100][100];
		int variable=0, j=0;
		for(Order order: orders){
			try{
				Order ord= (Order)order;
				mat[variable][j]=String.valueOf(ord.getIdorder());
				mat[variable][++j]=String.valueOf(ord.getDate());
				mat[variable][++j]=String.valueOf(ord.getClientOrder());
				mat[variable][++j]=String.valueOf(ord.getTable());
				variable++;
				j=0;
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return mat;	
	}
	
	public static String[][] getMenu(ArrayList<MenuItem> menuItems){
		String[][] mat=new String[100][100];
		int variable=0;
		for(MenuItem m: menuItems){
			if(m instanceof BaseProduct){
				try{
					BaseProduct product=((BaseProduct)m);
					mat[variable][0]=m.getClass().getSimpleName();
					mat[variable][1]=product.getNameP();
					mat[variable][2]=String.valueOf(product.getPrice());
					variable++;
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			else 
				if(m instanceof CompositeProduct){
					try{
						//int k=0;
						CompositeProduct product=(CompositeProduct)m;
						mat[variable][0]=m.getClass().getSimpleName();
						mat[variable][1]=product.getNameP();
						mat[variable][2]=String.valueOf(product.computeTotalPrice());
						mat[variable][3]=String.valueOf(product.getMenuItems());
						variable++;
				    }catch(Exception e){
				    	e.printStackTrace();
				    }
				}
			}
			
		return mat;
	}
	
	/*pentru a afisa meniul in tabel*/
	/*public void showMenu(){
		//TableFill table=new TableFill();	
		ArrayList<MenuItem> menu=new ArrayList<MenuItem>();
		restaurant.setMenuItems(menu);
		//for(MenuItem m: menu) 
		TableFill table=new TableFill(restaurant);
		table.getTable();
		String[][] s=table.getMenu(menu);
		table.menuTable(s);
		//}
					
	}*/
	
	/*pentru afisarea comenzilor in tabel*/
	public void showOrders(){
		//TableFill table=new TableFill();	
		ArrayList<Order> orders=new ArrayList<Order>();
		TableFill table=new TableFill(restaurant);
		String[][] s=table.getOrders(orders);
		table.orderTable(s);
					
	}
	
	public static void main(String[] args) {
		Restaurant restaurant=new Restaurant();
		BaseProduct bp=new BaseProduct("rosie", 5);
		System.out.println(bp);
		ArrayList<MenuItem> list=new ArrayList<MenuItem>();
		//list.add(bp);
		restaurant.addSomethingInMenu(bp);
		list=restaurant.getMenuItems();
		System.out.println(list);
		
		//restaurant.addSomethingInMenu(bp);
		//System.out.println(restaurant);
		
		TableFill table=new TableFill();
		table.setRestaurant(restaurant);
		System.out.println(table);
		String[][] meniu=new String[5][5];
		meniu = table.getMenu(list);
		System.out.println(meniu[0][0]);
		System.out.println(meniu[0][1]);
		System.out.println(meniu[0][2]);
		table.menuTable(meniu);
		//meniu=list;
		//System.out.println(table.getMenu(list));
		System.out.println(restaurant.getMenuItems());
		
		//table.showMenu();
		
	}
	@Override
	public String toString() {
		return "TableFill [restaurant=" + restaurant + "]";
	}

}
