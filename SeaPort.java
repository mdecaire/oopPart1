/**
 * File Name: SeaPort.java
 * Date Due: 3/25/2018
 * Author: Michelle DeCaire
 * Purpose: To represent its segment of the world
 * it holds list of docks,  ships, persons and que. 
 * It is responsible for most searches since it owns the list.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class SeaPort extends Thing {
	ArrayList<Dock> docks = new ArrayList<Dock>();
	ArrayList<Ship> que = new ArrayList<Ship>();
	ArrayList<Ship> ships = new ArrayList<Ship>();
	ArrayList<Person> persons = new ArrayList<Person>();

	SeaPort(Scanner sc) {
		super(sc);

	}

	// adds dock to list
	public void addDock(Dock dock) {
		docks.add(dock);
	}

	// adds person to list
	public void addPerson(Person p) {
		persons.add(p);
	}

	// returns a list of ships to world
	public String getShips() {
		String shipList = "\n--- List of all ships: \n";
		for (Ship s : ships) {
			if (ships.isEmpty()) {
				shipList += "None Listed";
			} else {
				shipList += " > " + s;
			}
		}
		return shipList;
	}

	// list of docks for this port
	public String getDocks() {
		String dockList = "";
		for (Dock d : docks) {
			if (docks.isEmpty()) {
				dockList += "None Listed\n";
			} else {
				dockList += "\n " + d;
			}
		}
		return dockList;
	}

	// the list of ships in que for this port
	public String getQue() {
		String queList = "\n--- List of all ships in Que: \n";
		if (que.isEmpty()|| que.equals(null)) {
			queList += "None Listed";
		}
		for (Ship qShips : que) {
			
			queList += " > " + qShips;

		}
		return queList;
	}

	// a list of persons for this port
	public String getPersons() {
		String pList = "\n--- List of all Persons: \n";
		if (persons.equals(null) || persons.isEmpty()) {
			pList += "No personnel listed \n";
		}
		for (Person p : persons) {

			pList += " > " + p;

		}
		return pList;
	}

	// list of jobs for this port
	public String getJobs() {
		String jobList = "";
		String endProduct = "\n--- List of all Jobs: \n";
		for (Ship sh : ships) {
			String shipJob = sh.getJobs();
			if (shipJob.isEmpty()) {
				jobList += "";
			} else {

				jobList += shipJob;
			}

		}
		if (jobList.isEmpty()) {
			endProduct += "No Jobs Listed.";
		} else {
			endProduct += jobList;
		}
		return endProduct;
	}

	// searches for given name in this port
	public String searchForName(String text) {
		String nameResult = "";
		for (Dock d : docks) {
			if (d.name.equalsIgnoreCase(text)) {
				nameResult += d;
			}
		}
		for (Ship s : ships) {
			if (s.name.equalsIgnoreCase(text)) {
				nameResult += s;
			}
			nameResult += s.jobByName(text);
		}
		for (Person p : persons) {
			if (p.name.equalsIgnoreCase(text)) {
				nameResult += p;
			}
		}

		return nameResult;
	}

	// searches by index in this port
	public String searchbyIndex(int num) {
		String indexResult = "";
		for (Dock d : docks) {
			if (d.index == num) {
				indexResult += d;
			}
		}
		for (Ship s : ships) {
			if (s.index == num) {
				indexResult += s;
			}
			indexResult += s.jobByIndex(num);
		}
		for (Person p : persons) {
			if (p.index == num) {
				indexResult += p;
			}
		}

		return indexResult;
	}

	// searches for skills in this port
	public String searchForSkill(String text) {
		String skill = "";
		for (Person p : persons) {
			if (p.getSkill().equalsIgnoreCase(text)) {
				skill += p;
			}
		}
		return skill;
	}

	// searches by weight in this port
	public String searchByWeight(double wgt) {
		String shipByLB = "";
		for (Ship s : ships) {
			if (s.getWeight() >= wgt) {
				shipByLB += s+ "Weight: "+s.getWeight()+"\n";
			}
		}
		return shipByLB;
	}

	// searches by length in this port
	public String searchByLength(double len) {
		String shipByLength = "";
		for (Ship s : ships) {
			if (s.getLength() >= len) {
				shipByLength += s+ "Length: "+s.getLength()+"\n";
			}
		}
		return shipByLength;
	}

	// searches by minimum passenger at this port
	public String findShipsWithMinimumPassengers(int numPass) {
		String shipByPassenger = "";
		for (Ship s : ships) {
			if (s.getWhetherPassenger()) {
				if (((PassengerShip) s).getNumPassengers() >= numPass) {
					shipByPassenger += s + "Number of Passengers: " + (((PassengerShip) s)).getNumPassengers() + "\n\n";
				}
			}
		}
		return shipByPassenger;
	}

	// all attributes of this port class
	public String toString() {
		String allData = "";
		allData += "SeaPort: " + super.toString() + "\n" + getDocks() + getQue() + getShips() + getPersons()
				+ getJobs();
		return allData;
	}

	// just the name and index of this port.
	public String toString(Boolean needPortList) {
		return "SeaPort: " + super.toString() + "\n";
	}
}
