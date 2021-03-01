package PT2020.Assignment2;

public class Client implements Comparable<Client> {
	
	private int id;/*each client is recognized by a unique id*/
	private int arrivalTime;/*simulation time when they are ready to go to the queue*/
	private int processingTime;/*time interval needed to serve the client*/
	
	public Client(int id, int arrivalTime, int processingTime) {
		super();
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.processingTime = processingTime;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;/*arrival time*/
	}
	
	public int getProcessingTime() {
		return processingTime;
	}
	
	public void setProcessingTime(int processingTime) {
		this.processingTime = processingTime; /* processing time*/
	}
	
	@Override
	public String toString() {
		return "Client (id=" + id + ", arrivalTime=" + arrivalTime + ", processingTime=" + processingTime + ")";
	}

	public int compareTo(Client o) {
		// TODO Auto-generated method stub
		return arrivalTime - o.getArrivalTime();
	}
	

}
