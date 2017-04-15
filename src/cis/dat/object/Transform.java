package cis.dat.object;

import java.util.ArrayList;

public class Transform{
	private Status bs;
	private Status es;
	private String alphabet;
	public ArrayList<String> getBeginStatusInListString() {
		return bs.getListStatus();
	}
	public Status getBeginStatus() {
		return bs;
	}
	public void setBs(Status bs) {
		this.bs = bs;
	}
	public ArrayList<String> getEndStatusInListString() {
		return es.getListStatus();
	}
	public Status getEndStatus() {
		return es;
	}
	public void setEs(Status es) {
		this.es = es;
	}
	public String getAlphabet() {
		return alphabet;
	}
	public void setAlphabet(String alphabet) {
		this.alphabet = alphabet;
	}
	@Override
	public String toString() {
		String endSttInString = es.isNullStatus() == true ? "null" : es.toString();
		return bs.toString() + " " + getAlphabet() + " " + endSttInString;
	}
	
}
