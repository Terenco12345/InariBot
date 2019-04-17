package listener;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import utils.TextFileEditor;
import view.swingwindows.MainMenuWindow;

public class MessageListener extends ListenerAdapter{

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		MessageChannel channel = event.getChannel();

		// Listen in on specific messages
		if(event.isFromType(ChannelType.PRIVATE))
		{
			File chatFile = new File("./config/chatlog.txt");
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
		
		MainMenuWindow.mainMenuWindow.updateMessageBox();
	}	
}