package listener;

import java.util.Random;

import bot.Ref;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter{

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.isFromType(ChannelType.PRIVATE))
		{
			System.out.printf("[Private Message] %s: %s\n", event.getAuthor().getName(),
					event.getMessage().getContentDisplay());
		}
		else
		{
			System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
					event.getTextChannel().getName(), event.getMember().getEffectiveName(),
					event.getMessage().getContentDisplay());
		}
		
		// Check for commands
		String[] commands = event.getMessage().getContentRaw().split(" ");
		if(commands[0].startsWith(Ref.botPrefix)) {
			String command = commands[0].substring(1);
			
			// help command. Sends list of commands to chat.
			if(command.equals("help")) {
				event.getTextChannel().sendMessage(Ref.helpText).queue();
			}
			
			// dice command. Sends a random number between 1 and 6.
			if(command.equals("dice")) {
				Random rnd = new Random();
				event.getTextChannel().sendMessage("Inari Bot has rolled a "+(rnd.nextInt(6)+1)+".").queue();
			}
			
			// 8ball command. Replies with yes or no answers to a message.
			if(command.equals("8ball")) {
				if(commands.length == 1) {
					event.getTextChannel().sendMessage("You didn't ask any question. Try again.").queue();
				} else {
					String response = Ref.eightBallResponses[new Random().nextInt(Ref.eightBallResponses.length)];
					event.getTextChannel().sendMessage(response).queue();
				}
				
			}
		}
		
	}	

}
