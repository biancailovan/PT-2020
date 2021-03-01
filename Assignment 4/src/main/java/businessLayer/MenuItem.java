package businessLayer;

/*clasa abstracta*/
public abstract class MenuItem {

	/*metode pe care le vor implementa si clasele
	 * BaseProduct si CompositeProduct*/
	public abstract double computePrice();
	public abstract String getNameP();
	
}
