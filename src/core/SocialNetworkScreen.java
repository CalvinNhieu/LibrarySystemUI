package core;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JTextArea;


public class SocialNetworkScreen extends InScreens {

	private static final long serialVersionUID = 1L;
	
	private JTextArea oops; // OOPS message
	
	public SocialNetworkScreen (LYNXsys system) { // constructor 

		super(system); // call super class
		oops = new JTextArea("This page is currently under maintenance. Please check again later."); // set oops message
		oops.setBackground(Color.pink); // other stuff
		oops.setEditable(false);
		oops.setBounds(325,350,375,20); // set bounds
		add(oops,0); // add thing
	}
	
	public void paintComponent(Graphics g) { // paint method
		super.paintComponent(g);
	}

	// GETTERS AND SETTERS
	public JTextArea getOops() {
		return oops;
	}

	public void setOops(JTextArea oops) {
		this.oops = oops;
	}
	
}
