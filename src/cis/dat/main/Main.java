package cis.dat.main;

import cis.dat.io.ReadAutomata;
import cis.dat.object.Alphabet;
import cis.dat.object.Status;
import cis.dat.object.TransformFunction;

public class Main {
	static TransformFunction tffOld;
	static TransformFunction tffmNew;
	static Alphabet alphabet;
	static String initialStatus;
	static String finishStatus;
	public static void main(String[] args) {
		readAutomata("input.txt");
	}
	public static void test1(){
		Status end = tffOld.getTransform("p_1", "c");
		System.out.println(end.toString());
	}
	public static void readAutomata(String filePath){
		ReadAutomata readAutomata = new ReadAutomata(filePath);
		tffOld = readAutomata.getTfOld();
		alphabet = readAutomata.getAlphabet();
		initialStatus = readAutomata.getInitialStatus();
		finishStatus = readAutomata.getFinishStatus();
	}
	
}
