package businessLayer;

public class BaseProduct extends MenuItem {
	
	private String nameP; /*numele base product-ului*/
	private double price; /*pret base product*/
	private String name;
	
	public BaseProduct(String nameP, double price) {
		super();
		this.nameP = nameP;
		this.price = price;
	}
	
	public BaseProduct() {
		// TODO Auto-generated constructor stub
	}

	public String getNameP() {
		return nameP;
	}
	public String getName() {
		return name;
	}

	public void setNameP(String nameP) {
		this.nameP = nameP;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public double computePrice(){
		return price;
	}
	
	@Override
	public String toString() {
		return "BaseProduct [nameP=" + nameP + ", price=" + price + "]";
	}

}
