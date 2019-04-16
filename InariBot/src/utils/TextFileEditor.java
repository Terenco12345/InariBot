package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TextFileEditor {
	
	/**
	 * Takes in a file, and returns all the lines in an ArrayList.
	 * @param path
	 * @return
	 */
	public static List<String> getTextFileAsList(File file) {
		
		List<String> textFileLines = new ArrayList<String>();
		try {
			// Initialize the reader
			BufferedReader fileReader = new BufferedReader(new FileReader(file));
			
			// Read all lines from the file
			String line;
			while((line = fileReader.readLine())!=null) {
				textFileLines.add(line);
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return textFileLines;
	}
	
	
	/**
	 * Writes a new line in a text file
	 */
	public static void writeLineInFile(String line, File file) {
		try {
			FileWriter writer = new FileWriter (file, true);
		    writer.write(line+"\n");
		    writer.flush();
		    
		    writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
