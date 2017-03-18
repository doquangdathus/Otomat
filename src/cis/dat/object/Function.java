package cis.dat.object;

import java.util.ArrayList;

public class Function {
	private ArrayList<Transform> listTransform;
	public Function() {
		listTransform = new ArrayList<>();
	}
	public void setTransformFunction(String beginStatus, String endStatus, String alphabet){
		Transform tf = new Transform(beginStatus, endStatus, alphabet);
		listTransform.add(tf);
	}
	public ArrayList<Transform> getListTransform(){
		return listTransform;
	}
}
