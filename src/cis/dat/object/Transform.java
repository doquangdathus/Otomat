package cis.dat.object;

import java.util.ArrayList;

public class Transform {
	private Status bs;
	private Status es;
	private String alphabet;
	public ArrayList<String> getBeginStatus() {
		return bs.getListEndStatus();
	}
	public void setBs(Status bs) {
		this.bs = bs;
	}
	public ArrayList<String> getEndStatusInList() {
		return es.getListEndStatus();
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
	public String transListSttToString(ArrayList<String> listStt){
		String res = "";
		for (String endSt : listStt) {
			res += endSt + ",";
		}
		return res;
	}
	@Override
	public String toString() {
		String beginStt = transListSttToString(getBeginStatus());
		String endStt = transListSttToString(getEndStatusInList());
		return beginStt + " " + getAlphabet() + " " + endStt;
	}
	
}
