package cis.dat.object;

import java.util.ArrayList;

public class State implements Comparable<State>{
	private ArrayList<String> listStatus = new ArrayList<>();
	private boolean isFinishStatus = false;
	private boolean isInitialStatus = false;
	public State(String endSttInString) {
		String[] es = endSttInString.split(",");
		for (String e : es) {
			setStatus(e);
		}
	}
	public State() {
		// TODO Auto-generated constructor stub
	}
	public void setStatus(String stt){
		if(!listStatus.contains(stt)) listStatus.add(stt);
	}
	public ArrayList<String> getListStatus(){
		return listStatus;
	}
	public void setFinishStatus(){
		isFinishStatus = true;
	}
	public boolean isFinishStatus(){
		return isFinishStatus;
	}
	public void setInitialStatus(){
		isInitialStatus = true;
	}
	public boolean isInitialStatus(){
		return isInitialStatus;
	}
	public boolean isNullStatus(){
		return listStatus.size() == 0;
	}
	@Override
	public String toString() {
		if(listStatus.size() == 0) return "null";
		String sttInString = "{";
		for(int i = 0; i < listStatus.size(); i++){
			sttInString += i < listStatus.size() - 1 ? listStatus.get(i) + "," : listStatus.get(i) + "}" ;
		}
		return sttInString;
	}
	public String singleDFAState(){
		return listStatus.get(0);
	}
	@Override
	public int compareTo(State o) {
		ArrayList<String> listCompareStatus = o.getListStatus();
		if(listCompareStatus.size() != this.listStatus.size()){
			return 1;
		}
		for(int i = 0; i < listCompareStatus.size(); i ++){
			if(!listStatus.contains(listCompareStatus.get(i))){
				return 1;
			}
		}
		return 0;
	}
}
