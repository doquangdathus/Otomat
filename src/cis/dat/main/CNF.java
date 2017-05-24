package cis.dat.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import cis.dat.object.Rule;

public class CNF {
	static ArrayList<Rule> rules = new ArrayList<>();
	static Hashtable<String, Integer> replace = new Hashtable<>();
	static String[] als;
	static Hashtable<String, Integer> ruleForAls = new Hashtable<>();
	static Hashtable<String, Integer> ruleForV = new Hashtable<>();
	static int digit = 90;

	public static void main(String[] args) throws IOException {
		process("cnf4.txt");
	}
	public static void process(String fileInput) throws IOException{
		readGrammar(fileInput);
		if(isCNF()){
			System.out.println("Is CNF form");
		} else {
			ruleForAls();
			step1();
//			step2();
			step31();
			step32();
			step1();
		}
		printNewRules(rules);
	}
	// remove rule: A -> B
	// lowercase 97 - 122
	// uppercase 65 - 90
	public static void step1() {
		for (Rule rule : rules) {
			ArrayList<String> cp = cp(rule.rights);
			for (String right : cp) {
				if (right.length() == 1 && right.toUpperCase().compareTo(right) == 0) {
					rule.rights.remove(right);
				}
			}
		}
	}

	// step2 replace
	public static void step2() {
		for (int k = 0; k < rules.size(); k++) {
			for (int j = 0; j < rules.get(k).rights.size(); j++) {
				String oldRule = rules.get(k).rights.get(j);
				char[] s = oldRule.toCharArray();
				int count = 0;
				int mark = -1;
				for (int i = 0; i < s.length; i++) {
					if ((int) s[i] >= 97 && (int) s[i] <= 122) {
						if (mark == -1)
							mark = i;
						count++;
					} else {
						rules.set(k, replaceRule(rules.get(k), j, mark, count, oldRule));
						count = 0;
						mark = -1;
					}
				}
				rules.set(k, replaceRule(rules.get(k), j, mark, count, oldRule));
			}
		}
	}

	// step 3.2
	public static void step32() {
		boolean change = true;
		while (change) {
			change = false;
			for (Rule rule : rules) {
				for (int i = 0; i < rule.rights.size(); i++) {
					if (rule.rights.get(i).length() > 2) {
						change = true;
						String right = rule.rights.get(i);
						String tmp = right;
						for (int j = 0; j < right.length() - 2; j += 2) {
							String z = right.substring(j, j + 2);
							if (ruleForV.get(z) == null) {
								ruleForV.put(z, digit--);
								tmp = tmp.replaceFirst(z, String.valueOf((char) (digit + 1)));
							} else {
								tmp = tmp.replaceFirst(z, String.valueOf((char) (int) ruleForV.get(z)));
							}
						}
						rule.rights.set(i, tmp);
					}
				}
			}
		}
		Enumeration<String> keys = ruleForV.keys();
		while (keys.hasMoreElements()) {
			String right = keys.nextElement();
			rules.add(new Rule(String.valueOf((char) (int) ruleForV.get(right)), right));
		}
	}

	public static void step31() {
		for (int i = 0; i < rules.size(); i++) {
			if (rules.get(i).rights.size() > 1) {
				for (int j = 0; j < rules.get(i).rights.size(); j++) {
					String temp = rules.get(i).rights.get(j);
					if (temp.length() > 1) {
						for (int k = 0; k <= temp.length() - 1; k++) {
							String al = temp.substring(k, k + 1);
							if (ruleForAls.get(al) != null) {
								temp = temp.replaceAll(al, String.valueOf((char) (int) ruleForAls.get(al)));
							}
						}
						rules.get(i).rights.set(j, temp);
					}
				}
			}
		}
	}
	public static Rule replaceRule(Rule rule, int j, int mark, int count, String oldRule) {
		String tmp = rule.rights.get(j);
		if (count >= 2) {
			String z = oldRule.substring(mark, mark + count);
			if (replace.get(z) == null) {
				tmp = tmp.replaceFirst(z, String.valueOf((char) (digit)));
				replace.put(z, digit--);
			} else {
				int d = replace.get(z);
				tmp = tmp.replaceFirst(z, String.valueOf((char) d));
			}
		}
		rule.rights.set(j, tmp);
		return rule;
	}
	public static boolean isCNF(){
		for (Rule rule : rules) {
			for (String string : rule.rights) {
				if(string.length() == 2 && string.toUpperCase().compareTo(string) == 0
						|| string.length() == 1 && string.toLowerCase().compareTo(string) == 0){
					
				} else {
					return false;
				}
			}
		}
		return true;
	}

	public static void readGrammar(String fileInput) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileInput));
		String line = "";
		als = br.readLine().split(" ");
		while ((line = br.readLine()) != null) {
			rules.add(new Rule(line));
		}
		br.close();
	}

	public static void ruleForAls() {
		for (String string : als) {
			ruleForAls.put(string, digit--);
			rules.add(new Rule(String.valueOf((char) (digit + 1)), string));
		}
	}

	public static void printNewRules(ArrayList<Rule> rs) {
		for (Rule rule : rs) {
			System.out.println(rule.toString());
		}
	}

	public static ArrayList<String> cp(ArrayList<String> source) {
		ArrayList<String> out = new ArrayList<>();
		for (String string : source) {
			out.add(string);
		}
		return out;
	}
}
