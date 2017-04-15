package cis.dat.object;

public class MyLink {
	int id; // good coding practice would have this as private
	String name;

	public MyLink(String name) {
		this.id++;
		this.name = name;
	}

	public String toString() { // Always a good idea for debuging
		return name; // JUNG2 makes good use of these.
	}
}
