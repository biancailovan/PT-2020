package PT2020.Assignment5;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toMap;

public class Tasks {
	private static List<MonitoredData> listOfActivities;
	
	/*TASK 1*/
	public static void readFromFile() {
		listOfActivities = new ArrayList<MonitoredData>();
		try {
			String file = "Activities.txt";
			Stream<String> stream = Files.lines(Paths.get(file));
			listOfActivities = stream.map(line->line.split("\t\t", -2))
									      .map(a -> new MonitoredData(a[0], a[1], a[2]))
	                                      .collect(Collectors.toList());
			System.out.println("The file contains: ");
			listOfActivities.forEach(System.out::println);
		
		} catch(Exception e) {
			System.out.println("File not found");
			e.printStackTrace();
		}		
		
	}
		
	public static void task_1() {
		try {
			FileWriter fileWriter = new FileWriter("Task_1.txt");
			for(MonitoredData moniData: (ArrayList<MonitoredData>)listOfActivities) {
				fileWriter.write(moniData + "\n");
				moniData.toString();
			}
			fileWriter.close();
			
		} catch (Exception e) {
			e.getCause();
			System.out.println("Could not create Task_1.txt!");
		}
		
	}
	
	public String getDay(String string) {
		try {
			String [] str = string.split(" ");
			String date = str[0];
			String hour = str[1];
			str = date.split("-");
			String month = str[1];
			String day = str[2];
			return day;
		}catch(NumberFormatException ex) {
			ex.getCause();
		}
		
		return null;
	}
	
	public int getUniqueDay(String string) {
		try {
			String [] str = string.split(" ");
			String date = str[0];
			str = date.split("-");
			String month = str[1];
			//System.out.println(month);
			String day = str[2];
			//System.out.println(day);
			return Integer.parseInt(month)*31+Integer.parseInt(day);
		}catch(NumberFormatException ex) {
			ex.getCause();
		}
		
		return 0;
	}
	
	/*TASK 2 1st version*/
	public long countDistinctDays() {
		LocalDateTime str = null;
		List<String> start_time = new ArrayList<String>();
		List<String> end_time = new ArrayList<String>();
		List<Integer> list = new ArrayList<Integer>();
		
		for(MonitoredData moniData: listOfActivities) {
			str = moniData.getStart_time();
			String s = str.toString();
			start_time.add(s);
			
		}
		
		for(String st: start_time){			
			int x = getUniqueDay(st);
			list.add(x);
		}
		
		long count1 = list.stream().distinct().count();
		
		for(MonitoredData moniData: listOfActivities) {
			str = moniData.getEnd_time();
			String s = str.toString();
			end_time.add(s);
			
		}

		for(String st: end_time){			
			int x = getUniqueDay(st);
			list.add(x);
		}
		
		long count2 = list.stream().distinct().count();
		
		long count = count1 + count2;
		System.out.println("Total: "+ count);

		return count;
	}
	
	/*TASK 2*/
	public static long distinctDays() {
		long count = listOfActivities.stream()
				.map(l->l.getStart_timeDayOfTheYear()).distinct().count();
		return count;
	}
	
	public void task_2() {
		System.out.println("\nTASK 2: ");
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			FileWriter fileWriter = new FileWriter("Task_2.txt");		
			fileWriter.write("There are: " + distinctDays() +" distinct days that appear in the monitoring data.\n");
			System.out.println("There are " + distinctDays() + " distinct days that appear in the monitoring data.");
			fileWriter.close();

		} catch(Exception e) {
			e.getCause();
			System.out.println("Could not create Task_2.txt! ");
		}	
	}
	
	
	/*TASK 3*/
	public static Map<String, Integer> countActivities(){
		Map<String,Integer> nrOfActivities = listOfActivities.stream()
				.collect(Collectors.groupingBy(MonitoredData::getActivity_label, Collectors.summingInt(e->1)));
		return nrOfActivities;
	}
	
	public static void task_3() {
		//this.listOfActivities = new ArrayList<MonitoredData>();
		System.out.println("\nTASK 3: ");
		try {
			FileWriter fileWriter = new FileWriter("Task_3.txt");

			for(Map.Entry<String, Integer> entry: countActivities().entrySet()) {
				fileWriter.write(entry.getKey()+ " has appeared "+entry.getValue()+" times\n");
				System.out.println(entry.getKey()+ " has appeared "+entry.getValue()+" times");
			}
			fileWriter.close();
			
		} catch (Exception e) {
			e.getCause();
			System.out.println("Could not create Task_3.txt!");
		}
		
	}
	
	/*TASK 4*/
	public static Map<Integer, Map<String, Integer>> countDailyActivities(){
		Map<Integer, Map<String,Integer>> nrOfActivities = listOfActivities.stream()
				.collect(Collectors.groupingBy(MonitoredData::getStart_timeDayOfTheYear,
				Collectors.groupingBy(MonitoredData::getActivity_label, Collectors.summingInt(e->1))));
		return nrOfActivities;
	}
	
	public static void task_4() {
		//this.listOfActivities = new ArrayList<MonitoredData>();
		System.out.println("\nTASK 4: ");
		try {
			FileWriter fileWriter = new FileWriter("Task_4.txt");

			for(Map.Entry<Integer, Map<String, Integer>> entry : countDailyActivities().entrySet()) {
				fileWriter.write(entry.getKey()+ " has appeared "+entry.getValue()+"\n");
				System.out.println(entry.getKey()+ " has appeared "+entry.getValue());
			}
			fileWriter.close();
			
		} catch (Exception e) {
			e.getCause();
			System.out.println("Could not create Task_4.txt!");
		}
		
	}
	
	/*TASK 5*/
	public static Map<String, Duration> entireDuration(){
		Map<String, Duration> durationOfActivity = listOfActivities.stream()
				.collect(toMap(MonitoredData::getActivity_label, MonitoredData::getDuration, Duration::plus));
		for (MonitoredData moniData: listOfActivities) {
		    durationOfActivity.merge(moniData.getActivity_label(), moniData.getDuration(), Duration::plus);
		}
				
		return durationOfActivity;
	}
	
	public static void task_5() {
		System.out.println("\nTASK 5: ");
		try {
			FileWriter fileWriter = new FileWriter("Task_5.txt");

			for(Map.Entry<String, Duration> entry : entireDuration().entrySet()) {
				fileWriter.write(entry.getKey()+ " has a duration of "+entry.getValue().toHours()/2+" hours and "+entry.getValue().toMinutes()/2+" minutes\n");
				System.out.println(entry.getKey()+ " has a duration of "+entry.getValue().toHours()/2+" hours and "+entry.getValue().toMinutes()/2+" minutes");
			}
			fileWriter.close();
			
		} catch (Exception e) {
			e.getCause();
			System.out.println("Could not create Task_5.txt!");
		}
		
	}
	
	public Tasks() {
		readFromFile();
		task_1();
		task_2();
		task_3();
		task_4();
		task_5();		
	}
	

	public static void main(String[] args) {
		Tasks task = new Tasks(); 			
	}

}
