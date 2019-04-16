package bot;

import java.io.File;
import java.util.List;

import utils.TextFileEditor;

public class Ref {
	public static String botToken = "";
	public static String botPrefix = "";
	public static String helpText = "";
	
	public static void initializeRef() {
		File referenceFile = new File(Ref.class.getClassLoader().getResource("reference.txt").getFile());
		File helpFile = new File(Ref.class.getClassLoader().getResource("help.txt").getFile());
		
		List<String> reference = TextFileEditor.getTextFileAsList(referenceFile);
		
		botToken = reference.get(0).split("=")[1];
		System.out.println("Bot token is initialized: "+botToken);
		
		botPrefix = reference.get(1).split("=")[1];
		System.out.println("Bot prefix is initialized: "+botPrefix);
		
		// Put all contents of help.txt in helpText.
		List<String> helpList = TextFileEditor.getTextFileAsList(helpFile);
		for(int i = 0; i < helpList.size(); i++) {
			helpText += helpList.get(i).replaceAll("\\[prefix\\]", botPrefix) + "\n";
		}
		System.out.println("Help text is initialized: "+helpText);
		
		
	}
}
