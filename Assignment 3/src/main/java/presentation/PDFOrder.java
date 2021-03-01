package presentation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.Order;
import model.OrderItem;
import model.Product;

/**
* PDFOrder Class provides a PDF with specific information for an order: 
* id order, id client, name client, total
*/
public class PDFOrder {
	
static ArrayList<Order> orders = new ArrayList<Order>();
	
	private static void addRows(PdfPTable table) {
		for (Order order : orders) {
			table.addCell(Integer.toString(order.getIdorder()));
			table.addCell(order.getNameClient());
			table.addCell(order.getIdclient()+"");
			table.addCell(order.getTotal()+"");
		}
	}
	
	private static int idorder;
	private static int variable = 1;
	
	public PDFOrder(ArrayList<Order> orders) {
		this.orders = orders;
		idorder = variable++;
	}
	
	private static void addTableHeader(PdfPTable table) {
		Stream.of("Id Order", "Id Client","Name client", "Total").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(3);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}
	
	
	public static void generateReport() throws FileNotFoundException, DocumentException {
		Document document = new Document();
		String name = "Order" + Integer.toString(idorder) + ".pdf";
		PdfWriter.getInstance(document, new FileOutputStream(name));
		document.open();
		PdfPTable table = new PdfPTable(4);
		addTableHeader(table);
		addRows(table);
		document.add(table);
		document.close();
	
	}
		
	/*public static void main(String[] args) throws FileNotFoundException, DocumentException {
		ArrayList<Order> orders = new ArrayList<Order>();
		Order order = new Order(2, 2, 15);
		orders.add(order);
		
		PDFOrder ordering = new PDFOrder(orders);
		ordering.generate();

	}*/
	
}
