package cis.dat.object;

import java.util.ArrayList;

public class Alphabet {
	private ArrayList<String> alphabet;
	
	public Alphabet(String alphabetInString) {
		alphabet = new ArrayList<>();
		String[] tokens = alphabetInString.split(" ");
		for (String token : tokens) {
			setAlphabet(token);
		}
	}
	
	public void setAlphabet(String token){
		alphabet.add(token);
	}
	
	public ArrayList<String> getAlphabe(){
		return this.alphabet;
	}
	
	@Override
	public String toString() {
		String alphabetInString = "";
		for (String al : alphabet) {
			alphabetInString += al + " ";
		}
		return alphabetInString;
	}
}
