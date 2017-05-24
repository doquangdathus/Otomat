package cis.dat.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import cis.dat.object.Rule;

public class CKY {
	static ArrayList<String> left;
	static ArrayList<String> right;
	static String word;
	static ArrayList<String>[][] table;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		word = "aabbbb";
		table = new ArrayList[word.length()][word.length()];
		buildRule("cnf5.txt");
		initTable();
		fillTable();
		printTable();
		if(table[0][word.length() - 1].size() != 0) System.out.println("Là từ thuộc ngôn ngữ");
	}
	
	public static void buildRule(String fileInput) throws IOException{
		CNF.process(fileInput);
		left = new ArrayList<>();
		right = new ArrayList<>();
		for (Rule rule : CNF.rules) {
			for (String string : rule.rights) {
				left.add(rule.left);
				right.add(string);
			}
		}
		
	}
	
	public static ArrayList<Integer> getLeft(String r){
		ArrayList<Integer> s = new ArrayList<>();
		for(int i = 0; i < right.size(); i++){
			if(right.get(i).compareTo(r) == 0) s.add(i); 
		}
		return s;
	}
	public static void initTable(){
		char[] s = word.toCharArray();
		for(int i = 0; i < table.length; i++){
			for(int j = 0; j < table.length; j++){
				if(i == j){
					table[i][j] = new ArrayList<>();
					table[i][j].add(left.get(getLeft(String.valueOf(s[i])).get(0)));
				} else {
					table[i][j] = new ArrayList<>();
				}
			}
		}
	}
	
	
	public static void fillTable(){
		for(int span = 1; span < table.length; span ++){
			for(int i = 0; i < table.length; i++){
				if(i + span < table.length) fillCell(i, i + span);
			}
		}
	}
	public static void fillCell(int i, int j){
		for(int k = i; k <= j - 1; k ++){
			 for (String Y : table[i][k]) {
				for(String Z : table[k + 1][j]){
					combineCell(i, j, Y.trim() + "" + Z.trim());
				}
			}
		}
	}
	
	public static void combineCell(int i, int j, String right){
		ArrayList<Integer> listLeft = getLeft(right);
		for (Integer integer : listLeft) {
			if(!table[i][j].contains(left.get(integer))){
				table[i][j].add(left.get(integer));
			}
		}
	}
	public static void printTable(){
		for(int i = 0; i < table.length; i++){
			for(int j = 0; j < table.length; j++){
				System.out.print(printList(table[i][j]) + "\t");
			}
			System.out.println();
		}
	}
	public static String printList(ArrayList<String> list){
		String line = "";
		if(list.size() == 0){
			line = "--";
		} else if(list.size() == 1){
			line = list.get(0);
		} else {
			for(int i = 0; i < list.size() - 1; i++){
				line += list.get(i) + ",";
			}
			line += list.get(list.size() - 1);
		}
		return line;
	}
}
