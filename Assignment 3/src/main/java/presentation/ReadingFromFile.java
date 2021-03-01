package presentation;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.DocumentException;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.OrderItemBLL;
import bll.ProductBLL;
import dao.ClientDAO;
import dao.OrderDAO;
import dao.OrderItemDAO;
import dao.ProductDAO;
import model.Client;
import model.Order;
import model.OrderItem;
import model.Product;

/**
* ReadingFromFile Class reads the content from the file given and it has some specific methods that will
* be used in the readFromFile method
*/
public class ReadingFromFile {
	ClientBLL clientBLL = new ClientBLL();
	ProductBLL productBLL = new ProductBLL();
	OrderBLL orderBLL = new OrderBLL();
	OrderItemBLL orderitemBLL = new OrderItemBLL();
	ClientDAO clientDAO = new ClientDAO();
	ProductDAO productDAO = new ProductDAO();
	OrderDAO orderDAO = new OrderDAO();
	OrderItemDAO orderitemDAO = new OrderItemDAO();
	
	/**
	 * Method insertClient helps us to insert a client in the database
	 * It will create a new object of type Client
	 * The arguments should be splited bc this is how the command is found in input the file
	 */
	private void insertClient(String name) {
		String arg[] = name.split(", ");
		Client c = new Client(arg[0], arg[1]);
		ClientDAO.insert(c);
	}
	
	/**
	 * Method insertProduct helps us to insert a product in the database
	 * It will create a new object of type Product
	 * The arguments should be splited bc this is how the command is found in the input file
	 */
	private void insertProduct(String name) {
		String arg[] = name.split(", ");
		Product prod = new Product(arg[0],Double.parseDouble(arg[1]), Integer.parseInt(arg[2]));
		//ProductDAO.insert(prod);
		ProductBLL.insertProduct(prod);
	}
	
	/**
	 * Method insertOrder helps us to insert an order in the database
	 * It will create a new object of type Order
	 * The arguments should be splited bc this is how the command is found in the input file
	 */
	private void insertOrder(String name) {
		String arg[] = name.split(", ");
		try {
		Order ord = new Order(/*Integer.parseInt(arg[0]),*/ Integer.parseInt(arg[1]), arg[2], Integer.parseInt(arg[3]));
		//OrderDAO.insert(ord);
		OrderBLL.insertOrder(ord);
		}catch(NumberFormatException e) {
			e.getCause();
		}
	}
	
	/**
	 * Method deleteClient helps us to delete a client from the database
	 * The arguments should be splited bc this is how the command is found in the input file
	 */
	private void deleteClient(String nameClient) {
		String arg[] = nameClient.split(", ");
		//clientBLL.deleteClient(arg[0]);
		clientDAO.deleteClient(arg[0]);
	}
	
	/**
	 * Method deleteProduct helps us to delete a client from the database
	 * The arguments should be splited bc this is how the command is found in the input file
	 */
	private void deleteProduct(String nameProduct) {
		//ProductDAO.delete(name);
		//String arg[] = nameProduct.split(", ");
		productBLL.deleteProduct(nameProduct);
	}

	Bill bill;
	/**
	 * Method makeOrder helps us to place an order in the database
	 * We will search in the database the client and the product by name
	 * We need to establish the quantity ordered, and the current stock of the product in the database
	 * If there are not enough products => get message
	 * We need to establish the new total of the order
	 * We need to create a new order item to be inserted in the database
	 * @param quantity quantity a client wants to buy
	 */
	private void makeOrder(String arguments) throws FileNotFoundException, DocumentException, IllegalArgumentException, IllegalAccessException {
		String arg[] = arguments.split(", ");
		Client client = clientBLL.findClientByName(arg[0]);
		Product product = productBLL.findProductByName(arg[1]);
		int quantity = Integer.parseInt(arg[2]);
		int newQuantity = product.getQuantity() - quantity; /*scad cantitatea primita ca argument din cantitatea veche*/
		if (newQuantity<0) { 
			System.out.println("There is no more " + product.getNameP()+" for this order, because the current stock is " + product.getQuantity()+"(quantity)");
		}
		else {
			product.setQuantity(newQuantity);
			productBLL.updateProduct(quantity, product.getNameP()); 
			Order order = new Order(0,client.getIdclient(),client.getName(), 0);
			//OrderDAO.insert(new Order(0,client.getIdclient(),client.getName(), 0));
			order = orderBLL.insert(order);
			//OrderDAO.insert(new Orderc(0, p.getClient(), newPrice));
			double price = (double) (product.getPrice()*quantity)+order.getTotal();
			order.setTotal((int)price);
			orderBLL.update(order);
			OrderItem ordItem = new OrderItem(order.getIdorder(), product.getIdproduct(), 5);
			orderitemBLL.insert(ordItem);
			ArrayList<OrderItem> orderedItems = new ArrayList<OrderItem>();
			orderedItems = orderitemBLL.findByOrder(ordItem);
			Bill listing = new Bill(order, orderedItems);
			//getBill();
			//Bill listing = new Bill(order, items);
			listing.generateBill();
			System.out.println("The client " + order.getIdclient() + " " + order.getNameClient() + " has bought products of " + order.getTotal() + " €.");
		}

	}
	
