package presentation;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.Client;
import model.Order;
import model.OrderItem;

/**
* Bill Class provides a bill for a client
*/
public class Bill {
	
	Order order=new Order();
	ArrayList<OrderItem> items= new ArrayList<OrderItem>();
	
	public Bill(Order ord, ArrayList<OrderItem> items) {
		this.order=ord;
		this.items=items;

	}
	
	private void addRows(PdfPTable table) {
		for (OrderItem items : items) {
			table.addCell(Integer.toString(items.getIdproduct()));
			table.addCell(Integer.toString(items.getQuantity()));
		}
	}
	
	private void addTableHeader(PdfPTable table) {
		Stream.of("ID product", "Product Quantity").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(3);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}
	
	public void generateBill() throws FileNotFoundException, DocumentException {
		Document document = new Document();
		String name = "Bill for client " + order.getIdclient() + ".pdf";
		PdfWriter.getInstance(document, new FileOutputStream(name));
		PdfPTable table = new PdfPTable(2);
		table.setSpacingBefore(20f);
		table.setSpacingAfter(20f);
		addTableHeader(table);
		addRows(table);
		document.open();
		document.add(table);
		com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk("The client " + order.getIdclient() + " has bought products of " + order.getTotal() + " €.",font);
		document.add(new Paragraph("\n\n"));
		document.add(chunk);
		document.close();
	}
	
}
