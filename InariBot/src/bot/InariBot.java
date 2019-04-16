package bot;

import javax.security.auth.login.LoginException;

import listener.CommandListener;
import listener.ReadyListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class InariBot{
	public InariBot() throws LoginException, InterruptedException{
		Ref.initializeRef();
		
		JDA jda = new JDABuilder(Ref.botToken)
				.addEventListeners(new ReadyListener())
				.addEventListeners(new CommandListener())
				.build();
		jda.awaitReady();
	}
}
