package PT2020.Assignment2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
	
	private BlockingQueue<Client> clients;
	private AtomicInteger waitingPeriod;
	private AtomicInteger numberOfClientsServed = new AtomicInteger();
	private boolean closed;
	public int number;
	private int index;
	
	
	public boolean isClosed() {
		closed=false;
		return closed;
	}
	
	public void setClosed(boolean closed) {
		this.closed=closed;
	}
	
	
	public Server(int index) {
		/*initialize queue and waitingPeriod*/
		this.index=index;
		clients = new LinkedBlockingQueue<Client>();
		waitingPeriod = new AtomicInteger(0);
		this.closed=false;
		this.number=0;

	}
	
	public void addClient(Client newClient) {
		/*add client to queue*/
		/*increment the waiting period*/
		clients.add(newClient);
		waitingPeriod.addAndGet(newClient.getProcessingTime());
			
	}
	
	/*metoda pentru stergerea unui client dupa ce acesta a stat suficient timp la coada*/
	public void removeClient(Client exClient) {
		clients.remove(exClient);
	}
	
	public int getSize() {
		return clients.size();/*coada de clienti*/
	}

	/*returneaza coada*/
	public BlockingQueue<Client> getClients() {
		return clients;
	}

	public void setClients(BlockingQueue<Client> clients) {
		this.clients = clients;
	}

	public AtomicInteger getWaitingPeriod() {
		return waitingPeriod;
	}

	public void setWaitingPeriod(AtomicInteger waitingPeriod) {
		this.waitingPeriod = waitingPeriod;
	}
	
	public Client getFirstClient()
	{
		Client client = getClientsArray()[0];
		return client;
	}
	
	public  Client[] getClientsArray() {
		Client[] clientsArray = new Client[clients.size()];
		clients.toArray(clientsArray);
		return clientsArray;
	}
	
	public void decrementClients() {
		this.number-=1;
	}
	
	public void incrementClients() {
		this.number+=1;
	}
	
	public int getNumber() {
		return number;
	}
	
	
	public void processClient ()  {
		int totalClients=0;
        Client client;
        client = clients.peek();
        //averageTime += client.getProcessingTime();
        totalClients ++;

        System.out.println("--PROCESSING-- " + client.getId() + " having Service Time " + client.getProcessingTime() + " seconds");

        try {
            TimeUnit.SECONDS.sleep(client.getProcessingTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clients.remove();
        System.out.println("--EXIT-- " + client.getId() + " has finished and quits "+index);

    }
	
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			/*take next client from queue*/
			/*stop the thread*/
			/*decrement the waitingPeriod*/
			//BlockingQueue<Client> c =this.getClients();
			if(clients.isEmpty())/*daca coada de clienti e goala sleep pt 1000s*/
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			else {
				Client c = clients.peek();
				try {
					Thread.sleep(c.getProcessingTime()*1000);
					this.closed=true;
					clients.remove(c);
					c=null;
					//removeClient(c);
					//decrementClients();
				}catch(InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		
	}
	
	public void decrementWaitingPeriod(AtomicInteger waitingPeriod) {
		if(waitingPeriod.get()!=0) {
			waitingPeriod.decrementAndGet();
		}
		
	}
	
	/*pentru a decrementa timpul de porcesare al clientilor in momentul in care acestia se afla in coada*/
	public void decre() {
		for(Client c: clients) {
			if(c.getArrivalTime()>0) {
				c.setProcessingTime(c.getProcessingTime()-1);
			}
		}
	}
	
	
	public String toString(int compare){
		String s = "Queue " + index + ": ";
		if(clients.isEmpty())
			return s + "closed";
		for(Client c: clients){
			if(c.getArrivalTime()>compare)
				c.setProcessingTime(c.getProcessingTime()-1);
			s += c.toString() + ";"; 
		}
		return s;
	}

}
