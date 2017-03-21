package cis.dat.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cis.dat.object.Alphabet;
import cis.dat.object.Transform;
import cis.dat.object.TransformFunction;

public class WriteAutomata {
	private BufferedWriter bw;
	public WriteAutomata(String filePath) {
		try {
			bw = new BufferedWriter(new FileWriter(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't open " + filePath);
			return;
		}
	}
	public void open(String filePath){
		try {
			bw = new BufferedWriter(new FileWriter(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't open " + filePath);
			return;
		}
	}
	public void write(TransformFunction tff, Alphabet alphabet){
		try {
			bw.write(alphabet + "\n");
			bw.write(tff.newStatusInString() + "\n");
			ArrayList<Transform> listTransform = tff.getTransformFunction();
			for (Transform transform : listTransform) {
				bw.write(transform.toString() + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't write ");
			return;
		}
		close();
	}
	public void close(){
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Can't close writer ");
			return;
		}
	}
}
