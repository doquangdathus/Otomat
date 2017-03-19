package cis.dat.main;

import java.util.ArrayList;

import cis.dat.io.ReadAutomata;
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
	static Status finishStatus;
	static ArrayList<Status> allStatus;
	static Queue queue;

	public static void main(String[] args) {
		readAutomata("input.txt");
		allStatus = new ArrayList<>();
		try {
			process();
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
		initialStatus.setBeginStatus();
		finishStatus = new Status(readAutomata.getFinishStatus());
		finishStatus.setFinishStatus();
	}

	private static Status temp;

	public static void process() throws InterruptedException {
		tffmNew = new TransformFunction();
		queue = new Queue();
		queue.enqueue(initialStatus);
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
		printTransformFunction(tffmNew);
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
