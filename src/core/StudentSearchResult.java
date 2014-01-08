package core;

import javax.swing.*;

public class StudentSearchResult {
	
	private User userReference; // holds user
	private JButton userButton; // button to navigate to user's screen
	private JTextArea displayText; // text to display user's info
	
	public StudentSearchResult(User userReference, String result) { // constructor
		// init vars
		this.userReference = userReference;
		displayText = new JTextArea(result);
		userButton = new JButton (new ImageIcon (getClass().getResource("/Resources/User Icon.png"))); 
		
		// add action listeners
		userButton.addActionListener(InScreens.getAction());
	}

	// GETTERS AND SETTERS
	public JTextArea getDisplayText() {
		return displayText;
	}

	public void setDisplayText(JTextArea displayText) {
		this.displayText = displayText;
	}

	public JButton getUserButton() {
		return userButton;
	}

	public void setUserButton(JButton userButton) {
		this.userButton = userButton;
	}
	
	public User getUserReference() {
		return userReference;
	}

	public void setBookReference(User userReference) {
		this.userReference = userReference;
	}
	
}
