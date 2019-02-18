package bot;

import net.dv8tion.jda.api.entities.User;

public class Message {
	
	private User author;
	private Message message;
	
	public Message(User author, Message message) {
		this.author = author;
		this.message = message;
	}
}
