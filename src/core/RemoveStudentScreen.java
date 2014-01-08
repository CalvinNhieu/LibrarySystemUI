package core;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class RemoveStudentScreen extends InfoCentreSubScreens {
	
	private static final long serialVersionUID = 1L;

	public RemoveStudentScreen (LYNXsys system) { // constructor
		super(system); // call super class
		
		// set bounds and stuff
		getTextHeaders().setIcon(new ImageIcon(getClass().getResource("/Resources/StudentEdit Options2.png")));
		getTextHeaders().setBounds(155,278,224,170);
		getConfirm().setText("Remove");
	}
	
	public void paintComponent (Graphics g) { // paint method
		super.paintComponent(g);
	}
	
	public void checkFields (LYNXsys system) { // check textfields method
		for (int i = 0; i < system.getUsers().size(); i++) { // loop through all users
			if (system.getUsers().get(i).getFirstName().equalsIgnoreCase(getFirstName().getTextField().getText()) && // if user exists (all fields match)
					system.getUsers().get(i).getLastName().equalsIgnoreCase(getLastName().getTextField().getText()) &&
					system.getUsers().get(i).getID().equalsIgnoreCase((getID().getTextField().getText()))) {
				system.removeStudent(system.getUsers().get(i)); // remove dis guy
			}
		}
	}
}
