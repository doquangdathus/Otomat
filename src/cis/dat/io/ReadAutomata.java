package cis.dat.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import cis.dat.object.Alphabet;
import cis.dat.object.Status;
import cis.dat.object.Transform;
import cis.dat.object.TransformFunction;

public class ReadAutomata {
	private BufferedReader br;
	private Alphabet alphabet;
	private TransformFunction tfOld;
	private String initialStatus;
	private String finishStatus;
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
			System.err.println(file + "Can't read!!");
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
			System.err.println("Can't read!!");
			return allLines;
		}
		close();
		return allLines;
	}
	public void readAutomata(){
		ArrayList<String> allLines = read();
		alphabet = new Alphabet(allLines.get(0));
		initialStatus = allLines.get(1);
		finishStatus = allLines.get(2);
		tfOld = new TransformFunction();
		for(int i = 3; i < allLines.size(); i ++){
			String[] s = allLines.get(i).split(" ");
			Transform tf = new Transform();
			tf.setAlphabet(s[1]);
			tf.setBs(new Status(s[0]));
			tf.setEs(new Status(s[2]));
			tfOld.setTransformFunciton(tf);
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
	public void close(){
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Can't close file reader!!");
		}
	}
}
