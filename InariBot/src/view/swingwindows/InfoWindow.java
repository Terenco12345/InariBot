package view.swingwindows;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class InfoWindow extends JFrame{
	
	private static final long serialVersionUID = -7202170466165380381L;

	public InfoWindow(String title, String message) {
		this.setTitle(title);
		this.getContentPane().setLayout(new FlowLayout());
		this.add(new JLabel(message));
		this.setSize(450, 100);
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
	}
}
