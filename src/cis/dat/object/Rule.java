package cis.dat.object;

import java.util.ArrayList;

public class Rule{
	public String left;
	public ArrayList<String> rights = new ArrayList<>();
	
	public Rule(String line) {
		this.left = line.split("->")[0];
		String rightSplit[] = line.split("->")[1].split("\\|");
		for (String right : rightSplit) {
			String prRight = right.trim();
			if(prRight.length() > 0){
				rights.add(prRight);
			}
		}
	}
	
	public Rule(String left, String right){
		this.left = left;
		this.rights.add(right);
	}
	
	public String toString(){
		String line = left + " -> ";
		
		if(rights.size() == 1) line += rights.get(0);
		else {
			for(int i = 0; i < rights.size() - 1; i ++){
				line += rights.get(i) + " | ";
			}
			line += rights.get(rights.size() - 1);
		}
		
		return line;
	}
}
