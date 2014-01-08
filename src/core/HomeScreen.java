package core;
import java.awt.Graphics;

import javax.swing.*;

public class HomeScreen extends InScreens {
	
	// define variable
	private static final long serialVersionUID = 1L; // suggested
	
	private JLabel welcomeMessage; // welcome image label
	
	
	public HomeScreen (LYNXsys system) { // constructor

		super(system); // call super class
		// init variables
		welcomeMessage = new JLabel (new ImageIcon (getClass().getResource("/Resources/Welcome Message.png")));
		welcomeMessage.setBounds(70,245,871,255); // set bounds
		add(welcomeMessage,0); // add to panel

	}
	
	public void paintComponent(Graphics g) { // paint method
		super.paintComponent(g);
	}

	public JLabel getWelcomeMessage() {
		return welcomeMessage;
	}

	public void setWelcomeMessage(JLabel welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}
	
	
}