	int counter=0;
	PDFClient pdfClient;
	/**
	 * Method reportClients helps us to generate a pdf with the clients from our database
	 * We need an ArrayList of Clients, andto find all clients from the database
	 */
	private void reportClients() throws FileNotFoundException, DocumentException  {
		counter++;
		ArrayList<Client> clients = clientDAO.findAllClients();
		PDFClient listing = new PDFClient(clients);
		listing.generateReport();
	}
	
	/**
	 * Method reportProducts helps us to generate a pdf with the products from our database
	 * We need an ArrayList of Product, and to find all our products from the database
	 */
	private void reportProducts() throws FileNotFoundException, DocumentException  {
		counter++;
		ArrayList<Product> products = productDAO.findAllProducts();
		//ArrayList<Product> products = productBLL.findAll();
		PDFProduct listing = new PDFProduct(products);
		listing.generate();
		//System.out.println("Lista mea de produse: " + products);
	}
	
	/**
	 * Method reportOrders helps us to generate a pdf with the orders from our database
	 * We need an ArrayList of Order, and to find all orders from the database
	 */
	//Nu am mai folosit metoda
	private void reportOrders() throws FileNotFoundException, DocumentException  {
		ArrayList<Order> orders = orderDAO.findAllOrders();
		ArrayList<Client> clients = clientBLL.findAll();
		PDFOrder listing = new PDFOrder(orders);
		listing.generateReport();
	}
	
	/**
	 * Method reportItems helps us to generate a pdf with the order items from our database
	 * We need an ArrayList of OrderItem, and to find all order items from the database
	 */
	private void reportItems() throws FileNotFoundException, DocumentException  {
		ArrayList<OrderItem> items = orderitemDAO.findAllItems();
		PDFOrderItem listing = new PDFOrderItem(items);
		listing.generateReport();
		
		ArrayList<Client> clients = clientDAO.findAllClients();
		PDFClient listing2 = new PDFClient(clients);
		listing2.generateReport();
	}
	
	/**
	 * Method getBill helps us to generate a pdf 
	 * We need an ArrayList of OrderItems and an object of type Order for generating the bill with what we have in our database
	 * All the commands from the text file should be executed
	 */
	private void getBill() throws FileNotFoundException, DocumentException  {
		Order order=new Order();
		ArrayList<OrderItem> items= new ArrayList<OrderItem>();
		Bill listing = new Bill(order, items);
		listing.generateBill();
	}

	/**
	 * Method readFromFile helps us to find the commands in the text file by certain words
	 * if the buffer will contain the word "Insert" then "client", we will use the insertClient method and so on
	 */
	public void readFromFile(String path) throws DocumentException, IllegalArgumentException, IllegalAccessException
	{
		File file = new File(path);
        String buff;
        try
        {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                buff = scanner.nextLine();
                if(buff.contains("Insert")){ 
                    if(buff.contains("client")){
                        String myDatas = buff.substring(15);
                        String[] x = myDatas.split(", ");
                        insertClient(myDatas);
                    }
                    else if(buff.contains("product")){
                        String myDatas = buff.substring(16);
                        String[] x = myDatas.split(", ");
                        insertProduct(myDatas);
                    }

                } 
                else if(buff.contains("Delete")){ 
                     if(buff.contains("client")){
                        String myDatas = buff.substring(15);
                        //ClientBLL.deleteClient(myDatas);
                        //clientBLL.delete(myDatas);
                        deleteClient(myDatas);
                     }
                     else if(buff.contains("product")){
                        String myDatas = buff.substring(16);
                        deleteProduct(myDatas);
                    }
               } 
               else if(buff.contains("Order")){
                    String myDatas = buff.substring(7);
                    String[] x = myDatas.split(", ");
                    //int quantity = Integer.parseInt(x[2]);
                    //makeOrder(myDatas);
                    insertOrder(myDatas);
                    makeOrder(myDatas);
               } 
               else if(buff.contains("Report")){
                    if(buff.contains("client")){
                    	reportClients();
                    }
                    else if(buff.contains("product")){
                    	reportProducts();
                    	//productBLL.reportProduct();
                    }
                    else if(buff.contains("order")){
                        //reportOrders();
                        reportItems();
                        //getBill();
                    }
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("There is no such file! ");
        }
    }
	
	/**
	 * Main method
	 */
	public static void main(String[] args) throws DocumentException,FileNotFoundException, IllegalArgumentException, IllegalAccessException {
		ReadingFromFile r = new ReadingFromFile();
		if(args.length!=0) {
			r.readFromFile(args[0]);
		}
	}
	
}
