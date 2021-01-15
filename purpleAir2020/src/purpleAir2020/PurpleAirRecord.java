//this class is used to create objects for the records of data we are reading in from
//
package purpleAir2020;

public class PurpleAirRecord {
	/*created_at	, 2020-11-19 23:50:00 UTC
	 * PM1.0_CF1_ug/m3	
	 * PM2.5_CF1_ug/m3	
	 * PM10.0_CF1_ug/m3	
	 * UptimeMinutes	
	 * RSSI_dbm	
	 * Temperature_F	
	 * Humidity_%	
	 * PM2.5_ATM_ug/m3
	 * */
	private String date;
	private double pm1;
	private double pm2_5;
	private double pm10;
	private double uptimeMin;
	private double rssi_dbm;
	private double temp;
	private double humidity;
	private double pm2_5_atm;
	private String line;
	
	public PurpleAirRecord() {
		line = "no record";
	}
	
	public PurpleAirRecord(String date, double pm1, double pm2_5, double pm10, double uptimeMin, double rssi_dbm,
			double temp, double humidity, double pm2_5_atm, String line) {
		super();
		this.date = date;
		this.pm1 = pm1;
		this.pm2_5 = pm2_5;
		this.pm10 = pm10;
		this.uptimeMin = uptimeMin;
		this.rssi_dbm = rssi_dbm;
		this.temp = temp;
		this.humidity = humidity;
		this.pm2_5_atm = pm2_5_atm;
		this.line = line;
	}

	public String getDate() {
		return date;
	}
	
	public int getMonth() {
		String part = date.substring(0,date.indexOf(' '));
		String split[] = part.split("-");
		return Integer.parseInt(split[1]);
	}
	
	public int getDay() {
		String part = date.substring(0,date.indexOf(' '));
		String split[] = part.split("-");
		return Integer.parseInt(split[2]);
	}
	
	public int getYear() {
		String part = date.substring(0,date.indexOf(' '));
		String split[] = part.split("-");
		return Integer.parseInt(split[0]);
	}
	
	public int getHour() {
		String part[] = date.split(" ");
		String split[] = part[1].split(":");
		return Integer.parseInt(split[0]);
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getPm1() {
		return pm1;
	}

	public void setPm1(double pm1) {
		this.pm1 = pm1;
	}

	public double getPm2_5() {
		return pm2_5;
	}

	public void setPm2_5(double pm2_5) {
		this.pm2_5 = pm2_5;
	}

	public double getPm10() {
		return pm10;
	}

	public void setPm10(double pm10) {
		this.pm10 = pm10;
	}

	public double getUptimeMin() {
		return uptimeMin;
	}

	public void setUptimeMin(double uptimeMin) {
		this.uptimeMin = uptimeMin;
	}

	public double getRssi_dbm() {
		return rssi_dbm;
	}

	public void setRssi_dbm(double rssi_dbm) {
		this.rssi_dbm = rssi_dbm;
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

	public double getPm2_5_atm() {
		return pm2_5_atm;
	}

	public void setPm2_5_atm(double pm2_5_atm) {
		this.pm2_5_atm = pm2_5_atm;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Override
	public String toString() {
		//"date,pm1,pm2_5,pm10,upTimeMin,rssi_dbm,temp,humidity,pm2_5atm
		return ""+ date + "," + pm1 + "," + pm2_5 + "," + pm10 + ","
				+ uptimeMin + "," + rssi_dbm + "," + temp + "," + humidity + ","
				+ pm2_5_atm;
	}
	
}
