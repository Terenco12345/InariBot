package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.jupiter.api.Test;

import utils.TextFileEditor;

class TextFileEditorTest {

	@Test
	void testReadEmptyTextFile() {
		// The textFileReader reading from a file
		String resourceName = "test/emptyTextFile.txt";
		File file = new File(this.getClass().getClassLoader().getResource(resourceName).getFile());
		List<String> stringList = TextFileEditor.getTextFileAsList(file);
		
		assertEquals(stringList, new ArrayList<String>());
	}
	
	@Test
	void testReadNormalTextFile() {
		// The textFileReader reading from a file
		String resourceName = "test/normalTextFile.txt";
		File file = new File(this.getClass().getClassLoader().getResource(resourceName).getFile());
		List<String> stringList = TextFileEditor.getTextFileAsList(file);
		
		// The correct list that should be produced
		String[] correctArray = {"yeet", "feet", "meat"};
		List<String> correctList = Arrays.asList(correctArray);
		
		assertEquals(stringList, correctList);
	}
	
	@Test
	void testWriteLineInFile() {
		// Get the test writing file
		String resourceName = "test/writingFile.txt";
		File file = new File(this.getClass().getClassLoader().getResource(resourceName).getFile());
		
		// Establish the correct list that should be obtained
		String[] correctArray = {"test test test."};
		List<String> correctList = Arrays.asList(correctArray);
		
		// Write the line in the file
		String testline = "test test test.";
		TextFileEditor.writeLineInFile(testline, file);
		
		assertEquals(TextFileEditor.getTextFileAsList(file), correctList);
	}

	@After
	void after() {
		// Get the test writing file
		String resourceName = "test/writingFile.txt";
		File file = new File(this.getClass().getClassLoader().getResource(resourceName).getFile());
		
		// Clean the test writing file
		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.print("");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
