package cis.dat.object;

import java.util.ArrayList;

public class Status {
	private ArrayList<String> listStatus;
	private boolean isFinishStatus = false;
	public Status(String endSttInString) {
		String[] es = endSttInString.split(",");
		listStatus = new ArrayList<>();
		for (String e : es) {
			setEndStatus(e);
		}
	}
	public void setEndStatus(String stt){
		if(!listStatus.contains(stt)) listStatus.add(stt);
	}
	public ArrayList<String> getListEndStatus(){
		return listStatus;
	}
	public void setFinishStatus(){
		isFinishStatus = true;
	}
	public boolean isFinishStatus(){
		return isFinishStatus;
	}
	@Override
	public String toString() {
		String sttInString = "";
		for(int i = 0; i < listStatus.size(); i++){
			sttInString += i < listStatus.size() - 1 ? listStatus.get(i) + "," : listStatus.get(i); ;
		}
		return sttInString;
	}
}
