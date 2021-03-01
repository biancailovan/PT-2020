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

import model.Client;
import model.Product;

/**
* PDFProduct Class provides a PDF with specific information for a product: 
* id product, name of the product, price of the product, quantity of the product
*/
public class PDFProduct {
	
static ArrayList<Product> products = new ArrayList<Product>();
	
	private static void addRows(PdfPTable table) {
		for (Product product : products) {
			table.addCell(Integer.toString(product.getIdproduct()));
			table.addCell(product.getNameP());
			table.addCell(Double.toString(product.getPrice()));
			table.addCell(Integer.toString(product.getQuantity()));
		}
	}
	private static int idproduct;
	private static int variable = 1;
	
	public PDFProduct(ArrayList<Product> products) {
		this.products = products;
		idproduct = variable++;
	}
	
	private static void addTableHeader(PdfPTable table) {
		Stream.of("IdProduct", "Name", "Price", "Quantity").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(3);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}
	
	public static void generate() throws FileNotFoundException, DocumentException {
		Document document = new Document();
		String name = "Product" + Integer.toString(idproduct) + ".pdf";
		PdfWriter.getInstance(document, new FileOutputStream(name));
		document.open();
		PdfPTable table = new PdfPTable(4);
		addTableHeader(table);
		addRows(table);
		document.add(table);
		document.close();

	}
	
}
