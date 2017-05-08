package cis.dat.object;

import java.util.ArrayList;

public class TransformFunction {
	private ArrayList<State> allStatus;
	private ArrayList<Transform> listTransform;
	private ArrayList<Boolean> checked;
	public TransformFunction() {
		listTransform = new ArrayList<>();
		checked = new ArrayList<>();
	}	
	public void setTransformFunciton(Transform tf){
		listTransform.add(tf);
		checked.add(false);
	}
	public ArrayList<Transform> getTransformFunction(){
		return listTransform;
	}
	public State getTransform(String stt, String alphabet){
		for (Transform transform : listTransform) {
			ArrayList<String> beginStt = transform.getBeginStatusInListString();
			for (String begin : beginStt) {
				if(begin.compareTo(stt) == 0
						&& transform.getAlphabet().compareTo(alphabet) == 0){
					return transform.getEndStatus();
				}
			}
		}
		return new State();
	}
	public void setAllStatus(ArrayList<State> allStatus) {
		this.allStatus = allStatus;
	}
	public String newStatusInString(){
		String res = "[";
		for(int i = 0; i < allStatus.size(); i++){
			res += i < allStatus.size() - 1 ? sttDetail(allStatus.get(i)) + "," : sttDetail(allStatus.get(i));
		}
		return res + "]";
	}
	public String sttDetail(State stt){
		if(stt.isInitialStatus()){
			return stt.toString() + " " + "init";
		} else if(stt.isFinishStatus()){
			return stt.toString() + " " + "finish";
		} else {
			return stt.toString();
		}
	}
	public String getAllAlphabet(State b, State e){
		String edg = "";
		for (int i = 0; i < listTransform.size(); i++) {
			Transform transform = listTransform.get(i);
			if(transform.getBeginStatus().compareTo(b) == 0 
					&& transform.getEndStatus().compareTo(e) == 0){
				edg += transform.getAlphabet() + ",";
			}
		}
		return edg;
	}
	@Override
	public String toString() {
		String res = newStatusInString() + "\n";
		for (Transform transform : listTransform) {
			res += transform.toString() + "\n";
		}
		return res;
	}
}
