/**
 * File Name: Thing.java
 * Due Date: 3/25/2018
 * Author: Michelle DeCaire
 * Purpose: Parent Class of most classes
 * 	defines the index name parent and toString base methods.
 */
import java.util.Scanner;

public class Thing implements Comparable<Thing> {

	protected int index = 0;
	protected String name = "";
	protected int parent = 0;

	public Thing() {
	}

	public Thing(Scanner sc) {

		if (sc.hasNext())
			this.name = sc.next();
		if (sc.hasNextInt())
			this.index = sc.nextInt();
		if (sc.hasNextInt())
			this.parent = sc.nextInt();
	}

	//compares the indexes of each child class
	@Override
	public int compareTo(Thing myThing) {
		return this.index - myThing.index;
	}

	public String toString() {
		return name + " " + index;
	}

}
