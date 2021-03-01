package businessLayer;

public interface Observable<T> {

	/*method for adding an observer*/
	public void addObserver(T o);
	/*method for notifing an observer*/
	public void notifyObserver();
	/*method for deleting an observer*/
	public void deleteObserver(T o);
	
}
