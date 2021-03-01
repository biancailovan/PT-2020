package dataLayer;

import java.io.FileWriter;

public class FileWrite {
	
	public void writer(String file) {
		try {
			FileWriter fileWriter=new FileWriter("bill.txt");
			fileWriter.write(file);
			fileWriter.close();
		}catch(Exception e) {
			System.out.println("Cannot write file! ");
		}
	}

}
