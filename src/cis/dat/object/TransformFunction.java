package cis.dat.object;

import java.util.ArrayList;

public class TransformFunction {
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
	public Status getTransform(String stt, String alphabet){
		for (Transform transform : listTransform) {
			ArrayList<String> beginStt = transform.getBeginStatus();
			for (String begin : beginStt) {
				if(begin.compareTo(stt) == 0
						&& transform.getAlphabet().compareTo(alphabet) == 0){
					return transform.getEndStatus();
				}
			}
		}
		return new Status();
	}
	public void setChecked(int i){
		checked.set(i, true);
	}
	public boolean isChecked(int i){
		return checked.get(i);
	}
	@Override
	public String toString() {
		String res = "";
		for (Transform transform : listTransform) {
			res += transform.toString() + "\n";
		}
		return res;
	}
}
