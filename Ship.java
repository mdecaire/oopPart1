/**
 * File Name: Ship.java
 * Date: 3/25/2018
 * Author: Michelle Decaire
 * Purpose: to define a parent ship class and hold 
 * all jobs attached to the ship. Perfoms searches on
 * the jobs arraylist.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Ship extends Thing {
	private PortTime arrivalTime;
	private PortTime dockTime;
	private double weight;
	private double length;
	private double width;
	private double draft;
	private ArrayList<Job> jobs = new ArrayList<Job>();
	private boolean amPassenger = false;

	Ship() {}

	Ship(Scanner sc) {
		super(sc);

		if (sc.hasNextDouble())
			this.setWeight(sc.nextDouble());
		if (sc.hasNextDouble())
			this.setLength(sc.nextDouble());
		if (sc.hasNextDouble())
			this.setWidth(sc.nextDouble());
		if (sc.hasNextDouble())
			this.setDraft(sc.nextDouble());

	}

	// established whether it is of type passenger or cargo
	public void setWhetherPassenger(boolean amPass) {
		this.amPassenger = amPass;
	}

	public boolean getWhetherPassenger() {
		return amPassenger;
	}

	// getters and setters
	public void setArrivalTime(PortTime arrTime) {
		this.arrivalTime = arrTime;
	}

	public PortTime getArrivalTime(PortTime arrTime) {
		return arrivalTime;
	}

	public void setDockTime() {
		dockTime = new PortTime();
	}

	public PortTime getDockTime() {
		return dockTime;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getDraft() {
		return draft;
	}

	public void setDraft(double draft) {
		this.draft = draft;
	}

	// adds jobs to list
	public void addJob(Job j) {
		jobs.add(j);
	}

	public String toString() {
		return super.toString() + "\n";
	}

	// returns a list of jobs belonging to this ship
	public String getJobs() {
		String job = "";
		if (jobs.isEmpty()) {
			return "";
		} else {
			for (Job j : jobs) {
				job += "> " + j;
			}
		}

		return job;
	}

	// searches for a job by the name to report back to port
	public String jobByName(String text) {
		String nameResult = "";
		for (Job j : jobs) {
			if (j.name.equalsIgnoreCase(text)) {
				nameResult += j;
			}
		}
		return nameResult;
	}

	// searches for a job by the index to report back to port
	public String jobByIndex(int num) {
		String indexResult = "";
		for (Job j : jobs) {
			if (j.index == num) {
				indexResult += j;
			}
		}
		return indexResult;
	}

}
