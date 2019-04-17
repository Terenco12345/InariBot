package bot;

import java.io.File;
import java.util.List;

import utils.TextFileEditor;

public class Ref {
	public static String botToken = "";
	public static String botPrefix = "";
	public static String helpText = "";
	
	public static void initializeRef() {
		File referenceFile = new File("./config/reference.txt");
		
		List<String> reference = TextFileEditor.getTextFileAsList(referenceFile);
		
		botToken = reference.get(0).split("=")[1];
		System.out.println("Bot token is initialized: "+botToken);
	}
}
