package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.login.LoginException;

import bot.InariBot;

public class TextGUI {
	public static void main(String args[]) {
		try {
			new InariBot();
			
			BufferedReader systemInput = new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				String line = systemInput.readLine();
				if(line.toLowerCase().equals("stop")) {
					System.exit(1);
				}
			}
		} catch (LoginException | InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
