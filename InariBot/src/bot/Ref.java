package bot;

public class Ref {
	public static final String botToken = "NDMzMTc4MjY5NjI2NDAwNzY4.DyygnQ.wmDqYn0S1295J2OcgqPVdE-bqLw";
	public static String botPrefix = "^";
	public static String[] eightBallResponses = 
		{
				"No.",
				"I don't think so.",
				"The answer to your question is no.",
				"Nuh-uh.",
				"Absolutely not.",
				"Err, maybe not.",
				"I'm leaning towards a no.",
				"Yes.",
				"Sure.",
				"I think maybe.",
				"Of course.",
				"Without a doubt.",
				"I'm leaning towards a yes.",
				"It's obvious."
		};
	public static final String helpText = 
	"Welcome to InariBot!\n"
	+ "Here is a list of all of the commands:\n"
	+ "	"+botPrefix+"help: Lists out all of the commands.\n"
	+ "	"+botPrefix+"dice: Rolls a dice.\n"
	+ "	"+botPrefix+"8ball: Responds yes or no to a question.\n";
}
