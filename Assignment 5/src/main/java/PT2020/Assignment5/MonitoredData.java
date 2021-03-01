package PT2020.Assignment5;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MonitoredData {
	
	private LocalDateTime start_time;
	private LocalDateTime end_time;
	private String activity_label;
	
	public MonitoredData() {}
	
	public MonitoredData(LocalDateTime start_time, LocalDateTime end_time, String activity_label) {
		// TODO Auto-generated constructor stub
		this.start_time = start_time;
		this.end_time = end_time;
		this.activity_label = activity_label;
	}
	
	public MonitoredData(String start_time, String end_time, String activity_label) {
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		this.start_time = LocalDateTime.parse(start_time, date);
		this.end_time = LocalDateTime.parse(end_time, date);
		this.activity_label = activity_label;
	}
	
	List <MonitoredData> listOfObj = new ArrayList<MonitoredData>();

	public List<MonitoredData> getListOfObj() {
		return listOfObj;
	}

	public void setListOfObj(List<MonitoredData> listOfObj) {
		this.listOfObj = listOfObj;
	}
	
	public int getStart_timeDayOfTheYear() {
		return start_time.getDayOfYear();
	}

	@Override
	public String toString() {
		return start_time+"\t\t"+end_time+"\t\t"+activity_label;
	}

	public LocalDateTime getStart_time() {
		return start_time;
	}

	public void setStart_time(LocalDateTime start_time) {
		this.start_time = start_time;
	}

	public LocalDateTime getEnd_time() {
		return end_time;
	}

	public void setEnd_time(LocalDateTime end_time) {
		this.end_time = end_time;
	}

	public String getActivity_label() {
		return activity_label;
	}

	public void setActivity_label(String activity_label) {
		this.activity_label = activity_label;
	}
	
	public Duration getDuration() {
		return Duration.between(start_time, end_time);
		
	}
	
	/**
	 * @param args
	 * @throws IOException
	 */
	/*public static void main(String[] args) throws IOException {
		MonitoredData md = new MonitoredData(getStart_time(), getEnd_time(), getActivity_label());
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		List<MonitoredData> objects = new ArrayList<>(); 
		
		Tasks task2 = new Tasks();
		String file = "Activities.txt";
		ArrayList<MonitoredData> moniData = new ArrayList<MonitoredData>();
		//md.readFile();
		//moniData = task2.createListOfObjects();
		//task2.distinctDays(moniData);
		

		try (Stream<String> stream = Files.lines(Paths.get("Activities.txt"),Charset.defaultCharset())) {
			PrintWriter printWriter = new PrintWriter("Task_1.txt");
            stream.forEach(System.out::println);
         
            md.readFile();
            moniData = md.readFile();
            md.getListOfObj();
            md.setListOfObj(moniData);
            moniData = task2.createListOfObjects();
 
 
		}		
		
	}*/
	
	
	
}
