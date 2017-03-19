package cis.dat.object;

import java.util.ArrayList;

public class TransformFunction {
	private ArrayList<Transform> listTransform;
	
	public TransformFunction() {
		listTransform = new ArrayList<>();
	}	
	public void setTransformFunciton(Transform tf){
		listTransform.add(tf);
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
		return null;
	}
}
