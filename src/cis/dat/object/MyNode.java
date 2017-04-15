package cis.dat.object;

public class MyNode {
	int id = 0;
	String name;

	public MyNode(String name) {
		this.id++;
		this.name = name;
	}

	public String toString() { // Always a good idea for debuging
		return name; // JUNG2 makes good use of these.
	}
}
