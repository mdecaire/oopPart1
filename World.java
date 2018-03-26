/**
 * FileName: World.java
 * Date Due: 3/25/2018
 * Author: Michelle Decaire
 * Purpose: To take the file and build a world of ports that hold docks ships and jobs;
 * docks that hold ships; and ships that hold people.
 * After building this world it has the capability to search for special types of searches and structures
 * Note: This class is closely tied to the port class and usually iterates through a list of ports to find 
 * structure information.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class World extends Thing {

	private Scanner sc = null;
	private ArrayList<SeaPort> ports = new ArrayList<SeaPort>();
	private PortTime time;
	private SeaPort port = null;
	private Dock dock = null;
	private PassengerShip pShip;
	private CargoShip cShip;
	private Person employee;
	private Job job;

	//sets time when new instance is made
	public World() {
		time = new PortTime();
	}

	public World(Scanner sc) {
		super(sc);
	}

	// reads the file, creates a scanner of one line to pass to each class
	// instantiation.
	public void processFile(File file) {
		try {
			sc = new Scanner(new FileReader(file));

			while (sc.hasNextLine()) {
				String type = sc.next();
				Scanner scan = new Scanner(sc.nextLine());
				if (type.equals("//") || type.isEmpty()) {
					continue;
				} else {

					createObjects(type, scan);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			return;
		}
		sc.close();
		return;
	}

	// instantiates each class in the file. the case statement
	// represents each type of class available
	private void createObjects(String type, Scanner sc2) {

		switch (type) {
		case "port":
			port = new SeaPort(sc2);
			ports.add(port);
			break;
		case "dock":
			dock = new Dock(sc2);
			addDock(dock);
			break;
		case "pship":
			pShip = new PassengerShip(sc2);
			pShip.setWhetherPassenger(true);
			assignShip((Ship) pShip);
			break;
		case "cship":
			cShip = new CargoShip(sc2);
			cShip.setWhetherPassenger(false);
			assignShip((Ship) cShip);
			break;
		case "person":
			employee = new Person(sc2);
			addPerson(employee);
			break;
		case "job":
			job = new Job(sc2);
			addJob(job);
			break;
		default:
			break;

		}
	}

	// determine which port the dock belongs to.
	private void addDock(Dock dock2) {
		SeaPort p = findPortByIndex(dock2.parent);
		if (p != null) {
			p.addDock(dock2);
		}
	}

	// determines which port the person belongs to.
	private void addPerson(Person employee2) {
		SeaPort p = findPortByIndex(employee2.parent);
		if (p != null) {
			p.addPerson(employee2);
		}

	}

	// helper method to iterate through and actually find the port
	private SeaPort findPortByIndex(int index) {
		for (SeaPort p : ports) {
			if (index == p.index) {
				return p;
			}
		}
		return null;
	}

	// find which ship the job belongs to.
	private void addJob(Job job2) {
		Ship myShip = getShipByIndex(job2.parent);
		if (myShip != null) {
			myShip.addJob(job2);
		}

	}

	// helper method to find and return ship
	private Ship getShipByIndex(int parent) {
		for (SeaPort p : ports) {
			for (Ship s : p.ships) {
				if (parent == s.index || parent == s.parent) {
					return s;
				}
			}
		}
		return null;
	}

	// assigns ships to the correct dock and or list by
	// adding it to the que if it does not belong to a dock.
	public void assignShip(Ship myShip) {
		myShip.setArrivalTime(time);
		Dock myDock = getDockByIndex(myShip.parent);
		SeaPort port = findPortByIndex(myShip.parent);
		if (myDock == null) {
			port.ships.add(myShip);
			port.que.add(myShip);
			return;
		} else {
			port = findPortByIndex(myDock.parent);
			myDock.setShip(myShip);
			myShip.setDockTime();
			port.ships.add(myShip);
		}

	}

	// helper method that returns the dock whose index matches the parent.
	private Dock getDockByIndex(int parent) {
		Dock matchingDock = null;
		for (SeaPort p : ports) {
			for (Dock d : p.docks) {
				if (d.index == parent) {
					matchingDock = d;
					return matchingDock;
				}
			}
		}

		return matchingDock;
	}

	// returns a list of ships from each port
	public String getShips() {
		String ships = "";
		for (SeaPort pt : ports) {
			ships += "\n--- Port: " + pt.name + ": \n";
			ships += pt.getShips();
		}
		return ships;
	}

	// returns a list of ports
	public String getPorts() {
		String portList = "";
		for (SeaPort p : ports) {
			portList += p.toString(true);
		}
		return portList;
	}

	// returns the list of ships in que as string for each port
	public String getQue() {
		String qList = "";
		for (SeaPort p : ports) {
			qList += "Port: " + p.name + "\n" + p.getQue() + "\n";
		}
		return qList;
	}

	// returns a list of persons for each port
	public String getPerson() {
		String personList = "";
		for (SeaPort p : ports) {
			personList += "Port: " + p.name + "\n" + p.getPersons() + "\n";
		}
		return personList;
	}

	// returns a list of docks for each port
	public String getDock() {
		String dockList = "";
		for (SeaPort p : ports) {
			dockList += "Port: " + p.name + "\n" + p.getDocks() + "\n";
		}
		return dockList;
	}

	// returns a list of jobs for each port
	public String getJob() {
		String jobList = "";
		for (SeaPort p : ports) {
			jobList += "Port: " + p.name + "\n" + p.getJobs() + "\n";
		}
		return jobList;
	}

	// returns the results for a name search
	public String searchName(String text) {
		String resultForName = "";
		String tempString = "";
		for (SeaPort p : ports) {
			if (p.name.equalsIgnoreCase(text)) {
				resultForName += p;
				return resultForName;
			} else {
				tempString += p.searchForName(text);
				if (!tempString.isEmpty()) {
					resultForName += "\n Port:" + p.name + ":\n" + tempString;
					tempString = "";
				}
			}
		}

		return resultForName;
	}

	// returns the results of an index search
	public String searchByIndex(int num) {
		String ResultbyIndex = "";
		String tempString = "";
		for (SeaPort p : ports) {
			if (p.index == num) {
				ResultbyIndex += p;
				return ResultbyIndex;
			} else {
				tempString = p.searchbyIndex(num);
				if (!tempString.isEmpty()) {
					ResultbyIndex += "\n Port:" + p.name + ":\n" + tempString;
					tempString = "";
				}
			}
		}

		return ResultbyIndex;
	}

	// performs a skill search
	public String searchSkill(String text) {
		String skill = "";
		String tempString = "";
		for (SeaPort s : ports) {
			tempString += s.searchForSkill(text);
			if (!tempString.isEmpty()) {
				skill += "\n Port:" + s.name + ":\n" + tempString;
				tempString = "";
			}
		}
		return skill;
	}

	// takes in a double, determines whether it is a length or width search
	// and returns the results of a lenght or width search
	public String searchByDouble(double dub, Boolean isLength) {
		String shipByDouble = "";
		String tempString = "";
		for (SeaPort s : ports) {
			if (isLength) {
				tempString += s.searchByLength(dub);
			} else {
				tempString += s.searchByWeight(dub);
			}
			if (!tempString.isEmpty()) {
				shipByDouble += "\n Port:" + s.name + ":\n" + tempString;
				tempString = "";
			}
		}

		return shipByDouble;
	}

	// returns the results of search by minimum number of passengers.
	public String searchByNumberOfPassengers(int numPass) {
		String shipWithMinPassengers = "";
		String tempString = "";
		for (SeaPort s : ports) {
			tempString += s.findShipsWithMinimumPassengers(numPass);
			if (!tempString.isEmpty()) {
				shipWithMinPassengers += "\n Port:" + s.name + ":\n" + tempString;
				tempString = "";
			}
		}
		return shipWithMinPassengers;
	}

	public String toString() {
		String allData = ">>>>>>>>>>>> THE WORLD: \n\n";
		for (SeaPort p : ports) {
			allData += "\n" + p;
		}

		return allData;
	}

}
