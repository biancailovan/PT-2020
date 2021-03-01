package PT2020.Assignment2;

import java.awt.List;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Scheduler {
	
	private ArrayList <Server> servers;
	private int maxNoServers;
	private int maxClientsPerServer;
	
	public Scheduler(int maxNoServers, int maxClientsPerServer) {
		super();
		//this.servers = servers;
		this.maxNoServers = maxNoServers;
		servers = new ArrayList<Server>(maxNoServers);
		this.maxClientsPerServer = maxClientsPerServer;
	}
	
	/*public void changeStrategy(SelectionPolicy policy) {
		if(policy==SelectionPolicy.SHORTEST_QUEUE) {
			strategy = new ConcreteStrategyQueue();
		}
		
		if(policy==SelectionPolicy.SHORTEST_TIME) {
			strategy = new ConcreteStrategyTime();
		}
	}*/

	public ArrayList<Server> getServers() {
		return servers;
	}

	public void setServers(ArrayList<Server> servers) {
		this.servers = servers;
	}

	public int getMaxNoServers() {
		return maxNoServers;
	}

	public void setMaxNoServers(int maxNoServers) {
		this.maxNoServers = maxNoServers;
	}

	public int getMaxClientsPerServer() {
		return maxClientsPerServer;
	}

	public void setMaxClientsPerServer(int maxClientsPerServer) {
		this.maxClientsPerServer = maxClientsPerServer;
	}
	
	/*adaugare server*/
	public void addServer(Server server) {
		servers.add(server);
	}
	
	/*public void processClient ()  {
		int totalClients=0;
        Client client;
        client = clients.peek();
        totalClients ++;

        System.out.println("--PROCESSING-- " + client.getId() + " having Service Time " + client.getProcessingTime() + " seconds");

        try {
            TimeUnit.SECONDS.sleep(client.getProcessingTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clients.remove();
        System.out.println("--EXIT-- " + client.getId() + " has finished and quits ");

    }*/
	
	 public int getServiceTimeOfQueue() {
	        int processTime = 0;
	        for (Client client : clients)
	            processTime=processTime+client.getProcessingTime();
	        return processTime;
	 }
	 
	 public int getSize() {
	        return clients.size();
	 }

	/*pentru a trimite clientul la coada*/
	public void timeToProcessClient(Client client, int arrivalTime) {	
		Server serv = null;
		int minim=100000;
		for(int i=0;i<servers.size();i++) {
			
				if(servers.get(i).getSize()< minim && !servers.get(i).isClosed()){
				minim = servers.get(i).getSize();
				serv=servers.get(i);
			}
				else if(servers.get(i).isClosed()) {
					serv=servers.get(i);
				}
			
			servers.get(i).setClosed(false);
		}
		serv.addClient(client);
	}
	
	public void serveClients() {
		for(Server server: servers) {
			Thread serverThread = new Thread(server);
			serverThread.start();
		}
	}
	
	private LinkedBlockingQueue<Client> clients = new LinkedBlockingQueue<Client>();
	public String showQueue() {
        String s = new String("");
        for (Client cl : clients)
            s += " " + cl.toString() + " | ST: "+cl.getProcessingTime()+" ";
        return s;
    }
	
	public void stop() {
		for(Server server: servers) {
			server.setClosed(false);
		}
		
	}	
	
}
