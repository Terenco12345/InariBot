package view.swingwindows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bot.InariBot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Invite;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerInviteWindow {

	private JFrame serverInviteFrame;
	private JComboBox<String> serverSelectBox;
	public Guild selectedServer;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}
	
	/**
	 * Create the application.
	 */
	public ServerInviteWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		serverInviteFrame = new JFrame();
		serverInviteFrame.setResizable(false);
		serverInviteFrame.setTitle("Send Invite");
		serverInviteFrame.setBounds(100, 100, 373, 155);
		serverInviteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		serverInviteFrame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel titlePanel = new JPanel();
		serverInviteFrame.getContentPane().add(titlePanel);
		
		JLabel lblSelectAServer = new JLabel("Select a server to invite "+MainMenuWindow.mainMenuWindow.selectedMember.getEffectiveName()+" to:");
		titlePanel.add(lblSelectAServer);
		
		JPanel serverSelectPanel = new JPanel();
		serverInviteFrame.getContentPane().add(serverSelectPanel);
		
		serverSelectBox = new JComboBox<String>();
		serverSelectBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedServer = InariBot.servers.get(serverSelectBox.getSelectedIndex());
				System.out.println("New server selected for invite: "+selectedServer.getName());
			}
		});
		
		serverSelectPanel.add(serverSelectBox);
		
		JPanel buttonPanel = new JPanel();
		serverInviteFrame.getContentPane().add(buttonPanel);
		
		JButton inviteButton = new JButton("Invite");
		inviteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invite();
			}
		});
		buttonPanel.add(inviteButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		buttonPanel.add(quitButton);
		
		updateServerSelection();
		serverInviteFrame.setVisible(true);
	}

	/**
	 * Update server selection box
	 */
	public void updateServerSelection() {
		InariBot.servers = InariBot.jda.getGuilds();
		serverSelectBox.removeAllItems();

		for(Guild g: InariBot.servers) {
			serverSelectBox.addItem(g.getName());
		}
	}
	
	/**
	 * Send an invite to the selected user
	 */
	public void invite() {
		Invite invite = selectedServer.getDefaultChannel().createInvite().complete();
		MainMenuWindow.mainMenuWindow.sendMessage(invite.getUrl());
		serverInviteFrame.dispose();
	}
	
	/**
	 * Dispose of the frame.
	 */
	public void quit() {
		serverInviteFrame.dispose();
	}
}
