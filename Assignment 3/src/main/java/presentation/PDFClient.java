package presentation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.Client;
import model.OrderItem;

/**
* PDFClient Class provides a PDF with specific information for a client: hid id, his name, his address
*/
public class PDFClient {
	
	ArrayList<Client> clients = new ArrayList<Client>();
	
	private void addRows(PdfPTable table) {
		if(clients!=null) {
		for (Client client: clients) {
			table.addCell(Integer.toString(client.getIdclient()));
			table.addCell(client.getName());
			table.addCell(client.getAddress());
		}}
	}
	private static int idclient;
	private static int variable = 1;
	
	public PDFClient(ArrayList<Client> clients) {
		this.clients = clients;
		idclient = variable++;
	}
	
	
	private void addTableHeader(PdfPTable table) {
		Stream.of("IdClient", "Name", "Address").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(3);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}
	
	public void generateReport() throws FileNotFoundException, DocumentException {
		Document document = new Document();
		String name = "Client" + Integer.toString(idclient) + ".pdf";
		PdfWriter.getInstance(document, new FileOutputStream(name));
		document.open();
		PdfPTable table = new PdfPTable(3);
		addTableHeader(table);
		addRows(table);
		document.add(table);
		document.close();

	}
	
/*public static void main(String[] args) throws FileNotFoundException, DocumentException {
		
		ArrayList<Client> clients = new ArrayList<Client>();
		Client client = new Client(1,"Popescu Ion","Bucuresti");
		clients.add(client);
		
		PDFClient listing = new PDFClient(clients);
		listing.generateReport();

	}*/

	    
}
