/**
 * File Name: PortTime.java
 * Date Due: 3/25/2018
 * author: Michelle Decaire
 * Purpose: to establish time stamps of world and ships.
 */
public class PortTime {
		long  time;
		//gets the current computer time when a new time is called
		public PortTime(){
			time= (System.currentTimeMillis()/1000);
		}
		
		//converts time to human readable format.
	public String toString() {
		String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (time*1000));
		return date;
		}
	}
