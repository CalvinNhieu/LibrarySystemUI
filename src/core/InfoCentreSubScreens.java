package core;

import java.awt.Graphics;

import javax.swing.*;


public class InfoCentreSubScreens extends InScreens {

	private static final long serialVersionUID = 1L; // suggested...

	private static JButton back; // button to go back
	
	private JLabel textHeaders; // labels for textfields
	
	private JButton confirm; // button to continue
	
	private Textbox ID; // field to fill ID
	private Textbox firstName; // field to fill first name 
	private Textbox lastName; // field to fill last name
	private Textbox password; // field to fill password
	
	public InfoCentreSubScreens(LYNXsys system) { // constructor
		super(system); // call super class
		
		// init var
		textHeaders = new JLabel (new ImageIcon(getClass().getResource("/Resources/StudentEdit Options.png")));
		back = new JButton("<<");
		confirm = new JButton();
		
		// set bounds
		textHeaders.setBounds(142,197,224,335); // buttons
		back.setBounds(30,200,50,32);
		confirm.setBounds(765,500,150,32);
		
		firstName = new Textbox (345,275,349,32); // text fields
		lastName = new Textbox (345,325,349,32);
		ID = new Textbox (345,375,349,32);
		password = new Textbox(345,425,349,32);
		
		// add to panels
		add(textHeaders,0);
		add(back,0);
		add(confirm,0);
		
		add(firstName.getTextField());
		add(firstName.getBox(),0);
		add(lastName.getTextField());
		add(lastName.getBox(),0);
		add(ID.getTextField());
		add(ID.getBox(),0);
		
		// add action listeners
		back.addActionListener(getAction());
		confirm.addActionListener(getAction());
		
	}
	
	public void paintComponent(Graphics g) { // paint method
		super.paintComponent(g);
	}
	
	public void fillFields (User user) { // fill fields when entered from student view screen
		ID.getTextField().setText(user.getID());
		firstName.getTextField().setText(user.getFirstName());
		lastName.getTextField().setText(user.getLastName());
	}
	
	public void resetFields () { // empty all fields...
		ID.getTextField().setText("");
		firstName.getTextField().setText("");
		lastName.getTextField().setText("");
	}
	
	// GETTERS AND SETTERS
	public JLabel getTextHeaders() {
		return textHeaders;
	}


	public void setTextHeaders(JLabel textHeaders) {
		this.textHeaders = textHeaders;
	}
	
	public static JButton getBack() {
		return back;
	}


	public static void setBack(JButton back) {
		InfoCentreSubScreens.back = back;
	}


	public JButton getConfirm() {
		return confirm;
	}

	public void setConfirm(JButton confirm) {
		this.confirm = confirm;
	}


	public Textbox getID() {
		return ID;
	}


	public void setID(Textbox ID) {
		this.ID = ID;
	}


	public Textbox getFirstName() {
		return firstName;
	}


	public void setFirstName(Textbox firstName) {
		this.firstName = firstName;
	}


	public Textbox getLastName() {
		return lastName;
	}


	public void setLastName(Textbox lastName) {
		this.lastName = lastName;
	}


	public Textbox getPassword() {
		return password;
	}


	public void setPassword(Textbox password) {
		this.password = password;
	}

}
