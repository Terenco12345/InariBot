package listener;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

import bot.Ref;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import utils.TextFileEditor;

public class CommandListener extends ListenerAdapter{

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		User author = event.getAuthor();
		Guild server = event.getGuild();
		MessageChannel channel = event.getChannel();

		// Listen in on specific messages
		if(event.isFromType(ChannelType.PRIVATE))
		{
			File chatFile = new File(this.getClass().getClassLoader().getResource("chatlog.txt").getFile());
			String line = "{"+LocalTime.now()+" "+LocalDate.now()+"} "
					+"[Private Message] "
					+event.getAuthor().getName()+": "
					+event.getMessage().getContentDisplay();
			TextFileEditor.writeLineInFile(line, chatFile);
			System.out.println(line);
		}
		else
		{
			File chatFile = new File(this.getClass().getClassLoader().getResource("chatlog.txt").getFile());
			String line = "{"+LocalTime.now()+" "+LocalDate.now()+"} "
					+"["+event.getGuild().getName()+"]"
					+" ["+channel.getName()+"] "
					+event.getMember().getEffectiveName()+": "+
					event.getMessage().getContentDisplay();
			TextFileEditor.writeLineInFile(line, chatFile);
			System.out.println(line);
		}

		// Check for commands
		String[] commands = event.getMessage().getContentRaw().split(" ");
		if(commands[0].startsWith(Ref.botPrefix)) {
			String command = commands[0].substring(1);

			// help command. Sends list of commands to chat.
			if(command.equals("help")) {
				System.out.println("help command invoked by a user.");
				channel.sendMessage(Ref.helpText).queue();
			}

			// dice command. Sends a random number between 1 and 6.
			if(command.equals("dice")) {
				System.out.println("dice command invoked by a user.");
				Random rnd = new Random();
				channel.sendMessage("Inari Bot has rolled a "+(rnd.nextInt(6)+1)+".").queue();
			}

			// profilepic command. Grabs the profile picture of a user.
			if(command.equals("profilepic")) {
				System.out.print("profilepic command invoked by a user");
				if(commands.length < 2) {
					channel.sendMessage("Not enough arguments.").queue();
				}
				String user = commands[1].toLowerCase();
				System.out.println(" on the search: "+user);
				for(Member member:server.getMembers()) {
					if(member.getEffectiveName().toLowerCase().contains(user)) {
						System.out.println("Found a user: "+member.getEffectiveName());
						channel.sendMessage(member.getUser().getAvatarUrl()).queue();
					}
				}
			}
		}
	}	
}
