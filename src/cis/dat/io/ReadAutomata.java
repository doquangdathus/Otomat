package cis.dat.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadAutomata {
	private BufferedReader br;
	public ReadAutomata(String file) {
		try {
			br = new BufferedReader(new FileReader(file));
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
	public void close(){
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Can't close file reader!!");
		}
	}
}
