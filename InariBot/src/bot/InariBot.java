package bot;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import listener.CommandListener;
import listener.ReadyListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class InariBot{
	
	public static List<String> chatHistory = new ArrayList<String>();
	
	public InariBot() throws LoginException, InterruptedException{
		JDA jda = new JDABuilder(Ref.botToken)
				.addEventListeners(new ReadyListener())
				.addEventListeners(new CommandListener())
				.build();
		jda.awaitReady();
	}
}
