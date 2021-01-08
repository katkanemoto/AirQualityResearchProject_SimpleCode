package purpleAir2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataAnalysis {

	private static ArrayList<Record> array = new ArrayList<Record>();
	private static ArrayList<PurpleAirRecord> purpleArray = new ArrayList<PurpleAirRecord>();

	public static void main(String[] args) {

		//**change these names to the files you are using
		String mesoWestData = "MPOC1.csv";
		String purpleAirData = "mariposa_purpleAir2.csv";
		String outputFile = "mariposaOutput.csv";
		
		Scanner input = null;
		try {
			input = new Scanner(new File(mesoWestData)); //looks for file losBanosData2
		} catch (FileNotFoundException e) {
			System.out.println("file not found"); //if file not found then it is caught and 'file not found' prints
			e.printStackTrace();
		}
		
		Scanner input2 = null;
		try {
			input2 = new Scanner(new File(purpleAirData)); //looks for file
		} catch (FileNotFoundException e) {
			System.out.println("file not found"); //if file not found then it is caught and 'file not found' prints
			e.printStackTrace();
		}
		
		PrintWriter output = null;
		try {
			output = new PrintWriter(outputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//to read in the 8 header lines of the text file
		int count = 0;
		while(count++ < 8) {
			input.nextLine();
			//count++;
		}

		while(input.hasNext())  {
			String line = input.nextLine();
			System.out.println(line);
			String[] fields = line.split(",");
			//public Record(String station, String date, double temp, double humidity, double windSpeed, double windDirection,
			//				String recordAsString) {
			//if(fields.length < 6)
			array.add(new Record(fields[0], 
					fields[1],
					Double.parseDouble(fields[2]),
					Double.parseDouble(fields[3]),
					Double.parseDouble(fields[4]),
					Double.parseDouble(fields[5]),
					line, new PurpleAirRecord()));
		}//end while
		
		input2.nextLine();//skip the first line in the file, the header
		
		while(input2.hasNext())  {
			String line = input2.nextLine();
			String[] fields = line.split(",");
			//public Record(String station, String date, double temp, double humidity, double windSpeed, double windDirection,
			//				String recordAsString) {
			purpleArray.add(new PurpleAirRecord(fields[0], 
					Double.parseDouble(fields[1]),
					Double.parseDouble(fields[2]),
					Double.parseDouble(fields[3]),
					Double.parseDouble(fields[4]),
					Double.parseDouble(fields[5]),
					Double.parseDouble(fields[6]),
					Double.parseDouble(fields[7]),
					Double.parseDouble(fields[8]),
					line));
		}//end while
		
		String[] hoursToLookFor = {"02", "08", "14", "20"};
		//date looks like this "10/30/2020 05:34 UTC", where the hour is at index after first whitespace

		ArrayList<Record> arrayOfRecordsWeNeed = new ArrayList<Record>();
		
		/*Loop that does not skip all the extra with the same hour
		for(int i = 0; i < array.size(); i++) {
			String getHour = array.get(i).getDate().substring(
					array.get(i).getDate().indexOf(' ') + 1, array.get(i).getDate().indexOf(' ') + 3);
			for(int j = 0; j < hoursToLookFor.length; j++)
				if(getHour.equals(hoursToLookFor[j])) 
					arrayOfRecordsWeNeed.add(array.get(i));
		}//end for
		*/
		
		//note that this for loop could do an array out of bounds error if the records we are looking for
		//in the hoursToLookFor array are to close to the end of the file - within 5 records.
		//we could fix this or just work around it for a while
		for(int i = 0; i < array.size(); i++) {
			String getHour = array.get(i).getDate().substring(
					array.get(i).getDate().indexOf(' ') + 1, array.get(i).getDate().indexOf(' ') + 3);
			for(int j = 0; j < hoursToLookFor.length; j++)
				if(getHour.equals(hoursToLookFor[j])) {
					//checking if the wind speed is 0, then use record after this one
					//this also fixes the 0s in wind direction - since they are usually 0 together
					int counter = 0;//check only 5 then get out of this loop
					while(array.get(i).getWindSpeed() == 0 && counter > 0) {
						i++;
						counter++;
					}
					arrayOfRecordsWeNeed.add(array.get(i));//add the first one
					//now skip through all the rest with the same hour
					while(getHour.equals(hoursToLookFor[j]))
						getHour = array.get(++i).getDate().substring(
							array.get(i).getDate().indexOf(' ') + 1, array.get(i).getDate().indexOf(' ') + 3);
				}
		}//end for
		
		//now add in the PurpleAirRecord
		for(Record r: arrayOfRecordsWeNeed)
			for(int i =0; i < purpleArray.size(); i++) {
	
				if(purpleArray.get(i).getMonth() == r.getMonth() && 
						purpleArray.get(i).getDay() == r.getDay() && 
						purpleArray.get(i).getHour() == r.getHour())  {
					//System.out.println("pmonth "+ pMonth + " rMonth " + rMonth + " pDay " + pDay + " rDay " + rDay);
					r.setPurpleAirRecord(purpleArray.get(i));	
					while(purpleArray.get(i).getHour() == r.getHour()) {
						i++;
					}
				}	
			}
			
		//output header on the file
		output.println("Station_ID, Date_Time, air_temp in Fahrenheit,relative_humidity as a %, "
				+ "wind_speed as Miles/Hour, wind_direction as degrees,"
				+ "date,pm1,pm2_5,pm10,upTimeMin,rssi_dbm,temp,humidity,pm2_5atm");
		for(Record r: arrayOfRecordsWeNeed)
			output.println(r);
					
		output.close();

	}//end main
}
