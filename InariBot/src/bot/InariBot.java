package bot;

import java.util.List;

import javax.security.auth.login.LoginException;

import listener.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;

public class InariBot{
	public static List<Guild> servers;
	public static JDA jda;
	
	public InariBot() throws LoginException, InterruptedException{
		Ref.initializeRef();
		
		jda = new JDABuilder(Ref.botToken)
				.addEventListeners(new ReadyListener())
				.addEventListeners(new CommandListener())
				.addEventListeners(new MessageListener())
				.build();
		jda.awaitReady();
	}
}
