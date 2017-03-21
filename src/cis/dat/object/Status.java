package cis.dat.object;

import java.util.ArrayList;

public class Status implements Comparable<Status>{
	private ArrayList<String> listStatus = new ArrayList<>();
	private boolean isFinishStatus = false;
	private boolean isInitialStatus = false;
	public Status(String endSttInString) {
		String[] es = endSttInString.split(",");
		for (String e : es) {
			setStatus(e);
		}
	}
	public Status() {
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
		String sttInString = "{";
		for(int i = 0; i < listStatus.size(); i++){
			sttInString += i < listStatus.size() - 1 ? listStatus.get(i) + "," : listStatus.get(i) + "}" ;
		}
		return sttInString;
	}
	@Override
	public int compareTo(Status o) {
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
