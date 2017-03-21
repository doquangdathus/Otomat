package cis.dat.main;

import java.util.ArrayList;

import cis.dat.io.ReadAutomata;
import cis.dat.io.WriteAutomata;
import cis.dat.object.Alphabet;
import cis.dat.object.Status;
import cis.dat.object.Transform;
import cis.dat.object.TransformFunction;
import sun.misc.Queue;

public class Main {
	static TransformFunction tffOld;
	static TransformFunction tffmNew;
	static Alphabet alphabet;
	static Status initialStatus;
	static String finishStatus;
	static String[] allStatusInString;
	static ArrayList<Status> allStatus;
	static Queue queue;
	static WriteAutomata wa;
	public static void main(String[] args) {
//		testOneFile();
		testAllCase();
	}
	public static void testAllCase(){
		for(int i = 1; i <= 5; i++){
			readAutomata("testcase" + i + ".txt");
			allStatus = new ArrayList<>();
			try {
				process();
				wa = new WriteAutomata("testcase" + i + "_out.txt");
				wa.write(tffmNew, alphabet);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void testOneFile(){
		readAutomata("testcase" + 3 + ".txt");
		allStatus = new ArrayList<>();
		try {
			process();
			wa = new WriteAutomata("testcase" + 3 + "_out.txt");
			wa.write(tffmNew, alphabet);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void test1() {
		Status end = tffOld.getTransform("p_1", "c");
		System.out.println(end.toString());
	}

	public static void readAutomata(String filePath) {
		ReadAutomata readAutomata = new ReadAutomata(filePath);
		tffOld = readAutomata.getTfOld();
		alphabet = readAutomata.getAlphabet();
		initialStatus = new Status(readAutomata.getInitialStatus());
		initialStatus.setInitialStatus();
		finishStatus = readAutomata.getFinishStatus();
		allStatusInString = readAutomata.getListStatusInString();
	}

	private static Status temp;

	public static void process() throws InterruptedException {
		tffmNew = new TransformFunction();
		queue = new Queue();
		queue.enqueue(initialStatus);
//		initialQueue();
		allStatus.add(initialStatus);
		ArrayList<String> alpha = alphabet.getAlphabe();
		while (!queue.isEmpty()) {
			Status currentStatus = (Status) queue.dequeue();
			ArrayList<String> listCurrentStatus = currentStatus.getListStatus();
			for (String al : alpha) {
				Transform tf = new Transform();
				tf.setBs(currentStatus);
				tf.setAlphabet(al);
				temp = new Status();
				for (String curStt : listCurrentStatus) {
					Status target = tffOld.getTransform(curStt, al);
					combineStatus(target);
				}
				tf.setEs(temp);
				tffmNew.setTransformFunciton(tf);
				if (!checkStatusExist(temp) && !temp.isNullStatus()) {
					allStatus.add(temp);
					queue.enqueue(temp);

				}
			}
		}
		tffmNew.setAllStatus(allStatus);
		setNewFinishStatus();
	}
	public static void setNewFinishStatus(){
		for (Status status : allStatus) {
			ArrayList<String> listStt = status.getListStatus();
			for (String stt : listStt) {
				if(finishStatus.contains(stt)){
					status.setFinishStatus();
					continue;
				}
			}
		}
	}
	public static void initialQueue(){
		for (String stt : allStatusInString) {
			Status st = new Status(stt);
			queue.enqueue(st);
		}
	}
	public static void combineStatus(Status stt) {
		ArrayList<String> listStt = stt.getListStatus();
		for (String st : listStt) {
			temp.setStatus(st);
		}
	}

	public static void printTransformFunction(TransformFunction tff) {
		System.out.println(tff.toString());
	}

	public static boolean checkStatusExist(Status stt) {
		for (Status status : allStatus) {
			if (status.compareTo(stt) == 0)
				return true;
		}
		return false;
	}
}
