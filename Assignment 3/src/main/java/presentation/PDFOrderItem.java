package presentation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.Order;
import model.OrderItem;

/**
* PDFOrderItem Class provides a PDF with specific information for an order item
*/
public class PDFOrderItem {
	
	ArrayList<OrderItem> ord = new ArrayList<OrderItem>();
	
	private void addRowsO(PdfPTable table) {
		for (OrderItem orderItem : ord) {
			table.addCell(Integer.toString(orderItem.getIdorder()));
			table.addCell(Integer.toString(orderItem.getIdproduct()));
			table.addCell(Integer.toString(orderItem.getQuantity()));
			
		}
	}
	
	private int idorder;
	private static int variable = 1;
	
	public PDFOrderItem(ArrayList<OrderItem> ord) {
		this.ord = ord;
		idorder = variable++;
	}
	
	private void addTableHeaderO(PdfPTable table) {
		Stream.of("Id Order", "Id Product", "Quantity").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(3);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}
	
	public void generateReport() throws FileNotFoundException, DocumentException {
		Document document = new Document();
		String name = "OrderItem" + Integer.toString(idorder) + ".pdf";
		PdfWriter.getInstance(document, new FileOutputStream(name));
		document.open();
		PdfPTable table = new PdfPTable(3);
		addTableHeaderO(table);
		addRowsO(table);
		document.add(table);
		document.close();
	
	}
	
	/*public static void main(String[] args) throws FileNotFoundException, DocumentException {
		ArrayList<OrderItem> items = new ArrayList<OrderItem>();
		OrderItem item = new OrderItem(1,1,20);
		OrderItem item2 = new OrderItem(2,2,15);
		items.add(item);
		items.add(item2);	
		PDFOrderItem ordering = new PDFOrderItem(items);
		ordering.generateReport();
	}*/

}
