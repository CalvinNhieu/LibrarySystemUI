package core;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class AddStudentScreen extends InfoCentreSubScreens {
	
	private static final long serialVersionUID = 1L; // suggested...

	public AddStudentScreen (LYNXsys system) { // constructor
		super(system); // call super class
		
		getConfirm().setText("Add"); // set button text to...
		getTextHeaders().setIcon(new ImageIcon(getClass().getResource("/Resources/StudentEdit Options.png"))); // set image
		
		add(getPassword().getTextField()); // add appropriate textfields
		add(getPassword().getBox(),0);
		
	}
	
	public void paintComponent (Graphics g) { // paint method
		super.paintComponent(g);
	}
	
	public void checkFields (LYNXsys system) { // check textfields before adding student to system
		boolean existingStudentFlag = false; // temp var to flag if student info is valid
		if (!(getFirstName().getTextField().getText().equals("")) && // check that ALL FIELDS are filled (4)
					!(getLastName().getTextField().getText().equals("")) &&
					!(getID().getTextField().getText().equals("")) && 
					!(getPassword().getTextField().getText().equals(""))) { // end check
			for (int i = 0; i < system.getUsers().size(); i ++) { // loop through all users
				if (getID().getTextField().getText().equalsIgnoreCase(system.getUsers().get(i).getID())){ // check if user is already in system or not
					existingStudentFlag = true; // if so, set true ... addStudent method will not be called
				}
			}
		}
		if (!existingStudentFlag) { // if flag is never flipped true, this code will run
			system.addStudent(new User (getFirstName().getTextField().getText(), getLastName().getTextField().getText(), getID().getTextField().getText(),getPassword().getTextField().getText()));
		}
	}
}
