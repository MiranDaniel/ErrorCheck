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
	
	
	public static void tabSpaceError() {
		if(vars.tabError == false) {
			System.out.println("TabSpace error detected!");
			vars.tabError = true;
		}
	}
	public static void syntaxError() {
		System.out.println("syntaxError detected!");
	}
	public static void main(String[] args) {
		boolean usesSpaces = false;
		boolean usesTabs = false;
		List<String> data = read();
		for(String line : data) {
			if(line.startsWith(" ")) {
				if(usesTabs == false) {
					usesSpaces = true;
				}
				else {
					tabSpaceError();
				}
			}
			else if(line.startsWith("\t")){
				if(usesSpaces == false) {
					usesTabs = true;
				}
				else {
					tabSpaceError();
				}
			}
			if(line.contains("for ")) {
				if(line.endsWith(":") == false) {
					syntaxError();
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
						syntaxError();
					}
				}	
			}
		}
	}
}
