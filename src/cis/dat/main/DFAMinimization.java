package cis.dat.main;

import java.util.ArrayList;

import cis.dat.io.ReadAutomata;
import cis.dat.object.Alphabet;
import cis.dat.object.State;
import cis.dat.object.Transform;
import cis.dat.object.TransformFunction;

public class DFAMinimization {

	static TransformFunction tffOld;
	static TransformFunction tffNew;
	static Alphabet alphabet;
	static ArrayList<State> listNewState;
	static ArrayList<State> listOldState;
	static State initState;
	static ArrayList<State> listFinishState;
	static int[][] table;

	public static void main(String[] args) {
		init();
		fillTable();
		getListNewStates();
		getNewTransformFunction();
	}

	public static void init() {
		ReadAutomata ra = new ReadAutomata("dfa2.txt");
		tffOld = ra.getTfOld();
		alphabet = ra.getAlphabet();
		listOldState = ra.getListState();
		initState = new State(ra.getInitialStatus());
		listFinishState = ra.getListFinishState();
		initTable();
	}

	public static void initTable() {
		table = new int[listOldState.size()][listOldState.size()];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < i; j++) {
				State si = listOldState.get(i);
				State sj = listOldState.get(j);
				boolean t1 = contains(listFinishState, si);
				boolean t2 = contains(listFinishState, sj);
				if (t1 ^ t2) {
					table[i][j] = 1;
				}
			}
		}
	}

	public static void fillTable() {
		boolean t = true;
		do {
			t = true;
			for (int i = 0; i < table.length; i++) {
				for (int j = 0; j < i; j++) {
					if (table[i][j] == 0) {
						State si = listOldState.get(i);
						State sj = listOldState.get(j);

						ArrayList<String> al = alphabet.getAlphabe();
						for (String a : al) {
							State sti = tffOld.getTransform(si.singleDFAState(), a);
							State stj = tffOld.getTransform(sj.singleDFAState(), a);

							int m = getIdState(sti);
							int n = getIdState(stj);

							if (table[m][n] == 1 || table[n][m] == 1) {
								t = false;
								table[i][j] = 1;
							}
						}
					}
				}
			}
		} while (t == false);
	}

	public static void getListNewStates() {
		listNewState = new ArrayList<>();
		int mark = 2;
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < i; j++) {
				if (table[i][j] == 0) {
					for (int k = i; k < table.length; k++) {
						if (table[k][j] == 0)
							table[k][j] = mark;
					}
					for (int l = j; l < i; l++) {
						if (table[i][l] == 0)
							table[i][l] = mark;
					}
					mark++;
				} else if (table[i][j] != 1) {
					for (int k = i; k < table.length; k++) {
						if (table[k][j] == 0)
							table[k][j] = table[i][j];
					}
					for (int l = j; l < i; l++) {
						if (table[i][l] == 0)
							table[i][l] = table[i][j];
					}
				}
			}
		}

		ArrayList<State> cpOldState = copyListState(listOldState);
		for (int i = 2; i < mark; i++) {
			State st = new State();
			for (int j = 0; j < table.length; j++) {
				for (int k = 0; k < i; k++) {
					if (table[j][k] == i) {
						st.setStatus(listOldState.get(j).singleDFAState());
						st.setStatus(listOldState.get(k).singleDFAState());

						cpOldState.remove(listOldState.get(j));
						cpOldState.remove(listOldState.get(k));
					}
				}
			}
			listNewState.add(st);
		}
		for (State state : cpOldState) {
			listNewState.add(state);
		}

	}

	public static void getNewTransformFunction() {
		tffNew = new TransformFunction();
		ArrayList<String> al = alphabet.getAlphabe();
		for (State newState : listNewState) {
			String singleStateInString = newState.singleDFAState();
			for (String a : al) {
				State stEnd = tffOld.getTransform(singleStateInString, a);
				if (!stEnd.isNullStatus())
					for (State st : listNewState) {
						if (st.getListStatus().contains(stEnd.singleDFAState())) {
							Transform tf = new Transform();
							tf.setBs(newState);
							tf.setEs(st);
							tf.setAlphabet(a);
							tffNew.setTransformFunciton(tf);
							break;
						}
					}
			}
		}
		setNewInitState();
		setNewFinishState();
		tffNew.setAllStatus(listNewState);
		System.out.println(tffNew.toString());
	}

	public static void setNewInitState() {
		for (State state : listNewState) {
			if (state.getListStatus().contains(initState.singleDFAState())) {
				state.setInitialStatus();
				break;
			}
		}
	}

	public static void setNewFinishState() {
		for (State state : listNewState) {
			for (State fn : listFinishState) {
				if (state.getListStatus().contains(fn.singleDFAState())) {
					state.setFinishStatus();
					break;
				}
			}
		}
	}

	public static boolean contains(ArrayList<State> listState, State st) {
		for (int i = 0; i < listState.size(); i++) {
			if (listState.get(i).compareTo(st) == 0)
				return true;
		}
		return false;
	}

	public static void printTable() {
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table.length; j++) {
				System.out.print(table[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static ArrayList<State> copyListState(ArrayList<State> listState) {
		ArrayList<State> cp = new ArrayList<>();
		for (State state : listState) {
			cp.add(state);
		}
		return cp;
	}

	public static int getIdState(State st) {
		int i = 0;
		for (State state : listOldState) {
			if (state.compareTo(st) == 0)
				return i;
			i++;
		}
		return -1;
	}
}
