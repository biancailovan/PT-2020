package PT2020.Assignment2;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class SimulationManager implements Runnable{
	private int numberOfServers;
	private int numberOfClients;
	private int timeLimit=100;
	private int maxProcessingTime=10;
	private int minProcessingTime=2;
	private Scheduler scheduler;
	private int maxArrivalTime;
	private int minArrivalTime;
	private int clientsTime;
	int currentTime=0;
	
	private String inFile;
	private String outFile;
	FileWriter fileWr;
	
	private ArrayList<Server> servers = new ArrayList<Server>();
	private ArrayList<Client> generatedClients = new ArrayList<Client>();
	private BlockingQueue<Client> clients;
	
	
	public int getMaxProcessingTime() {
		return maxProcessingTime;
	}

	public void setMaxProcessingTime(int maxProcessingTime) {
		this.maxProcessingTime = maxProcessingTime;
	}

	public int getMinProcessingTime() {
		return minProcessingTime;
	}

	public void setMinProcessingTime(int minProcessingTime) {
		this.minProcessingTime = minProcessingTime;
	}

	public int getMaxArrivalTime() {
		return maxArrivalTime;
	}

	public void setMaxArrivalTime(int maxArrivalTime) {
		this.maxArrivalTime = maxArrivalTime;
	}

	public int getMinArrivalTime() {
		return minArrivalTime;
	}

	public void setMinArrivalTime(int minArrivalTime) {
		this.minArrivalTime = minArrivalTime;
	}

	public SimulationManager(String args1, String args2){
		
		this.setInFile(args1);
		this.setOutFile(args2);
		System.out.println(System.getProperty("user.dir")+"\\"+getInFile());
		File file=new File(System.getProperty("user.dir")+"\\"+getInFile()); 
		
		Scanner scanner = null;
		try {
			scanner=new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		if(scanner.hasNextInt())
			this.numberOfClients=scanner.nextInt();
		System.out.println("Sunt "+numberOfClients+" clienti");/*imi citeste nr clientilor din fisier*/
		if(scanner.hasNextInt())
			this.numberOfServers=scanner.nextInt();
		System.out.println("Sunt "+numberOfServers+" cozi");
		if(scanner.hasNextInt())
			this.timeLimit=scanner.nextInt();
		System.out.println("Simularea "+timeLimit+" secunde");/*cat dureaza simularea*/
		if(scanner.hasNext()){
			String str1 = scanner.next();String[] str2 = str1.split(",");
			this.minArrivalTime=Integer.parseInt(str2[0]);
			this.maxArrivalTime=Integer.parseInt(str2[1]);
		}
		System.out.println("Min arrival time: "+minArrivalTime);/*timpul minim de asteptare citit din fisier*/
		System.out.println("Max arrival time: "+maxArrivalTime);/*timpul maxim de asteptare citit din fisier*/
		if(scanner.hasNext()){
			String str1 = scanner.next();String[] str2 = str1.split(",");
			this.minProcessingTime=Integer.parseInt(str2[0]);
			this.maxProcessingTime=Integer.parseInt(str2[1]);
		}
		System.out.println("Min processing time: "+minProcessingTime);/*timpul minim de procesare citit din fisier*/
		System.out.println("Max processing time: "+maxProcessingTime);/*timpul maxim de procesare citit din fisier*/
		int periodOfTime=0;
		for(Client c: generatedClients)
			if(c.getArrivalTime()+c.getProcessingTime()>periodOfTime)
				periodOfTime=c.getArrivalTime()+c.getProcessingTime();
		/*pt exemplul din assignment*/
		if(numberOfClients==4) {
			this.generatedClients = generateRandomClients(4);
			generatedClients = new ArrayList<Client>(4);
			generatedClients.add(new Client(1, 2, 2));
			generatedClients.add(new Client(2, 3, 3));
			generatedClients.add(new Client(3, 4, 3));
			generatedClients.add(new Client(4, 10, 2));
		}
		generatedClients = generateRandomClients(50);
		for(Client c: generatedClients)
			if(c.getArrivalTime() + c.getProcessingTime()>periodOfTime)
				periodOfTime=c.getArrivalTime() + c.getProcessingTime();
		this.clientsTime = periodOfTime;
		this.servers = new ArrayList<Server>(numberOfServers);
		initialize();
		}
	
	  /*acesti clienti sunt asezati in lista cu waiting clients de unde vor fi luati si adaugati la coada 
	  cand timpul lor de sosire este acelasi cu timpul real*/ 
	private ArrayList<Client> generateRandomClients(int list) {
		int arrivalTime=0;
		int processingTime=0;
		list=numberOfClients;
		int id=1;
		ArrayList<Client> listCl = new ArrayList<Client>(numberOfClients); 
		while(id<=numberOfClients){
			Random random = new Random();
			arrivalTime = minArrivalTime + random.nextInt(maxArrivalTime-minArrivalTime);
			processingTime = minProcessingTime + random.nextInt(maxProcessingTime-minProcessingTime);
			Client c = new Client(id, arrivalTime, processingTime);
			listCl.add(c);
			id++;
			System.out.println(c.getArrivalTime() + " " + c.getProcessingTime());
		}
		sortClients(listCl);
		return listCl;
	}
	
	public void sortClients( ArrayList<Client> clients){
		Collections.sort(clients);
		for(int i=0;i<clients.size();i++){
			clients.get(i).setId(i+1);
		}
	}
	
	/*initializare cu un anumit numar de clients si servers*/
	private void initialize() {
		this.scheduler = new Scheduler(numberOfServers, numberOfClients);	
		for (int i=1;i<=numberOfServers;i++) {
			Server q = new Server(i);
			scheduler.addServer(q);
		}
	}
	
	/*metoda pentru stergerea unui client dupa ce acesta a stat suficient timp la coada*/
	public void removeClient(ArrayList<Client> generatedClients2) {
		clients.remove(generatedClients2);
	}
	
	public void run() {
		FileWriter fileWr = null;
		try { 
			fileWr = new FileWriter(System.getProperty("user.dir") + "\\" + getOutFile());
			} catch (IOException e1) {
					e1.printStackTrace();
				}
		
		while ((currentTime<=clientsTime)&&currentTime<timeLimit/*&&generatedClients.get(0).getProcessingTime()>0*/){
			System.out.println("Time: " + currentTime);
			Client client=null;
			int cl=0;
			boolean valid=true;
			while(valid==true&&generatedClients.size()>0){
				valid=false;
				if(generatedClients.get(0).getProcessingTime()>0 && currentTime>=generatedClients.get(0).getArrivalTime()){
					client = generatedClients.get(cl);
					System.out.println(client);
					scheduler.timeToProcessClient(client, client.getArrivalTime());
					generatedClients.remove(cl);
					valid=true;
				}
				scheduler.serveClients();
			}
			try {
				fileWr.write("Time: " + currentTime + "\n" + "Waiting clients: " + currentQueueClients(generatedClients) + "\n"+ serversStatus(scheduler.getServers(), currentTime) + "\n");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println("Waiting clients: " + currentQueueClients(generatedClients));
			System.out.println(serversStatus(scheduler.getServers(), currentTime));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			/*pentru a decrementa timpul de procesare pentru fiecare client in parte*/
			for(Server ser: scheduler.getServers()) {
				if(ser.getSize()>0) {
					ser.decre();
				}
			}
			currentTime++;	
		}
		scheduler.stop();
		System.out.println("Average waiting time: "+averageTime(scheduler.getServers()));
		try {
			fileWr.write("Average waiting time: "+averageTime(scheduler.getServers())+"\n");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		try {
			fileWr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String serversStatus(ArrayList<Server> serv, int currentTime) {
		String s="";
		for(int i=0 ;i<serv.size();i++) {
			s=s+serv.get(i).toString(currentTime) + "\n";
		}
		return s;
	}
		
	public int getClientsTime() {
		return clientsTime;
	}

	public void setClientsTime(int clientsTime) {
		this.clientsTime = clientsTime;
	}

	public String getInFile() {
		return inFile;
	}

	public void setInFile(String inFile) {
		this.inFile = inFile;
	}

	public String getOutFile() {
		return outFile;
	}

	public void setOutFile(String outFile) {
		this.outFile = outFile;
	}
	
	public String currentQueueClients(ArrayList<Client> client) {
		String s="";
		for(Client c: client)
			s = s +c.toString();
		return s;
	}
	
	public float averageTime(ArrayList<Server> serv) {
		int average = 0;
		for(Server server: serv) {
			average=average+server.getWaitingPeriod().get();
		}
		float result = (float)average/numberOfClients;
		return result;
	}

	public static void main(String[] args) {
		SimulationManager gen = new SimulationManager(args[0], args[1]);
		Thread t = new Thread(gen);
		t.start();
	}

}
