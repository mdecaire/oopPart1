/**
 * File Name: PassengerShip.java
 * Date: 3/25/2018
 * Author: Michelle Decaire
 * Purpose: to define a passenger ship
 */
import java.util.Scanner;

public class PassengerShip extends Ship {

	private int numPassengers;
	private int numRooms;
	private int numOccupied;
	public Boolean amPassenger = true;

	public PassengerShip(Scanner sc) {
		super(sc);
		if (sc.hasNextInt())
			this.setNumPassengers(sc.nextInt());
		if (sc.hasNextInt())
			this.setNumRooms(sc.nextInt());
		if (sc.hasNextInt())
			this.setNumOccupied(sc.nextInt());

	}

	public int getNumPassengers() {
		return numPassengers;
	}

	public void setNumPassengers(int numPassengers) {
		this.numPassengers = numPassengers;
	}

	public int getNumOccupied() {
		return numOccupied;
	}

	public void setNumOccupied(int numOccupied) {
		this.numOccupied = numOccupied;
	}

	public int getNumRooms() {
		return numRooms;
	}

	public void setNumRooms(int numRooms) {
		this.numRooms = numRooms;
	}

	public String toString() {
		return "Passenger ship: " + super.toString();
	}
}
