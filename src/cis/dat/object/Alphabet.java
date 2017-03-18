package cis.dat.object;

import java.util.ArrayList;

public class Alphabet {
	private ArrayList<String> alphabet;

	public Alphabet(ArrayList<String> alphabet) {
		this.alphabet = alphabet;
	}

	public ArrayList<String> getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(ArrayList<String> alphabet) {
		this.alphabet = alphabet;
	}
	
	@Override
	public String toString() {
		String alphabetInString = "";
		for (String alpha : alphabet) {
			alpha += alpha + " ";
		}
		return alphabetInString;
	}
}
