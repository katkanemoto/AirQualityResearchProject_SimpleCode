package purpleAir2020;
/*A file will have these fields:
 * Station_ID, TS623
 * Date_Time, 10/30/2020 05:34 UTC
 * air_temp_set_1 in Fahrenheit
 * relative_humidity_set_1 as a %
 * wind_speed_set_1 as Miles/Hour
 * wind_direction_set_1 as degrees - sometimes this field is blank
 */

public class Record {
	
	//data variable
	private String station;
	private String date;
	private double temp;
	private double humidity;
	private double windSpeed;
	private double windDirection;
	private String recordAsString;
	private PurpleAirRecord purpleAirRecord;
	
	public Record(String station, String date, double temp, double humidity, double windSpeed, double windDirection,
			String recordAsString, PurpleAirRecord purpleAirRecord) {
		super();
		this.station = station;
		this.date = date;
		this.temp = temp;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.recordAsString = recordAsString;
		this.purpleAirRecord = purpleAirRecord;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getDate() {
		return date;
	}
	
	public int getMonth() {
		String part = date.substring(0,date.indexOf(' '));
		String split[] = part.split("/");
		return Integer.parseInt(split[0]);
	}
	
	public int getDay() {
		String part = date.substring(0,date.indexOf(' '));
		String split[] = part.split("/");
		return Integer.parseInt(split[1]);
	}
	
	public int getYear() {
		String part = date.substring(0,date.indexOf(' '));
		String split[] = part.split("/");
		return Integer.parseInt(split[2]);
	}
	
	public int getHour() {
		String part[] = date.split(" ");
		String split[] = part[1].split(":");
		return Integer.parseInt(split[0]);
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public double getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(double windDirection) {
		this.windDirection = windDirection;
	}

	public String getRecordAsString() {
		return recordAsString + "," + purpleAirRecord.toString();
	}

	public void setRecordAsString(String recordAsString) {
		this.recordAsString = recordAsString;
	}

	public PurpleAirRecord getPurpleAirRecord() {
		return purpleAirRecord;
	}

	public void setPurpleAirRecord(PurpleAirRecord purpleAirRecord) {
		this.purpleAirRecord = purpleAirRecord;
	}

	@Override
	public String toString() {
		return getRecordAsString();
	}
}
