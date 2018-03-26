/**
 * FileName: Person.java
 * Date: 3/25/2018
 * Author: Michelle Decaire
 * Purpose: to define and hold elements of each person
 */
import java.util.Scanner;

public class Person extends Thing {
	private String skill;

	public Person(Scanner sc) {
		super(sc);
		if (sc.hasNext())
			setSkill(sc.next());
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String toString() {
		return "Person: " + super.toString()+" "+ skill+"\n";
	}

}
