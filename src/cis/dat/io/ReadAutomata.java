package cis.dat.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import cis.dat.object.Alphabet;
import cis.dat.object.State;
import cis.dat.object.Transform;
import cis.dat.object.TransformFunction;

public class ReadAutomata {
	private BufferedReader br;
	private Alphabet alphabet;
	private TransformFunction tfOld;
	private String initialStatus;
	private String finishStatus;
	private ArrayList<State> listFinishState;
	private ArrayList<State> listState;
	public ArrayList<State> getListState() {
		return listState;
	}
	public String getInitialStatus() {
		return initialStatus;
	}
	public void setInitialStatus(String initialStatus) {
		this.initialStatus = initialStatus;
	}
	public String getFinishStatus() {
		return finishStatus;
	}
	public void setFinishStatus(String finishStatus) {
		this.finishStatus = finishStatus;
	}
	public ReadAutomata(String file) {
		try {
			br = new BufferedReader(new FileReader(file));
			readAutomata();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void open(String file){
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(file + "Can't read!!");
		}
	}
	public ArrayList<String> read(){
		ArrayList<String> allLines = new ArrayList<>();
		String line = "";
		try {
			while((line = br.readLine()) != null){
				allLines.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return allLines;
		}
		close();
		return allLines;
	}
	public void readAutomata(){
		ArrayList<String> allLines = read();
		alphabet = new Alphabet(allLines.get(0));
//		listStatusInString = allLines.get(1).split(" ");
		initialStatus = allLines.get(1);
		finishStatus = allLines.get(2);
		listFinishState = new ArrayList<>();
		String[] sts = finishStatus.split(" ");
		for (String string : sts) {
			string = string.trim();
			if(string.length() > 0){
				State st = new State(string);
				listFinishState.add(st);
			}
		}
		tfOld = new TransformFunction(); 
		for(int i = 3; i < allLines.size() - 1; i ++){
			String[] s = allLines.get(i).split(" ");
			Transform tf = new Transform();
			tf.setAlphabet(s[1]);
			tf.setBs(new State(s[0]));
			tf.setEs(new State(s[2]));
			tfOld.setTransformFunciton(tf);
		}
		String[] stInString = allLines.get(allLines.size() - 1).split(" ");
		listState = new ArrayList<>();
		for (String string : stInString) {
			State st = new State(string);
			listState.add(st);
		}
	}
	
	public Alphabet getAlphabet() {
		return alphabet;
	}
	public void setAlphabet(Alphabet alphabet) {
		this.alphabet = alphabet;
	}
	public TransformFunction getTfOld() {
		return tfOld;
	}
	public void setTfOld(TransformFunction tfOld) {
		this.tfOld = tfOld;
	}
	public ArrayList<State> getListFinishState(){
		return listFinishState;
	}
	public void close(){
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Can't close file reader!!");
		}
	}
}
