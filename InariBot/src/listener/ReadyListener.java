package listener;

import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class ReadyListener implements EventListener{

	@Override
	public void onEvent(Event event) {
		if(event instanceof ReadyEvent) {
			System.out.println("Bot is ready!");
		}
	}
	
}
