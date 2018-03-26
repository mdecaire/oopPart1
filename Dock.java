/**
 * File Name: Dock.java
 * Date due: 3/25/2018
 * Author: Michelle Decaire
 * Purpose: to define a dock which holds a ship.
 */
import java.util.Scanner;

public class Dock extends Thing {
	Ship ship;
	
	public Dock(Scanner sc2) {
		super(sc2);
	}
	
	public void setShip(Ship listShip) {
		this.ship=listShip;
	}

	public String toString() {
		return ("Dock: "+ super.toString()+"\n"+  "  "+ship.toString());
	}
	
}
