package view;

import javax.security.auth.login.LoginException;

import bot.InariBot;

public class TextGUI {
	public static void main(String args[]) {
		try {
			new InariBot();
		} catch (LoginException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
