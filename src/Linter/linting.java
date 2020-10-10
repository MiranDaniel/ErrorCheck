package Linter;

import java.io.*;
import java.util.*;


public class linting {
	
	public static List<String> read() {
		try {
			File file = new File("C:\\Users\\miran\\!java\\PyLint\\script.py");
			Scanner myReader = new Scanner(file);
			List<String> dataList = new ArrayList<>();

		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        dataList.add(data);
		      }
		      myReader.close();
		      return dataList;
		} 
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		return null;
	}
	public static class vars{
		static boolean tabError = false;
	}
	
	@SuppressWarnings("unused")
	public static String remTabSpace(String input) {
		for(char i : input.toCharArray()) {
			input = input.replace("    ", "");
			input = input.replace("\t", "");
		}
		return input;
	}
	public static String compileError(int pos, String arg) {
		return "Line: "+pos+", Text: ("+remTabSpace(arg)+")";
	}
	
	public static void tabSpaceError(int pos,String arg) {
		if(vars.tabError == false) {
			System.out.println("TabSpace error detected on line: "+compileError(pos,arg));
			vars.tabError = true;
		}
	}
	public static void syntaxError(int pos,String arg) {
		System.out.println("syntaxError detected on line: "+compileError(pos,arg));
	}
	
	public static void main(String[] args) {
		boolean usesSpaces = false;
		boolean usesTabs = false;
		List<String> data = read();
		int pos = 1;
		for(String line : data) {
			if(line.startsWith(" ")) {
				if(usesTabs == false) {
					usesSpaces = true;
				}
				else {
					tabSpaceError(pos,line);
				}
			}
			else if(line.startsWith("\t")){
				if(usesSpaces == false) {
					usesTabs = true;
				}
				else {
					tabSpaceError(pos,line);
				}
			}
			if(line.contains("for ")) {
				if(line.endsWith(":") == false) {
					syntaxError(pos,line);
				}
			}
			if(line.contains("=")) {
				String newLine;
				if(line.startsWith(" ")) {
					newLine = line.replace("    ", "");
				}
				else {
					newLine = line.replace("\t", "");
				}
				char[] alphabet = "123456789-".toCharArray();
				for(char key : alphabet) {
					if(newLine.startsWith(Character.toString(key))) {
						syntaxError(pos,line);
					}
				}	
			}
			pos++;
		}
	}
}
